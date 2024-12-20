package com.envoy.illiad.ssocks.cipher;

public abstract class AbstractCipher implements Cipher {

    private final String name;

    public AbstractCipher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
