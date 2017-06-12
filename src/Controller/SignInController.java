package Controller;

import DTO.Response.Error;
import DTO.SignInParam;
import DTO.Result.SignInResult;

import DAO.Entity.User;
import DAO.Mapper.UserMapper;
import DTO.Token;
import Helper.UserInfoHelper;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jaho on 2017/5/10.
 * 登录
 */

@CrossOrigin
@RestController
@RequestMapping("/signIn")
public class SignInController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST)
    public SignInResult signIn(@RequestBody SignInParam signInParam){

        if(signInParam.getPhone() == null || signInParam.getPassword() == null)
            return SignInResult.SIGN_IN_ERROR;
        User user = userMapper.getUserByPhone(signInParam.getPhone());

        // 未注册直接登录，则数据库中无此用户
        if(user == null)
            return SignInResult.NOT_REGISTERED_ERROR;


        // 登录成功
        if(user.getPassword().equals(UserInfoHelper.encryptionPassword(signInParam.getPassword()))){

            // 分配新的Token
            SignInResult signInResult = new SignInResult(new Error(0,"登录成功"),new Token(signInParam.getPhone()));

            // 更新数据库中用户的Token以及失效日期
            user.setExpired_date(UserInfoHelper.getExpiredDate());
            user.setToken(signInResult.getToken().getTokenStr());
            userMapper.updateVerifyInfo(user);

            return signInResult;

        }else
            return SignInResult.SIGN_IN_ERROR;

    }
}

