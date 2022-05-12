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
 *
 */

package org.eclipse.dataspaceconnector.core.defaults;

import org.eclipse.dataspaceconnector.common.concurrency.LockManager;
import org.eclipse.dataspaceconnector.core.defaults.assetindex.InMemoryAssetIndex;
import org.eclipse.dataspaceconnector.core.defaults.contractdefinition.InMemoryContractDefinitionStore;
import org.eclipse.dataspaceconnector.core.defaults.negotiationstore.InMemoryContractNegotiationStore;
import org.eclipse.dataspaceconnector.core.defaults.policystore.InMemoryPolicyStore;
import org.eclipse.dataspaceconnector.core.defaults.transferprocessstore.InMemoryTransferProcessStore;
import org.eclipse.dataspaceconnector.dataloading.AssetLoader;
import org.eclipse.dataspaceconnector.dataloading.ContractDefinitionLoader;
import org.eclipse.dataspaceconnector.spi.asset.AssetIndex;
import org.eclipse.dataspaceconnector.spi.asset.DataAddressResolver;
import org.eclipse.dataspaceconnector.spi.contract.negotiation.store.ContractNegotiationStore;
import org.eclipse.dataspaceconnector.spi.contract.offer.store.ContractDefinitionStore;
import org.eclipse.dataspaceconnector.spi.policy.store.PolicyStore;
import org.eclipse.dataspaceconnector.spi.system.Provider;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.transfer.store.TransferProcessStore;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Provides (in-mem) defaults for various stores, registries etc.
 * Provider methods are only invoked if no other implementation was found on the classpath.
 */
public class DefaultServicesExtension implements ServiceExtension {

    private InMemoryAssetIndex assetIndex;
    private InMemoryContractDefinitionStore contractDefinitionStore;

    public DefaultServicesExtension() {
    }

    @Provider(isDefault = true)
    public AssetIndex defaultAssetIndex() {
        return getAssetIndex();
    }

    @Provider(isDefault = true)
    public DataAddressResolver defaultDataAddressResolver() {
        return getAssetIndex();
    }

    @Provider(isDefault = true)
    public AssetLoader defaultAssetLoader() {
        return getAssetIndex();
    }


    @Provider(isDefault = true)
    public ContractDefinitionStore defaultContractDefinitionStore() {
        return getContractDefinitionStore();
    }

    private ContractDefinitionStore getContractDefinitionStore() {
        if (contractDefinitionStore == null) {
            contractDefinitionStore = new InMemoryContractDefinitionStore();
        }
        return contractDefinitionStore;
    }

    @Provider(isDefault = true)
    public ContractDefinitionLoader defaultContractDefinitionLoader() {
        return getContractDefinitionStore()::save;
    }

    @Provider(isDefault = true)
    public ContractNegotiationStore defaultContractNegotiationStore() {
        return new InMemoryContractNegotiationStore();
    }

    @Provider(isDefault = true)
    public TransferProcessStore defaultTransferProcessStore() {
        return new InMemoryTransferProcessStore();
    }

    @Provider(isDefault = true)
    public PolicyStore defaultPolicyStore() {
        return new InMemoryPolicyStore(new LockManager(new ReentrantReadWriteLock(true)));
    }

    private InMemoryAssetIndex getAssetIndex() {
        if (assetIndex == null) {
            assetIndex = new InMemoryAssetIndex();
        }
        return assetIndex;
    }
}
