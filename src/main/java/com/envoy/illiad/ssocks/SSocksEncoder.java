package com.envoy.illiad.ssocks;

import com.envoy.illiad.ssocks.cipher.Cipher;
import com.envoy.illiad.ssocks.cipher.CipherUtils;
import com.envoy.illiad.ssocks.cipher.worker.CipherWorker;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.SecretKey;

public class SSocksEncoder extends MessageToByteEncoder<ByteBuf> {

    private static final Logger LOG = LoggerFactory.getLogger(SSocksEncoder.class);

    private final Cipher cipher;

    private final SecretKey key;

    private final CipherWorker worker;

    private boolean initialized = false;

    public SSocksEncoder(Cipher cipher, SecretKey key) {
        this.cipher = cipher;
        this.key = key;
        this.worker = cipher.newWorker();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, ByteBuf msg, ByteBuf out) throws Exception {
        if (!initialized) {
            if (LOG.isTraceEnabled()) {
                LOG.trace("init encoder IV");
            }
            byte[] iv = CipherUtils.genRandomBytes(cipher.getIVLength());
            worker.init(true, key.getEncoded(), iv);
            out.writeBytes(iv);
            initialized = true;
        }
        if (LOG.isTraceEnabled()) {
            LOG.trace("encode payload");
        }
        byte[] plain = new byte[msg.readableBytes()];
        msg.readBytes(plain);
        byte[] encoded = worker.process(plain);
        out.writeBytes(encoded);
    }
}
