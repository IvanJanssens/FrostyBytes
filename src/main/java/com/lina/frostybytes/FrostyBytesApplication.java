package com.lina.frostybytes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = "com.lina.frostybytes.query")
public class FrostyBytesApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrostyBytesApplication.class, args);
    }

}
