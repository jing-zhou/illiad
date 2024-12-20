package com.envoy.illiad.utils;

import com.envoy.illiad.utils.hex.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    private static final String MD5 = "MD5";

    private static MessageDigest getMessageDigest(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    public static MessageDigest getMD5() {
        return getMessageDigest(MD5);
    }

    public static byte[] md5(byte[] bytes) {
        return getMessageDigest(MD5).digest(bytes);
    }

    public static String md5AndHex(byte[] bytes) {
        byte[] result = md5(bytes);
        return HexUtils.bin2hexl(result);
    }
}
