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

package org.eclipse.dataspaceconnector.catalog.spi.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.eclipse.dataspaceconnector.spi.query.Criterion;

import java.util.ArrayList;
import java.util.List;

/**
 * Query class that wraps around a list of {@link Criterion} objects.
 * It is used to submit queries to the FederatedCatalogCache.
 */
@JsonDeserialize(builder = FederatedCatalogCacheQuery.Builder.class)
public class FederatedCatalogCacheQuery {
    private final List<Criterion> criteria;

    private FederatedCatalogCacheQuery(List<Criterion> criteria) {
        this.criteria = criteria;
    }

    public List<Criterion> getCriteria() {
        return criteria;
    }


    @JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
        // will be implemented in a subsequent PR
        private final List<Criterion> criteria;

        private Builder() {
            criteria = new ArrayList<>();
        }

        @JsonCreator
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder where(Criterion criterion) {
            criteria.add(criterion);
            return this;
        }

        public FederatedCatalogCacheQuery build() {
            return new FederatedCatalogCacheQuery(criteria);
        }
    }
}
