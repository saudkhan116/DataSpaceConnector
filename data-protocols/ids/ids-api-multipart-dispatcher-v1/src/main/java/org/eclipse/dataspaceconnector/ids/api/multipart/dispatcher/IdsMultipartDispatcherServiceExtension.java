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

package org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher;

import okhttp3.OkHttpClient;
import org.eclipse.dataspaceconnector.ids.api.configuration.IdsApiConfiguration;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartArtifactRequestSender;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartCatalogDescriptionRequestSender;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartContractAgreementSender;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartContractOfferSender;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartContractRejectionSender;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartDescriptionRequestSender;
import org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.sender.MultipartEndpointDataReferenceRequestSender;
import org.eclipse.dataspaceconnector.ids.core.serialization.ObjectMapperFactory;
import org.eclipse.dataspaceconnector.ids.spi.IdsId;
import org.eclipse.dataspaceconnector.ids.spi.IdsIdParser;
import org.eclipse.dataspaceconnector.ids.spi.IdsType;
import org.eclipse.dataspaceconnector.ids.spi.transform.IdsTransformerRegistry;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.EdcSetting;
import org.eclipse.dataspaceconnector.spi.iam.IdentityService;
import org.eclipse.dataspaceconnector.spi.message.RemoteMessageDispatcherRegistry;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.security.Vault;
import org.eclipse.dataspaceconnector.spi.system.Inject;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtensionContext;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class IdsMultipartDispatcherServiceExtension implements ServiceExtension {

    @EdcSetting
    public static final String EDC_IDS_ID = "edc.ids.id";
    public static final String DEFAULT_EDC_IDS_ID = "urn:connector:edc";

    @EdcSetting
    public static final String IDS_WEBHOOK_ADDRESS = "ids.webhook.address";
    public static final String DEFAULT_IDS_WEBHOOK_ADDRESS = "http://localhost";

    private Monitor monitor;
    @Inject
    private OkHttpClient httpClient;
    @Inject
    private IdentityService identityService;
    @Inject
    private IdsTransformerRegistry transformerRegistry;
    @Inject
    private IdsApiConfiguration idsApiConfiguration;
    @Inject
    private ObjectMapperFactory objectMapperFactory;

    @Override
    public String name() {
        return "IDS Multipart Dispatcher API";
    }


    @Override
    public void initialize(ServiceExtensionContext context) {
        monitor = context.getMonitor();

        var connectorId = resolveConnectorId(context);

        // TODO ObjectMapper needs to be replaced by one capable to write proper IDS JSON-LD
        //      once https://github.com/eclipse-dataspaceconnector/DataSpaceConnector/issues/236 is done
        var objectMapper = objectMapperFactory.getObjectMapper();

        var idsWebhookAddress = getSetting(context, IDS_WEBHOOK_ADDRESS, DEFAULT_IDS_WEBHOOK_ADDRESS);
        var idsApiPath = idsApiConfiguration.getPath();
        var webhookPath = idsApiPath + (idsApiPath.endsWith("/") ? "data" : "/data");
        idsWebhookAddress = idsWebhookAddress + webhookPath;

        var multipartDispatcher = new IdsMultipartRemoteMessageDispatcher();
        multipartDispatcher.register(new MultipartArtifactRequestSender(connectorId, httpClient, objectMapper, monitor, context.getService(Vault.class), identityService, transformerRegistry, idsWebhookAddress));
        multipartDispatcher.register(new MultipartDescriptionRequestSender(connectorId, httpClient, objectMapper, monitor, identityService, transformerRegistry));
        multipartDispatcher.register(new MultipartContractOfferSender(connectorId, httpClient, objectMapper, monitor, identityService, transformerRegistry, idsWebhookAddress));
        multipartDispatcher.register(new MultipartContractAgreementSender(connectorId, httpClient, objectMapper, monitor, identityService, transformerRegistry, idsWebhookAddress));
        multipartDispatcher.register(new MultipartContractRejectionSender(connectorId, httpClient, objectMapper, monitor, identityService, transformerRegistry));
        multipartDispatcher.register(new MultipartCatalogDescriptionRequestSender(connectorId, httpClient, objectMapper, monitor, identityService, transformerRegistry));
        multipartDispatcher.register(new MultipartEndpointDataReferenceRequestSender(connectorId, httpClient, objectMapper, monitor, identityService, transformerRegistry));

        var registry = context.getService(RemoteMessageDispatcherRegistry.class);
        registry.register(multipartDispatcher);
    }

    private String resolveConnectorId(@NotNull ServiceExtensionContext context) {
        Objects.requireNonNull(context);

        String value = getSetting(context, EDC_IDS_ID, DEFAULT_EDC_IDS_ID);

        try {
            // Hint: use stringified uri to keep uri path and query
            IdsId idsId = IdsIdParser.parse(value);
            if (idsId != null && idsId.getType() == IdsType.CONNECTOR) {
                return idsId.getValue();
            }
        } catch (IllegalArgumentException e) {
            String message = "IDS Settings: Expected valid URN for setting '%s', but was %s'. Expected format: 'urn:connector:[id]'";
            throw new EdcException(String.format(message, EDC_IDS_ID, DEFAULT_EDC_IDS_ID));
        }

        return value;
    }

    @NotNull
    private String getSetting(@NotNull ServiceExtensionContext context, String key, String defaultValue) {
        String value = context.getSetting(key, null);

        if (value == null) {
            String message = "IDS Settings: No setting found for key '%s'. Using default value '%s'";
            monitor.warning(String.format(message, key, defaultValue));
            return defaultValue;
        } else {
            return value;
        }
    }

}
