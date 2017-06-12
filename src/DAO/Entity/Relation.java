package DAO.Entity;

/**
 * Created by Jaho on 2017/5/23.
 */
public class Relation {
    private String host;
    private String friend;
    private String remark;


    public Relation(String host, String friend, String remark) {
        this.host = host;
        this.friend = friend;
        this.remark = remark;
    }

    public Relation(String host, String friend) {
        this.host = host;
        this.friend = friend;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
