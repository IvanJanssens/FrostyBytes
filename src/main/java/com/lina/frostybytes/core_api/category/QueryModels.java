package com.lina.frostybytes.core_api.category;

import com.lina.frostybytes.config.validators.ValidSVG;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record QueryModels() {

    public record FetchCategoriesQuery() {}

    public record FetchCategoryQuery(UUID id) {}

    public record Category(
            @NotNull
            UUID id,
            @NotBlank
            String name,
            @ValidSVG
            String icon
    ) {
    }
}
