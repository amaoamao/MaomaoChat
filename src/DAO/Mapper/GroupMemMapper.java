package DAO.Mapper;

import DAO.Entity.GroupMem;
import DAO.Entity.Mem;

import java.util.List;

/**
 * Created by Jaho on 2017/5/23.
 */
public interface GroupMemMapper {
    public void insert(GroupMem groupMem);
    public void delete(GroupMem groupMem);
    public List<Mem> getMemsOfGrp(int grp_id);
    public List<Integer> getGrps(String usr_phone);

}
