package com.lina.frostybytes.query.item;

import com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitter;
import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import com.lina.frostybytes.utils.LogUtils;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemProjector {


    private final ItemRepository repository;
    private final ItemMapper mapper;
    private final CRUDQueryUpdateEmitter queryUpdateEmitter;
    @EventHandler
    public void addItem(EventModels.ItemAddedToFridgeEvent event) {
        ItemEntity entity = mapper.toEntity(event);
        repository.findById(event.itemId())
                .defaultIfEmpty(entity)
                .flatMap(repository::save)
                .doOnError(LogUtils::onError)
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitAdd(QueryModels.GetAllItemsQuery.class, query -> true, mapper.toModel(savedEntity)))
                .block();
    }
}
