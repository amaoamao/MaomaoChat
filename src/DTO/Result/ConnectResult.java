package DTO.Result;

import DTO.Response.Error;
import DTO.Response.Response;
import DTO.Token;

/**
 * Created by Jaho on 2017/5/26.
 * 这个类作为WebSocket以及Header拦截的错误返回结果
 */


public class ConnectResult extends Response<Token> {

    public static final ConnectResult EXPIRED_DATE_ERROR = new ConnectResult(new Error(1,"登录失效，请重新登录"));
    public static final ConnectResult NULL_TOKEN_ERROR = new ConnectResult(new Error(1,"缺少验证信息，请检查或重新登录"));
    public static final ConnectResult INVALID_TOKEN_ERROR = new ConnectResult(new Error(1,"验证信息错误，请检查或重新登录"));
    public static final ConnectResult MESSAGE_FORMAT_ERROR = new ConnectResult(new Error(1,"消息格式错误"));
    public ConnectResult(Error error){
        super(error);
    }

    public Token getToken(){
        return super.getData();
    }

    public void setToken(Token token){
        super.setData(token);
    }
}
