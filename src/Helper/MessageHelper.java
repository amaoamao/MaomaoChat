package Helper;

import DAO.Entity.Mem;
import DAO.Entity.MsgRecord;
import DAO.Mapper.GroupMemMapper;
import DAO.Mapper.ChannelMemMapper;

import DTO.Message.Message;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Jaho on 2017/5/26.
 * 对消息进行处理
 */
public class MessageHelper {

    private static GroupMemMapper gMapper;
    private static ChannelMemMapper cMapper;

    static {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("Configuration/spring-dao.xml");
        gMapper = (GroupMemMapper) ctx.getBean("gMapper");
        cMapper = (ChannelMemMapper) ctx.getBean("cMapper");
    }



    public List<MsgRecord> msgToRecords(Message message){



        List<MsgRecord> msgRecords = new ArrayList<MsgRecord>();

        switch (message.getMessage().getType()){
            // 发送给个人
            case 0:
                msgRecords.add(msgToRecord(message));
                break;

            // 发送给群组
            case 1:

                int grpId = Integer.parseInt(message.getReceiver().getId());
                List<Mem> groupMems = gMapper.getMemsOfGrp(grpId);
                addRecordsToList(message,msgRecords,groupMems);
                break;
            // 发送到频道
            case 2:

                int chnId = Integer.parseInt(message.getReceiver().getId());
                List<Mem> channelMems = cMapper.getMemsOfChn(chnId);
                addRecordsToList(message,msgRecords,channelMems);
                break;

            default:
                break;
        }

        return msgRecords;
    }

    private MsgRecord msgToRecord(Message message){

        MsgRecord msgRecord = new MsgRecord();
        // Host: 接收方的手机号
        msgRecord.setHost(message.getReceiver().getId());
        // 发送方的ID
        msgRecord.setSender(message.getSender());
        // 设置接收方的ID、类型
        msgRecord.setReceiver(message.getReceiver().getId());
        msgRecord.setReceiverType(message.getReceiver().getType());

        // 默认消息未发送
        msgRecord.setHasSent(false);
        // 添加时间戳
        msgRecord.setDateTime(new Timestamp(System.currentTimeMillis()));

        // 设置消息的内容和类型
        msgRecord.setContent(message.getMessage().getContent());
        msgRecord.setContentType(message.getMessage().getType());

        message.getMessage().setDateTime(msgRecord.getDateTime());
        return msgRecord;
    }

    public Message recordToMessage(MsgRecord record){

        Message message = new Message();

        // 设置发送方
        message.setSender(record.getSender());

        // 设置消息体的内容、时间、类型
        message.getMessage().setContent(record.getContent());
        message.getMessage().setType(record.getContentType());
        message.getMessage().setDateTime(record.getDateTime());

        // 设置接收方的ID、类型
        message.getReceiver().setId(record.getReceiver());
        message.getReceiver().setType(record.getReceiverType());

        return message;
    }

    private void addRecordsToList(Message message,List<MsgRecord> msgRecords,List<Mem> mems){

        for(Mem mem:mems){
            MsgRecord msgRecord = msgToRecord(message);
            msgRecord.setHost(mem.getUsrPhone());
            msgRecords.add(msgRecord);
        }
    }




}

