package com.pszczolkowski.twitchconnector.rest;

import java.util.Optional;

public interface AuthorizationHeaderProvider {
    default Optional<String> getAuthorizationHeader() {
        return Optional.empty();
    }

    static AuthorizationHeaderProvider noAuth(){
        return new AuthorizationHeaderProvider(){};
    }
}
