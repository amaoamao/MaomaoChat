package Controller;

import DAO.Entity.User;
import DAO.Mapper.UserMapper;
import DTO.Response.Error;
import DTO.Response.Response;
import DTO.Result.SetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jaho on 2017/6/2.
 */

@CrossOrigin
@RestController
@RequestMapping("/set/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public SetResult update(@RequestBody User newInfo){

        // 查询手机号是否有效
        User user = userMapper.getUserByPhone(newInfo.getPhone());
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        user.setName(newInfo.getName());
        user.setMale(newInfo.isMale());
        user.setAvatar(newInfo.getAvatar());

        userMapper.update(user);

        return SetResult.UPDATE_SUCCESS;
    }

    // 根据手机号获取用户公开基本信息
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public Response update(@RequestParam("user") String phone){

        // 查询手机号是否有效
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        user.setId(0);
        user.setToken(null);
        user.setPassword(null);
        user.setExpired_date(null);

        Response<User> response = new Response<User>();
        response.setError(new Error(0,"获取成功"));
        response.setData(user);


        return response;
    }



}
