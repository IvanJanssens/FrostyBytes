package com.lina.frostybytes.core_api.category;

import com.lina.frostybytes.config.validators.ValidSVG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.Duration;
import java.util.UUID;

public record CommandModels() {

    public record createCategory(
            @TargetAggregateIdentifier
            @NotNull UUID id,
            @NotBlank String name,
            @ValidSVG String icon,
            @NotNull Duration expiryPeriod
    ) {}

    public record UpdateCategory(
            @TargetAggregateIdentifier
            @NotNull UUID id,
            @NotBlank String name,
            @ValidSVG String icon,
            @NotNull Duration expiryPeriod
    ) {}

    public record DeleteCategory(
            @TargetAggregateIdentifier
            @NotNull UUID id
    ) {}
}
