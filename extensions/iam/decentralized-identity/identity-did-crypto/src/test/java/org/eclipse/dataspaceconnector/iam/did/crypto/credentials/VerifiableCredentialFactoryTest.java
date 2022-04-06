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

package org.eclipse.dataspaceconnector.iam.did.crypto.credentials;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.ECKey;
import org.eclipse.dataspaceconnector.iam.did.crypto.helper.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class VerifiableCredentialFactoryTest {


    private ECKey privateKey;

    @BeforeEach
    void setup() throws JOSEException {
        String contents = TestHelper.readFile("private_p256.pem");

        privateKey = (ECKey) ECKey.parseFromPEMEncodedObjects(contents);
    }


    @Test
    void createVerifiableCredential() throws ParseException {
        var vc = VerifiableCredentialFactory.create(privateKey, Map.of("did-url", "someUrl"), "test-connector");

        assertThat(vc).isNotNull();
        assertThat(vc.getJWTClaimsSet().getClaim("did-url")).isEqualTo("someUrl");
        assertThat(vc.getJWTClaimsSet().getClaim("iss")).isEqualTo("test-connector");
        assertThat(vc.getJWTClaimsSet().getClaim("sub")).isEqualTo("verifiable-credential");
        assertThat(vc.getJWTClaimsSet().getExpirationTime()).isNotNull()
                .isAfter(Instant.now())
                .isBefore(Instant.now().plus(11, ChronoUnit.MINUTES));
    }

    @Test
    void ensureSerialization() throws ParseException {
        var vc = VerifiableCredentialFactory.create(privateKey, Map.of("did-url", "someUrl"), "test-connector");

        assertThat(vc).isNotNull();
        String jwtString = vc.serialize();

        //deserialize
        var deserialized = VerifiableCredentialFactory.parse(jwtString);

        assertThat(deserialized.getJWTClaimsSet()).isEqualTo(vc.getJWTClaimsSet());
        assertThat(deserialized.getHeader().getAlgorithm()).isEqualTo(vc.getHeader().getAlgorithm());
        assertThat(deserialized.getPayload().toString()).isEqualTo(vc.getPayload().toString());
    }

    @Test
    void verifyJwt() throws JOSEException {
        var vc = VerifiableCredentialFactory.create(privateKey, Map.of("did-url", "someUrl"), "test-connector");
        String jwtString = vc.serialize();

        //deserialize
        var jwt = VerifiableCredentialFactory.parse(jwtString);
        var pubKey = TestHelper.readFile("public_p256.pem");

        assertThat(VerifiableCredentialFactory.verify(jwt, (ECKey) ECKey.parseFromPEMEncodedObjects(pubKey))).isTrue();

    }
}
