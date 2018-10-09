package pfr.center.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Util {
    public static String getHash256(String string)  {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(string.getBytes(StandardCharsets.UTF_8));
        return new String(messageDigest.digest());
    }
    public static String getHashMD5(String string)  {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(string.getBytes(StandardCharsets.UTF_8));
        return new String(messageDigest.digest());
    }

    public static ArrayList<Date> GetLastDates(int days){
        long DAY_IN_MS = 1000 * 60 * 60 * 24;
        ArrayList<Date> dates= new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        LocalDateTime ldt = LocalDateTime.now();
        for(int i=days;i>=0;i--){
          Date date = new Date(System.currentTimeMillis() - (i * DAY_IN_MS));
          dates.add(date);
        }
        return dates;
    }
}
