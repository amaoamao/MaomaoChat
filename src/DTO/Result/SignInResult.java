package DTO.Result;

import DTO.Response.Error;
import DTO.Response.Response;
import DTO.Token;

/**
 * Created by Jaho on 2017/5/17.
 * 这个类作为登陆的错误返回结果
 */
public class SignInResult extends Response<Token> {

    public static final SignInResult NOT_REGISTERED_ERROR = new SignInResult(new Error(1,"登录失败，账号未注册"));
    public static final SignInResult SIGN_IN_ERROR = new SignInResult(new Error(1,"登录失败，密码不符"));


    public SignInResult(Error error){
        super(error);
    }
    public SignInResult(Error error, Token token){
        super(error,token);
    }

    public Token getToken(){
        return super.getData();
    }

    public void setToken(Token token){
        super.setData(token);
    }


}
