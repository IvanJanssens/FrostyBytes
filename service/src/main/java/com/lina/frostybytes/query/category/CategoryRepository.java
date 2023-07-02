package com.lina.frostybytes.query.category;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;
public interface CategoryRepository extends ReactiveCrudRepository<CategoryEntity, UUID> {
}
