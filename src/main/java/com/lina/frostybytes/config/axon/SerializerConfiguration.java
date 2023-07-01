package com.lina.frostybytes.config.axon;

import com.lina.frostybytes.config.mappers.FrostyBytesObjectMapper;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SerializerConfiguration {

    @Bean
    @Primary
    public Serializer buildSerializer() {
        return JacksonSerializer.builder()
                .lenientDeserialization()
                .objectMapper(FrostyBytesObjectMapper.getAxonInstance())
                .defaultTyping()
                .build();
    }
}