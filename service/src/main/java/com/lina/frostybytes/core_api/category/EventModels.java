package com.lina.frostybytes.core_api.category;

import com.lina.frostybytes.config.validators.ValidSVG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Duration;
import java.util.UUID;

public record EventModels() {
    public record CategoryAddedEvent(
            @NotNull UUID id,
            @NotBlank String name,
            @ValidSVG
            String icon,
            @NotNull Duration expiryPeriod
    ) {}

    public record CategoryUpdatedEvent(
            @NotNull UUID id,
            @NotBlank String name,
            @ValidSVG String icon,
            @NotNull Duration expiryPeriod
    ) {}

    public record CategoryDeletedEvent(
            @NotNull UUID id
    ) {}
}
