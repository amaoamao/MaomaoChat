package DTO;

import DAO.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by Jaho on 2017/5/9.
 * 生成Token以及作为登录注册返回结果的Response:data
 */

@CrossOrigin
@RestController
public class Token {

    private String phone;
    private String token;

    public static String getATokenStr(){
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        String token;

        for (int i = 0; i < 16; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }

            token = sb.toString();

        return token;
    }

    public Token(){
        this.phone = "";
        this.token = "";
    }

    public Token(String phone){
        this.phone = phone;
        this.token = this.getATokenStr();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTokenStr() {
        return token;
    }

    public void setTokenStr(String token) {
        this.token = token;
    }

}
