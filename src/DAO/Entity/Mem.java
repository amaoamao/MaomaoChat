package DAO.Entity;

/**
 * Created by Jaho on 2017/5/26.
 */
public class Mem {

    private int usr_id;
    private String usr_phone;

    public Mem(int usr_id,String usr_phone) {
        this.usr_id = usr_id;
        this.usr_phone = usr_phone;
    }

    public int getUsrId() {
        return usr_id;
    }

    public void setUsrId(int usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsrPhone() {
        return usr_phone;
    }

    public void setUsrPhone(String usr_phone) {
        this.usr_phone = usr_phone;
    }

}
