package com.pszczolkowski.twitchconnector.twitch;

import com.pszczolkowski.twitchconnector.rest.AuthorizationHeaderProvider;
import com.pszczolkowski.twitchconnector.rest.RestClient;
import com.pszczolkowski.twitchconnector.rest.RestClientProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TwitchRestClientConfiguration {

    @Bean
    public RestClientProperties twitchRestClientProperties(
            @Value("${twitch.schema}") String schema,
            @Value("${twitch.host}") String host
    ) {
        return RestClientProperties.builder()
                .schema(schema)
                .host(host)
                .build();
    }

    @Bean
    public RestClient twitchRestClient(
            RestClientProperties twitchRestClientProperties,
            AuthorizationHeaderProvider oAuth2AuthorizationHeaderProvider
    ) {
        return new RestClient(
                new RestTemplate(),
                twitchRestClientProperties,
                oAuth2AuthorizationHeaderProvider
        );
    }
}
