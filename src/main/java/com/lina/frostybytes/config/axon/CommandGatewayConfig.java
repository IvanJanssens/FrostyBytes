package com.lina.frostybytes.config.axon;

import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.extensions.reactor.commandhandling.gateway.DefaultReactorCommandGateway;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommandGatewayConfig {

    @Bean
    @Primary
    public ReactorCommandGateway reactorCommandGateway() {
       return DefaultReactorCommandGateway
                .builder()
                .commandBus(SimpleCommandBus.builder().build())
                .build();

    }
}
