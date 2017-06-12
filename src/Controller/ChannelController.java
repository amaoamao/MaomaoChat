package Controller;

import DAO.Entity.*;
import DAO.Mapper.*;
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
 * 频道管理
 */

@CrossOrigin
@RestController
@RequestMapping("/set/channel")
public class ChannelController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private ChannelMemMapper channelMemMapper;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public SetResult create(@RequestBody Channel channel,HttpServletRequest request){


        String phone = (String) request.getAttribute("phone");
        // 查询创建人是否有效
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        // 分配频道ID
        int id = getChnId();

        channel.setId(id);
        channel.setFounder(phone);

        // 存储频道
        channelMapper.insert(channel);

        // 返回ID
        SetResult success = new SetResult(new Error(0,"创建成功"),id);

        return success;

    }

    @RequestMapping(value = "/delete",method = RequestMethod.GET)
    public SetResult delete(@RequestParam("channel") int channelId,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        Channel channel = channelMapper.getChannel(channelId);
        if(channel == null)
            return SetResult.CHN_NOT_FOUND_ERROR;

        if(phone != channel.getFounder())
            return SetResult.DELETE_ERROR;
        else
            return SetResult.DELETE_SUCCESS;

    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public SetResult update(@RequestBody Channel temp,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        Channel channel = channelMapper.getChannel(temp.getId());

        if(channel == null)
            return SetResult.CHN_NOT_FOUND_ERROR;

        if(!channel.getFounder().equals(phone))
            return SetResult.UPDATE_ERROR;

        channel.setName(temp.getName());
        channel.setIntro(temp.getIntro());

        channelMapper.update(channel);

        return SetResult.UPDATE_SUCCESS;

    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response getChns(HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        List<Integer> chnIds =  channelMemMapper.getChns(phone);
        List<Channel>chns = new ArrayList<Channel>();

        for(Integer id:chnIds)
            chns.add(channelMapper.getChannel(id));
        Response<List<Channel>> response = new Response<List<Channel>>();

        response.setError(new Error(0,"获取频道列表成功"));
        response.setData(chns);
        return response;

    }

    @RequestMapping(value = "/mem/create",method = RequestMethod.GET)
    public SetResult createMem(@RequestParam("channel") int channelId,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        ChannelMem channelMem = new ChannelMem(channelId,user.getId(),phone);
        channelMemMapper.insert(channelMem);

        return SetResult.CREATE_SUCCESS;

    }


    @RequestMapping(value = "/mem/delete",method = RequestMethod.GET)
    public SetResult deleteMem(@RequestParam("channel") int channelId,HttpServletRequest request){

        String phone = (String) request.getAttribute("phone");
        User user = userMapper.getUserByPhone(phone);
        if(user == null)
            return SetResult.USR_NOT_FOUND_ERROR;

        ChannelMem channelMem = new ChannelMem(channelId,user.getId(),phone);
        channelMemMapper.delete(channelMem);


        return SetResult.DELETE_SUCCESS;

    }

    private int getChnId(){

        Random random = new Random();

        int id = random.nextInt();

        if(id < 0)
            id = 0 - id;

        return id;
    }

}