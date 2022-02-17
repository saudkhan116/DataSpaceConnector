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

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * A provider metadata contains provider basic access information which is stored in service registry */
@JsonDeserialize(builder = Battery.Builder.class)
public class Battery {

    private String id;
    private String name;
    private String type;
  
	@NotNull
    public String getId() {
        return id;
    }
	
	@NotNull
    public String getName() {
        return name;
    }
	
    @NotNull
    public String getType() {
		return type;
	}

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type);
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        private String id;
        private String name;
        private String type;

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

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        
        public Battery build() {
            Objects.requireNonNull(id);

            Battery battery = new Battery();
            battery.id = this.id;
            battery.name = this.name;
            battery.type = this.type;
            return battery;
        }
    }
}
