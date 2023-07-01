package com.lina.frostybytes.core_api.fridge;

import com.lina.frostybytes.config.validators.ValidSVG;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record QueryModels() {

    public record Fridge(
            @NotNull
            UUID id,
            @NotBlank
            String name,
            @NotEmpty
            List<Items> items
    ) {
    }

    public record Items(
            @NotNull
            UUID id,
            @NotBlank
            String name,
            @NotNull
            @Valid
            com.lina.frostybytes.core_api.category.QueryModels.Category category,
            @NotNull
            LocalDateTime expirationDate,
            @NotBlank
            LocalDateTime placedAt
    ) {
    }

}
