package com.lina.frostybytes.command.fridge;

import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-08T14:27:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1 (Eclipse Adoptium)"
)
@Component
class FridgeAggregateMapperImpl implements FridgeAggregateMapper {

    @Override
    public EventModels.FridgeCreatedEvent toEvent(CommandModels.CreateFridgeCommand command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = command.fridgeId();
        name = command.name();

        EventModels.FridgeCreatedEvent fridgeCreatedEvent = new EventModels.FridgeCreatedEvent( id, name );

        return fridgeCreatedEvent;
    }

    @Override
    public EventModels.FridgeUpdatedEvent toEvent(CommandModels.UpdateFridgeCommand command) {
        if ( command == null ) {
            return null;
        }

        String name = null;

        name = command.name();

        UUID id = null;

        EventModels.FridgeUpdatedEvent fridgeUpdatedEvent = new EventModels.FridgeUpdatedEvent( id, name );

        return fridgeUpdatedEvent;
    }

    @Override
    public EventModels.FridgeDeletedEvent toEvent(CommandModels.DeleteFridgeCommand command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;

        EventModels.FridgeDeletedEvent fridgeDeletedEvent = new EventModels.FridgeDeletedEvent( id );

        return fridgeDeletedEvent;
    }

    @Override
    public EventModels.ItemAddedToFridgeEvent toEvent(CommandModels.AddItemToFridgeCommand command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;
        UUID fridgeId = null;
        String name = null;
        UUID categoryId = null;
        LocalDate expirationDate = null;
        LocalDate placedAt = null;

        id = command.id();
        fridgeId = command.fridgeId();
        name = command.name();
        categoryId = command.categoryId();
        expirationDate = command.expirationDate();
        placedAt = command.placedAt();

        EventModels.ItemAddedToFridgeEvent itemAddedToFridgeEvent = new EventModels.ItemAddedToFridgeEvent( id, fridgeId, name, categoryId, expirationDate, placedAt );

        return itemAddedToFridgeEvent;
    }
}
