package pfr.center.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
}
