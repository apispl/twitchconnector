package com.pszczolkowski.twitchconnector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TwitchConnectorApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitchConnectorApplication.class, args);
    }

}
