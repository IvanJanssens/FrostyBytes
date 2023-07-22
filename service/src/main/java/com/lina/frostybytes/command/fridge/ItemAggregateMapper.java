package com.lina.frostybytes.command.fridge;


import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Mapper
@Validated
interface ItemAggregateMapper {

    @Valid
    EventModels.ItemAddedToFridgeEvent toEvent(CommandModels.AddItemToFridgeCommand command);

    @Valid
    EventModels.ItemUpdatedEvent toEvent(CommandModels.UpdateItemCommand command);

    @Valid
    EventModels.ItemDeletedEvent toEvent(CommandModels.DeleteItemCommand command);
}
