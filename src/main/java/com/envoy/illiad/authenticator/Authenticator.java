package com.envoy.illiad.authenticator;

public interface Authenticator {

    boolean identify(String username, String password);
}
