package com.lina.frostybytes.query.category;

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
public class Category {

    @Id
    private UUID id;
    private String name;
    private String icon;
}
