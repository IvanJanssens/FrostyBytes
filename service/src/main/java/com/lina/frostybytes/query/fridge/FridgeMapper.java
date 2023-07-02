package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.validation.annotation.Validated;

@Validated
@Mapper
interface FridgeMapper {


    @Valid
    QueryModels.Fridge toModel(FridgeEntity entity);

    @Valid
    FridgeEntity toEntity(EventModels.FridgeCreatedEvent fridge);

    @Valid
    FridgeEntity toEntity(EventModels.FridgeUpdatedEvent event);
}
