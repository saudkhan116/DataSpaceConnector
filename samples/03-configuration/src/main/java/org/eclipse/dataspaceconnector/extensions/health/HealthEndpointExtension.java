/*
 *  Copyright (c) 2021 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - Initial implementation
 *
 */

package org.eclipse.dataspaceconnector.extensions.health;

import org.eclipse.dataspaceconnector.spi.WebService;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;

public class HealthEndpointExtension implements ServiceExtension {

    @Inject
    WebService webService;

    private static final String LOG_PREFIX_SETTING = "edc.samples.03.logprefix";

    @Override
    public void initialize(ServiceExtensionContext context) {
        var logPrefix = context.getSetting(LOG_PREFIX_SETTING, "health");
        webService.registerResource(new HealthApiController(context.getMonitor(), logPrefix));
    }
}
