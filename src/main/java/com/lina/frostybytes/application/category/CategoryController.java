package com.lina.frostybytes.application.category;

import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Controller
@RequestMapping("/graphql")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ReactorCommandGateway commandGateway;
    private final ReactorQueryGateway queryGateway;

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
    public Flux<QueryModels.Category> getCategories(
            DataFetchingEnvironment environment
    ) {
        return queryGateway.subscriptionQuery(
                new QueryModels.FetchCategoriesQuery(),
                QueryModels.Category.class
        );
    }
}
