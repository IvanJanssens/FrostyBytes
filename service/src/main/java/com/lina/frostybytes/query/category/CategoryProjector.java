package com.lina.frostybytes.query.category;

import com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitter;
import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import com.lina.frostybytes.core_api.shared.EntityNotFound;
import com.lina.frostybytes.query.item.ItemEntity;
import com.lina.frostybytes.utils.LogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.GenericSubscriptionQueryUpdateMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashSet;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryProjector {

    private final CategoryMapper mapper;
    private final CategoryRepository repo;
    private final CRUDQueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(EventModels.CategoryAddedEvent event) {
        CategoryEntity entity = mapper.toEntity(event, new HashSet<>());
        repo.findById(event.id())
                .defaultIfEmpty(entity)
                .flatMap(repo::save)
                .doOnError(LogUtils::onError)
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitAdd(QueryModels.GetCategoriesQuery.class, query -> true, mapper.toModel(savedEntity)))
                .block();
    }

    @EventHandler
    public void on(EventModels.CategoryUpdatedEvent event) {
        repo.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), CategoryEntity.class)))
                .map(entity -> mapper.toEntity(entity, event))
                .flatMap(repo::save)
                .doOnError(LogUtils::onError)
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitUpdate(QueryModels.GetCategoriesQuery.class, query -> true, mapper.toModel(savedEntity)))
                .doOnSuccess(savedEntity -> queryUpdateEmitter.emitUpdate(QueryModels.GetCategoryQuery.class, query -> query.id().equals(savedEntity.getId()), mapper.toModel(savedEntity)))
                .block();
    }



    @EventHandler
    public void delete(EventModels.CategoryDeletedEvent event) {
        repo.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), CategoryEntity.class)))
                .flatMap(entity -> repo.delete(entity).thenReturn(entity))
                .doOnSuccess(entity -> queryUpdateEmitter.emitDelete(QueryModels.GetCategoriesQuery.class, query -> true, mapper.toModel(entity)))
                .doOnSuccess(entity -> queryUpdateEmitter.emitDelete(QueryModels.GetCategoryQuery.class, query -> query.id().equals(event.id()), mapper.toDeleted(entity)))
                .block();
    }

    @EventHandler
    public void addItem(com.lina.frostybytes.core_api.fridge.EventModels.ItemAddedToFridgeEvent event) {
        repo.findById(event.itemFields().categoryId())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.itemFields().categoryId(), CategoryEntity.class)))
                .map(entity -> {
                    entity.getItems().add(new ItemEntity(event.itemId(), event.itemFields().name(), event.itemFields().expirationDate(), event.itemFields().placedAt()));
                    return entity;
                })
                .flatMap(repo::save)
                .doOnSuccess(entity -> queryUpdateEmitter.emitUpdate(QueryModels.GetCategoriesQuery.class, query -> true, mapper.toModel(entity)))
                .doOnSuccess(entity -> queryUpdateEmitter.emitUpdate(QueryModels.GetCategoryQuery.class, query -> query.id().equals(event.itemFields().categoryId()), mapper.toDeleted(entity)))
                .block();
    }

    @QueryHandler
    public Iterable<QueryModels.Category> getAllCategories(QueryModels.GetCategoriesQuery query) {
        return repo.findAll().map(mapper::toModel).toIterable();
    }

    @QueryHandler
    public Mono<QueryModels.Category> getCategory(QueryModels.GetCategoryQuery query) {
        return repo.findById(query.id()).map(mapper::toModel);
    }
}
