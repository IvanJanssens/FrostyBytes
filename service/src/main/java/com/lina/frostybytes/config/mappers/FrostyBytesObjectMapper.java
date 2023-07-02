package com.lina.frostybytes.config.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FrostyBytesObjectMapper {
    private static ObjectMapper instance = null;
    private static ObjectMapper axonInstance = null;

    private FrostyBytesObjectMapper() {

    }

    public static ObjectMapper getInstance() {
        if (instance == null) {
            instance =  new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        }
        return instance;
    }


    // AXON Adds an axon Module to the mapper which we do not want to use in our services
    public static ObjectMapper getAxonInstance() {
        if (axonInstance == null) {
            axonInstance =  new ObjectMapper().registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());
        }
        return axonInstance;
    }
}
