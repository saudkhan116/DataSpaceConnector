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

package org.eclipse.dataspaceconnector.iam.did.spi.hub.message;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;


@JsonTypeName("WriteResponse")
@JsonDeserialize(builder = WriteResponse.Builder.class)
public class WriteResponse extends HubMessage {
    private final List<String> revisions = new ArrayList<>();

    private WriteResponse() {
    }

    public List<String> getRevisions() {
        return revisions;
    }

    public static class Builder extends HubMessage.Builder {
        private final WriteResponse writeResponse;

        private Builder() {
            writeResponse = new WriteResponse();
        }

        @JsonCreator()
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder revisions(List<String> revisions) {
            writeResponse.revisions.addAll(revisions);
            return this;
        }

        public Builder revision(String revision) {
            writeResponse.revisions.add(revision);
            return this;
        }

        public WriteResponse build() {
            return writeResponse;
        }
    }
}
