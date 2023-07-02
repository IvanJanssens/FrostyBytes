package com.lina.frostybytes.query.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @NotBlank
    private final String icon;
}
