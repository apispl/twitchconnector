package com.pszczolkowski.twitchconnector.twitch.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Builder
@ToString
public class TwitchStreamDto {

    private final List<TwitchStream> twitchStreams;
    private final Pagination pagination;
    private final Map<String, Object> additionalProperties;
}
