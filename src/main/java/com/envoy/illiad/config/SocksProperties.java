package com.envoy.illiad.config;

import com.envoy.illiad.upstream.Upstream;
import io.netty.channel.socket.SocketChannel;
import lombok.Data;

import java.util.Map;

@Data
public class SocksProperties {

    private Address listen = new Address("127.0.0.1", 6000);

    private Upstream<SocketChannel> upstream = null;

    private boolean auth = false;

    private Map<String, String> authMap;

    private int acceptors = 2;

    private int backlog = 128;

    private int connectTimeoutMillis = 3000;

    private int readTimeoutMillis = 30000;

    private int writeTimeoutMillis = 10000;
}
