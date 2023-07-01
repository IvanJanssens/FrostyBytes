package com.lina.frostybytes.command.category;

import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.EventModels;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

@Mapper
@Validated
public interface CategoryAggregateMapper {

    EventModels.CategoryAddedEvent toEvent(CommandModels.addCategory command);
    EventModels.CategoryUpdatedEvent toEvent(CommandModels.updateCategory command);
    EventModels.CategoryDeletedEvent toEvent(CommandModels.deleteCategory command);
}
