/*
 *  Copyright (c) 2021 Daimler TSS GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Daimler TSS GmbH - Initial API and Implementation
 *
 */

package org.eclipse.dataspaceconnector.spi.types.domain.contract.offer;

import java.util.List;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * A provider metadata contains provider basic access information which is stored in service registry */
@JsonDeserialize(builder = ProviderMetadata.Builder.class)
public class ProviderMetadata {
    private String id;
    private String provider;
    private String role;
    private String providerRegion;
    private List<Battery> batteries;
    private String providerConnector;
    private List<String> contractOffers;

	@NotNull
    public String getId() {
        return id;
    }
	
	@NotNull
    public String getProvider() {
        return provider;
    }

	@NotNull
    public String getRole() {
        return role;
    }
	
    @NotNull
    public String getProviderRegion() {
		return providerRegion;
	}

    @NotNull
	public List<Battery> getBatteries() {
		return batteries;
	}

    @NotNull
	public String getProvideConnector() {
		return providerConnector;
	}

    @NotNull
	public List<String> getContractOffers() {
		return contractOffers;
	}

    @Override
    public int hashCode() {
        return Objects.hash(id, provider, role, providerRegion, batteries, providerConnector, contractOffers);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String id;
        private String provider;
        private String role;
        private String providerRegion;
        private List<Battery> batteries;
        private String providerConnector;
        private List<String> contractOffers;

        private Builder() {
        }

        @JsonCreator
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder provider(String provider) {
            this.provider = provider;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public Builder providerRegion(String providerRegion) {
            this.providerRegion = providerRegion;
            return this;
        }

        public Builder batteries(List<Battery> batteries) {
            this.batteries = batteries;
            return this;
        }

        public Builder providerConnector(String providerConnector) {
            this.providerConnector = providerConnector;
            return this;
        }

        public Builder contractOffers(List<String> contractOffers) {
        	this.contractOffers = contractOffers;
            return this;
        }
        
        public ProviderMetadata build() {
            Objects.requireNonNull(id);

            ProviderMetadata metadata = new ProviderMetadata();
            metadata.id = this.id;
            metadata.provider = this.provider;
            metadata.role = this.role;
            metadata.providerRegion = this.providerRegion;
            metadata.batteries = this.batteries;
            metadata.providerConnector = this.providerConnector;
            metadata.contractOffers = this.contractOffers;
            return metadata;
        }
    }
}
