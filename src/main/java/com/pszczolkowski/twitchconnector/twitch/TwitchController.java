package com.pszczolkowski.twitchconnector.twitch;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/twitch")
@RequiredArgsConstructor
class TwitchController {

    private final TwitchService twitchService;

    @GetMapping("/streams")
    public ResponseEntity<?> getStreams() {

        twitchService.invokeStreamsDownloading();

        return ResponseEntity.ok().build();
    }
}
