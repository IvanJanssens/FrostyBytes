package com.lina.frostybytes.core_api.category;

import com.lina.frostybytes.config.axon.extensions.WithId;
import com.lina.frostybytes.config.validators.ValidSVG;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record QueryModels() {

    public record GetCategoriesQuery() {}

    public record GetCategoryQuery(UUID id) {}

    public record Category(
            @NotNull
            UUID id,
            @NotBlank
            String name,
            @ValidSVG
            String icon
    ) implements WithId {
    }
}
