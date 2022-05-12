/*
 *  Copyright (c) 2022 Bayerische Motoren Werke Aktiengesellschaft (BMW AG)
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.transform;

import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.model.NegotiationInitiateRequestDto;
import org.eclipse.dataspaceconnector.api.transformer.DtoTransformer;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.transformer.TransformerContext;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.ContractOfferRequest;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.offer.ContractOffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;

public class NegotiationInitiateRequestDtoToDataRequestTransformer implements DtoTransformer<NegotiationInitiateRequestDto, ContractOfferRequest> {
    @Override
    public Class<NegotiationInitiateRequestDto> getInputType() {
        return NegotiationInitiateRequestDto.class;
    }

    @Override
    public Class<ContractOfferRequest> getOutputType() {
        return ContractOfferRequest.class;
    }

    @Override
    public @Nullable ContractOfferRequest transform(@Nullable NegotiationInitiateRequestDto object, @NotNull TransformerContext context) {
        // TODO: ContractOfferRequest should contain only the contractOfferId and the contract offer should be retrieved from the catalog. Ref #985
        var contractOffer = ContractOffer.Builder.newInstance()
                .id(object.getOffer().getOfferId())
                .asset(Asset.Builder.newInstance().id(object.getOffer().getAssetId()).build())
                .policyId(object.getOffer().getPolicyId())
                // TODO: this is a workaround for the bug described in https://github.com/eclipse-dataspaceconnector/DataSpaceConnector/issues/753
                .consumer(uri("urn:connector:consumer"))
                .provider(uri("urn:connector:provider"))
                .policy(object.getOffer().getPolicy())
                .build();
        return ContractOfferRequest.Builder.newInstance()
                .connectorId(object.getConnectorId())
                .connectorAddress(object.getConnectorAddress())
                .protocol(object.getProtocol())
                .contractOffer(contractOffer)
                .type(ContractOfferRequest.Type.INITIAL)
                .build();
    }

    private URI uri(String s) {
        try {
            return new URI(s);
        } catch (URISyntaxException e) {
            throw new EdcException(e);
        }
    }
}
