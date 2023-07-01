package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.validation.annotation.Validated;

import java.util.EventObject;

@Mapper
@Validated
public interface CategoryMapper {

    Category toEntity(EventModels.CategoryAddedEvent event);

    void updateEntity(@MappingTarget Category entity, EventModels.CategoryUpdatedEvent event);
}
