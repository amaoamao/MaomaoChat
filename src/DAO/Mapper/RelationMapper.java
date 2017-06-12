package DAO.Mapper;

import DAO.Entity.Relation;

import java.util.List;

/**
 * Created by Jaho on 2017/5/23.
 */
public interface RelationMapper {

    public void insert(Relation relation);
    public void delete(Relation relation);
    public List<Relation> getFriendsOfUser(String host);
}
