package com.envoy.illiad.upstream;

import com.envoy.illiad.config.Address;
import io.netty.channel.Channel;

public abstract class Upstream<T extends Channel> {

    public static final String HANDLER_NAME = "proxy";

    private Address address;

    protected Upstream setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public abstract void initChannel(T channel);
}
