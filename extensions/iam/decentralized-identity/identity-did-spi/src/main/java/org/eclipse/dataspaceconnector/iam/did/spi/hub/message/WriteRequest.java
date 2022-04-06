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

import java.util.Objects;


@JsonTypeName("WriteRequest")
@JsonDeserialize(builder = WriteRequest.Builder.class)
public class WriteRequest extends AbstractHubRequest {
    private JsonCommitObject commit;

    private WriteRequest() {
    }

    @Override
    public String getIss() {
        return iss;
    }

    @Override
    public String getAud() {
        return aud;
    }

    @Override
    public String getSub() {
        return sub;
    }

    public JsonCommitObject getCommit() {
        return commit;
    }

    public static class Builder extends AbstractHubRequest.Builder<WriteRequest, Builder> {

        private Builder() {
            super(new WriteRequest());
        }

        @JsonCreator()
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder commit(JsonCommitObject commit) {
            request.commit = commit;
            return this;
        }

        public WriteRequest build() {
            verify();
            Objects.requireNonNull(request.commit, "commit");
            return request;
        }

    }
}
