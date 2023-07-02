package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Mapper
@Validated
public interface CategoryMapper {

    @Valid
    CategoryEntity toEntity(EventModels.CategoryAddedEvent event);

    @Valid
    CategoryEntity toEntity(EventModels.CategoryUpdatedEvent event);

    @Valid
    QueryModels.Category toModel(CategoryEntity entity);
}
