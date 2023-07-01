package com.lina.frostybytes.config.axon;

import org.axonframework.extensions.reactor.queryhandling.gateway.DefaultReactorQueryGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class QueryGatewayConfig {
    @Bean
    @Primary
    public ReactorQueryGateway reactorQueryGateway() {
       return DefaultReactorQueryGateway
                .builder()
                .queryBus(SimpleQueryBus.builder().build())
                .build();

    }
}
