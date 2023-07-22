package com.lina.frostybytes.query.item;

import com.lina.frostybytes.core_api.fridge.CommandModels;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@Document(collection = "items")
public class ItemEntity {

    @Id
    private final UUID id;
    @NotBlank
    private final String name;
    @NotNull
    private final LocalDate expirationDate;
    @NotNull
    private final LocalDate placedAt;

    @Transient
    private static final boolean deleted = false;
}
