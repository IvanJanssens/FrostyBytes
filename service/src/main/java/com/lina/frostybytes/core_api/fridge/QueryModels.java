package com.lina.frostybytes.core_api.fridge;

import com.lina.frostybytes.config.axon.extensions.WithId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record QueryModels() {
    public record GetAllFridgesQuery(int page, int size) {}
    public record GetFridgeQuery(UUID id) {}
    public record GetAllItemsQuery(int page, int size) {}
    public record GetItemQuery(UUID id) {}

    public record Fridge(
            @NotNull
            UUID id,
            @NotBlank
            String name,
            Set<Item> items,

            boolean deleted
    ) implements WithId {
    }

    public record Item(
            @NotNull
            UUID id,
            @NotBlank
            String name,
            @NotNull
            @Valid
            com.lina.frostybytes.core_api.category.QueryModels.Category category,
            @NotNull
            LocalDate expirationDate,
            @NotBlank
            LocalDate placedAt
    ) {
    }

}
