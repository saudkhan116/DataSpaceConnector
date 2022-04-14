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

import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.eclipse.dataspaceconnector.spi.transfer.TransferProcessManager;

public class ApiEndpointExtension implements ServiceExtension {

    @Inject
    private WebService webService;

    @Inject
    private TransferProcessManager transferProcessManager;

    @Override
    public String name() {
        return "API Endpoint";
    }

    @Override
    public void initialize(ServiceExtensionContext context) {
        webService.registerResource(new ConsumerApiController(context.getMonitor(), transferProcessManager));
    }
}
