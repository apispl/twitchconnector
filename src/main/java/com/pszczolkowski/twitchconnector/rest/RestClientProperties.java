package com.pszczolkowski.twitchconnector.rest;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RestClientProperties {
    private final String host;
    private final String port;
    private final String schema;
}
