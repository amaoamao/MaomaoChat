package DTO;

import DAO.Entity.User;

/**
 * Created by Jaho on 2017/5/9.
 * 这个类作为注册时前端传输的用户数据实体
 */
public class SignUpInfo {

    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
