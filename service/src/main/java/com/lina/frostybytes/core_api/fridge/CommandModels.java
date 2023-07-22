package com.lina.frostybytes.core_api.fridge;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;
import java.util.UUID;

public record CommandModels() {
    public record CreateFridgeCommand(
            @TargetAggregateIdentifier
            @NotNull UUID fridgeId,
            @NotBlank String name
    ) {}
    public record UpdateFridgeCommand(
            @TargetAggregateIdentifier
            @NotNull UUID fridgeId,
            @NotBlank String name
    ) {}
    public record DeleteFridgeCommand(
            @TargetAggregateIdentifier
            @NotNull UUID fridgeId
    ) {}

    public record ItemFields(
            @NotBlank String name,
            @NotNull UUID categoryId,
            @NotNull LocalDate expirationDate,
            @NotNull LocalDate placedAt
    ) {}

    public record AddItemToFridgeCommand(
            @NotNull UUID itemId,
            @TargetAggregateIdentifier
            @NotNull UUID fridgeId,
            @Valid @NotNull ItemFields itemFields
    ) {}

    public record UpdateItemCommand(
            @NotNull UUID itemId,
            @TargetAggregateIdentifier
            @NotNull UUID fridgeId,
            @Valid @NotNull ItemFields itemFields
    ) {}

    public record DeleteItemCommand(
            @NotNull UUID itemId,
            @TargetAggregateIdentifier
            @NotNull UUID fridgeId
    ) {}
}
