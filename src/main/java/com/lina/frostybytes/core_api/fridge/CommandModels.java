package com.lina.frostybytes.core_api.fridge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record CommandModels() {
    public record CreateFridgeCommand(
            @TargetAggregateIdentifier
            @NotNull UUID id,
            @NotBlank String name
    ) {}
    public record UpdateFridgeCommand(
            @TargetAggregateIdentifier
            @NotNull UUID id,
            @NotBlank String name
    ) {}
    public record DeleteFridgeCommand(
            @TargetAggregateIdentifier
            @NotNull UUID id
    ) {}
}
