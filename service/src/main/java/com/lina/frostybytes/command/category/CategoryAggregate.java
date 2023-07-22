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
class CategoryAggregate {

    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    private CategoryAggregate(
            CommandModels.createCategory command,
            CategoryAggregateMapper mapper
    ) {

        EventModels.CategoryAddedEvent event = mapper.toEvent(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private void handle(
            CommandModels.UpdateCategory command,
            CategoryAggregateMapper mapper
    ) {

        EventModels.CategoryUpdatedEvent event = mapper.toEvent(command);
        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    private void handle(
            CommandModels.DeleteCategory command,
            CategoryAggregateMapper mapper
    ) {

        EventModels.CategoryDeletedEvent event = mapper.toEvent(command);
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    private void on(EventModels.CategoryAddedEvent event) {
        this.id = event.id();
    }

    @EventSourcingHandler
    public void on(EventModels.CategoryDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
