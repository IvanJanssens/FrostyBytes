package com.lina.frostybytes.config.axon;

import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.axonframework.axonserver.connector.command.AxonServerCommandBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.distributed.RoutingStrategy;
import org.axonframework.extensions.reactor.commandhandling.gateway.DefaultReactorCommandGateway;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.serialization.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;




@Configuration
public class CommandGatewayConfig {

    @Bean
    CommandBus commandBus(RoutingStrategy routingStrategy, Serializer serializer, AxonServerConfiguration axonServerConfiguration, AxonServerConnectionManager axonServerConnectionManager){
        SimpleCommandBus localCommandBus = SimpleCommandBus.builder().build();


        return AxonServerCommandBus.builder()
                .configuration(axonServerConfiguration)
                .axonServerConnectionManager(axonServerConnectionManager)
                .localSegment(localCommandBus)
                .serializer(serializer)
                .routingStrategy(routingStrategy)
                .build();
    }

    @Bean
    @Primary
    public ReactorCommandGateway reactorCommandGateway(CommandBus commandBus) {
       return DefaultReactorCommandGateway
                .builder()
                .commandBus(commandBus)
                .build();

    }




}
