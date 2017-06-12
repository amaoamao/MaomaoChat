package Helper;

/**
 * Created by Jaho on 2017/5/17.
 */
public interface AuthHelper {
    public int sendAuthCode(String phone) throws Exception ;
    public int verifyAuthCode(String phone,String code) throws Exception ;
}
