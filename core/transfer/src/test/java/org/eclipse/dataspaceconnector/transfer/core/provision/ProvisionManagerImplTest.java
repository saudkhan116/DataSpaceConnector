/*
 *  Copyright (c) 2021 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.transfer.core.provision;

import org.eclipse.dataspaceconnector.policy.model.Policy;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.transfer.provision.DeprovisionResult;
import org.eclipse.dataspaceconnector.spi.transfer.provision.ProvisionResult;
import org.eclipse.dataspaceconnector.spi.transfer.provision.Provisioner;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.DeprovisionedResource;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.ProvisionResponse;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.ProvisionedDataDestinationResource;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.ProvisionedResource;
import org.eclipse.dataspaceconnector.transfer.core.TestProvisionedDataDestinationResource;
import org.eclipse.dataspaceconnector.transfer.core.TestResourceDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.failedFuture;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProvisionManagerImplTest {

    @SuppressWarnings("unchecked")
    private final Provisioner<TestResourceDefinition, TestProvisionedResource> provisioner = mock(Provisioner.class);
    private final Monitor monitor = mock(Monitor.class);
    private final ProvisionManagerImpl provisionManager = new ProvisionManagerImpl(monitor);
    private Policy policy;

    @BeforeEach
    void setUp() {
        provisionManager.register(provisioner);
        policy = Policy.Builder.newInstance().build();
    }

    @Test
    void provision_should_provision_all_the_transfer_process_definitions() {
        when(provisioner.canProvision(isA(TestResourceDefinition.class))).thenReturn(true);
        var provisionResult = ProvisionResult.success(ProvisionResponse.Builder.newInstance()
                .resource(new TestProvisionedDataDestinationResource("test-resource", "1"))
                .build());

        when(provisioner.provision(isA(TestResourceDefinition.class), isA(Policy.class))).thenReturn(completedFuture(provisionResult));

        var result = provisionManager.provision(List.of(new TestResourceDefinition()), policy);

        assertThat(result).succeedsWithin(1, SECONDS)
                .extracting(responses -> responses.get(0))
                .extracting(ProvisionResult::getContent)
                .extracting(ProvisionResponse::getResource)
                .extracting(ProvisionedDataDestinationResource.class::cast)
                .extracting(ProvisionedDataDestinationResource::getResourceName)
                .isEqualTo("test-resource");
    }

    @Test
    void provision_should_fail_when_provisioner_throws_exception() {
        when(provisioner.canProvision(isA(TestResourceDefinition.class))).thenReturn(true);
        when(provisioner.provision(isA(TestResourceDefinition.class), isA(Policy.class))).thenThrow(new EdcException("error"));

        var result = provisionManager.provision(List.of(new TestResourceDefinition()), policy);

        assertThat(result).failsWithin(1, SECONDS)
                .withThrowableOfType(ExecutionException.class)
                .withRootCauseInstanceOf(EdcException.class)
                .withMessageContaining("error");
    }

    @Test
    void provision_should_fail_when_provisioner_fails() {
        when(provisioner.canProvision(isA(TestResourceDefinition.class))).thenReturn(true);
        when(provisioner.provision(isA(TestResourceDefinition.class), isA(Policy.class))).thenReturn(failedFuture(new EdcException("error")));

        var result = provisionManager.provision(List.of(new TestResourceDefinition()), policy);

        assertThat(result).failsWithin(1, SECONDS)
                .withThrowableOfType(ExecutionException.class)
                .withRootCauseInstanceOf(EdcException.class)
                .withMessageContaining("error");
    }

    @Test
    void deprovision_should_deprovision_all_the_transfer_process_provisioned_resources() {
        var deprovisionResponse = DeprovisionResult.success(DeprovisionedResource.Builder.newInstance()
                .provisionedResourceId("1")
                .build());
        when(provisioner.canDeprovision(isA(ProvisionedResource.class))).thenReturn(true);
        when(provisioner.deprovision(isA(TestProvisionedResource.class), isA(Policy.class))).thenReturn(completedFuture(deprovisionResponse));

        var result = provisionManager.deprovision(List.of(new TestProvisionedResource()), policy);

        assertThat(result).succeedsWithin(1, SECONDS)
                .extracting(responses -> responses.get(0))
                .extracting(DeprovisionResult::getContent)
                .extracting(DeprovisionedResource::getProvisionedResourceId)
                .isEqualTo("1");
    }

    @Test
    void deprovision_should_fail_when_provisioner_throws_exception() {
        when(provisioner.canDeprovision(isA(ProvisionedResource.class))).thenReturn(true);
        when(provisioner.deprovision(isA(TestProvisionedResource.class), isA(Policy.class))).thenThrow(new EdcException("error"));

        var result = provisionManager.deprovision(List.of(new TestProvisionedResource()), policy);

        assertThat(result).failsWithin(1, SECONDS)
                .withThrowableOfType(ExecutionException.class)
                .withRootCauseInstanceOf(EdcException.class)
                .withMessageContaining("error");
    }

    @Test
    void deprovision_should_fail_when_provisioner_fails() {
        when(provisioner.canDeprovision(isA(ProvisionedResource.class))).thenReturn(true);
        when(provisioner.deprovision(isA(TestProvisionedResource.class), isA(Policy.class))).thenReturn(failedFuture(new EdcException("error")));

        var result = provisionManager.deprovision(List.of(new TestProvisionedResource()), policy);

        assertThat(result).failsWithin(1, SECONDS)
                .withThrowableOfType(ExecutionException.class)
                .withRootCauseInstanceOf(EdcException.class)
                .withMessageContaining("error");
    }

    private static class TestProvisionedResource extends ProvisionedResource {
    }

}
