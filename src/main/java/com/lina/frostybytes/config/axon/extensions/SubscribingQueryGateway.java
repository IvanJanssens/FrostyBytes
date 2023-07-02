package com.lina.frostybytes.config.axon.extensions;

import org.axonframework.extensions.reactor.queryhandling.gateway.ReactorQueryGateway;
import org.reactivestreams.Publisher;

import java.util.List;

public interface SubscribingQueryGateway extends ReactorQueryGateway {

    <Q, T extends WithId> Publisher<List<T>> subscriptionQueryList(Q query, Class<T> responseType);
}
