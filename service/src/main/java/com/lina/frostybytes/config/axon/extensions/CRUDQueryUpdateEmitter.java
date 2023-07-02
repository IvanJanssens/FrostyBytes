package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.queryhandling.QueryUpdateEmitter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Predicate;

public interface CRUDQueryUpdateEmitter extends QueryUpdateEmitter {

    <Q, U> void emitAdd(@Nonnull Class<Q> queryType, @Nonnull Predicate<? super Q> filter, @Nullable U update);
    <Q, U> void emitUpdate(@Nonnull Class<Q> queryType, @Nonnull Predicate<? super Q> filter, @Nullable U update);

    <Q, U> void emitDelete(@Nonnull Class<Q> queryType, @Nonnull Predicate<? super Q> filter, @Nullable U update);
}
