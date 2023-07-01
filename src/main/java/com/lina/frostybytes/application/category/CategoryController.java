package com.lina.frostybytes.application.category;

import com.lina.frostybytes.core_api.fridge.QueryModels;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/graphql")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final ReactorCommandGateway commandGateway;

    @MutationMapping
    public Mono<QueryModels.Fridge> createCategory(@Argument String name) {
        return Mono.empty();
    }

//
//    @MutationMapping
//    public Mono<Fridge> updateFridge(@Argument String id, @Argument String name) {
//        return fridgeService.updateFridge(id, name);
//    }
//
//    @MutationMapping
//    public Mono<Boolean> deleteFridge(@Argument String id) {
//        return fridgeService.deleteFridge(id);
//    }
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
