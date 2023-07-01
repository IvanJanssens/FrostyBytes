package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import com.lina.frostybytes.core_api.shared.EntityNotFound;
import com.lina.frostybytes.utils.LogUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryProjector {

    private final CategoryMapper mapper;
    private final CategoryRepository repo;
    private final QueryUpdateEmitter queryUpdateEmitter;

    @EventHandler
    public void on(EventModels.CategoryAddedEvent event) {
        Category entity = mapper.toEntity(event);
        repo.findById(event.id())
                .defaultIfEmpty(entity)
                .flatMap(repo::save)
                .doOnError(LogUtils::onError)
                .block();

        queryUpdateEmitter.emit(
                QueryModels.GetCategoryQuery.class,
                query -> true,
                repo.findAll()
        );
    }

    @EventHandler
    public void on(EventModels.CategoryUpdatedEvent event) {
        Category category = repo.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), Category.class)))
                .map(entity -> {
                    mapper.updateEntity(entity, event);
                    return entity;
                })
                .flatMap(repo::save)
                .doOnError(LogUtils::onError)
                .block();


        queryUpdateEmitter.emit(
                QueryModels.GetCategoryQuery.class,
                query -> true,
                repo.findAll()
        );


        queryUpdateEmitter.emit(
                QueryModels.GetCategoryQuery.class,
                query -> query.id().equals(event.id()),
                category
        );
    }

    @QueryHandler
    public Flux<QueryModels.Category> getAllFridges(QueryModels.GetCategoriesQuery query) {
        return repo.findAll().map(mapper::toModel);
    }
}
