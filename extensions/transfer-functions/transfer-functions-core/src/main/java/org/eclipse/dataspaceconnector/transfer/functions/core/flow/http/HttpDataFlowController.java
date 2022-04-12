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

package org.eclipse.dataspaceconnector.transfer.functions.core.flow.http;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.eclipse.dataspaceconnector.policy.model.Policy;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.response.StatusResult;
import org.eclipse.dataspaceconnector.spi.transfer.flow.DataFlowController;
import org.eclipse.dataspaceconnector.spi.types.TypeManager;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.DataFlowRequest;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.DataRequest;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import static org.eclipse.dataspaceconnector.spi.response.ResponseStatus.ERROR_RETRY;
import static org.eclipse.dataspaceconnector.spi.response.ResponseStatus.FATAL_ERROR;

/**
 * Connects with an HTTP(S) endpoint to initiate and manage a data transfer.
 * <p>
 * Endpoints must be idempotent. The endpoint should return HTTP code 200 to indicate the request was received. If the endpoint returns an HTTP code of 500 to 504, the request
 * will be retried; otherwise the request will be placed in the fatal error state.
 */
public class HttpDataFlowController implements DataFlowController {
    private static final MediaType JSON = MediaType.get("application/json");

    private final String transferEndpoint;
    private final Set<String> protocols;
    private final Supplier<OkHttpClient> clientSupplier;
    private final TypeManager typeManager;
    private final Monitor monitor;

    public HttpDataFlowController(HttpDataFlowConfiguration configuration) {
        this.transferEndpoint = configuration.getTransferEndpoint();
        this.protocols = configuration.getProtocols();
        this.clientSupplier = configuration.getClientSupplier();
        this.typeManager = configuration.getTypeManager();
        this.monitor = configuration.getMonitor();
    }

    @Override
    public boolean canHandle(DataRequest dataRequest, DataAddress contentAddress) {
        return protocols.contains(dataRequest.getDestinationType());
    }

    @Override
    public @NotNull StatusResult<String>  initiateFlow(DataRequest dataRequest, DataAddress contentAddress, Policy policy) {
        var dataFlowRequest = createRequest(dataRequest, contentAddress);
        var requestBody = RequestBody.create(typeManager.writeValueAsString(dataFlowRequest), JSON);
        var request = new Request.Builder().url(transferEndpoint).post(requestBody).build();
        try (var response = clientSupplier.get().newCall(request).execute()) {
            if (response.code() == 200) {
                return StatusResult.success("");
            } else if (response.code() >= 500 && response.code() <= 504) {
                // retry
                return StatusResult.failure(ERROR_RETRY, "Received error code: " + response.code());
            } else {
                // fatal error
                return StatusResult.failure(FATAL_ERROR, "Received fatal error code: " + response.code());
            }
        } catch (IOException e) {
            monitor.severe("Error invoking transfer function", e);
            return StatusResult.failure(ERROR_RETRY, e.getMessage());
        }
    }

    private DataFlowRequest createRequest(DataRequest dataRequest, DataAddress sourceAddress) {
        return DataFlowRequest.Builder.newInstance()
                .id(UUID.randomUUID().toString())
                .processId(dataRequest.getProcessId())
                .sourceDataAddress(sourceAddress)
                .destinationType(dataRequest.getDestinationType())
                .destinationDataAddress(dataRequest.getDataDestination())
                .build();
    }
}
