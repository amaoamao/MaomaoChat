package Controller;

import DTO.*;
import DTO.Response.Error;

import DAO.Entity.User;
import DAO.Mapper.UserMapper;

import DTO.Result.SignUpResult;
import Helper.*;
import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Jaho on 2017/5/9.
 * 注册
 */

@CrossOrigin
@RestController
@RequestMapping("/signUp")

public class SignUpController {


    @Autowired
    private UserMapper userMapper;

    // 网易云信验证码，停用
//    private AuthHelper authHelper;

    /*
     * @param phone:需要进行注册的手机号
     * @param code: 需要进行验证的验证码
     * 若验证码为空，说明需要发送验证码
     * 若验证码不为空，则进行验证，验证通过则分配Token
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public SignUpResult authPhone(@RequestParam("phone") String phone, @RequestParam(value = "code", required = false) String code) throws Exception{

        // 手机号码格式错误
        if(phoneIsInvalid(phone))
            return SignUpResult.PHONE_FORMAT_ERROR;

        // 重复注册错误
        if(userMapper.getUserByPhone(phone) != null)
            return SignUpResult.REGISTERED_REPEAT_ERROR;

        if(code == null){
            return SignUpResult.CODE_SEND_SUCCESS;

            //  网易云信短信业务用不起，暂时停用
//            if(authHelper.sendAuthCode(phone) == 0)
//                return SignUpResult.CODE_SEND_SUCCESS;
//            else
//                return SignUpResult.CODE_SEND_ERROR
//
        }else {
            //  网易云信短信业务用不起，暂时停用
//            if(authHelper.verifyAuthCode(phone,code) == 0)
            if(code.equals("1234")){


                //  分配Token
                SignUpResult signUpResult = new SignUpResult(new Error(0,"验证码验证成功"),new Token(phone));
                // 将用户手机号与Token绑定，保存到数据库
                User user = new User(phone, signUpResult.getToken().getTokenStr());
                userMapper.insert(user);
                return signUpResult;

            }else
                return SignUpResult.CODE_VERIFY_ERROR;
        }
    }

    /*
     * @param SignUpInfo: 注册接口，传输用户个人数据，根据Token进行验证
     */
    @RequestMapping(method = RequestMethod.POST)
    public SignUpResult signUp(@RequestBody SignUpInfo signUpInfo){

        User user = userMapper.getUserByPhone(signUpInfo.getUser().getPhone());

        // 如果根据手机号查询不到此用户，说明没有通过验证码验证
        if(user == null)
            return SignUpResult.REGISTERED_CODE_ERROR;

        // 如果前端、后端Token一致，则根据传输的用户数据更新数据库中对应的用户
        if(user.getToken().equals(signUpInfo.getToken())){

            // 根据注册信息完善数据库中保存的用户数据
            User temp = signUpInfo.getUser();

            user.setName(temp.getName());
            user.setMale(temp.isMale());
            user.setAvatar(temp.getAvatar());
            // 将密码MD5加密并保存
            user.setPassword(UserInfoHelper.encryptionPassword(temp.getPassword()));

            userMapper.update(user);

            return SignUpResult.REGISTERED_SUCCESS;

        }else {
            // 若前端、后端Token不一致，提示用户重新进行注册，并删除数据库此前保存的用户信息
            userMapper.deleteUserByPhone(signUpInfo.getUser().getPhone());

            return SignUpResult.REGISTERED_VERIFY_ERROR;
        }

    }

    private boolean phoneIsInvalid(String phone){

        Pattern phonePattern = Pattern.compile("[0-9]{11}");

        return !phonePattern.matcher(phone).matches();
    }


}
