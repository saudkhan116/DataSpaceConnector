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

package org.eclipse.dataspaceconnector.api.datamanagement.transferprocess;

import org.eclipse.dataspaceconnector.api.datamanagement.configuration.DataManagementApiConfiguration;
import org.eclipse.dataspaceconnector.api.datamanagement.transferprocess.transform.DataRequestToDataRequestDtoTransformer;
import org.eclipse.dataspaceconnector.api.datamanagement.transferprocess.transform.TransferProcessToTransferProcessDtoTransformer;
import org.eclipse.dataspaceconnector.api.transformer.DtoTransformerRegistry;
import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;

public class TransferProcessApiExtension implements ServiceExtension {
    @Inject
    private WebService webService;

    @Inject
    private DataManagementApiConfiguration configuration;

    @Inject
    private DtoTransformerRegistry transformerRegistry;

    @Override
    public void initialize(ServiceExtensionContext context) {
        webService.registerResource(configuration.getContextAlias(), new TransferProcessApiController(context.getMonitor()));

        transformerRegistry.register(new DataRequestToDataRequestDtoTransformer());
        transformerRegistry.register(new TransferProcessToTransferProcessDtoTransformer());
    }
}