package com.lina.frostybytes.query.category;

import com.lina.frostybytes.core_api.category.EventModels;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-01T18:35:46+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(EventModels.CategoryAddedEvent event) {
        if ( event == null ) {
            return null;
        }

        Category category = new Category();

        return category;
    }

    @Override
    public void updateEntity(Category entity, EventModels.CategoryUpdatedEvent event) {
        if ( event == null ) {
            return;
        }
    }
}
