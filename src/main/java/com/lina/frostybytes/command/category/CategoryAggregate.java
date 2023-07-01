package com.lina.frostybytes.command.category;

import com.lina.frostybytes.core_api.category.CommandModels;
import com.lina.frostybytes.core_api.category.EventModels;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
@Slf4j
@NoArgsConstructor
public class CategoryAggregate {

    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    private CategoryAggregate(
            CommandModels.addCategory command,
            CategoryAggregateMapper mapper
    ) {

        EventModels.CategoryCreatedEvent event = mapper.toEvent(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private CategoryAggregate(
            CommandModels.updateCategory command,
            CategoryAggregateMapper mapper
    ) {

        EventModels.CategoryUpdatedEvent event = mapper.toEvent(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private CategoryAggregate(
            CommandModels.deleteCategory command,
            CategoryAggregateMapper mapper
    ) {

        EventModels.CategoryDeletedEvent event = mapper.toEvent(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    private void on(EventModels.CategoryCreatedEvent event) {
        this.id = event.id();
    }
}
