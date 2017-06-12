package Helper;

import java.security.MessageDigest;

/**
 * Created by Jaho on 2017/5/9.
 */
public class CheckSumHelper {

    // Get Netease Check Sum
    public static String getCheckSum(String appSecret,String nonce,String curlTime){
        return encode("sha1",appSecret + nonce + curlTime);
    }

    public static String getSHA(String requestBody) {
        return encode("SHA",requestBody);
    }

    private static String encode(String algorithm, String value){
        if(value == null){
            return null;
        }

        try{
            MessageDigest messageDigest =  MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes){

        int len = bytes.length;

        StringBuilder strBuilder = new StringBuilder(len * 2);
        for(int i = 0; i < len; ++i){

            strBuilder.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            strBuilder.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }

        return strBuilder.toString();


    }

    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
}
