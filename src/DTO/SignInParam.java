package DTO;

/**
 * Created by Jaho on 2017/5/24.
 * 登录的参数实体
 */
public class SignInParam {
    private String phone;
    private String password;

    public SignInParam(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
