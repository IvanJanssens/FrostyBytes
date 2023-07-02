package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.extensions.reactor.queryhandling.gateway.DefaultReactorQueryGateway;
import org.axonframework.messaging.MetaData;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.*;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

import static com.lina.frostybytes.config.axon.extensions.CRUDQueryUpdateEmitterImpl.UPDATE_EMITTER_TYPE;

@Component
 class SubscribingQueryGatewayImpl extends DefaultReactorQueryGateway implements SubscribingQueryGateway {

    protected QueryBus queryBus;

    public SubscribingQueryGatewayImpl(QueryBus queryBus) {
        super(builder().queryBus(queryBus));
        this.queryBus = queryBus;
    }

    @Override
    public <Q, T extends WithId> Publisher<List<T>> subscriptionQueryList(Q query, Class<T> responseType) {
        AtomicReference<List<T>> resultList = new AtomicReference<>();
        SubscriptionQueryResult<QueryResponseMessage<List<T>>, SubscriptionQueryUpdateMessage<T>> subscriptionQueryResult = this.queryBus.subscriptionQuery(
                new GenericSubscriptionQueryMessage<>(
                        query,
                        ResponseTypes.multipleInstancesOf(responseType),
                        ResponseTypes.instanceOf(responseType)
                ));

        return Flux.combineLatest(
                subscriptionQueryResult.initialResult(),
                getSubsequentUpdatesWithInitialValue(responseType, subscriptionQueryResult),
                (initialQueryResultList, subsequentQueryResult) -> updateResultList(resultList, initialQueryResultList, subsequentQueryResult)
            ).log();
    }

    private <T extends WithId> List<T> updateResultList(
            AtomicReference<List<T>> resultList,
            QueryResponseMessage<List<T>> initialQueryResultList,
            SubscriptionQueryUpdateMessage<T> subsequentQueryResult) {
        List<T> updatedQueryList = updateResultList(resultList.get(), initialQueryResultList.getPayload(), subsequentQueryResult);
        resultList.set(updatedQueryList);
        return resultList.get();
    }

    @NotNull
     private <T extends WithId> Flux<SubscriptionQueryUpdateMessage<T>> getSubsequentUpdatesWithInitialValue(Class<T> responseType, SubscriptionQueryResult<QueryResponseMessage<List<T>>, SubscriptionQueryUpdateMessage<T>> subscriptionQuery) {
        return Flux.merge(
                Mono.just(new GenericSubscriptionQueryUpdateMessage<>(responseType, (T) null, Map.of(UPDATE_EMITTER_TYPE, CRUDQueryUpdateEmitterImpl.QueryUpdateEmitterType.INIT))),
                subscriptionQuery.updates()
        );
    }

    private <T extends WithId> List<T> updateResultList(List<T> resultList, List<T> initialQueryResultList, SubscriptionQueryUpdateMessage<T> subsequentQueryResult) {
        MetaData metaData = subsequentQueryResult.getMetaData();
        T payload = subsequentQueryResult.getPayload();
        CRUDQueryUpdateEmitterImpl.QueryUpdateEmitterType queryUpdateEmitterType = (CRUDQueryUpdateEmitterImpl.QueryUpdateEmitterType) metaData.get(UPDATE_EMITTER_TYPE);

        return switch (queryUpdateEmitterType) {
            case INIT -> initialQueryResultList;
            case ADD -> addToList(resultList, payload);
            case UPDATE -> updateInList(resultList, payload);
            case DELETE -> deleteFromList(resultList, payload);
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
}
