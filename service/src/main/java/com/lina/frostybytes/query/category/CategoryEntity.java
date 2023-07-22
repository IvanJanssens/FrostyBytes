package com.lina.frostybytes.query.category;

import com.lina.frostybytes.config.validators.ValidSVG;
import com.lina.frostybytes.query.item.ItemEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@Document(collection = "categories")
public class CategoryEntity {

    @Id
    private final UUID id;
    @NotBlank
    private final String name;
    @ValidSVG
    private final String icon;
    @NotNull
    private final Duration expiryPeriod;

    private final Set<ItemEntity> items;

    @Transient
    private static final boolean deleted = false;
}
