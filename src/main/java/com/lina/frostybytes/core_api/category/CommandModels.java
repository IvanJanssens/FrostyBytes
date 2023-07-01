package com.lina.frostybytes.core_api.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.axonframework.modelling.command.AggregateIdentifier;

import java.util.UUID;

public record CommandModels() {

    public record addCategory(
            @AggregateIdentifier UUID id,
            @NotBlank String name,
            @NotBlank String icon
    ) {}

    public record updateCategory(
            @AggregateIdentifier @NotNull UUID id,
            @NotBlank String name,
            @NotBlank String icon
    ) {}

    public record deleteCategory(
            @AggregateIdentifier @NotNull UUID id
    ) {}
}
