/*
 *  Copyright (c) 2021 Daimler TSS GmbH
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Daimler TSS GmbH - Initial API and Implementation
 *       Fraunhofer Institute for Software and Systems Engineering - extend tests
 *       Daimler TSS GmbH - fixed contract dates to epoch seconds
 *
 */

package org.eclipse.dataspaceconnector.ids.api.multipart;

import kotlin.NotImplementedError;
import org.eclipse.dataspaceconnector.common.annotations.ComponentTest;
import org.eclipse.dataspaceconnector.ids.core.serialization.ObjectMapperFactory;
import org.eclipse.dataspaceconnector.policy.model.Action;
import org.eclipse.dataspaceconnector.policy.model.Permission;
import org.eclipse.dataspaceconnector.policy.model.Policy;
import org.eclipse.dataspaceconnector.policy.model.PolicyType;
import org.eclipse.dataspaceconnector.spi.asset.AssetIndex;
import org.eclipse.dataspaceconnector.spi.asset.AssetSelectorExpression;
import org.eclipse.dataspaceconnector.spi.asset.DataAddressResolver;
import org.eclipse.dataspaceconnector.spi.contract.negotiation.ConsumerContractNegotiationManager;
import org.eclipse.dataspaceconnector.spi.contract.negotiation.ProviderContractNegotiationManager;
import org.eclipse.dataspaceconnector.spi.contract.negotiation.store.ContractNegotiationStore;
import org.eclipse.dataspaceconnector.spi.contract.offer.ContractOfferQuery;
import org.eclipse.dataspaceconnector.spi.contract.offer.ContractOfferService;
import org.eclipse.dataspaceconnector.spi.contract.offer.store.ContractDefinitionStore;
import org.eclipse.dataspaceconnector.spi.contract.validation.ContractValidationService;
import org.eclipse.dataspaceconnector.spi.iam.ClaimToken;
import org.eclipse.dataspaceconnector.spi.iam.IdentityService;
import org.eclipse.dataspaceconnector.spi.iam.TokenRepresentation;
import org.eclipse.dataspaceconnector.spi.message.MessageContext;
import org.eclipse.dataspaceconnector.spi.message.RemoteMessageDispatcher;
import org.eclipse.dataspaceconnector.spi.message.RemoteMessageDispatcherRegistry;
import org.eclipse.dataspaceconnector.spi.policy.store.PolicyArchive;
import org.eclipse.dataspaceconnector.spi.query.QuerySpec;
import org.eclipse.dataspaceconnector.spi.response.StatusResult;
import org.eclipse.dataspaceconnector.spi.result.Result;
import org.eclipse.dataspaceconnector.spi.system.Provides;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.transfer.store.TransferProcessStore;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.agreement.ContractAgreement;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.ContractNegotiation;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.ContractNegotiationStates;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.ContractOfferRequest;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.command.ContractNegotiationCommand;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.offer.ContractDefinition;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.offer.ContractOffer;
import org.eclipse.dataspaceconnector.spi.types.domain.message.RemoteMessage;
import org.eclipse.dataspaceconnector.spi.types.domain.transfer.TransferProcess;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.mock;

@ComponentTest
@Provides({
        AssetIndex.class,
        DataAddressResolver.class,
        ContractDefinitionStore.class,
        IdentityService.class,
        TransferProcessStore.class,
        ConsumerContractNegotiationManager.class,
        ProviderContractNegotiationManager.class,
        ContractOfferService.class,
        ContractValidationService.class,
        PolicyArchive.class,
        ObjectMapperFactory.class
})
class IdsApiMultipartEndpointV1IntegrationTestServiceExtension implements ServiceExtension {
    private final List<Asset> assets;

    IdsApiMultipartEndpointV1IntegrationTestServiceExtension(List<Asset> assets) {
        this.assets = Objects.requireNonNull(assets);
    }

