package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.fridge.QueryModels;
import com.lina.frostybytes.query.item.ItemEntity;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-08T14:27:31+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity toEntity(EventModels.CategoryAddedEvent event, Set<ItemEntity> items) {
        if ( event == null && items == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;
        if ( event != null ) {
            id = event.id();
            name = event.name();
            icon = event.icon();
        }
        Set<ItemEntity> items1 = null;
        Set<ItemEntity> set = items;
        if ( set != null ) {
            items1 = new LinkedHashSet<ItemEntity>( set );
        }

        CategoryEntity categoryEntity = new CategoryEntity( id, name, icon, items1 );

        return categoryEntity;
    }

    @Override
    public CategoryEntity toEntity(CategoryEntity entity, EventModels.CategoryUpdatedEvent event) {
        if ( entity == null && event == null ) {
            return null;
        }

        UUID id = null;
        Set<ItemEntity> items = null;
        if ( entity != null ) {
            id = entity.getId();
            Set<ItemEntity> set = entity.getItems();
            if ( set != null ) {
                items = new LinkedHashSet<ItemEntity>( set );
            }
        }
        String name = null;
        String icon = null;
        if ( event != null ) {
            name = event.name();
            icon = event.icon();
        }

        CategoryEntity categoryEntity = new CategoryEntity( id, name, icon, items );

        return categoryEntity;
    }

    @Override
    public com.lina.frostybytes.core_api.category.QueryModels.Category toModel(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;
        Set<QueryModels.Item> items = null;

        id = entity.getId();
        name = entity.getName();
        icon = entity.getIcon();
        items = itemEntitySetToItemsSet( entity.getItems() );

        boolean deleted = false;

        com.lina.frostybytes.core_api.category.QueryModels.Category category = new com.lina.frostybytes.core_api.category.QueryModels.Category( id, name, icon, items, deleted );

        return category;
    }

    @Override
    public com.lina.frostybytes.core_api.category.QueryModels.Category toDeleted(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;
        Set<QueryModels.Item> items = null;

        id = entity.getId();
        name = entity.getName();
        icon = entity.getIcon();
        items = itemEntitySetToItemsSet( entity.getItems() );

        boolean deleted = true;

        com.lina.frostybytes.core_api.category.QueryModels.Category category = new com.lina.frostybytes.core_api.category.QueryModels.Category( id, name, icon, items, deleted );

        return category;
    }

    protected QueryModels.Item itemEntityToItems(ItemEntity itemEntity) {
        if ( itemEntity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        LocalDate expirationDate = null;
        LocalDate placedAt = null;

        id = itemEntity.getId();
        name = itemEntity.getName();
        expirationDate = itemEntity.getExpirationDate();
        placedAt = itemEntity.getPlacedAt();

        com.lina.frostybytes.core_api.category.QueryModels.Category category = null;

        QueryModels.Item item = new QueryModels.Item( id, name, category, expirationDate, placedAt );

        return item;
    }

    protected Set<QueryModels.Item> itemEntitySetToItemsSet(Set<ItemEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<QueryModels.Item> set1 = new LinkedHashSet<QueryModels.Item>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ItemEntity itemEntity : set ) {
            set1.add( itemEntityToItems( itemEntity ) );
        }

        return set1;
    }
}
