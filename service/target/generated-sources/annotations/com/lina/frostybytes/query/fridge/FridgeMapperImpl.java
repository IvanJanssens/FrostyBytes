package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T19:13:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1 (Eclipse Adoptium)"
)
@Component
class FridgeMapperImpl implements FridgeMapper {

    @Override
    public QueryModels.Fridge toModel(FridgeEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        QueryModels.Fridge fridge = new QueryModels.Fridge( id, name );

        return fridge;
    }

    @Override
    public FridgeEntity toEntity(EventModels.FridgeCreatedEvent fridge) {
        if ( fridge == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = fridge.id();
        name = fridge.name();

        FridgeEntity fridgeEntity = new FridgeEntity( id, name );

        return fridgeEntity;
    }

    @Override
    public FridgeEntity toEntity(EventModels.FridgeUpdatedEvent event) {
        if ( event == null ) {
            return null;
        }

        UUID id = null;
        String name = null;

        id = event.id();
        name = event.name();

        FridgeEntity fridgeEntity = new FridgeEntity( id, name );

        return fridgeEntity;
    }
}
