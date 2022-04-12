/*
 *  Copyright (c) 2020 - 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - improvements
 *
 */

package org.eclipse.dataspaceconnector.api.exception.mappers;

import jakarta.ws.rs.NotAcceptableException;
import jakarta.ws.rs.NotAllowedException;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.NotSupportedException;
import org.eclipse.dataspaceconnector.api.exception.AuthenticationFailedException;
import org.eclipse.dataspaceconnector.api.exception.NotAuthorizedException;
import org.eclipse.dataspaceconnector.api.exception.ObjectExistsException;
import org.eclipse.dataspaceconnector.api.exception.ObjectNotFoundException;
import org.eclipse.dataspaceconnector.api.exception.ObjectNotModifiableException;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class EdcApiExceptionMapperTest {

    private final EdcApiExceptionMapper mapper = new EdcApiExceptionMapper();

    @ParameterizedTest
    @ArgumentsSource(EdcApiExceptions.class)
    @ArgumentsSource(JakartaApiExceptions.class)
    void toResponse(Throwable throwable, int expectedCode) {
        var response = mapper.toResponse(throwable);

        assertThat(response.getStatus()).isEqualTo(expectedCode);
        assertThat(response.getStatusInfo().getReasonPhrase()).isNotBlank();
    }

    private static class EdcApiExceptions implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new ObjectNotModifiableException("1234", "test-type"), 409),
                    Arguments.of(new AuthenticationFailedException(), 401),
                    Arguments.of(new ObjectExistsException(Object.class, "test-object-id"), 409),
                    Arguments.of(new ObjectNotFoundException(Object.class, "test-object-id"), 404),
                    Arguments.of(new IllegalStateException("foo"), 503),
                    Arguments.of(new IllegalArgumentException("foo"), 400),
                    Arguments.of(new UnsupportedOperationException("foo"), 501),
                    Arguments.of(new NullPointerException("foo"), 400),
                    Arguments.of(new EdcException("foo"), 503),
                    Arguments.of(new NotAuthorizedException(), 403)
            );
        }
    }

    private static class JakartaApiExceptions implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(new NotAcceptableException("Not acceptable"), 406),
                    Arguments.of(new NotFoundException(), 404),
                    Arguments.of(new NotSupportedException(), 415),
                    Arguments.of(new NotAllowedException("any"), 405)
            );
        }
    }
}