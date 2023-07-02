package com.lina.frostybytes.core_api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public record CommandModels() {

    public record addCategory(
            @TargetAggregateIdentifier
            @NotNull UUID id,
            @NotBlank String name,
            @NotBlank String icon
    ) {}

    public record updateCategory(
            @TargetAggregateIdentifier
            @NotNull UUID id,
            @NotBlank String name,
            @NotBlank String icon
    ) {}

    public record deleteCategory(
            @TargetAggregateIdentifier
            @NotNull UUID id
    ) {}
}
