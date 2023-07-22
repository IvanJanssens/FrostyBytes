package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import com.lina.frostybytes.query.item.ItemEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Mapper
@Validated
public interface CategoryMapper {

    @Valid
    @Mapping(target = "items", source = "items")
    CategoryEntity toEntity(EventModels.CategoryAddedEvent event, Set<ItemEntity> items);

    @Valid
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "items", source = "entity.items")
    @Mapping(target = "name", source = "event.name")
    @Mapping(target = "icon", source = "event.icon")
    CategoryEntity toEntity(CategoryEntity entity, EventModels.CategoryUpdatedEvent event);

    @Valid
    QueryModels.Category toModel(CategoryEntity entity);

    @Valid
    @Mapping(target = "deleted", constant = "true")
    QueryModels.Category toDeleted(CategoryEntity entity);
}
