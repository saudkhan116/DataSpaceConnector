/*
 *  Copyright (c) 2021 - 2022 Daimler TSS GmbH, Fraunhofer Institute for Software and Systems Engineering
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       Daimler TSS GmbH - Initial API and Implementation
 *       Fraunhofer Institute for Software and Systems Engineering - add methods
 *       Daimler TSS GmbH - introduce factory to create RequestInProcessMessage
 *
 */

package org.eclipse.dataspaceconnector.ids.api.multipart;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.fraunhofer.iais.eis.Contract;
import de.fraunhofer.iais.eis.ContractAgreementMessage;
import de.fraunhofer.iais.eis.ContractAgreementMessageBuilder;
import de.fraunhofer.iais.eis.ContractOfferMessage;
import de.fraunhofer.iais.eis.ContractOfferMessageBuilder;
import de.fraunhofer.iais.eis.ContractRejectionMessage;
import de.fraunhofer.iais.eis.ContractRejectionMessageBuilder;
import de.fraunhofer.iais.eis.ContractRequestMessage;
import de.fraunhofer.iais.eis.ContractRequestMessageBuilder;
import de.fraunhofer.iais.eis.DescriptionRequestMessage;
import de.fraunhofer.iais.eis.DescriptionRequestMessageBuilder;
import de.fraunhofer.iais.eis.DynamicAttributeToken;
import de.fraunhofer.iais.eis.DynamicAttributeTokenBuilder;
import de.fraunhofer.iais.eis.Message;
import jakarta.ws.rs.core.MediaType;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.MultipartReader;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.eclipse.dataspaceconnector.ids.api.multipart.controller.MultipartController;
import org.eclipse.dataspaceconnector.ids.api.multipart.message.MultipartResponse;
import org.eclipse.dataspaceconnector.ids.core.serialization.ObjectMapperFactory;
import org.eclipse.dataspaceconnector.ids.spi.IdsId;
import org.eclipse.dataspaceconnector.ids.spi.IdsIdParser;
import org.eclipse.dataspaceconnector.junit.launcher.EdcExtension;
import org.eclipse.dataspaceconnector.spi.system.ServiceExtension;
import org.eclipse.dataspaceconnector.spi.types.domain.asset.Asset;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.net.URI;
import java.net.http.HttpHeaders;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import static org.eclipse.dataspaceconnector.common.testfixtures.TestUtils.getFreePort;
import static org.eclipse.dataspaceconnector.ids.spi.IdsConstants.IDS_WEBHOOK_ADDRESS_PROPERTY;

@ExtendWith(EdcExtension.class)
abstract class AbstractMultipartControllerIntegrationTest {
    public static final String HEADER = "header";
    public static final String PAYLOAD = "payload";
    // TODO needs to be replaced by an objectmapper capable to understand IDS JSON-LD
    //      once https://github.com/eclipse-dataspaceconnector/DataSpaceConnector/issues/236 is done
    private static ObjectMapper objectMapper;
    private static final AtomicReference<Integer> PORT = new AtomicReference<>();
    private static final AtomicReference<Integer> IDS_PORT = new AtomicReference<>();
    private static final List<Asset> ASSETS = new LinkedList<>();
    
    static {
        objectMapper = new ObjectMapperFactory().getObjectMapper();
    }

    @AfterEach
    void after() {
        ASSETS.clear();

        for (String key : getSystemProperties().keySet()) {
            System.clearProperty(key);
        }

        PORT.set(null);
        IDS_PORT.set(null);
    }

    @BeforeEach
    protected void before(EdcExtension extension) {
        PORT.set(getFreePort());
        IDS_PORT.set(getFreePort());

        for (Map.Entry<String, String> entry : getSystemProperties().entrySet()) {
            System.setProperty(entry.getKey(), entry.getValue());
        }

        extension.registerSystemExtension(ServiceExtension.class, new IdsApiMultipartEndpointV1IntegrationTestServiceExtension(ASSETS));
    }

    protected void addAsset(Asset asset) {
        ASSETS.add(asset);
    }

    protected int getPort() {
        return PORT.get();
    }

    protected int getIdsPort() {
        return IDS_PORT.get();
    }

    protected String getUrl() {
        return String.format("http://localhost:%s/api/v1/ids%s", getIdsPort(), MultipartController.PATH);
    }

    protected abstract Map<String, String> getSystemProperties();

    protected DynamicAttributeToken getDynamicAttributeToken() {
        return new DynamicAttributeTokenBuilder()._tokenValue_("fake").build();
    }

    protected String toJson(Message message) throws Exception {
        return objectMapper.writeValueAsString(message);
    }

    protected String toJson(Contract contract) throws Exception {
        return objectMapper.writeValueAsString(contract);
    }

    protected DescriptionRequestMessage getDescriptionRequestMessage() {
        return getDescriptionRequestMessage(null);
    }

    protected DescriptionRequestMessage getDescriptionRequestMessage(IdsId idsId) {
        DescriptionRequestMessageBuilder builder = new DescriptionRequestMessageBuilder()
                ._securityToken_(getDynamicAttributeToken())
                ._issuerConnector_(URI.create("issuerConnector"));

        if (idsId != null) {
            builder._requestedElement_(
                    URI.create(String.join(IdsIdParser.DELIMITER, IdsIdParser.SCHEME, idsId.getType().getValue(), idsId.getValue())));
        }
        return builder.build();
    }

    protected ContractRequestMessage getContractRequestMessage() {
        var message = new ContractRequestMessageBuilder()
                ._correlationMessage_(URI.create("correlationId"))
                ._securityToken_(getDynamicAttributeToken())
                ._senderAgent_(URI.create("sender"))
                ._issuerConnector_(URI.create("issuerConnector"))
                .build();
        message.setProperty(IDS_WEBHOOK_ADDRESS_PROPERTY, "http://someUrl");
        return message;
    }

