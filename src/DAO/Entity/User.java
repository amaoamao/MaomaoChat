package DAO.Entity;

import Helper.UserInfoHelper;

import java.sql.Date;

/**
 * Created by Jaho on 2017/5/8.
 */

public class User {

    private int id;
    private String password;
    private String phone;
    private String name;
    private String avatar;
    private boolean isMale;
    private String token;
    private Date expired_date;

    public User(){
        this.password = "";
        this.phone = "";
        this.name = "";
        this.avatar = "";
        this.token = "";
        this.expired_date = UserInfoHelper.getExpiredDate();
        this.setMale(true);
    }

    public User(String phone,String token){
        this.password = "";
        this.phone = phone;
        this.name = "";
        this.avatar = "";
        this.token = token;
        this.expired_date = UserInfoHelper.getExpiredDate();
        this.setMale(true);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpired_date() {
        return expired_date;
    }

    public void setExpired_date(Date expired_date) {
        this.expired_date = expired_date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
