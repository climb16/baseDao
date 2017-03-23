package cn.com.secrety;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    private static char hexDigits[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    private static final String AES = "AES";

    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String key = "lzSN3tVMevb8ptqN";

    /**
     * AES 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] Encrypt(byte[] data, byte[] key) throws Exception {
        //转换秘钥材料
        Key k = toKey(key);
        Security.addProvider(new BouncyCastleProvider());
        //实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        //设置加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * AES 解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] Decrypt(byte[] data, byte[] key) throws Exception {
        //转换秘钥材料
        Key k = toKey(key);
        Security.addProvider(new BouncyCastleProvider());
        //实例化
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        //设置加密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }


    /**
     * 获取秘钥
     * @param key
     * @return
     */
    private static Key toKey(byte[] key){

        //实例化秘钥材料
        SecretKey secretKey = new SecretKeySpec(key, AES);
        return secretKey;
    }

    /**
     * byte数组转16进制表示
     * @param bytes
     * @return
     */
    private static String hexDigits(byte[] bytes){
        if(bytes == null) return "";
        int j = bytes.length;
        char str[] = new char[j * 2];
        int k = 0;
        for (int i = 0; i < j; i++) {
            byte byte0 = bytes[i];
            str[k++] = hexDigits[byte0 >>> 4 & 0xf];
            str[k++] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }

    /**
     * 16进制转 byte[] 数组
     * @param data
     * @return
     */
    public static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch
                    + " at index " + index);
        }
        return digit;
    }

    /**
     * 生成秘钥
     * @return
     */
    private static byte[] initKey() throws Exception {
        //添加BC支持，生成64位秘钥
        Security.addProvider(new BouncyCastleProvider());
        //实例化秘钥生成器
        KeyGenerator kg = KeyGenerator.getInstance(AES);
        //初始化秘钥长度
        kg.init(128);
        //生成秘钥
        SecretKey sk = kg.generateKey();
        //获得秘钥的二进制形式
        //byte[] key = sk.getEncoded();
        //base64编码
        return sk.getEncoded();
    }
}
