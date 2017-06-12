package DAO.Mapper;

import DAO.Entity.MsgRecord;

import java.util.List;

/**
 * Created by Jaho on 2017/5/23.
 */
public interface MsgRecordMapper {
    public void insert(MsgRecord msgRecord);
    public List<MsgRecord> getMsgsOfUser(String host);
    public void delete(MsgRecord msgRecord);
}
