package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import com.lina.frostybytes.core_api.category.QueryModels;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-02T19:13:14+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryEntity toEntity(EventModels.CategoryAddedEvent event) {
        if ( event == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;

        id = event.id();
        name = event.name();
        icon = event.icon();

        CategoryEntity categoryEntity = new CategoryEntity( id, name, icon );

        return categoryEntity;
    }

    @Override
    public CategoryEntity toEntity(EventModels.CategoryUpdatedEvent event) {
        if ( event == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;

        id = event.id();
        name = event.name();
        icon = event.icon();

        CategoryEntity categoryEntity = new CategoryEntity( id, name, icon );

        return categoryEntity;
    }

    @Override
    public QueryModels.Category toModel(CategoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String icon = null;

        id = entity.getId();
        name = entity.getName();
        icon = entity.getIcon();

        QueryModels.Category category = new QueryModels.Category( id, name, icon );

        return category;
    }
}
