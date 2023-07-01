package com.lina.frostybytes.command.fridge;

import com.lina.frostybytes.command.category.CategoryAggregateMapper;
import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
 class FridgeAggregate {
    @AggregateIdentifier
    private UUID id;

    @CommandHandler
    public FridgeAggregate(CommandModels.CreateFridgeCommand command,   FridgeAggregate mapper) {
        AggregateLifecycle.apply(new EventModels.FridgeCreatedEvent(command.id(), command.name()));
    }

    @EventSourcingHandler
    private void on(EventModels.FridgeCreatedEvent event) {
        this.id = event.id();
    }

    @CommandHandler
    public void handle(CommandModels.UpdateFridgeCommand command) {
        AggregateLifecycle.apply(new EventModels.FridgeUpdatedEvent(id, command.name()));
    }

    @CommandHandler
    public void handle(CommandModels.DeleteFridgeCommand command) {
        AggregateLifecycle.apply(new EventModels.FridgeDeletedEvent(id));
    }

    @EventSourcingHandler
    public void on(EventModels.FridgeDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }
}
