package DTO.Result;

import DTO.Response.Error;
import DTO.Response.Response;

/**
 * Created by Jaho on 2017/6/2.
 */
public class FileResult extends Response<String> {

    public static final FileResult FORM_TYPE_ERROR = new FileResult(new Error(1,"请使用文件表单提交"));
    public static final FileResult FILE_UP_ERROR = new FileResult(new Error(1,"文件上传出错，请稍后再试"));

    public static final FileResult NOT_FOUND_ERROR = new FileResult(new Error(1,"W无法找到该文件，请确定文件URL无误"));
    public static final FileResult FILE_DOWN_ERROR = new FileResult(new Error(1,"文件下载出错，请稍后再试"));
    public static final FileResult FILE_DOWN_SUCCESS = new FileResult(new Error(0,"文件下载成功"));




    public FileResult(Error error){
        super(error);
    }
    public FileResult(Error error, String url){
        super(error,url);
    }

    public String getUrl(){
        return super.getData();
    }

    public void setUrl(String url){
        super.setData(url);
    }

}
