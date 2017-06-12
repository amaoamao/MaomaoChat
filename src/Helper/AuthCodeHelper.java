package Helper;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jaho on 2017/5/9.
 */
public class AuthCodeHelper implements AuthHelper{

    public int sendAuthCode(String phone) throws Exception {

        JSONObject authJson = getAuthJson(phone,null);
        return jsonCodeStatus(authJson);
    }


    public int verifyAuthCode(String phone,String code) throws Exception {

        JSONObject authJson = getAuthJson(phone,code);
        return jsonCodeStatus(authJson);
    }

    private JSONObject getAuthJson(String phone, String code) throws Exception {


        String SEND_CODE_URL = "https://api.netease.im/sms/sendcode.action";
        String VERIFY_CODE_URL = "https://api.netease.im/sms/verifycode.action";
        // 根据验证码是否为空判断所需要请求的URL
        String REAL_URL = (code == null)? SEND_CODE_URL:VERIFY_CODE_URL;

        String APP_KEY = "a86d5b52383780c7d42db1bdb4b6ba46";//账号
        String APP_SECRET = "59060fa5fd4f";//密码
        String TEMPLATE_ID = "3054422";//Code Template Id
        String NONCE = "123456";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(REAL_URL);

        String curTime=String.valueOf((new Date().getTime()/1000L));
        String checkSum=CheckSumHelper.getCheckSum(APP_SECRET,NONCE,curTime);

        // Set Http Request Header
        httpPost.addHeader("AppKey",APP_KEY);
        httpPost.addHeader("CurTime",curTime);
        httpPost.addHeader("CheckSum",checkSum);
        httpPost.addHeader("Nonce",NONCE);
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        // Set Http Request Params
        List<NameValuePair> nameValuePairs =new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("templateid",TEMPLATE_ID));
        nameValuePairs.add(new BasicNameValuePair("mobile",phone));

        // 根据验证码是否为空判断请求参数是否添加code字段
        if(code != null)
            nameValuePairs.add(new BasicNameValuePair("code",code));

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));


        // Execute Http Request
        HttpResponse response = httpClient.execute(httpPost);

        JSONObject authJson = JSONObject.fromObject(EntityUtils.toString(response.getEntity(), "utf-8"));

        return authJson;
    }


    private int jsonCodeStatus(JSONObject json){

        int errCode = (int)json.get("code");

        if(errCode == 200)
            return 0;
        else
            return 1;
    }


}
