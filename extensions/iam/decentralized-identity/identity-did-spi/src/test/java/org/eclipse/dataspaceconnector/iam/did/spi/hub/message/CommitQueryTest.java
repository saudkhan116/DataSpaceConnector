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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CommitQueryTest {

    @Test
    void verifySerializeDeserialize() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        var serialized = mapper.writeValueAsString(CommitQuery.Builder.newInstance().objectId("123").build());
        var deserialized = mapper.readValue(serialized, CommitQuery.class);
        Assertions.assertNotNull(deserialized);
        Assertions.assertEquals("123", deserialized.getObjectId());
    }
}
