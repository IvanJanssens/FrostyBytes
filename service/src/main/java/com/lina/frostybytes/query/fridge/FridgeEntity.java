package com.lina.frostybytes.query.fridge;

import com.lina.frostybytes.query.item.ItemEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@AllArgsConstructor
@Document(collection = "fridges")
class FridgeEntity {

   @Id
   private final UUID id;
   @NotBlank
   private final String name;

   private final Set<ItemEntity> items;

   @Transient
   private static final boolean deleted = false;
}
