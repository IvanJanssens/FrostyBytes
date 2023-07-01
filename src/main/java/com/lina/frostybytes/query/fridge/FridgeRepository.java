package com.lina.frostybytes.query.fridge;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
 interface FridgeRepository extends ReactiveMongoRepository<FridgeEntity, UUID> {
 Flux<FridgeEntity> findAllBy(Pageable pageable);
}
