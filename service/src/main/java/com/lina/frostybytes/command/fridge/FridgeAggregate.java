package com.lina.frostybytes.command.fridge;

import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.command.ForwardMatchingInstances;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Aggregate
@NoArgsConstructor
class FridgeAggregate {
    @AggregateIdentifier
    private UUID fridgeId;

    @AggregateMember(eventForwardingMode = ForwardMatchingInstances.class)
    Map<UUID, ItemAggregateMember> itemsById = new ConcurrentHashMap<>();

    @CommandHandler
    private FridgeAggregate(CommandModels.CreateFridgeCommand command, FridgeAggregateMapper mapper) {
        AggregateLifecycle.apply(mapper.toEvent(command));
    }

    @EventSourcingHandler
    private void on(EventModels.FridgeCreatedEvent event) {
        this.fridgeId = event.fridgeId();
    }

    @CommandHandler
    private void handle(CommandModels.UpdateFridgeCommand command, FridgeAggregateMapper mapper) {
        AggregateLifecycle.apply(mapper.toEvent(command));
    }

    @CommandHandler
    private void handle(CommandModels.DeleteFridgeCommand command, FridgeAggregateMapper mapper) {
        AggregateLifecycle.apply(mapper.toEvent(command));
    }

    @EventSourcingHandler
    public void on(EventModels.FridgeDeletedEvent event) {
        AggregateLifecycle.markDeleted();
    }

    @CommandHandler
    private void handle(CommandModels.AddItemToFridgeCommand command, ItemAggregateMapper mapper) {
        AggregateLifecycle.apply(mapper.toEvent(command));
    }

    @EventSourcingHandler
    public void on(EventModels.ItemAddedToFridgeEvent event) {
        this.itemsById.put(event.itemId(), new ItemAggregateMember(event.itemId(), this));
    }
}
