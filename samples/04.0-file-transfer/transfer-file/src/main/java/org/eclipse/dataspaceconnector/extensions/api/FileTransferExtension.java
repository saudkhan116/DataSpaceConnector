/*
 *  Copyright (c) 2021 Fraunhofer Institute for Software and Systems Engineering
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Fraunhofer Institute for Software and Systems Engineering - initial API and implementation
 *
 */

package org.eclipse.dataspaceconnector.extensions.api;

import org.eclipse.dataspaceconnector.dataloading.AssetLoader;
import org.eclipse.dataspaceconnector.dataplane.spi.pipeline.DataTransferExecutorServiceContainer;
import org.eclipse.dataspaceconnector.dataplane.spi.pipeline.PipelineService;
import org.eclipse.dataspaceconnector.policy.model.Action;
import org.eclipse.dataspaceconnector.policy.model.Permission;
import org.eclipse.dataspaceconnector.policy.model.Policy;
import org.eclipse.dataspaceconnector.spi.asset.AssetSelectorExpression;
import org.eclipse.dataspaceconnector.spi.contract.offer.store.ContractDefinitionStore;
import org.eclipse.dataspaceconnector.spi.policy.store.PolicyStore;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.offer.ContractDefinition;

import java.nio.file.Path;

public class FileTransferExtension implements ServiceExtension {

    public static final String USE_POLICY = "use-eu";
    private static final String EDC_ASSET_PATH = "edc.samples.04.asset.path";
    @Inject
    private ContractDefinitionStore contractStore;
    @Inject
    private AssetLoader loader;
    @Inject
    private PipelineService pipelineService;
    @Inject
    private DataTransferExecutorServiceContainer executorContainer;
    @Inject
    private PolicyStore policyStore;

    @Override
    public void initialize(ServiceExtensionContext context) {
        var monitor = context.getMonitor();

        var sourceFactory = new FileTransferDataSourceFactory();
        pipelineService.registerFactory(sourceFactory);

        var sinkFactory = new FileTransferDataSinkFactory(monitor, executorContainer.getExecutorService(), 5);
        pipelineService.registerFactory(sinkFactory);

        // original
        // var policy = createPolicy();
        // policyStore.save(policy);
        // registerDataEntries(context);
        // registerContractDefinition(policy.getUid());

        // added by Saud
        var policy1 = createPolicy("test-document_oem");
        var policy2 = createPolicy("test-document_dismantler");
        var policy3 = createPolicy("test-document_recycler");
        policyStore.save(policy1);
        policyStore.save(policy2);
        policyStore.save(policy3);

        registerDataEntries(context);
        registerContractDefinition(policy1.getUid(), policy2.getUid(), policy3.getUid());

        context.getMonitor().info("File Transfer Extension initialized!");
    }

    private Policy createPolicy(String target) {
        var usePermission = Permission.Builder.newInstance()
                .action(Action.Builder.newInstance().type("USE").build())
                .build();

        return Policy.Builder.newInstance()
                .id(USE_POLICY)
                .permission(usePermission)
                .target(target)
                .build();
    }

    private void registerDataEntries(ServiceExtensionContext context) {
        // var assetPathSetting = context.getSetting(EDC_ASSET_PATH, "/tmp/provider/test-document.txt");
        var assetPathSetting = context.getSetting(EDC_ASSET_PATH, "C:/Users/muhammadsaud.khan/Desktop/ProviderSpace");
        var assetPath = Path.of(assetPathSetting);

        /*var dataAddress = DataAddress.Builder.newInstance()
                .property("type", "File")
                .property("path", assetPath.getParent().toString())
                .property("filename", assetPath.getFileName().toString())
                .build();*/

        var dataAddress = DataAddress.Builder.newInstance()
                .property("type", "File")
                .property("path", assetPath.toString())
                .property("filename", "test-document_oem.json")
                .build();

        // var assetId = "test-document";
        // var asset = Asset.Builder.newInstance().id(assetId).build();
        // loader.accept(asset, dataAddress);

        String assetId = "test-document_oem";
        Asset asset = Asset.Builder.newInstance().id(assetId).build();

        loader.accept(asset, dataAddress);

        // asset dismantler
        String assetPathSetting2 = context.getSetting(EDC_ASSET_PATH, "C:/Users/muhammadsaud.khan/Desktop/ProviderSpace");
        Path assetPath2 = Path.of(assetPathSetting2);
        DataAddress dataAddress2 = DataAddress.Builder.newInstance()
                .property("type", "File")
                .property("path", assetPath2.toString())
                .property("filename", "test-document_dismantler.json")
                .build();

        String assetId2 = "test-document_dismantler";
        Asset asset2 = Asset.Builder.newInstance().id(assetId2).build();

        loader.accept(asset2, dataAddress2);

        //asset recycler
        String assetPathSetting3 = context.getSetting(EDC_ASSET_PATH, "C:/Users/muhammadsaud.khan/Desktop/ProviderSpace");
        Path assetPath3 = Path.of(assetPathSetting3);
        DataAddress dataAddress3 = DataAddress.Builder.newInstance()
                .property("type", "File")
                .property("path", assetPath3.toString())
                .property("filename", "test-document_recycler.json")
                .build();

        String assetId3 = "test-document_recycler";
        Asset asset3 = Asset.Builder.newInstance().id(assetId3).build();

        loader.accept(asset3, dataAddress3);
    }

    private void registerContractDefinition(String uid, String uid2, String uid3) {
        var contractDefinition = ContractDefinition.Builder.newInstance()
                .id("1")
                .accessPolicyId(uid)
                .contractPolicyId(uid)
                .selectorExpression(AssetSelectorExpression.Builder.newInstance()
                        .whenEquals(Asset.PROPERTY_ID, "test-document_oem")
                        .build())
                .build();

        var contractDefinition2 = ContractDefinition.Builder.newInstance()
                .id("2")
                .accessPolicyId(uid2)
                .contractPolicyId(uid2)
                .selectorExpression(AssetSelectorExpression.Builder.newInstance()
                        .whenEquals(Asset.PROPERTY_ID, "test-document_dismantler")
                        .build())
                .build();

        var contractDefinition3 = ContractDefinition.Builder.newInstance()
                .id("3")
                .accessPolicyId(uid3)
                .contractPolicyId(uid3)
                .selectorExpression(AssetSelectorExpression.Builder.newInstance()
                        .whenEquals(Asset.PROPERTY_ID, "test-document_recycler")
                        .build())
                .build();

        contractStore.save(contractDefinition);
        contractStore.save(contractDefinition2);
        contractStore.save(contractDefinition3);
    }
}
