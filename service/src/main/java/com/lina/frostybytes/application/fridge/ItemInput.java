package com.lina.frostybytes.application.fridge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ItemInput(
        @NotBlank String name,
        @NotNull UUID categoryId,
        @NotNull LocalDate expirationDate,
        @NotNull LocalDate placedAt
) {}
