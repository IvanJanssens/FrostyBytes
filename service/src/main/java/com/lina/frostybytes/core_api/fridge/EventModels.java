package com.lina.frostybytes.core_api.fridge;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

public record EventModels() {

    public record FridgeCreatedEvent(
            @NotNull UUID fridgeId,
            @NotBlank String name
    ) {}
    public record FridgeUpdatedEvent(
            @NotNull UUID fridgeId,
            @NotBlank String name
    ) {}
    public record FridgeDeletedEvent(
            @NotNull UUID fridgeId
    ) {}

    public record ItemFields(
            @NotBlank String name,
            @NotNull UUID categoryId,
            @NotNull LocalDate expirationDate,
            @NotNull LocalDate placedAt
    ) {}

    public record ItemAddedToFridgeEvent(
            @NotNull UUID itemId,
            @NotNull UUID fridgeId,
            @Valid @NotNull ItemFields itemFields
    ) {}

    public record ItemUpdatedEvent(
            @NotNull UUID itemid,
            @NotNull UUID fridgeId,
            @Valid @NotNull ItemFields itemFields
    ) {}
    public record ItemDeletedEvent(
            @NotNull UUID itemId
    ) {}
}
