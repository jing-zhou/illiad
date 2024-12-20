package com.envoy.illiad.config;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Address {

    private String host;

    private int port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return port == address.port &&
                Objects.equals(host, address.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port);
    }
}
