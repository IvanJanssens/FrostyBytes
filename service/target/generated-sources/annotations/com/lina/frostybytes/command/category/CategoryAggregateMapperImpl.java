package com.lina.frostybytes.command.category;

import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.EventModels;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T19:13:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1 (Eclipse Adoptium)"
)
@Component
public class CategoryAggregateMapperImpl implements CategoryAggregateMapper {

    @Override
    public EventModels.CategoryAddedEvent toEvent(CommandModels.addCategory command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;

        id = command.id();
        name = command.name();
        icon = command.icon();

        EventModels.CategoryAddedEvent categoryAddedEvent = new EventModels.CategoryAddedEvent( id, name, icon );

        return categoryAddedEvent;
    }

    @Override
    public EventModels.CategoryUpdatedEvent toEvent(CommandModels.updateCategory command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;

        id = command.id();
        name = command.name();
        icon = command.icon();

        EventModels.CategoryUpdatedEvent categoryUpdatedEvent = new EventModels.CategoryUpdatedEvent( id, name, icon );

        return categoryUpdatedEvent;
    }

    @Override
    public EventModels.CategoryDeletedEvent toEvent(CommandModels.deleteCategory command) {
        if ( command == null ) {
            return null;
        }

        UUID id = null;

        id = command.id();

        EventModels.CategoryDeletedEvent categoryDeletedEvent = new EventModels.CategoryDeletedEvent( id );

        return categoryDeletedEvent;
    }
}
