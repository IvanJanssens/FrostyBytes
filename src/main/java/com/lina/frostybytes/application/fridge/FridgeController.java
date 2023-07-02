package com.lina.frostybytes.application.fridge;

import com.lina.frostybytes.config.axon.extensions.SubscribingQueryGateway;
import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

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
        return reactorQueryGateway.subscriptionQuery(
                new QueryModels.GetFridgeQuery(id),
                ResponseTypes.instanceOf(QueryModels.Fridge.class)
            );

    }
//
//    @MutationMapping
//    public Mono<Fridge> addItemToFridge(@Argument String fridgeId,
//                                        @Argument String name,
//                                        @Argument String categoryId,
//                                        @Argument String expirationDate,
//                                        @Argument String placedAt) {
//        return fridgeService.addItemToFridge(fridgeId, name, categoryId, expirationDate, placedAt);
//    }
//
//    @MutationMapping
//    public Mono<Fridge> updateItemInFridge(@Argument String fridgeId,
//                                           @Argument String itemId,
//                                           @Argument String name,
//                                           @Argument String categoryId,
//                                           @Argument String expirationDate,
//                                           @Argument String placedAt) {
//        return fridgeService.updateItemInFridge(fridgeId, itemId, name, categoryId, expirationDate, placedAt);
//    }
//
//    @MutationMapping
//    public Mono<Fridge> deleteItemFromFridge(@Argument String fridgeId,
//                                             @Argument String itemId) {
//        return fridgeService.deleteItemFromFridge(fridgeId, itemId);
//    }
//
//    @MutationMapping
//    public Mono<Fridge> addCategory(@Argument String fridgeId,
//                                    @Argument String name,
//                                    @Argument String categoryId,
//                                    @Argument String expirationDate,
//                                    @Argument String placedAt) {
//        return fridgeService.addCategory(fridgeId, name, categoryId, expirationDate, placedAt);
//    }
//
//    @MutationMapping
//    public Mono<Fridge> updateCategory(@Argument String fridgeId,
//                                       @Argument String itemId,
//                                       @Argument String name,
//                                       @Argument String categoryId,
//                                       @Argument String expirationDate,
//                                       @Argument String placedAt) {
//        return fridgeService.updateCategory(fridgeId, itemId, name, categoryId, expirationDate, placedAt);
//    }
//
//    @MutationMapping
//    public Mono<Fridge> deleteCategory(@Argument String fridgeId,
//                                       @Argument String itemId) {
//        return fridgeService.deleteCategory(fridgeId, itemId);
//    }
//
//    @QueryMapping
//    public Flux<Fridge> getAllFridges(DataFetchingEnvironment environment) {
//        return fridgeService.getAllFridges();
//    }
//
//    @BatchMapping
//    public Flux<Items> batchLoadItems(List<String> itemIds, DataFetchingEnvironment environment){
//
//    }
}
