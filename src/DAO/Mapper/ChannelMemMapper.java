package DAO.Mapper;

import DAO.Entity.ChannelMem;
import DAO.Entity.Mem;

import java.util.List;

/**
 * Created by Jaho on 2017/5/23.
 */
public interface ChannelMemMapper {
    public void insert(ChannelMem channelMem);
    public void delete(ChannelMem channelMem);
    public List<Mem> getMemsOfChn(int chn_id);
    public List<Integer> getChns(String usr_phone);
}
