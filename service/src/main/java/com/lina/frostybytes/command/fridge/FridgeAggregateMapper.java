package com.lina.frostybytes.command.fridge;


import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Mapper
@Validated
interface FridgeAggregateMapper {

    @Valid
    EventModels.FridgeCreatedEvent toEvent(CommandModels.CreateFridgeCommand command);

    @Valid
    EventModels.FridgeUpdatedEvent toEvent(CommandModels.UpdateFridgeCommand command);

    @Valid
    EventModels.FridgeDeletedEvent toEvent(CommandModels.DeleteFridgeCommand command);
}
