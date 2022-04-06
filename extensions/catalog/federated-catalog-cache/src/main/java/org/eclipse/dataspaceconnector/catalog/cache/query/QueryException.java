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

package org.eclipse.dataspaceconnector.catalog.cache.query;

import org.eclipse.dataspaceconnector.spi.EdcException;

import java.util.List;

public class QueryException extends EdcException {

    public QueryException(List<String> errors) {
        super(String.join(", ", errors));
    }
}
