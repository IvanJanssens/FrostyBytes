package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.extensions.reactor.queryhandling.gateway.DefaultReactorQueryGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.*;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitterImpl.UPDATE_TYPE;
import static org.axonframework.common.BuilderUtils.assertNonNull;

public class SubscribingQueryGatewayImpl extends DefaultReactorQueryGateway implements SubscribingQueryGateway {

    protected QueryBus queryBus;

    public SubscribingQueryGatewayImpl(Builder builder) {
        super(builder().queryBus(builder.queryBus));
        this.queryBus = builder.queryBus;
    }

    @Override
    public <Q, T extends WithId> Publisher<List<T>> subscriptionQueryList(Q query, Class<T> responseType) {
        AtomicReference<List<T>> buffer = new AtomicReference<>();
        SubscriptionQueryResult<QueryResponseMessage<List<T>>, SubscriptionQueryUpdateMessage<T>> subscriptionQuery = this.queryBus.subscriptionQuery(
                new GenericSubscriptionQueryMessage<>(
                        query,
                        ResponseTypes.multipleInstancesOf(responseType),
                        ResponseTypes.instanceOf(responseType)
                ));

        return Flux.combineLatest(
                subscriptionQuery.initialResult(),
                getSubsequentUpdates(responseType, subscriptionQuery),
                (initial, update) -> {
                    buffer.set(handleUpdate(buffer.get(), initial.getPayload(), update));
                    return buffer.get();
                }
            ).log();
    }

    @NotNull
    // Subsequent updates are merged with an INIT queryUpdateMessage to ensure combineLatest has an initial value.
    private <T extends WithId> Flux<SubscriptionQueryUpdateMessage<T>> getSubsequentUpdates(Class<T> responseType, SubscriptionQueryResult<QueryResponseMessage<List<T>>, SubscriptionQueryUpdateMessage<T>> subscriptionQuery) {
        return Flux.merge(
                Mono.just(new GenericSubscriptionQueryUpdateMessage<>(responseType, (T) null, Map.of(UPDATE_TYPE, CRUDQueryUpdateEmitterImpl.QueryUpdateType.INIT))),
                subscriptionQuery.updates()
        );
    }

    private <T extends WithId> List<T> handleUpdate(List<T> list, List<T> initialValue, SubscriptionQueryUpdateMessage<T> subsequentValue) {
        MetaData metaData = subsequentValue.getMetaData();
        T payload = subsequentValue.getPayload();
        CRUDQueryUpdateEmitterImpl.QueryUpdateType queryUpdateType = (CRUDQueryUpdateEmitterImpl.QueryUpdateType) metaData.get(UPDATE_TYPE);

        return switch (queryUpdateType) {
            case INIT -> initialValue;
            case ADD -> addToList(list, payload);
            case UPDATE -> updateInList(list, payload);
            case DELETE -> deleteFromList(list, payload);
        };
    }

    @NotNull
    private static <T extends WithId> List<T> addToList(List<T> list, T payload) {
        return Stream.concat(list.stream(), Stream.of(payload)).toList();
    }

    @NotNull
    private static <T extends WithId> List<T> updateInList(List<T> list, T payload) {
        return list.stream().map(item -> {
            if (item.id().equals(payload.id()))
                return payload;
            return item;
        }).toList();
    }

    @NotNull
    private static <T extends WithId> List<T> deleteFromList(List<T> list, T payload) {
        return list.stream().filter(item -> !item.id().equals(payload.id())).toList();
    }


    public static Builder subscribingBuilder() {
        return new Builder();
    }

    public static class Builder {

        private QueryBus queryBus;
        public Builder queryBus(QueryBus queryBus) {
            assertNonNull(queryBus, "QueryBus may not be null");
            this.queryBus = queryBus;
            return this;
        }

        public SubscribingQueryGatewayImpl build() {
            return new SubscribingQueryGatewayImpl(this);
        }
    }
}
