package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Predicate;

public interface CRUDQueryUpdateEmitter extends QueryUpdateEmitter {

    <Q, T> void emitAdd(@Nonnull Class<Q> queryType, @Nonnull Predicate<? super Q> filterWhenToEmit, @NotNull T valueToEmit);
    <Q, T> void emitUpdate(@Nonnull Class<Q> queryType, @Nonnull Predicate<? super Q> filterWhenToEmit, @NotNull T valueToEmit);

    <Q, T> void emitDelete(@Nonnull Class<Q> queryType, @Nonnull Predicate<? super Q> filterWhenToEmit, @NotNull T valueToEmit);
}
