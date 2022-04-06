/*
 *  Copyright (c) 2020, 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Amadeus - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.common.token;

import com.nimbusds.jwt.SignedJWT;
import org.eclipse.dataspaceconnector.spi.result.Result;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Interface for token validation rules.
 */
@FunctionalInterface
public interface TokenValidationRule {
    Result<SignedJWT> checkRule(@NotNull SignedJWT toVerify, @Nullable Map<String, Object> additional);

    default Result<SignedJWT> checkRule(@NotNull SignedJWT toVerify) {
        return checkRule(toVerify, new HashMap<>());
    }
}
