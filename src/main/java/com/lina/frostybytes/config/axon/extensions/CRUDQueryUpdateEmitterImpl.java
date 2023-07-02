package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.queryhandling.GenericSubscriptionQueryUpdateMessage;
import org.axonframework.queryhandling.SimpleQueryUpdateEmitter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.function.Predicate;

public class CRUDQueryUpdateEmitterImpl extends SimpleQueryUpdateEmitter implements CRUDQueryUpdateEmitter {

    public static final String UPDATE_TYPE = "updateType";
    public enum QueryUpdateType {
        INIT,
        ADD,
        UPDATE,
        DELETE
    }
    public CRUDQueryUpdateEmitterImpl(Builder builder) {
        super(builder);
    }

    @Override
    public <Q, U> void emitAdd(@NotNull Class<Q> queryType, @NotNull Predicate<? super Q> filter, @Nullable U update) {
        this.emit(queryType, filter, new GenericSubscriptionQueryUpdateMessage<>((Class<U>) update.getClass(), update, Map.of(UPDATE_TYPE, QueryUpdateType.ADD)));
    }

    @Override
    public <Q, U> void emitUpdate(@NotNull Class<Q> queryType, @NotNull Predicate<? super Q> filter, @Nullable U update) {
        this.emit(queryType, filter, new GenericSubscriptionQueryUpdateMessage<>((Class<U>) update.getClass(), update, Map.of(UPDATE_TYPE, QueryUpdateType.UPDATE)));
    }

    @Override
    public <Q, U> void emitDelete(@NotNull Class<Q> queryType, @NotNull Predicate<? super Q> filter, @Nullable U update) {
        this.emit(queryType, filter, new GenericSubscriptionQueryUpdateMessage<>((Class<U>) update.getClass(), update, Map.of(UPDATE_TYPE, QueryUpdateType.DELETE)));
    }
}
