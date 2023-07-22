package com.lina.frostybytes.application.category;

import com.lina.frostybytes.config.axon.extensions.SubscribingQueryGateway;
import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.*;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.concurrent.Queues;

import javax.management.Query;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/graphql")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ReactorCommandGateway commandGateway;
    private final SubscribingQueryGateway queryGateway;

    @MutationMapping
    public Mono<UUID> addCategory(
            @Argument String name,
            @Argument String icon

    ) {
        UUID id = UUID.randomUUID();
        return commandGateway
                .send(new CommandModels.addCategory(id, name, icon))
                .thenReturn(id);
    }

    @MutationMapping
    public Mono<UUID> updateCategory(
            @Argument UUID id,
            @Argument String name,
            @Argument String icon
    ) {
        return commandGateway
                .send(new CommandModels.updateCategory(id, name, icon))
                .thenReturn(id);
    }

    @MutationMapping
    public Mono<UUID> deleteCategory(
            @Argument UUID id
    ) {
        return commandGateway
                .send(new CommandModels.deleteCategory(id))
                .thenReturn(id);
    }

    @SubscriptionMapping
    public Publisher<List<QueryModels.Category>> getCategories() {
        return queryGateway.subscriptionQueryList(
                    new QueryModels.GetCategoriesQuery(),
                    QueryModels.Category.class
        );
    }

    @SubscriptionMapping
    public Publisher<QueryModels.Category> getCategory(@Argument UUID id) {
        return queryGateway.subscriptionQueryItem(
                new QueryModels.GetCategoryQuery(id),
                QueryModels.Category.class
        );
    }
}
