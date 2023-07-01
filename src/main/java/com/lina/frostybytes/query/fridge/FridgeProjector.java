package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.core_api.fridge.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import com.lina.frostybytes.core_api.shared.EntityNotFound;
import jakarta.validation.constraints.NotNull;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

class FridgeProjector {

    private final FridgeRepository fridgeRepository;
    private final FridgeMapper fridgeMapper;
    private final QueryUpdateEmitter queryUpdateEmitter;

    FridgeProjector(FridgeRepository fridgeRepository, FridgeMapper fridgeMapper, QueryUpdateEmitter queryUpdateEmitter) {
        this.fridgeRepository = fridgeRepository;
        this.fridgeMapper = fridgeMapper;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void create(EventModels.FridgeCreatedEvent event) {
        fridgeRepository.save(fridgeMapper.toEntity(event)).block();
    }

    @EventHandler
    public void update(EventModels.FridgeUpdatedEvent event) {
        fridgeRepository.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), FridgeEntity.class)))
                .flatMap(existingFridgeEntity -> {
                    FridgeEntity updatedFridgeEntity = fridgeMapper.toEntity(event);
                    return fridgeRepository.save(updatedFridgeEntity);
                })
                .doOnSuccess(updatedFridgeEntity -> queryUpdateEmitter.emit(QueryModels.Fridge.class, query -> query.id().equals(updatedFridgeEntity.getId()), updatedFridgeEntity))
                .block();

    }

    @EventHandler
    public void delete(EventModels.FridgeDeletedEvent event) {
        fridgeRepository.deleteById(event.id())
                .doOnSuccess(deletedFridgeEntity -> queryUpdateEmitter.emit(QueryModels.Fridge.class, query -> query.id().equals(event.id()), null))
                .block();
    }

    @QueryHandler
    Mono<QueryModels.Fridge> fetch(@NotNull UUID id) {
        return fridgeRepository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotFound(id, FridgeEntity.class)))
                .map(fridgeMapper::toModel);

    }

    @QueryHandler
    public Flux<FridgeEntity> getAllFridges(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return fridgeRepository.findAllBy(pageable);
    }
}
