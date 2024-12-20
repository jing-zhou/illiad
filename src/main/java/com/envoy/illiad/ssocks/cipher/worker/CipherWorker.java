package com.envoy.illiad.ssocks.cipher.worker;

public interface CipherWorker {

    void init(boolean isEncrypt, byte[] key, byte[] iv);

    byte[] process(byte[] input);
}
