/*
 *  Copyright (c) 2022 Microsoft Corporation
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Microsoft Corporation - initial API and implementation
 *       ZF Friedrichshafen AG - add management api configurations
 *       Fraunhofer Institute for Software and Systems Engineering - added IDS API context
 *
 */

package org.eclipse.dataspaceconnector.system.tests.local;

import org.eclipse.dataspaceconnector.junit.launcher.EdcRuntimeExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.io.File;
import java.util.Map;

import static java.lang.String.format;
import static org.eclipse.dataspaceconnector.common.testfixtures.TestUtils.getFreePort;
import static org.eclipse.dataspaceconnector.common.testfixtures.TestUtils.tempDirectory;
import static org.eclipse.dataspaceconnector.system.tests.utils.FileTransferSimulationUtils.PROVIDER_ASSET_NAME;

/**
 * Class providing a consumer and provider EdcRuntimeExtension used to test a file transfer.
 */
public abstract class FileTransferEdcRuntime {
    public static final String CONSUMER_ASSET_PATH = new File(tempDirectory(), "output.txt").getAbsolutePath();
    public static final int CONSUMER_CONNECTOR_PORT = getFreePort();
    public static final int CONSUMER_MANAGEMENT_PORT = getFreePort();
    public static final String CONSUMER_CONNECTOR_PATH = "/api";
    public static final String CONSUMER_MANAGEMENT_PATH = "/api/v1/data";
    public static final String CONSUMER_CONNECTOR_MANAGEMENT_URL = "http://localhost:" + CONSUMER_MANAGEMENT_PORT;
    public static final int CONSUMER_IDS_API_PORT = getFreePort();
    public static final String CONSUMER_IDS_API = "http://localhost:" + CONSUMER_IDS_API_PORT;

    public static final String PROVIDER_ASSET_PATH = format("%s/%s.txt", tempDirectory(), PROVIDER_ASSET_NAME);
    public static final int PROVIDER_CONNECTOR_PORT = getFreePort();
    public static final int PROVIDER_MANAGEMENT_PORT = getFreePort();
    public static final String PROVIDER_CONNECTOR_PATH = "/api";
    public static final String PROVIDER_MANAGEMENT_PATH = "/api/v1/data";
    public static final int PROVIDER_IDS_API_PORT = getFreePort();
    public static final String PROVIDER_IDS_API = "http://localhost:" + PROVIDER_IDS_API_PORT;

    @RegisterExtension
    protected static EdcRuntimeExtension consumer = new EdcRuntimeExtension(
            ":system-tests:runtimes:file-transfer-consumer",
            "consumer",
            Map.of(
                    "web.http.port", String.valueOf(CONSUMER_CONNECTOR_PORT),
                    "web.http.path", CONSUMER_CONNECTOR_PATH,
                    "web.http.data.port", String.valueOf(CONSUMER_MANAGEMENT_PORT),
                    "web.http.data.path", CONSUMER_MANAGEMENT_PATH,
                    "web.http.ids.port", String.valueOf(CONSUMER_IDS_API_PORT),
                    "web.http.ids.path", "/api/v1/ids",
                    "ids.webhook.address", CONSUMER_IDS_API));

    @RegisterExtension
    protected static EdcRuntimeExtension provider = new EdcRuntimeExtension(
            ":system-tests:runtimes:file-transfer-provider",
            "provider",
            Map.of(
                    "web.http.port", String.valueOf(PROVIDER_CONNECTOR_PORT),
                    "edc.test.asset.path", PROVIDER_ASSET_PATH,
                    "web.http.path", PROVIDER_CONNECTOR_PATH,
                    "web.http.data.port", String.valueOf(PROVIDER_MANAGEMENT_PORT),
                    "web.http.data.path", PROVIDER_MANAGEMENT_PATH,
                    "web.http.ids.port", String.valueOf(PROVIDER_IDS_API_PORT),
                    "web.http.ids.path", "/api/v1/ids",
                    "edc.samples.04.asset.path", PROVIDER_ASSET_PATH,
                    "ids.webhook.address", PROVIDER_IDS_API));
}
