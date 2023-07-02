package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.queryhandling.GenericSubscriptionQueryUpdateMessage;
import org.axonframework.queryhandling.SimpleQueryUpdateEmitter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Predicate;

@Component
 class CRUDQueryUpdateEmitterImpl extends SimpleQueryUpdateEmitter implements CRUDQueryUpdateEmitter {

    public static final String UPDATE_EMITTER_TYPE = "updateEmitterType";
    public enum QueryUpdateEmitterType {
        INIT,
        ADD,
        UPDATE,
        DELETE
    }
    public CRUDQueryUpdateEmitterImpl() {
        super(SimpleQueryUpdateEmitter.builder());
    }

    @Override
    public <Q, T> void emitAdd(@NotNull Class<Q> queryType, @NotNull Predicate<? super Q> filterWhenToEmit, @NotNull T valueToEmit) {
        this.emit(queryType,
                filterWhenToEmit,
                new GenericSubscriptionQueryUpdateMessage<>(
                        (Class<T>) valueToEmit.getClass(),
                        valueToEmit,
                        Map.of(UPDATE_EMITTER_TYPE, QueryUpdateEmitterType.ADD)
                )
        );
    }

    @Override
    public <Q, T> void emitUpdate(@NotNull Class<Q> queryType, @NotNull Predicate<? super Q> filterWhenToEmit, @NotNull T valueToEmit) {
        this.emit(queryType, filterWhenToEmit,
                new GenericSubscriptionQueryUpdateMessage<>(
                        (Class<T>) valueToEmit.getClass(),
                        valueToEmit, Map.of(UPDATE_EMITTER_TYPE,
                        QueryUpdateEmitterType.UPDATE)
                )
        );
    }

    @Override
    public <Q, T> void emitDelete(@NotNull Class<Q> queryType, @NotNull Predicate<? super Q> filterWhenToEmit, @NotNull T valueToEmit) {
        this.emit(
                queryType,
                filterWhenToEmit,
                new GenericSubscriptionQueryUpdateMessage<>(
                        (Class<T>) valueToEmit.getClass(),
                        valueToEmit,
                        Map.of(UPDATE_EMITTER_TYPE, QueryUpdateEmitterType.DELETE)
                )
        );
    }
}
