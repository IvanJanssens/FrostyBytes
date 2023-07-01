package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.axonframework.queryhandling.QueryHandler;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Validated
@Mapper
interface FridgeMapper {


    @Valid
    QueryModels.Fridge toModel(FridgeEntity entity);

    @Valid
    FridgeEntity toEntity(EventModels.FridgeCreatedEvent fridge);

    @Valid
    FridgeEntity toEntity(EventModels.FridgeUpdatedEvent fridge);
}
