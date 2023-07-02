package com.lina.frostybytes.config.axon;

import com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitter;

import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.axonframework.axonserver.connector.query.AxonServerQueryBus;
import org.axonframework.queryhandling.QueryBus;
import org.axonframework.queryhandling.SimpleQueryBus;
import org.axonframework.serialization.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class QueryGatewayConfig {

    @Bean
    QueryBus queryBus(
            AxonServerConfiguration axonServerConfiguration,
            AxonServerConnectionManager axonServerConnectionManager,
            Serializer serializer,
            CRUDQueryUpdateEmitter queryUpdateEmitter
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

}
