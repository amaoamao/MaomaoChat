package DAO.Mapper;


import DAO.Entity.Channel;

/**
 * Created by Jaho on 2017/5/23.
 */
public interface ChannelMapper {

    public void insert(Channel channel);
    public void delete(Channel channel);
    public void update(Channel channel);
    public Channel getChannel(int id);
}
