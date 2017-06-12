package Helper;
import java.sql.Date;
import java.util.Calendar;
/**
 * Created by Jaho on 2017/5/9.
 */
public class UserInfoHelper {

    public static Date getExpiredDate(){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, +1);
        java.util.Date utilDate = calendar.getTime();

        //java.util.Date日期转换成转成java.sql.Date格式
        Date sqlDate =new Date(utilDate.getTime());
        return sqlDate;
    }

    public static Date getDate(){

        Calendar calendar = Calendar.getInstance();
        java.util.Date utilDate = calendar.getTime();

        //java.util.Date日期转换成转成java.sql.Date格式
        Date sqlDate =new Date(utilDate.getTime());
        return sqlDate;
    }

    public static boolean afterExpiredDate(Date date){

        Calendar calendar = Calendar.getInstance();
        java.util.Date utilDate = calendar.getTime();
        //java.util.Date日期转换成转成java.sql.Date格式
        Date nowDate =new Date(utilDate.getTime());

        return nowDate.after(date);
    }


    public static String encryptionPassword(String value){

        return CheckSumHelper.getSHA(value);
    }
}
