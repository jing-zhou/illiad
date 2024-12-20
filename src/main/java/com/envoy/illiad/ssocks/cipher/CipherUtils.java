package com.envoy.illiad.ssocks.cipher;

import java.security.SecureRandom;

public class CipherUtils {

    private static final SecureRandom RANDOM = new SecureRandom();

    public static byte[] genRandomBytes(int size) {
        byte[] iv = new byte[size];
        RANDOM.nextBytes(iv);
        return iv;
    }
}
