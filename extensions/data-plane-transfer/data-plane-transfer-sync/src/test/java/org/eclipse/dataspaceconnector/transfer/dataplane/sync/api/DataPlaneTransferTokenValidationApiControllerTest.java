/*
 *  Copyright (c) 2022 Amadeus
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Amadeus - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.transfer.dataplane.sync.api;

import com.github.javafaker.Faker;
import org.eclipse.dataspaceconnector.common.token.TokenValidationService;
import org.eclipse.dataspaceconnector.spi.iam.ClaimToken;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.result.Result;
import org.eclipse.dataspaceconnector.transfer.dataplane.spi.security.DataEncrypter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.dataspaceconnector.dataplane.spi.DataPlaneConstants.DATA_ADDRESS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DataPlaneTransferTokenValidationApiControllerTest {

    private static final Faker FAKER = new Faker();

    private DataEncrypter encrypterMock;
    private TokenValidationService tokenValidationServiceMock;
    private DataPlaneTransferTokenValidationApiController controller;

    @BeforeEach
    void setUp() {
        encrypterMock = mock(DataEncrypter.class);
        tokenValidationServiceMock = mock(TokenValidationService.class);
        var monitor = mock(Monitor.class);
        controller = new DataPlaneTransferTokenValidationApiController(monitor, tokenValidationServiceMock, encrypterMock);
    }

    @Test
    void verifyValidateSuccess() {
        var token = FAKER.internet().uuid();
        var encryptedDataAddress = FAKER.internet().uuid();
        var decryptedDataAddress = FAKER.internet().uuid();
        var claims = createClaims(encryptedDataAddress);
        when(tokenValidationServiceMock.validate(token)).thenReturn(Result.success(claims));
        when(encrypterMock.decrypt(encryptedDataAddress)).thenReturn(decryptedDataAddress);

        var response = controller.validate(token);

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getEntity()).isInstanceOf(ClaimToken.class);
        var claimsResult = (ClaimToken) response.getEntity();
        assertThat(claimsResult.getClaims())
                .containsEntry("foo", "bar")
                .containsEntry("hello", "world")
                .containsEntry(DATA_ADDRESS, decryptedDataAddress);
    }

    @Test
    void verifyValidateFailure() {
        var token = FAKER.internet().uuid();
        var errorMsg = FAKER.internet().uuid();
        when(tokenValidationServiceMock.validate(token)).thenReturn(Result.failure(errorMsg));

        var response = controller.validate(token);

        assertThat(response.getStatus()).isEqualTo(400);
        assertThat(response.getEntity()).asString().isEqualTo("Token validation failed: " + errorMsg);
    }

    private static ClaimToken createClaims(String encryptedDataAddress) {
        return ClaimToken.Builder.newInstance()
                .claims(Map.of("foo", "bar", "hello", "world", DATA_ADDRESS, encryptedDataAddress))
                .build();
    }
}