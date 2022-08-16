package com.skwita.Blog.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

//@Configuration
public class RSocketClientConfiguration {
//    @Bean
    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
        return args -> {
            RSocketRequester tcp = requesterBuilder.tcp("localhost",
                                                        7000);
            tcp.route("/{id}/like", 1)
               .data(1)
               .retrieveMono(Integer.class)
               .subscribe();
        };
    }
}
