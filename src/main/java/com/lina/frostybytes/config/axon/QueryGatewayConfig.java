package com.lina.frostybytes.config.axon;

import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.axonframework.axonserver.connector.command.AxonServerCommandBus;
import org.axonframework.axonserver.connector.query.AxonServerQueryBus;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.distributed.RoutingStrategy;
import org.axonframework.extensions.reactor.queryhandling.gateway.DefaultReactorQueryGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.axonframework.queryhandling.SimpleQueryUpdateEmitter;
import org.axonframework.serialization.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class QueryGatewayConfig {


    @Bean
    QueryBus queryBus(
            AxonServerConfiguration axonServerConfiguration,
            AxonServerConnectionManager axonServerConnectionManager,
            Serializer serializer,
            QueryUpdateEmitter queryUpdateEmitter
    ){
        SimpleQueryBus localCommandBus = SimpleQueryBus.builder().build();


        return AxonServerQueryBus.builder()
                .configuration(axonServerConfiguration)
                .axonServerConnectionManager(axonServerConnectionManager)
                .localSegment(localCommandBus)
                .messageSerializer(serializer)
                .genericSerializer(serializer)
                .updateEmitter(queryUpdateEmitter)
                .build();
    }
    @Bean
    @Primary
    public ReactorQueryGateway reactorQueryGateway(QueryBus queryBus) {
       return DefaultReactorQueryGateway
                .builder()
                .queryBus(queryBus)
                .build();

    }
}
