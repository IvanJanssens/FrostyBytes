package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

@Mapper
@Validated
public interface CategoryMapper {

    Category toEntity(EventModels.CategoryAddedEvent event);
}
