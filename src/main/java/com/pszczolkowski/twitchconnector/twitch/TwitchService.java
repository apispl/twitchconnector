package com.pszczolkowski.twitchconnector.twitch;

import com.pszczolkowski.twitchconnector.twitch.dto.TwitchStreamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
class TwitchService {

    private final TwitchApiAdapter twitchAdapter;

    void invokeStreamsDownloading() {
        TwitchStreamDto twitchStreamDto = twitchAdapter.getStreams();

        log.info("Downloaded streams: {}", twitchStreamDto);

    }
}
