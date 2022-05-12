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

package org.eclipse.dataspaceconnector.iam.did.spi.hub;

import org.eclipse.dataspaceconnector.iam.did.spi.hub.message.ObjectQueryRequest;
import org.eclipse.dataspaceconnector.iam.did.spi.key.PublicKeyWrapper;
import org.eclipse.dataspaceconnector.spi.result.Result;

import java.util.Map;

/**
 * An interface to a foreign identity hub.
 */
public interface IdentityHubClient {


    /**
     * Queries credentials from the hub.
     *
     * @param query     the credential query
     * @param hubUrl    the base hub URL
     * @param publicKey the foreign hub's public key resolved from its DID
     */
    Result<Map<String, Object>> queryCredentials(ObjectQueryRequest query, String hubUrl, PublicKeyWrapper publicKey);

}
