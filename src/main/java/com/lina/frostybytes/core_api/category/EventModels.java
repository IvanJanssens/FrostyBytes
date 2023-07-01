package com.lina.frostybytes.core_api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EventModels() {
    public record CategoryCreatedEvent(
            @NotNull UUID id,
            @NotBlank String name,
            @NotBlank String icon
    ) {}

    public record CategoryUpdatedEvent(
            @NotNull UUID id,
            @NotBlank String name,
            @NotBlank String icon
    ) {}

    public record CategoryDeletedEvent(
            @NotNull UUID id
    ) {}
}
