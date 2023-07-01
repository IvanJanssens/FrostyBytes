package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.shared.EntityNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryProjector {

    private final CategoryMapper mapper;
    private final CategoryRepository repo;

    @EventHandler
    public void on(EventModels.CategoryAddedEvent event) {
        Category category = mapper.toEntity(event);
        repo.save(category).block();
    }

    @EventHandler
    public void on(EventModels.CategoryUpdatedEvent event) {
        repo.findById(event.id())
                .switchIfEmpty(Mono.error(new EntityNotFound(event.id(), Category.class)));
//                .map()
//        Category category = mapper.toEntity(event);
//        repo.save(category).block();
    }
}
