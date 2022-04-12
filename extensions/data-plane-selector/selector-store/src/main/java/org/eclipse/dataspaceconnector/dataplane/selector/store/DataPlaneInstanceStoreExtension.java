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

<<<<<<<< HEAD:extensions/api/data-management/contractagreement/src/main/java/org/eclipse/dataspaceconnector/api/datamanagement/contractagreement/ContractAgreementApiExtension.java
package org.eclipse.dataspaceconnector.api.datamanagement.contractagreement;

import org.eclipse.dataspaceconnector.api.datamanagement.configuration.DataManagementApiConfiguration;
import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;

public class ContractAgreementApiExtension implements ServiceExtension {
    @Inject
    private WebService webService;

    @Inject
    private DataManagementApiConfiguration config;

    @Override
    public void initialize(ServiceExtensionContext context) {
        webService.registerResource(config.getContextAlias(), new ContractAgreementApiController(context.getMonitor()));
========
package org.eclipse.dataspaceconnector.dataplane.selector.store;

import org.eclipse.dataspaceconnector.spi.system.Provides;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;

@Provides(DataPlaneInstanceStore.class)
public class DataPlaneInstanceStoreExtension implements ServiceExtension {
    
    @Override
    public void initialize(ServiceExtensionContext context) {
        context.registerService(DataPlaneInstanceStore.class, new DefaultDataPlaneInstanceStore());
>>>>>>>> 98038f7e6bb101d81f96096a58d5a402f3494cc7:extensions/data-plane-selector/selector-store/src/main/java/org/eclipse/dataspaceconnector/dataplane/selector/store/DataPlaneInstanceStoreExtension.java
    }
}