package com.pszczolkowski.twitchconnector.twitch;

import com.pszczolkowski.twitchconnector.rest.RestClient;
import com.pszczolkowski.twitchconnector.twitch.dto.TwitchStreamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
class TwitchApiAdapter {
    private static final String GET_STREAMS = "/helix/streams";

    private final RestClient twitchRestClient;

    public TwitchStreamDto getStreams() {
        log.debug("Fetch streams");
        var uri = twitchRestClient.uriBuilder(GET_STREAMS).build();

        log.debug("Trying to send request: " + uri.toUriString());

        ResponseEntity<TwitchStreamDto> response = twitchRestClient.callOperation(
                uri,
                HttpMethod.GET,
                new ParameterizedTypeReference<>() {
                },
                Collections.emptyMap()
        );

        if (response.getStatusCode().isError()) {
            throw new RuntimeException("Unexpected HTTP status code: " + response.getStatusCode() + " occurred.");
        }

        return response.getBody();
    }

}
