package Encrypters;

import java.security.MessageDigest;

/**
 * Created by madsgade on 17/10/2016.
 */

public class Digester {
    private final static String SALT = "82efbcc2cc33d33cdadf12806d75591a";
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String hash(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Fejl");
        }
        return Digester._hash(str);
    }

    public static String hashWithSalt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Fejl");
        }

        str = str + Digester.SALT;

        return Digester._hash(str);
    }

    private static String _hash(String str) {
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuffer hexString = new StringBuffer();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0" + Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }
}