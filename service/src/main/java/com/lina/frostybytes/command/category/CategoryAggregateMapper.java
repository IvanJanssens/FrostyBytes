package com.lina.frostybytes.command.category;

import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.EventModels;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Mapper
@Validated
public interface CategoryAggregateMapper {

    @Valid
    EventModels.CategoryAddedEvent toEvent(CommandModels.createCategory command);
    @Valid
    EventModels.CategoryUpdatedEvent toEvent(CommandModels.UpdateCategory command);
    @Valid
    EventModels.CategoryDeletedEvent toEvent(CommandModels.DeleteCategory command);
}
