package com.lina.frostybytes.command.category;

import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.EventModels;
import org.mapstruct.Mapper;

@Mapper
public interface CategoryAggregateMapper {

    EventModels.CategoryCreatedEvent toEvent(CommandModels.addCategory command);
    EventModels.CategoryUpdatedEvent toEvent(CommandModels.updateCategory command);
    EventModels.CategoryDeletedEvent toEvent(CommandModels.deleteCategory command);
}
