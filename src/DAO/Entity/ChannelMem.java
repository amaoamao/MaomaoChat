package DAO.Entity;

/**
 * Created by Jaho on 2017/5/23.
 */
public class ChannelMem extends Mem{

    private int chn_id;

    public ChannelMem(int chn_id, int usr_id,String usr_phone) {
        super(usr_id,usr_phone);
        this.chn_id = chn_id;

    }

    public int getChnId() {
        return chn_id;
    }

    public void setChnId(int chn_id) {
        this.chn_id = chn_id;
    }

}
