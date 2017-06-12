package Controller;

import DAO.Entity.Group;
import DAO.Entity.GroupMem;
import DAO.Entity.User;
import DAO.Mapper.GroupMapper;
import DAO.Mapper.GroupMemMapper;
import DAO.Mapper.UserMapper;
import DTO.Response.Error;
import DTO.Response.Response;
import DTO.Result.SetResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jaho on 2017/6/1.
 * 对群组进行操作
 */

@CrossOrigin
@RestController
@RequestMapping("/set/group")
public class GroupController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private GroupMemMapper groupMemMapper;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public SetResult create(@RequestBody Group group,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        // 查询创建人是否有效
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        // 分配群组ID
        int id = getId();

        group.setId(id);

        // 存储群组
        group.setFounder(phone);
        groupMapper.insert(group);

        // 返回ID
        SetResult success = new SetResult(new Error(0,"创建成功"),id);

        return success;

    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public SetResult delete(@RequestParam("group") int groupId,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        Group group = groupMapper.getGroup(groupId);
        if(group == null)
            return SetResult.GRP_NOT_FOUND_ERROR;

        if(!phone.equals(group.getFounder()))
            return SetResult.DELETE_ERROR;
        else
            return SetResult.DELETE_SUCCESS;

    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public SetResult update(@RequestBody Group temp,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        Group group = groupMapper.getGroup(temp.getId());

        if(group == null)
            return SetResult.GRP_NOT_FOUND_ERROR;

        if(!group.getFounder().equals(phone))
            return SetResult.UPDATE_ERROR;

        group.setName(temp.getName());
        group.setIntro(temp.getIntro());

        groupMapper.update(group);

        return SetResult.UPDATE_SUCCESS;

    }


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response getGrps(HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        List<Integer> grpIds =  groupMemMapper.getGrps(phone);
        Response<List<Group>> response = new Response<List<Group>>();
        List<Group>grps = new ArrayList<Group>();

        for(Integer id:grpIds)
            grps.add(groupMapper.getGroup(id));

        response.setError(new Error(0,"获取群组列表成功"));
        response.setData(grps);
        return response;

    }


    @RequestMapping(value = "/mem/create",method = RequestMethod.GET)
    public SetResult createMem(@RequestParam("group") int groupId,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        GroupMem groupMem = new GroupMem(groupId,user.getId(),phone);
        groupMemMapper.insert(groupMem);


        return SetResult.CREATE_SUCCESS;

    }


    @RequestMapping(value = "/mem/delete",method = RequestMethod.GET)
    public SetResult deleteMem(@RequestParam("group") int groupId,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        GroupMem groupMem = new GroupMem(groupId,user.getId(),phone);
        groupMemMapper.delete(groupMem);


        return SetResult.DELETE_SUCCESS;

    }

    private int getId(){

        Random random = new Random();

        int id = random.nextInt();

        if(id < 0)
            id = 0 - id;

        return id;
    }

}
