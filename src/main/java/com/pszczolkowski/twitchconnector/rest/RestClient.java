package com.pszczolkowski.twitchconnector.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class RestClient {
    private final RestTemplate restTemplate;
    private final RestClientProperties configuration;
    private final AuthorizationHeaderProvider authorizationHeaderProvider;

    public <RequestT, ResponseT> ResponseEntity<ResponseT> callOperation(
            UriComponents uri,
            HttpMethod httpMethod,
            RequestT request,
            ParameterizedTypeReference<ResponseT> responseType,
            Map<String, String> additionalHeaders
    ) {
        String url = uri.toUriString();
        log.debug("Call operation " + httpMethod.name() + " for URL: " + url);
        ResponseEntity<ResponseT> response = restTemplate.exchange(url, httpMethod, getHttpRequest(request, additionalHeaders), responseType);
        log.debug("Getting response with status: " + response.getStatusCode());
        return response;
    }

    public <ResponseT> ResponseEntity<ResponseT> callOperation(
            UriComponents uri,
            HttpMethod httpMethod,
            ParameterizedTypeReference<ResponseT> responseType,
            Map<String, String> additionalHeaders
    ) {
        String url = uri.toUriString();
        log.debug("Call operation " + httpMethod.name() + " for URL: " + url);
        ResponseEntity<ResponseT> response = restTemplate.exchange(url, httpMethod, getHttpRequest(additionalHeaders), responseType);
        log.debug("Getting response with status: " + response.getStatusCode());
        return response;
    }


    public UriComponentsBuilder uriBuilder(String path) {
        return UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort())
                .path(path);
    }

    private <RequestT> HttpEntity<RequestT> getHttpRequest(RequestT bodyRequest, Map<String, String> additionalHeaders) {
        return new HttpEntity<>(bodyRequest, getHttpHeaders(additionalHeaders));
    }

    private HttpEntity<?> getHttpRequest(Map<String, String> additionalHeaders) {
        return new HttpEntity<>(getHttpHeaders(additionalHeaders));
    }

    private HttpHeaders getHttpHeaders(Map<String, String> optionalHeaders) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Optional.ofNullable(optionalHeaders)
                .ifPresent(headersMap -> {
                    headersMap.forEach(headers::add);
                });
        authorizationHeaderProvider.getAuthorizationHeader().ifPresent(authHeader ->
                headers.add(HttpHeaders.AUTHORIZATION, authHeader)
        );
        return headers;
    }
}
