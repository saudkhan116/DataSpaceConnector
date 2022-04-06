/*
 *  Copyright (c) 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.catalog.spi;

import org.eclipse.dataspaceconnector.catalog.spi.model.FederatedCatalogCacheQuery;
import org.eclipse.dataspaceconnector.spi.system.Feature;

import java.util.Collection;

/**
 * Registry where {@link CacheQueryAdapter} instances are stored and maintained.
 * {@link FederatedCatalogCacheQuery}s should be issued to the registry rather than to the {@link CacheQueryAdapter} directly!
 */
@Feature(CacheQueryAdapterRegistry.FEATURE)
public interface CacheQueryAdapterRegistry {
    String FEATURE = "edc:catalog:cache:query:registry";

    /**
     * Finds all instances of {@link CacheQueryAdapter} that was registered.
     *
     * @return The full list of query adapters that have been registered.
     */
    Collection<CacheQueryAdapter> getAllAdapters();

    /**
     * Registers a {@link CacheQueryAdapter} for a given storage type
     */
    void register(CacheQueryAdapter adapter);

    /**
     * Attempts to execute a query by forwarding it to all suitable {@link CacheQueryAdapter}s. It returns a query response with a status equal to
     * {@link QueryResponse.Status#ACCEPTED} if there was at least one adapter that could accept the query. If no suitable adapter was found, the status will be
     * {@link QueryResponse.Status#NO_ADAPTER_FOUND}. The actual result of the query, which could be mixed as some adapters might succeed, others might fail, can be
     * obtained from {@link QueryResponse#getOffers()} and {@link QueryResponse#getErrors()}. The earlier list returns an aggregated stream of
     * {@link org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset}, the latter contains a list of errors.
     * <p>
     * For example, when 1 of 5 adapters that receive the query times out, there will be results from 4, errors from 1.
     *
     * @param query The {@link FederatedCatalogCacheQuery}
     * @return a {@link QueryResponse} with {@link QueryResponse#getStatus()} that contains the result (Assets) and potentially errors.
     */
    QueryResponse executeQuery(FederatedCatalogCacheQuery query);
}
