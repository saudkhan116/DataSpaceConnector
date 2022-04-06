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

package org.eclipse.dataspaceconnector.spi.transaction.local;

/**
 * Registers transactional resources so they can be coordinated.
 */
public interface LocalTransactionContextManager {

    /**
     * Registers the resource.
     */
    void registerResource(LocalTransactionResource resource);

}
