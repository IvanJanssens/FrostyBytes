package com.lina.frostybytes.command.fridge;

import com.lina.frostybytes.core_api.fridge.CommandModels;
import com.lina.frostybytes.core_api.fridge.EventModels;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

import java.util.UUID;
@Slf4j
@AllArgsConstructor
@Getter
public class ItemAggregateMember {
    @EntityId
    UUID itemId;

    FridgeAggregate fridge;

    @CommandHandler
    private void handle(CommandModels.UpdateItemCommand command, ItemAggregateMapper mapper) {
        AggregateLifecycle.apply(mapper.toEvent(command));
    }

    @CommandHandler
    private void handle(CommandModels.DeleteItemCommand command, ItemAggregateMapper mapper) {
        AggregateLifecycle.apply(mapper.toEvent(command));
    }

    @EventSourcingHandler
    public void on(EventModels.ItemDeletedEvent event) {
        fridge.itemsById.remove(event.itemId());
        AggregateLifecycle.markDeleted();
    }
}
