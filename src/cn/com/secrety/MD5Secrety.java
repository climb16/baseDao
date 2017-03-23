package cn.com.secrety;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xiao on 2016/1/19.
 */
public class MD5Secrety {

    protected static MessageDigest md5 = null;
    private static final String MD5 = "MD5";
    private static final String ENCODING = "ISO-8859-1";
    static{
        try {
            md5 = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String MD5Encoder(String str){
        if(str == null || str.trim().length() <= 0){
            return "";
        }else{
            try {
                return new String(md5.digest(str.getBytes()), ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return "";
    }



    public static void main(String[] args){
        String a = MD5Secrety.MD5Encoder("aaa111");
        System.out.println(a);
    }
}