package com.lina.frostybytes.command.fridge;

import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T19:13:14+0200",
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

        id = command.id();
        name = command.name();

        EventModels.FridgeCreatedEvent fridgeCreatedEvent = new EventModels.FridgeCreatedEvent( id, name );

        return fridgeCreatedEvent;
    }

    @Override
    public EventModels.FridgeUpdatedEvent toEvent(CommandModels.UpdateFridgeCommand command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = command.id();
        name = command.name();

        EventModels.FridgeUpdatedEvent fridgeUpdatedEvent = new EventModels.FridgeUpdatedEvent( id, name );

        return fridgeUpdatedEvent;
    }

    @Override
    public EventModels.FridgeDeletedEvent toEvent(CommandModels.DeleteFridgeCommand command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;

        id = command.id();

        EventModels.FridgeDeletedEvent fridgeDeletedEvent = new EventModels.FridgeDeletedEvent( id );

        return fridgeDeletedEvent;
    }
}
