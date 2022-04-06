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
 *       Microsoft Corporation - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.dataplane.spi.pipeline;

import org.eclipse.dataspaceconnector.dataplane.spi.result.TransferResult;
import org.eclipse.dataspaceconnector.spi.result.Result;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.DataFlowRequest;

import java.util.concurrent.CompletableFuture;

/**
 * Transfers data from a source to a sink.
 */
public interface PipelineService {

    /**
     * Returns true if this service can transfer the request.
     */
    boolean canHandle(DataFlowRequest request);

    /**
     * Returns true if the request is valid.
     */
    Result<Boolean> validate(DataFlowRequest request);

    /**
     * Transfers data from source to destination.
     */
    CompletableFuture<TransferResult> transfer(DataFlowRequest request);

    /**
     * Transfers data using the supplied data source.
     */
    CompletableFuture<TransferResult> transfer(DataSource source, DataFlowRequest request);

    /**
     * Transfers data using the supplied data sink.
     */
    CompletableFuture<TransferResult> transfer(DataSink sink, DataFlowRequest request);

    /**
     * Registers a factory for creating data sources.
     */
    void registerFactory(DataSourceFactory factory);

    /**
     * Registers a factory for creating data sinks.
     */
    void registerFactory(DataSinkFactory factory);
}
