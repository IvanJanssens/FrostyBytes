package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitter;
import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import com.lina.frostybytes.core_api.shared.EntityNotFound;
import com.lina.frostybytes.query.category.CategoryEntity;
import com.lina.frostybytes.utils.LogUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;



@Service
@RequiredArgsConstructor
class FridgeProjector {

    private final FridgeRepository fridgeRepository;
    private final FridgeMapper fridgeMapper;
    private final CRUDQueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void create(EventModels.FridgeCreatedEvent event) {
        FridgeEntity entity = fridgeMapper.toEntity(event);
        fridgeRepository.findById(event.id())
                .defaultIfEmpty(entity)
                .flatMap(fridgeRepository::save)
                .doOnError(LogUtils::onError)
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitAdd(QueryModels.GetAllFridgesQuery.class, query -> true, fridgeMapper.toModel(savedEntity)))
                .block();
    }

    @EventHandler
    public void update(EventModels.FridgeUpdatedEvent event) {
        fridgeRepository.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), CategoryEntity.class)))
                .map(entity -> fridgeMapper.toEntity(event))
                .flatMap(fridgeRepository::save)
                .doOnError(LogUtils::onError)
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitUpdate(QueryModels.GetAllFridgesQuery.class, query -> true, fridgeMapper.toModel(savedEntity)))
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitUpdate(QueryModels.GetFridgeQuery.class, query -> query.id().equals(savedEntity.getId()), fridgeMapper.toModel(savedEntity)))
                .block();
    }

    @EventHandler
    public void delete(EventModels.FridgeDeletedEvent event) {
        fridgeRepository.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), CategoryEntity.class)))
                .flatMap(entity -> fridgeRepository.delete(entity).thenReturn(entity))
                .doOnSuccess(entity -> queryUpdateEmitter.emitDelete(QueryModels.GetAllFridgesQuery.class, query -> true, fridgeMapper.toModel(entity)))
                .doOnSuccess(entity -> queryUpdateEmitter.emit(QueryModels.GetFridgeQuery.class, query -> query.id().equals(event.id()), (QueryModels.Fridge) null))
                .block();
    }

    @QueryHandler
    Mono<QueryModels.Fridge> fetch(@NotNull QueryModels.GetFridgeQuery query) {
        return fridgeRepository.findById(query.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(query.id(), FridgeEntity.class)))
                .map(fridgeMapper::toModel);

    }

    @QueryHandler
    public Iterable<QueryModels.Fridge> getAllFridges(QueryModels.GetAllFridgesQuery query) {
        Pageable pageable = PageRequest.of(query.page(), query.size());
        return fridgeRepository.findAllBy(pageable).map(fridgeMapper::toModel).toIterable();
    }
}
