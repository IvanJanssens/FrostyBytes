package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import javax.crypto.spec.PSource;

@Validated
@Mapper
interface FridgeMapper {


    @Valid
    QueryModels.Fridge toModel(FridgeEntity entity);

    @Valid
    @Mapping(target = "id", source = "fridgeId")
    FridgeEntity toEntity(EventModels.FridgeCreatedEvent fridge);

    @Valid
    @Mapping(target = "id", source = "fridgeId")
    FridgeEntity toEntity(EventModels.FridgeUpdatedEvent event);
    @Valid
    @Mapping(target = "deleted", constant = "true")
    QueryModels.Fridge toDeleted(FridgeEntity entity);
}
