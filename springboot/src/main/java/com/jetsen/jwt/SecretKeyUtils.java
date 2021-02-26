package com.jetsen.jwt;

import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Nickel Fang
 * @date: 2020/10/30 15:36
 */
public class SecretKeyUtils {
    public static final String KEY_ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static RSA256Key rsa256Key;

    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static String getPublicKey(RSA256Key rsa256Key) {
        Key key = rsa256Key.getPublicKey();
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return encryptBASE64(key.getEncoded());
    }

    public static String getPrivateKey(RSA256Key rsa256Key) {
        Key key = rsa256Key.getPrivateKey();
        return encryptBASE64(key.getEncoded());
    }

    private static String encryptBASE64(byte[] encoded) {
        return (new BASE64Encoder()).encode(encoded);
    }

    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, keyPair.getPublic());
        keyMap.put(PRIVATE_KEY, keyPair.getPrivate());
        return keyMap;
    }

    public static synchronized RSA256Key getRSA256Key() throws Exception {
        if (rsa256Key == null) {
            synchronized (RSA256Key.class) {
                if (rsa256Key == null) {
                    rsa256Key = new RSA256Key();
                    Map<String, Object> map = initKey();
                    rsa256Key.setPrivateKey((RSAPrivateKey) map.get(SecretKeyUtils.PRIVATE_KEY));
                    rsa256Key.setPublicKey((RSAPublicKey) map.get(SecretKeyUtils.PUBLIC_KEY));
                }
            }
        }
        return rsa256Key;
    }

    public static void main(String[] args) throws Exception {
        RSA256Key rsa256Key = getRSA256Key();
        System.out.println("公钥：" + getPublicKey(rsa256Key));
        System.out.println("私钥：" + getPrivateKey(rsa256Key));
    }
}
