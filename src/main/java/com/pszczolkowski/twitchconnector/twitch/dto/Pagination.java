package com.pszczolkowski.twitchconnector.twitch.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Builder
public class Pagination {

    private final String cursor;
    private final Map<String, Object> additionalProperties;

}
