package com.lina.frostybytes.query.category;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class Category {

    @Id
    private UUID id;
    private String name;
    private String icon;
}
