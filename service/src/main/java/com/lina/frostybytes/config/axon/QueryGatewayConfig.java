package com.lina.frostybytes.config.axon;

import com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitter;
import com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitterImpl;
import com.lina.frostybytes.config.axon.extensions.SubscribingQueryGateway;
import com.lina.frostybytes.config.axon.extensions.SubscribingQueryGatewayImpl;
import org.axonframework.axonserver.connector.AxonServerConfiguration;
import org.axonframework.axonserver.connector.AxonServerConnectionManager;
import org.axonframework.axonserver.connector.query.AxonServerQueryBus;
import org.axonframework.extensions.reactor.queryhandling.gateway.DefaultReactorQueryGateway;
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
    CRUDQueryUpdateEmitter crudQueryUpdateEmitter() {
        return new CRUDQueryUpdateEmitterImpl(SimpleQueryUpdateEmitter.builder());
    }

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
    @Bean
    @Primary
    public SubscribingQueryGateway reactorQueryGateway(QueryBus queryBus) {
       return SubscribingQueryGatewayImpl
               .subscribingBuilder()
               .queryBus(queryBus)
               .build();

    }
}
