package DTO.Result;

import DTO.Response.Error;
import DTO.Response.Response;

/**
 * Created by Jaho on 2017/6/2.
 */
public class SetResult extends Response<Integer> {

    public static final SetResult CREATE_SUCCESS = new SetResult(new Error(0,"添加成功"));
    public static final SetResult DELETE_SUCCESS = new SetResult(new Error(0,"删除成功"));
    public static final SetResult UPDATE_SUCCESS = new SetResult(new Error(0,"更新成功"));

    public static final SetResult DELETE_ERROR = new SetResult(new Error(1,"删除失败，该用户无权限"));
    public static final SetResult UPDATE_ERROR = new SetResult(new Error(1,"更新失败，该用户无权限"));

    public static final SetResult USR_NOT_FOUND_ERROR = new SetResult(new Error(1,"根据手机号查询不到此用户信息"));
    public static final SetResult GRP_NOT_FOUND_ERROR = new SetResult(new Error(1,"根据群组号查询不到此群组信息"));
    public static final SetResult CHN_NOT_FOUND_ERROR = new SetResult(new Error(1,"根据频道号查询不到此频道信息"));




    public SetResult(Error error){
        super(error);
    }

    public SetResult(Error error, int id){
        super(error,id);
    }

    public int getId(){
        return super.getData();
    }

    public void setId(int id){
        super.setData(id);
    }

}
