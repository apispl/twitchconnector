package com.pszczolkowski.twitchconnector.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

import java.util.Optional;

@RequiredArgsConstructor
public class OAuthAuthorizationHeaderProvider implements AuthorizationHeaderProvider {

    private static final String HEADER_VALUE_PREFIX = "Bearer ";

    private final OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager;
    private final OAuth2AuthorizeRequest authorizeRequest;

    @Override
    public Optional<String> getAuthorizationHeader() {
        return Optional.ofNullable(oAuth2AuthorizedClientManager.authorize(authorizeRequest))
                .map(e -> HEADER_VALUE_PREFIX.concat(e.getAccessToken().getTokenValue()));
    }
}
