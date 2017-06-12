package Controller;


import DTO.Response.Error;
import DTO.Result.FileResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.*;
import java.util.Iterator;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jaho on 2017/6/1.
 * 文件上传与下载
 */

@CrossOrigin
@RestController
@RequestMapping("/set/file")
public class FileController {

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public FileResult upload(HttpServletRequest request, HttpServletResponse response){

        // 获取拦截器添加的用户信息
        String phone = (String) request.getAttribute("phone");
        // 获取上下文信息
        ServletContext context = request.getSession().getServletContext();
        // 获取文件解析器
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(context);
        // 用户目录
        String filePath = context.getRealPath("/WEB-INF/UserFile/" + phone);
        // 完整文件路径
        String fullFilePath = new String();
        File dir = new File(filePath);
        if(!dir.exists())
            dir.mkdirs();
        // 判断是否是文件
        if(resolver.isMultipart(request)){
            // 文件转换
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取文件名称
            Iterator<String> iterator = multiRequest.getFileNames();

            // 处理文件
            while (iterator.hasNext()){
                // 处理文件
                MultipartFile file = multiRequest.getFile(iterator.next());
                String fileName = file.getOriginalFilename();
                fullFilePath = filePath + fileName;
                File newFile = new File(fullFilePath);
                try {
                    file.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                    // 返回文件存储错误
                    return FileResult.FILE_UP_ERROR;
                }
            }
        }else
            return FileResult.FORM_TYPE_ERROR;
        // 返回文件错误

        FileResult success = new FileResult(new Error(0,"文件上传成功"),fullFilePath);
        return success;
    }

    @RequestMapping(value = "/download",method = RequestMethod.POST)
    public FileResult download(HttpServletResponse response,@RequestParam(value = "fileUrl") String fileUrl) {

        // Set Request and Response
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/JavaScript");
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileUrl);

        try {

            FileInputStream in = new FileInputStream(fileUrl);
            OutputStream outputStream = response.getOutputStream();
            byte buffer[] = new byte[1024];

            int len;
            while ((len = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, len);
                in.close();
                outputStream.close();
            }
        }catch(FileNotFoundException e){
                e.printStackTrace();
                return FileResult.NOT_FOUND_ERROR;
        } catch(IOException e){
                e.printStackTrace();
                return FileResult.FILE_DOWN_ERROR;
        }

        return FileResult.FILE_DOWN_SUCCESS;

    }

}
