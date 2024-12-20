package com.envoy.illiad.upstream;

import com.envoy.illiad.config.Address;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.proxy.Socks5ProxyHandler;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author xp
 */
public class Socks5Upstream extends Upstream<SocketChannel> {

    public Socks5Upstream(final Address address) {
        setAddress(address);
    }

    @Override
    public void initChannel(SocketChannel channel) {
        ChannelPipeline pipeline = channel.pipeline();

        Address upstreamAddress = getAddress();
        SocketAddress address = new InetSocketAddress(upstreamAddress.getHost(), upstreamAddress.getPort());
        pipeline.addFirst(HANDLER_NAME, new Socks5ProxyHandler(address));
    }
}