    private static ContractNegotiation fakeContractNegotiation() {
        return ContractNegotiation.Builder.newInstance()
                .id(UUID.randomUUID().toString())
                .correlationId(UUID.randomUUID().toString())
                .counterPartyId("test-counterparty-1")
                .counterPartyAddress("test-counterparty-address")
                .protocol("test-protocol")
                .stateCount(1)
                .contractAgreement(ContractAgreement.Builder.newInstance().id("1")
                        .providerAgentId("provider")
                        .consumerAgentId("consumer")
                        .assetId(UUID.randomUUID().toString())
                        .policyId("policyId")
                        .contractStartDate(Instant.now().getEpochSecond())
                        .contractEndDate(Instant.now().plus(1, ChronoUnit.DAYS).getEpochSecond())
                        .contractSigningDate(Instant.now().getEpochSecond())
                        .id("1:2").build())
                .state(ContractNegotiationStates.CONFIRMED.code())
                .build();
    }

    @Override
    public void initialize(ServiceExtensionContext context) {
        context.registerService(IdentityService.class, new FakeIdentityService());
        context.registerService(TransferProcessStore.class, new FakeTransferProcessStore());
        context.registerService(RemoteMessageDispatcherRegistry.class, new FakeRemoteMessageDispatcherRegistry());
        var assetIndex = new FakeAssetIndex(assets);
        context.registerService(AssetIndex.class, assetIndex);
        context.registerService(DataAddressResolver.class, assetIndex);
        context.registerService(ContractOfferService.class, new FakeContractOfferService(assets));
        context.registerService(ContractDefinitionStore.class, new FakeContractDefinitionStore());
        context.registerService(ContractValidationService.class, new FakeContractValidationService());
        context.registerService(ContractNegotiationStore.class, mock(ContractNegotiationStore.class));
        context.registerService(ProviderContractNegotiationManager.class, new FakeProviderContractNegotiationManager());
        context.registerService(ConsumerContractNegotiationManager.class, new FakeConsumerContractNegotiationManager());
        context.registerService(PolicyArchive.class, mock(PolicyArchive.class));
        context.registerService(ObjectMapperFactory.class, new ObjectMapperFactory());
    }

    private static class FakeIdentityService implements IdentityService {
        @Override
        public Result<TokenRepresentation> obtainClientCredentials(String scope) {
            return Result.success(TokenRepresentation.Builder.newInstance().build());
        }

        @Override
        public Result<ClaimToken> verifyJwtToken(TokenRepresentation tokenRepresentation) {
            return Result.success(ClaimToken.Builder.newInstance().build());
        }
    }

    private static class FakeAssetIndex implements AssetIndex, DataAddressResolver {
        private final List<Asset> assets;

        private FakeAssetIndex(List<Asset> assets) {
            this.assets = Objects.requireNonNull(assets);
        }

        @Override
        public Stream<Asset> queryAssets(AssetSelectorExpression expression) {
            return assets.stream();
        }

        @Override
        public Stream<Asset> queryAssets(QuerySpec querySpec) {
            throw new UnsupportedOperationException("Filtering/Paging not supported");
        }

        @Override
        public Asset findById(String assetId) {
            return assets.stream().filter(a -> a.getId().equals(assetId)).findFirst().orElse(null);
        }

        @Override
        public DataAddress resolveForAsset(String assetId) {
            var asset = findById(assetId);
            if (asset == null) {
                return null;
            }
            return DataAddress.Builder.newInstance().type("test").build();
        }
    }

    private static class FakeContractOfferService implements ContractOfferService {
        private final List<Asset> assets;

        private FakeContractOfferService(List<Asset> assets) {
            this.assets = assets;
        }

        @Override
        @NotNull
        public Stream<ContractOffer> queryContractOffers(ContractOfferQuery query) {
            return assets.stream().map(asset ->
                    ContractOffer.Builder.newInstance()
                            .id("1")
                            .policy(createEverythingAllowedPolicy())
                            .asset(asset)
                            .build()
            );
        }

        private Policy createEverythingAllowedPolicy() {
            var policyBuilder = Policy.Builder.newInstance();
            var permissionBuilder = Permission.Builder.newInstance();
            var actionBuilder = Action.Builder.newInstance();

            policyBuilder.type(PolicyType.CONTRACT);
            actionBuilder.type("USE");
            permissionBuilder.target("1");

            permissionBuilder.action(actionBuilder.build());
            policyBuilder.permission(permissionBuilder.build());

            policyBuilder.target("1");
            return policyBuilder.build();
        }
    }

