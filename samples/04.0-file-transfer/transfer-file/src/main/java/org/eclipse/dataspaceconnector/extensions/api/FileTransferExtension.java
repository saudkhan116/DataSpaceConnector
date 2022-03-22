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
import org.eclipse.dataspaceconnector.policy.model.Action;
import org.eclipse.dataspaceconnector.policy.model.AtomicConstraint;
import org.eclipse.dataspaceconnector.policy.model.Constraint;
import org.eclipse.dataspaceconnector.policy.model.Expression;
import org.eclipse.dataspaceconnector.policy.model.LiteralExpression;
import org.eclipse.dataspaceconnector.policy.model.Operator;
import org.eclipse.dataspaceconnector.policy.model.Permission;
import org.eclipse.dataspaceconnector.policy.model.Policy;
import org.eclipse.dataspaceconnector.policy.model.Prohibition;
import org.eclipse.dataspaceconnector.policy.model.Expression.Visitor;
import org.eclipse.dataspaceconnector.spi.asset.AssetSelectorExpression;
import org.eclipse.dataspaceconnector.spi.asset.DataAddressResolver;
import org.eclipse.dataspaceconnector.spi.contract.offer.store.ContractDefinitionStore;
import org.eclipse.dataspaceconnector.spi.security.Vault;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.transfer.flow.DataFlowController;
import org.eclipse.dataspaceconnector.spi.transfer.flow.DataFlowManager;
import org.eclipse.dataspaceconnector.spi.transfer.inline.DataOperatorRegistry;
import org.eclipse.dataspaceconnector.spi.types.domain.DataAddress;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.offer.ContractDefinition;
import org.eclipse.dataspaceconnector.transfer.core.inline.InlineDataFlowController;

import java.nio.file.Path;

public class FileTransferExtension implements ServiceExtension {

    public static final String USE_POLICY = "use-eu";
    private static final String EDC_ASSET_PATH = "edc.samples.04.asset.path";
    @Inject
    private DataFlowManager dataFlowMgr;
    @Inject
    private DataAddressResolver dataAddressResolver;
    @Inject
    private DataOperatorRegistry dataOperatorRegistry;
    @Inject
    private ContractDefinitionStore contractStore;
    @Inject
    private AssetLoader loader;

    @Override
    public void initialize(ServiceExtensionContext context) {
        var vault = context.getService(Vault.class);
        dataOperatorRegistry.registerStreamPublisher(new FileTransferDataStreamPublisher(context.getMonitor(), dataAddressResolver));

        DataFlowController dataFlowController = new InlineDataFlowController(vault, context.getMonitor(), dataOperatorRegistry, dataAddressResolver);
        dataFlowMgr.register(dataFlowController);

        var policy = createPolicy("test-document_dismantler");
        var policy2 = createPolicy("test-document_oem");

        registerDataEntries(context);
        registerContractDefinition(policy, policy2);

        context.getMonitor().info("File Transfer Extension initialized!");
    }

    private Policy createPolicy(String target) {

//    	var atomicConstraint = AtomicConstraint.Builder.newInstance().
//    			leftExpression(new LiteralExpression("passportNumber")).
//    			rightExpression(new LiteralExpression("12345")).
//    			operator(Operator.EQ).build();
    	
//        var usePermission = Permission.Builder.newInstance()
//                .action(Action.Builder.newInstance().type("idsc:USE").constraint(atomicConstraint).build())
//                .build();
    	
    	var usePermission = Permission.Builder.newInstance()
                .action(Action.Builder.newInstance().type("idsc:USE").build())
                .build();
        
//        var useProhibition = Prohibition.Builder.newInstance()
//                .action(Action.Builder.newInstance().type("idsc:USE").build())
//                .build();

        return Policy.Builder.newInstance()
                .id(USE_POLICY)
                .permission(usePermission)
                .target(target)
                .build();
        
//        return Policy.Builder.newInstance()
//                .id(USE_POLICY)
//                .prohibition(useProhibition)
//                .target("test-document")
//                .build();
    }

    private void registerDataEntries(ServiceExtensionContext context) {
        String assetPathSetting = context.getSetting(EDC_ASSET_PATH, "/tmp/provider/test-document_dismantler.json");
        Path assetPath = Path.of(assetPathSetting);

        DataAddress dataAddress = DataAddress.Builder.newInstance()
                .property("type", "File")
                .property("path", assetPath.toString())
                .property("filename", "test-document_dismantler.json")
                .build();
        
//        .property("path", assetPath.getParent().toString())
//      .property("filename", assetPath.getFileName().toString())


        String assetId = "test-document_dismantler";
        Asset asset = Asset.Builder.newInstance().id(assetId).build();

        loader.accept(asset, dataAddress);
        
        DataAddress dataAddress2 = DataAddress.Builder.newInstance()
                .property("type", "File")
                .property("path", assetPath.toString())
                .property("filename", "test-document_oem.json")
                .build();

//        .property("path", assetPath.getParent().toString())
//        .property("filename", assetPath.getFileName().toString())

        String assetId2 = "test-document_oem";
        Asset asset2 = Asset.Builder.newInstance().id(assetId2).build();
        
        loader.accept(asset2, dataAddress2);
    }

    private void registerContractDefinition(Policy policy, Policy policy2) {

//        ContractDefinition contractDefinition = ContractDefinition.Builder.newInstance()
//                .id("1")
//                .accessPolicy(policy)
//                .contractPolicy(policy)
//                .selectorExpression(AssetSelectorExpression.Builder.newInstance().whenEquals(Asset.PROPERTY_ID, "test-document").constraint("passportNumber", "=","12345").build())
//                .build();
        ContractDefinition contractDefinition = ContractDefinition.Builder.newInstance()
                .id("1")
                .accessPolicy(policy)
                .contractPolicy(policy)
                .selectorExpression(AssetSelectorExpression.Builder.newInstance().whenEquals(Asset.PROPERTY_ID, "test-document_dismantler").build())
                .build();
        
        ContractDefinition contractDefinition2 = ContractDefinition.Builder.newInstance()
                .id("2")
                .accessPolicy(policy2)
                .contractPolicy(policy2)
                .selectorExpression(AssetSelectorExpression.Builder.newInstance().whenEquals(Asset.PROPERTY_ID, "test-document_oem").build())
                .build();

        contractStore.save(contractDefinition);
        contractStore.save(contractDefinition2);
    }
}
