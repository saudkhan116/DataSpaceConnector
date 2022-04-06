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
 *       Amadeus - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.transfer.dataplane.flow;

import com.github.javafaker.Faker;
import org.eclipse.dataspaceconnector.dataplane.spi.result.TransferResult;
import org.eclipse.dataspaceconnector.spi.asset.DataAddressResolver;
import org.eclipse.dataspaceconnector.spi.response.ResponseStatus;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.DataFlowRequest;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.DataRequest;
import org.eclipse.dataspaceconnector.transfer.dataplane.client.DataPlaneTransferClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.eclipse.dataspaceconnector.transfer.dataplane.spi.DataPlaneTransferType.SYNC;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DataPlaneTransferFlowControllerTest {

    private static final Faker FAKER = new Faker();

    private DataAddressResolver addressResolverMock;
    private DataPlaneTransferClient transferClientMock;

    private DataPlaneTransferFlowController flowController;

    @BeforeEach
    public void setUp() {
        addressResolverMock = mock(DataAddressResolver.class);
        transferClientMock = mock(DataPlaneTransferClient.class);
        flowController = new DataPlaneTransferFlowController(addressResolverMock, transferClientMock);
    }

    @Test
    void canHandle() {
        assertThat(flowController.canHandle(createDataRequest())).isTrue();
        assertThat(flowController.canHandle(createDataRequest(SYNC))).isFalse();
    }

    @Test
    void transferFailure_shouldReturnFailedTransferResult() {
        var errorMsg = FAKER.internet().uuid();
        var request = createDataRequest();

        when(addressResolverMock.resolveForAsset(request.getAssetId())).thenReturn(testDataAddress());
        when(transferClientMock.transfer(any())).thenReturn(TransferResult.failure(ResponseStatus.FATAL_ERROR, errorMsg));

        var result = flowController.initiateFlow(request, null);

        verify(addressResolverMock, times(1)).resolveForAsset(any());
        verify(transferClientMock, times(1)).transfer(any());

        assertThat(result.failed()).isTrue();
        assertThat(result.getFailureMessages()).allSatisfy(s -> assertThat(s).contains(errorMsg));
    }

    @Test
    void transferSuccess() {
        var request = createDataRequest();
        var source = testDataAddress();

        var dfrCapture = ArgumentCaptor.forClass(DataFlowRequest.class);
        when(addressResolverMock.resolveForAsset(request.getAssetId())).thenReturn(source);
        when(transferClientMock.transfer(any())).thenReturn(TransferResult.success());

        var result = flowController.initiateFlow(request, null);

        verify(addressResolverMock, times(1)).resolveForAsset(any());
        verify(transferClientMock, times(1)).transfer(dfrCapture.capture());

        assertThat(result.succeeded()).isTrue();
        var dataFlowRequest = dfrCapture.getValue();
        assertThat(dataFlowRequest.isTrackable()).isTrue();
        assertThat(dataFlowRequest.getProcessId()).isEqualTo(request.getProcessId());
        assertThat(dataFlowRequest.getSourceDataAddress().getType()).isEqualTo(source.getType());
        assertThat(dataFlowRequest.getDestinationDataAddress().getType()).isEqualTo(request.getDestinationType());
        assertThat(dataFlowRequest.getProperties()).isEmpty();
    }

    private static DataAddress testDataAddress() {
        return DataAddress.Builder.newInstance().type(FAKER.internet().uuid()).build();
    }

    public static DataRequest createDataRequest() {
        return createDataRequest(FAKER.internet().uuid());
    }

    /**
     * Create a {@link DataRequest} object with provided destination type.
     */
    public static DataRequest createDataRequest(String destinationType) {
        return DataRequest.Builder.newInstance()
                .id(FAKER.internet().uuid())
                .protocol(FAKER.internet().uuid())
                .contractId(FAKER.internet().uuid())
                .assetId(FAKER.internet().uuid())
                .connectorAddress(FAKER.internet().url())
                .processId(FAKER.internet().uuid())
                .destinationType(destinationType)
                .build();
    }
}
