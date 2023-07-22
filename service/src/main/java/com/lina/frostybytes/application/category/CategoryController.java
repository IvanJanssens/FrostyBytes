package com.lina.frostybytes.application.category;

import com.lina.frostybytes.config.axon.extensions.SubscribingQueryGateway;
import com.lina.frostybytes.config.validators.ValidSVG;
import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.time.Duration;
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
    public Mono<UUID> createCategory(
            @Argument @NotBlank String name,
            @Argument @ValidSVG String icon,
            @Argument @NotNull Duration expiryPeriod

    ) {
        UUID id = UUID.randomUUID();
        return commandGateway
                .send(new CommandModels.createCategory(id, name, icon, expiryPeriod))
                .thenReturn(id);
    }

    @MutationMapping
    public Mono<UUID> updateCategory(
            @Argument @NotNull UUID id,
            @Argument @NotBlank String name,
            @Argument @ValidSVG String icon,
            @Argument @NotNull Duration expiryPeriod
    ) {
        return commandGateway
                .send(new CommandModels.UpdateCategory(id, name, icon, expiryPeriod))
                .thenReturn(id);
    }

    @MutationMapping
    public Mono<UUID> deleteCategory(
            @Argument @NotNull UUID id
    ) {
        return commandGateway
                .send(new CommandModels.DeleteCategory(id))
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
    public Publisher<QueryModels.Category> getCategory(@Argument @NotNull UUID id) {
        return queryGateway.subscriptionQueryItem(
                new QueryModels.GetCategoryQuery(id),
                QueryModels.Category.class
        );
    }
}
