package DAO.Entity;

/**
 * Created by Jaho on 2017/5/23.
 */
public class GroupMem extends Mem{

    private int grp_id;

    public GroupMem(int grp_id, int usr_id,String usr_phone) {
        super(usr_id,usr_phone);
        this.grp_id = grp_id;

    }

    public int getGrpId() {
        return grp_id;
    }

    public void setGrpId(int grp_id) {
        this.grp_id = grp_id;
    }

}
