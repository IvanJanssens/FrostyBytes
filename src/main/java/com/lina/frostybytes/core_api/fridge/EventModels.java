package com.lina.frostybytes.core_api.fridge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EventModels() {

    public record FridgeCreatedEvent(
            @NotNull UUID id,
            @NotBlank String name
    ) {}
    public record FridgeUpdatedEvent(
            @NotNull UUID id,
            @NotBlank String name
    ) {}
    public record FridgeDeletedEvent(
            @NotNull UUID id
    ) {}
}
