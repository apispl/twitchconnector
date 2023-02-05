package com.pszczolkowski.twitchconnector.security;

import com.pszczolkowski.twitchconnector.rest.AuthorizationHeaderProvider;
import com.pszczolkowski.twitchconnector.rest.OAuthAuthorizationHeaderProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;

@Configuration
public class OAuthClientConfiguration {

    @Bean
    ClientRegistration twitchClientRegistration(
            @Value("${spring.security.oauth2.client.registration-id}") String registrationId,
            @Value("${spring.security.oauth2.client.provider.twitch.token-uri}") String tokenUri,
            @Value("${spring.security.oauth2.client.registration.twitch.client-id}") String clientId,
            @Value("${spring.security.oauth2.client.registration.twitch.client-secret}") String clientSecret,
            @Value("${spring.security.oauth2.client.registration.twitch.scope}") String scope,
            @Value("${spring.security.oauth2.client.registration.twitch.authorization-grant-type}") String authorizationGrantType

    ) {
        return ClientRegistration
                .withRegistrationId(registrationId)
                .tokenUri(tokenUri)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scope(scope)
                .authorizationGrantType(new AuthorizationGrantType(authorizationGrantType))
                .build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository(ClientRegistration twitchClientRegistration) {
        return new InMemoryClientRegistrationRepository(twitchClientRegistration);
    }

    @Bean
    public OAuth2AuthorizedClientService oAuth2AuthorizedClientService(ClientRegistrationRepository clientRegistrationRepository) {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientServiceAndManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService oAuth2AuthorizedClientService
    ) {
        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                clientRegistrationRepository,
                oAuth2AuthorizedClientService
        );

        authorizedClientManager.setAuthorizedClientProvider(
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build()
        );

        return authorizedClientManager;
    }

    @Bean
    public OAuth2AuthorizeRequest oAuth2AuthorizeRequest(
            @Value("${spring.security.oauth2.client.registration-id}") String clientRegistrationId,
            @Value("${spring.security.oauth2.client.principal}") String principal
    ) {
        return OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistrationId)
                .principal(principal)
                .build();
    }

    @Bean
    public AuthorizationHeaderProvider oAuth2AuthorizationHeaderProvider(
            OAuth2AuthorizedClientManager oAuth2AuthorizedClientManager,
            OAuth2AuthorizeRequest oAuth2AuthorizeRequest
    ) {
        return new OAuthAuthorizationHeaderProvider(
                oAuth2AuthorizedClientManager,
                oAuth2AuthorizeRequest
        );
    }
}
