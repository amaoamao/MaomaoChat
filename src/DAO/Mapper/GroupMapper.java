package DAO.Mapper;

import DAO.Entity.Group;

/**
 * Created by Jaho on 2017/5/23.
 */
public interface GroupMapper {
    public void insert(Group group);
    public void delete(Group group);
    public void update(Group group);
    public Group getGroup(int id);
}
