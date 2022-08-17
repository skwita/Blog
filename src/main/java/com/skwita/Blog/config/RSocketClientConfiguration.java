package com.skwita.Blog.config;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.integration.IntegrationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebSocketMessageBroker
public class RSocketClientConfiguration implements WebSocketMessageBrokerConfigurer {
//    @Bean
//    public ApplicationRunner sender(RSocketRequester.Builder requesterBuilder) {
//        return args -> {
//            RSocketRequester tcp = requesterBuilder.tcp("localhost",
//                                                        7000);
//            tcp.route("/{id}/like", 1)
//               .data(1)
//               .retrieveMono(Integer.class)
//               .subscribe();
//        };
//    }

    @Bean
    public ApplicationRunner server() {
        return null;
    }
}
