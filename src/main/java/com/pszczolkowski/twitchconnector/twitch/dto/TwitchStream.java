package com.pszczolkowski.twitchconnector.twitch.dto;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Builder
@ToString
public class TwitchStream {

    private final String id;
    private final String userId;
    private final String userLogin;
    private final String userName;
    private final String gameId;
    private final String gameName;
    private final String type;
    private final String title;
    private final List<String> tags;
    private final Integer viewerCount;
    private final String startedAt;
    private final String language;
    private final String thumbnailUrl;
    private final List<String> tagIds;
    private final Boolean isMature;
    private final Map<String, Object> additionalProperties;
}