    private static class FakeTransferProcessStore implements TransferProcessStore {
        @Override
        public TransferProcess find(String id) {
            return null;
        }

        @Override
        public @Nullable String processIdForTransferId(String id) {
            return null;
        }

        @Override
        public @NotNull List<TransferProcess> nextForState(int state, int max) {
            return emptyList();
        }

        @Override
        public void create(TransferProcess process) {
        }

        @Override
        public void update(TransferProcess process) {
        }

        @Override
        public void delete(String processId) {
        }

        @Override
        public Stream<TransferProcess> findAll(QuerySpec querySpec) {
            return null;
        }

    }

    private static class FakeRemoteMessageDispatcherRegistry implements RemoteMessageDispatcherRegistry {

        @Override
        public void register(RemoteMessageDispatcher dispatcher) {
        }

        @Override
        public <T> CompletableFuture<T> send(Class<T> responseType, RemoteMessage message, MessageContext context) {
            return null;
        }
    }

    private static class FakeContractDefinitionStore implements ContractDefinitionStore {

        private final List<ContractDefinition> contractDefinitions = new ArrayList<>();

        @Override
        public @NotNull Collection<ContractDefinition> findAll() {
            return contractDefinitions;
        }

        @Override
        public @NotNull Stream<ContractDefinition> findAll(QuerySpec spec) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public ContractDefinition findById(String definitionId) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public void save(Collection<ContractDefinition> definitions) {
            contractDefinitions.addAll(definitions);
        }

        @Override
        public void save(ContractDefinition definition) {
            contractDefinitions.add(definition);
        }

        @Override
        public void update(ContractDefinition definition) {
            throw new NotImplementedError();
        }

        @Override
        public ContractDefinition deleteById(String id) {
            throw new NotImplementedError();
        }

        @Override
        public void reload() {
            throw new NotImplementedError();
        }
    }

    private static class FakeContractValidationService implements ContractValidationService {

        @Override
        public @NotNull Result<ContractOffer> validate(ClaimToken token, ContractOffer offer) {
            return Result.success(ContractOffer.Builder.newInstance().build());
        }

        @Override
        public @NotNull Result<ContractOffer> validate(ClaimToken token, ContractOffer offer, ContractOffer latestOffer) {
            return Result.success(offer);
        }

        @Override
        public boolean validate(ClaimToken token, ContractAgreement agreement) {
            return true;
        }

        @Override
        public boolean validate(ClaimToken token, ContractAgreement agreement, ContractOffer latestOffer) {
            return false;
        }
    }

    private static class FakeProviderContractNegotiationManager implements ProviderContractNegotiationManager {

        @Override
        public StatusResult<ContractNegotiation> declined(ClaimToken token, String negotiationId) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public void enqueueCommand(ContractNegotiationCommand command) {
        }

        @Override
        public StatusResult<ContractNegotiation> requested(ClaimToken token, ContractOfferRequest request) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public StatusResult<ContractNegotiation> offerReceived(ClaimToken token, String correlationId, ContractOffer offer, String hash) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public StatusResult<ContractNegotiation> consumerApproved(ClaimToken token, String correlationId, ContractAgreement agreement, String hash) {
            return StatusResult.success(fakeContractNegotiation());
        }
    }

    private static class FakeConsumerContractNegotiationManager implements ConsumerContractNegotiationManager {

        @Override
        public StatusResult<ContractNegotiation> initiate(ContractOfferRequest contractOffer) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public StatusResult<ContractNegotiation> offerReceived(ClaimToken token, String negotiationId, ContractOffer contractOffer, String hash) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public StatusResult<ContractNegotiation> confirmed(ClaimToken token, String negotiationId, ContractAgreement agreement, Policy policy) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public StatusResult<ContractNegotiation> declined(ClaimToken token, String negotiationId) {
            return StatusResult.success(fakeContractNegotiation());
        }

        @Override
        public void enqueueCommand(ContractNegotiationCommand command) {
        }
    }
}
