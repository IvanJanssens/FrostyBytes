package com.lina.frostybytes.query.item;

import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

@Validated
@Mapper
public interface ItemMapper {

    @Valid
    @Mapping(target = "id", source = "itemId")
    ItemEntity toEntity(EventModels.ItemAddedToFridgeEvent event);

    @Valid QueryModels.Item toModel(ItemEntity entity);
}
