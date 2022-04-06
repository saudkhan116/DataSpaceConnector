/*
 *  Copyright (c) 2022 Daimler TSS GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Daimler TSS GmbH - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.iam.verifier;
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

import org.eclipse.dataspaceconnector.iam.did.spi.credentials.CredentialsVerifier;
import org.eclipse.dataspaceconnector.iam.did.spi.key.PublicKeyWrapper;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.result.Result;

import java.util.Map;

/**
 * Implements a sample credentials validator that checks for signed registration credentials.
 */
public class DummyCredentialsVerifier implements CredentialsVerifier {
    private final Monitor monitor;

    /**
     * Create a new credentials verifier that uses an Identity Hub
     *
     * @param monitor a {@link Monitor}
     */
    public DummyCredentialsVerifier(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public Result<Map<String, String>> verifyCredentials(String hubBaseUrl, PublicKeyWrapper othersPublicKey) {
        monitor.debug("Starting (dummy) credential verification against hub URL " + hubBaseUrl);

        return Result.success(Map.of("region", "eu"));
    }
}