    protected ContractAgreementMessage getContractAgreementMessage() {
        return new ContractAgreementMessageBuilder()
                ._correlationMessage_(URI.create("correlationId"))
                ._securityToken_(getDynamicAttributeToken())
                ._issuerConnector_(URI.create("issuerConnector"))
                .build();
    }

    protected ContractRejectionMessage getContractRejectionMessage() {
        return new ContractRejectionMessageBuilder()
                ._correlationMessage_(URI.create("correlationId"))
                ._transferContract_(URI.create("contractId"))
                ._securityToken_(getDynamicAttributeToken())
                ._issuerConnector_(URI.create("issuerConnector"))
                .build();
    }

    protected ContractOfferMessage getContractOfferMessage() {
        return new ContractOfferMessageBuilder()
                ._correlationMessage_(URI.create("correlationId"))
                ._senderAgent_(URI.create("sender"))
                ._securityToken_(getDynamicAttributeToken())
                ._issuerConnector_(URI.create("issuerConnector"))
                .build();
    }

    // create the multipart-form-data request having the given message in its "header" multipart payload
    protected Request createRequest(Message message) throws Exception {
        Objects.requireNonNull(message);

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(okhttp3.MediaType.get(MediaType.MULTIPART_FORM_DATA))
                .addPart(createIdsMessageHeaderMultipart(message))
                .build();

        return new Request.Builder()
                .url(Objects.requireNonNull(HttpUrl.parse(getUrl())))
                .addHeader("Content-Type", MediaType.MULTIPART_FORM_DATA)
                .post(multipartBody)
                .build();
    }

    protected Request createRequestWithPayload(Message message, Contract payload) throws Exception {
        Objects.requireNonNull(message);

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(okhttp3.MediaType.get(MediaType.MULTIPART_FORM_DATA))
                .addPart(createIdsMessageHeaderMultipart(message))
                .addPart(createIdsMessagePayloadMultipart(payload))
                .build();

        return new Request.Builder()
                .url(Objects.requireNonNull(HttpUrl.parse(getUrl())))
                .addHeader("Content-Type", MediaType.MULTIPART_FORM_DATA)
                .post(multipartBody)
                .build();
    }

    // extract response to MultipartResponse container object
    protected MultipartResponse extractMultipartResponse(Response response) throws Exception {
        Message header = null;
        byte[] payload = null;
        try (MultipartReader multipartReader = new MultipartReader(Objects.requireNonNull(response.body()))) {
            MultipartReader.Part part;
            while ((part = multipartReader.nextPart()) != null) {
                HttpHeaders httpHeaders = HttpHeaders.of(
                        part.headers().toMultimap(),
                        (a, b) -> a.equalsIgnoreCase("Content-Disposition")
                );

                String value = httpHeaders.firstValue("Content-Disposition").orElse(null);
                if (value == null) {
                    continue;
                }

                ContentDisposition contentDisposition = new ContentDisposition(value);
                String multipartName = contentDisposition.getParameters().get("name");
                if (multipartName == null) {
                    continue;
                }

                if (multipartName.equalsIgnoreCase(HEADER)) {
                    header = objectMapper.readValue(part.body().inputStream(), Message.class);
                } else if (multipartName.equalsIgnoreCase(PAYLOAD)) {
                    payload = part.body().readByteArray();
                }
            }
        }

        return MultipartResponse.Builder.newInstance().header(header).payload(payload).build();
    }

    // extract response to list of NamedMultipartContent container object
    protected List<NamedMultipartContent> extractNamedMultipartContent(Response response) throws Exception {
        List<NamedMultipartContent> namedMultipartContentList = new LinkedList<>();
        try (MultipartReader multipartReader = new MultipartReader(Objects.requireNonNull(response.body()))) {
            MultipartReader.Part part;
            while ((part = multipartReader.nextPart()) != null) {
                HttpHeaders httpHeaders = HttpHeaders.of(
                        part.headers().toMultimap(),
                        (a, b) -> a.equalsIgnoreCase("Content-Disposition")
                );

                String value = httpHeaders.firstValue("Content-Disposition").orElse(null);
                if (value == null) {
                    continue;
                }

                ContentDisposition contentDisposition = new ContentDisposition(value);
                String multipartName = contentDisposition.getParameters().get("name");
                if (multipartName == null) {
                    continue;
                }

                namedMultipartContentList.add(new NamedMultipartContent(multipartName, part.body().readByteArray()));
            }
        }

        return namedMultipartContentList;
    }

    // create the "header" multipart payload
    private MultipartBody.Part createIdsMessageHeaderMultipart(Message message) throws Exception {
        Headers headers = new Headers.Builder()
                .add("Content-Disposition", "form-data; name=\"header\"")
                .build();

        RequestBody requestBody = RequestBody.create(
                toJson(message),
                okhttp3.MediaType.get(MediaType.APPLICATION_JSON));

        return MultipartBody.Part.create(headers, requestBody);
    }

    // create the "header" multipart payload
    private MultipartBody.Part createIdsMessagePayloadMultipart(Contract contract) throws Exception {
        Headers headers = new Headers.Builder()
                .add("Content-Disposition", "form-data; name=\"payload\"")
                .build();

        RequestBody requestBody = RequestBody.create(
                toJson(contract),
                okhttp3.MediaType.get(MediaType.APPLICATION_JSON));

        return MultipartBody.Part.create(headers, requestBody);
    }

    public static class NamedMultipartContent {
        private final String name;
        private final byte[] content;

        NamedMultipartContent(String name, byte[] content) {
            this.name = name;
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public byte[] getContent() {
            return content;
        }
    }
}
