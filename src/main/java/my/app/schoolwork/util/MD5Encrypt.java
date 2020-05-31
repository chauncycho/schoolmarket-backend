package my.app.schoolwork.util;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.util.DigestUtils;

public class MD5Encrypt {
    public static String encrypt(String pass){
        return DigestUtils.md5DigestAsHex(pass.getBytes());
    }
}
