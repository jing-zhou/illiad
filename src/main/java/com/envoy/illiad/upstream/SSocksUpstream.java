package com.envoy.illiad.upstream;

import com.envoy.illiad.config.Address;
import com.envoy.illiad.ssocks.SSocksConnectHandler;
import com.envoy.illiad.ssocks.cipher.Cipher;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class SSocksUpstream extends Upstream<SocketChannel> {

    private final Cipher cipher;

    private final String password;

    public SSocksUpstream(Address address, Cipher cipher, String password) {
        setAddress(address);
        this.cipher = cipher;
        this.password = password;
    }

    @Override
    public void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        Address upstreamAddress = getAddress();
        SocketAddress address = new InetSocketAddress(upstreamAddress.getHost(), upstreamAddress.getPort());
        pipeline.addFirst(HANDLER_NAME, new SSocksConnectHandler(channel.newPromise(), address, cipher, password));
    }
}
