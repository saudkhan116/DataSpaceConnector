/*
 *  Copyright (c) 2022 Amadeus
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Amadeus - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.transfer.dataplane.spi.proxy;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * POJO class for requesting creation of a proxy using Data Plane Public API that wraps a data source.
 */
public class DataPlaneTransferProxyCreationRequest {
    private final String id;
    private final DataAddress contentAddress;
    private final String proxyEndpoint;
    private final String contractId;
    private final Map<String, String> properties;

    private DataPlaneTransferProxyCreationRequest(String id, DataAddress contentAddress, String proxyEndpoint, String contractId, Map<String, String> properties) {
        this.id = id;
        this.contentAddress = contentAddress;
        this.proxyEndpoint = proxyEndpoint;
        this.contractId = contractId;
        this.properties = properties;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public DataAddress getContentAddress() {
        return contentAddress;
    }

    @NotNull
    public String getProxyEndpoint() {
        return proxyEndpoint;
    }

    @NotNull
    public String getContractId() {
        return contractId;
    }

    @NotNull
    public Map<String, String> getProperties() {
        return properties;
    }

    public static class Builder {
        private String id = UUID.randomUUID().toString();
        private DataAddress contentAddress;
        private String proxyEndpoint;
        private String contractId;
        private final Map<String, String> properties = new HashMap<>();

        private Builder() {
        }

        @JsonCreator
        public static DataPlaneTransferProxyCreationRequest.Builder newInstance() {
            return new DataPlaneTransferProxyCreationRequest.Builder();
        }

        public DataPlaneTransferProxyCreationRequest.Builder id(String id) {
            this.id = id;
            return this;
        }

        public DataPlaneTransferProxyCreationRequest.Builder contentAddress(DataAddress address) {
            this.contentAddress = address;
            return this;
        }

        public DataPlaneTransferProxyCreationRequest.Builder proxyEndpoint(String proxyEndpoint) {
            this.proxyEndpoint = proxyEndpoint;
            return this;
        }

        public DataPlaneTransferProxyCreationRequest.Builder contractId(String contractId) {
            this.contractId = contractId;
            return this;
        }

        public DataPlaneTransferProxyCreationRequest.Builder property(String key, String value) {
            this.properties.put(key, value);
            return this;
        }

        public DataPlaneTransferProxyCreationRequest build() {
            Objects.requireNonNull(contentAddress, "contentAddress");
            Objects.requireNonNull(contractId, "contractId");
            Objects.requireNonNull(proxyEndpoint, "proxyEndpoint");
            return new DataPlaneTransferProxyCreationRequest(id, contentAddress, proxyEndpoint, contractId, properties);
        }
    }
}
