package com.lina.frostybytes.query.item;

import com.lina.frostybytes.query.category.CategoryEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

public interface ItemRepository extends ReactiveCrudRepository<ItemEntity, UUID> {
}
