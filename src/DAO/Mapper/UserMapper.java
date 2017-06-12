package DAO.Mapper;

import DAO.Entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Jaho on 2017/5/8.
 */

@Repository
public interface UserMapper {

    public void insert(User user);

    public void update(User user);

    public User getUserById(int user_id);

    public void updateVerifyInfo(User user);

    public User getUserByPhone(String phone);

    public User getUserByToken(String token);

    public void deleteUserByPhone(String phone);

}
