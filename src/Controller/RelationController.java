package Controller;

import DAO.Entity.Relation;
import DAO.Entity.User;
import DAO.Mapper.RelationMapper;
import DAO.Mapper.UserMapper;
import DTO.Response.Error;
import DTO.Response.Response;
import DTO.Result.SetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaho on 2017/6/2.
 */

@CrossOrigin
@RestController
@RequestMapping("/set/relation")
public class RelationController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RelationMapper relationMapper;

    // 新添好友关系
    @RequestMapping(value = "/create",method = RequestMethod.GET)
    public SetResult create(HttpServletRequest request, @RequestParam("friend") String frndPhone, @RequestParam(value = "remark",required = false) String remark){

        String hostPhone = (String) request.getAttribute("phone");
        // 查询关系双方手机号是否有效
        User host = userMapper.getUserByPhone(hostPhone);
        if(host == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        User friend = userMapper.getUserByPhone(frndPhone);

        if(friend == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        Relation relation = new Relation(hostPhone,frndPhone);
        // 每次添加前先删除该关系:
        // 1.避免重复
        // 2.相当于更新关系
        relationMapper.delete(relation);
        //备注名称
        relation.setRemark(remark);
        relationMapper.insert(relation);

        return SetResult.CREATE_SUCCESS;

    }

    // 删除好友关系
    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public SetResult delete(HttpServletRequest request, @RequestParam("friend") String frndPhone){

        String hostPhone = (String) request.getAttribute("phone");
        // 查询关系双方手机号是否有效
        User host = userMapper.getUserByPhone(hostPhone);
        if(host == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        User friend = userMapper.getUserByPhone(frndPhone);
        if(friend == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        Relation relation = new Relation(hostPhone,frndPhone);
        relationMapper.delete(relation);

        return SetResult.DELETE_SUCCESS;

    }

    // 获取好友基本信息
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response getRelationInfo(HttpServletRequest request){

        String hostPhone = (String) request.getAttribute("phone");
        // 查询手机号是否有效
        User host = userMapper.getUserByPhone(hostPhone);
        if(host == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        List<Relation> relations = relationMapper.getFriendsOfUser(hostPhone);

        List<User> users = new ArrayList<User>();

        for(Relation relation:relations){
            User user = userMapper.getUserByPhone(relation.getFriend());

            if(user == null)
                continue;

            //修改为备注名
            user.setName(relation.getRemark());
            user.setId(0);
            user.setToken(null);
            user.setPassword(null);
            user.setExpired_date(null);

            users.add(user);
        }

        Response<List<User>> response = new Response<List<User>>();
        response.setError(new Error(0,"获取成功"));
        response.setData(users);

        return response;

    }


}

