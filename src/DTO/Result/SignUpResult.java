package DTO.Result;

import DTO.Response.Error;
import DTO.Response.Response;
import DTO.Token;

/**
 * Created by Jaho on 2017/5/9.
 * 这个类作为注册的错误返回结果
 */
public class SignUpResult extends Response<Token> {

    public static final SignUpResult REGISTERED_SUCCESS = new SignUpResult(new Error(0,"注册成功"));
    public static final SignUpResult CODE_SEND_SUCCESS = new SignUpResult(new Error(0,"验证码已发送，请注意查收"));
    public static final SignUpResult RESET_PASSWORD_SUCCESS  = new SignUpResult(new Error(0,"密码已重置，请重新登录"));

    public static final SignUpResult CODE_VERIFY_ERROR = new SignUpResult(new Error(1,"验证码验证失败"));
    public static final SignUpResult PHONE_FORMAT_ERROR = new SignUpResult(new Error(1,"手机号码不正确"));
    public static final SignUpResult CODE_SEND_ERROR = new SignUpResult(new Error(1,"验证码发送失败，请稍后再试"));
    public static final SignUpResult REGISTERED_REPEAT_ERROR = new SignUpResult(new Error(1,"注册失败，该手机号码已经注册，请直接登录"));
    public static final SignUpResult REGISTERED_VERIFY_ERROR = new SignUpResult(new Error(1,"注册失败，密钥验证信息不符，请重新注册"));
    public static final SignUpResult REGISTERED_CODE_ERROR = new SignUpResult(new Error(1,"注册失败，请确定已通过短信验证"));


    public SignUpResult(Error error){
        super(error);
    }
    public SignUpResult(Error error, Token token){
        super(error,token);
    }

    public Token getToken(){
        return super.getData();
    }

    public void setToken(Token token){
        super.setData(token);
    }

}



