package com.envoy.illiad.ssocks.cipher;

import com.envoy.illiad.ssocks.cipher.worker.CipherWorker;

public interface Cipher {

    int getKeyLength();

    int getIVLength();

    CipherWorker newWorker();
}
