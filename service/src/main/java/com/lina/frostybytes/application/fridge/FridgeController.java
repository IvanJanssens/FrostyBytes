package com.lina.frostybytes.application.fridge;

import com.lina.frostybytes.config.axon.extensions.SubscribingQueryGateway;
import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
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

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/graphql")
@RequiredArgsConstructor
@Slf4j
public class FridgeController {

    private final ReactorCommandGateway commandGateway;
    private final SubscribingQueryGateway reactorQueryGateway;



    @MutationMapping
    public Mono<UUID> createFridge(@Argument @NotBlank String name) {
        UUID newId = UUID.randomUUID();
        return commandGateway.send(new CommandModels.CreateFridgeCommand(newId, name))
                .thenReturn(newId);
    }


    @MutationMapping
    public Mono<UUID> updateFridge(@Argument @NotNull UUID id,
                                   @Argument @NotBlank String name) {
        return commandGateway.send(new CommandModels.UpdateFridgeCommand(id, name))
                .thenReturn(id);
    }

    @MutationMapping
    public Mono<UUID> deleteFridge(@Argument @NotNull UUID id) {
        return commandGateway.send(new CommandModels.DeleteFridgeCommand(id))
                .thenReturn(id);
    }

    @SubscriptionMapping
    public Publisher<List<QueryModels.Fridge>> getFridges(@Argument int page, @Argument int pageSize) {
        return reactorQueryGateway.subscriptionQueryList(
                    new QueryModels.GetAllFridgesQuery(page, pageSize),
                    QueryModels.Fridge.class
                );
    }

    @SubscriptionMapping
    public Publisher<QueryModels.Fridge> getFridge(@Argument UUID id) {
        return reactorQueryGateway.subscriptionQueryItem(
                new QueryModels.GetFridgeQuery(id),
                QueryModels.Fridge.class
        );
    }

    @MutationMapping
    public Mono<UUID> addItemToFridge(
            @Argument UUID fridgeId,
            @Argument ItemInput itemInput
    ) {
        UUID newId = UUID.randomUUID();
        return commandGateway
                .send(new CommandModels.AddItemToFridgeCommand(
                        newId,
                        fridgeId,
                        new CommandModels.ItemFields(itemInput.name(), itemInput.categoryId(), itemInput.expirationDate(), itemInput.placedAt()))
                )
                .thenReturn(newId);
    }

    @MutationMapping
    public Mono<UUID> updateItem(
            @Argument @NotNull UUID itemId,
            @Argument @NotNull UUID fridgeId,
            @Argument ItemInput itemInput
    ) {
        return commandGateway
                .send(new CommandModels.UpdateItemCommand(
                        itemId,
                        fridgeId,
                        new CommandModels.ItemFields(itemInput.name(), itemInput.categoryId(), itemInput.expirationDate(), itemInput.placedAt()))
                )
                .thenReturn(itemId);
    }

    @MutationMapping
    public Mono<UUID> deleteItemFromFridge(
            @Argument @NotNull UUID itemId,
            @Argument @NotNull UUID fridgeId
    ) {
        return commandGateway
            .send(new CommandModels.DeleteItemCommand(itemId, fridgeId))
            .thenReturn(itemId);
    }
}
