package cn.com.secrety;

import org.bouncycastle.util.encoders.Base64;

public class Base64Coder {
    private static final String ENCODING = "UTF-8";

    public static String encode(String data) throws Exception {
        if(data != null && data.equals("")){
            byte[] b = Base64.encode(data.getBytes(ENCODING));
            return new String(b, ENCODING);
        }
        return "";
    }
}
