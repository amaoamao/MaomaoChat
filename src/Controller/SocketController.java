package Controller;

/**
 * Created by Jaho on 2017/5/14.
 */

import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.ConcurrentHashMap;

import DAO.Entity.MsgRecord;
import DAO.Entity.User;
import DAO.Mapper.MsgRecordMapper;
import DAO.Mapper.UserMapper;

import DTO.Result.ConnectResult;
import DTO.Message.Message;

import Helper.MessageHelper;
import Helper.UserInfoHelper;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@ServerEndpoint(value = "/chat/{token}")
public class SocketController {

    private Gson gson;
    private User user;
    private String phone;
    private Session session;
    private MessageHelper msgHelper;
    // 存储，手机号为键，SocketController为值
    private static Map<String, SocketController> WebSocketMap = new ConcurrentHashMap<String, SocketController>();

    private static MsgRecordMapper msgRecordMapper;
    private static UserMapper userMapper;

    static {
        // 检查该用户是否有未接收的消息
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Configuration/spring-dao.xml");
        msgRecordMapper = (MsgRecordMapper) ctx.getBean("msgRecordMapper");
        userMapper = (UserMapper) ctx.getBean("userMapper");
    }

    @OnOpen
    public void onOpen(@PathParam("token") String token, Session session) throws IOException {

        if(tokenIsInvalid(token))
            session.close();

        // 有效则验证通过，进行初始化设置
        this.gson = new Gson();
        this.session = session;
        this.phone = user.getPhone();
        WebSocketMap.put(phone,this);
        this.msgHelper = new MessageHelper();


        List<MsgRecord> msgRecords = msgRecordMapper.getMsgsOfUser(phone);

        // 如果数据库中有新上线用户的未接收消息，则逐一处理
        if(msgRecords.size() > 0){
            for (MsgRecord msgRecord:msgRecords){
                msgRecordMapper.delete(msgRecord);
                Message message = msgHelper.recordToMessage(msgRecord);
                this.processMessage(message);
            }
        }

    }

    @OnClose
    public void onClose(){

        // 从Map中移除此Socket对象
        if(user != null)
            WebSocketMap.remove(phone);
    }

    @OnMessage
    public void onMessage(String messageStr, Session session) {

        // 将JSON字符串转换为消息实体类
        Message message = gson.fromJson(messageStr, Message.class);

        if(message == null)
            this.sendMessage(gson.toJson(ConnectResult.MESSAGE_FORMAT_ERROR));
        else
            this.processMessage(message);
    }

    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误-Jaho:" + error);
    }


    public void sendMessage(String message){

        try {
            this.session.getBasicRemote().sendText(gson.toJson(message));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*
     *  @param messageStr: 前端后端进行交互的消息实体类的JSON字符串
     */
    public void processMessage(Message message){

        // 通过processMessage函数处理消息
        // 将消息实体转化为数据持久化对象
        // 也提取出消息对应得所有的实际接收用户
        List<MsgRecord> msgRecords = msgHelper.msgToRecords(message);

        for(MsgRecord msgRecord:msgRecords){
            // 从Map中根据手机号获取SocketController
            // 获取到，表示该用户在线
            SocketController sc = WebSocketMap.get(msgRecord.getHost());

            // 如果在线，则直接发送消息
            if(sc != null){
                msgRecord.setHasSent(true);
                sc.sendMessage(gson.toJson(message));
            }
            // 将消息保存至数据库
            msgRecordMapper.insert(msgRecord);

        }
    }

    private boolean tokenIsInvalid(String token) {


        // WebSocket连接缺少Token
        if(token == null){
            this.sendMessage(gson.toJson(ConnectResult.NULL_TOKEN_ERROR));
            return true;
        }

        // 根据Token查询对应用户
        this.user = userMapper.getUserByToken(token);

        // 用户不存在则返回错误信息
        if(user == null){
            this.sendMessage(gson.toJson(ConnectResult.INVALID_TOKEN_ERROR));
            return true;
        }

        //用户存在则检查登录有效期
        // 失效则返回错误信息
        if(UserInfoHelper.afterExpiredDate(user.getExpired_date())){
            this.sendMessage(gson.toJson(ConnectResult.EXPIRED_DATE_ERROR));
            return true;
        }

        return false;
    }

}
