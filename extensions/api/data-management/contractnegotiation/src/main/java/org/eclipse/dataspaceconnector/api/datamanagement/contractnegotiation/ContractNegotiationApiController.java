/*
 *  Copyright (c) 2022 ZF Friedrichshafen AG
 *
 *  This program and the accompanying materials are made available under the
 *  terms of the Apache License, Version 2.0 which is available at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  SPDX-License-Identifier: Apache-2.0
 *
 *  Contributors:
 *       ZF Friedrichshafen AG - Initial API and Implementation
 *       Microsoft Corporation - Added initiate-negotiation endpoint
 *       Bayerische Motoren Werke Aktiengesellschaft (BMW AG) - Improvements
 *
 */

package org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.model.ContractAgreementDto;
import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.model.ContractNegotiationDto;
import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.model.NegotiationId;
import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.model.NegotiationInitiateRequestDto;
import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.model.NegotiationState;
import org.eclipse.dataspaceconnector.api.datamanagement.contractnegotiation.service.ContractNegotiationService;
import org.eclipse.dataspaceconnector.api.exception.ObjectExistsException;
import org.eclipse.dataspaceconnector.api.exception.ObjectNotFoundException;
import org.eclipse.dataspaceconnector.api.query.QuerySpecDto;
import org.eclipse.dataspaceconnector.api.result.ServiceResult;
import org.eclipse.dataspaceconnector.api.transformer.DtoTransformerRegistry;
import org.eclipse.dataspaceconnector.spi.EdcException;
import org.eclipse.dataspaceconnector.spi.monitor.Monitor;
import org.eclipse.dataspaceconnector.spi.query.QuerySpec;
import org.eclipse.dataspaceconnector.spi.result.Result;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.ContractNegotiation;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.negotiation.ContractOfferRequest;
import org.eclipse.dataspaceconnector.spi.types.domain.contract.offer.ContractDefinition;
import org.eclipse.dataspaceconnector.spi.types.domain.material.battery.BatteryPassport;
import org.eclipse.dataspaceconnector.spi.types.domain.material.battery.ProviderMetadata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })

@Path("/contractnegotiations")
public class ContractNegotiationApiController implements ContractNegotiationApi {
    private final Monitor monitor;
    private final ContractNegotiationService service;
    private final DtoTransformerRegistry transformerRegistry;

    private static final String REGISTRY_PATH = "/samples/04.0-file-transfer/registry";
    private static final String DATA_PATH = "/samples/04.0-file-transfer/data";
    private static final String METADATA_PATH = REGISTRY_PATH + "/metadata";

    public ContractNegotiationApiController(Monitor monitor, ContractNegotiationService service, DtoTransformerRegistry transformerRegistry) {
        this.monitor = monitor;
        this.service = service;
        this.transformerRegistry = transformerRegistry;
    }

    @GET
    @Override
    public List<ContractNegotiationDto> getNegotiations(@Valid @BeanParam QuerySpecDto querySpecDto) {
        var result = transformerRegistry.transform(querySpecDto, QuerySpec.class);
        if (result.failed()) {
            monitor.warning("Error transforming QuerySpec: " + String.join(", ", result.getFailureMessages()));
            throw new IllegalArgumentException("Cannot transform QuerySpecDto object");
        }

        var spec = result.getContent();

        monitor.debug(format("Get all contract definitions %s", spec));

        return service.query(spec).stream()
                .map(it -> transformerRegistry.transform(it, ContractNegotiationDto.class))
                .filter(Result::succeeded)
                .map(Result::getContent)
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    @Override
    public ContractNegotiationDto getNegotiation(@PathParam("id") String id) {
        monitor.debug(format("Get contract negotiation with id %s", id));

        return Optional.of(id)
                .map(service::findbyId)
                .map(it -> transformerRegistry.transform(it, ContractNegotiationDto.class))
                .filter(Result::succeeded)
                .map(Result::getContent)
                .orElseThrow(() -> new ObjectNotFoundException(ContractDefinition.class, id));
    }

    @GET
    @Path("/{id}/state")
    @Override
    public NegotiationState getNegotiationState(@PathParam("id") String id) {
        monitor.debug(format("Get contract negotiation state with id %s", id));
        return Optional.of(id)
                .map(service::getState)
                .map(NegotiationState::new)
                .orElseThrow(() -> new ObjectNotFoundException(ContractDefinition.class, id));
    }

    @GET
    @Path("/{id}/agreement")
    @Override
    public ContractAgreementDto getAgreementForNegotiation(@PathParam("id") String negotiationId) {
        monitor.debug(format("Get contract agreement of negotiation with id %s", negotiationId));

        return Optional.of(negotiationId)
                .map(service::getForNegotiation)
                .map(it -> transformerRegistry.transform(it, ContractAgreementDto.class))
                .filter(Result::succeeded)
                .map(Result::getContent)
                .orElseThrow(() -> new ObjectNotFoundException(ContractDefinition.class, negotiationId));
    }

    @POST
    @Override
    public NegotiationId initiateContractNegotiation(@Valid NegotiationInitiateRequestDto initiateDto) {
        if (!isValid(initiateDto)) {
            throw new IllegalArgumentException("Negotiation request is invalid");
        }

        var transformResult = transformerRegistry.transform(initiateDto, ContractOfferRequest.class);
        if (transformResult.failed()) {
            throw new IllegalArgumentException("Negotiation request is invalid");
        }

        var request = transformResult.getContent();

        ContractNegotiation contractNegotiation = service.initiateNegotiation(request);
        return new NegotiationId(contractNegotiation.getId());
    }

    @POST
    @Path("/{id}/cancel")
    @Override
    public void cancelNegotiation(@PathParam("id") String id) {
        monitor.debug(format("Attempting to cancel contract definition with id %s", id));
        var result = service.cancel(id);
        if (result.succeeded()) {
            monitor.debug(format("Contract negotiation canceled %s", result.getContent().getId()));
        } else {
            handleFailedResult(result, id);
        }
    }

    @POST
    @Path("/{id}/decline")
    @Override
    public void declineNegotiation(@PathParam("id") String id) {
        monitor.debug(format("Attempting to decline contract definition with id %s", id));
        var result = service.decline(id);
        if (result.succeeded()) {
            monitor.debug(format("Contract negotiation declined %s", result.getContent().getId()));
        } else {
            handleFailedResult(result, id);
        }
    }

    private boolean isValid(NegotiationInitiateRequestDto initiateDto) {
        return StringUtils.isNoneBlank(initiateDto.getConnectorId(), initiateDto.getConnectorAddress(), initiateDto.getProtocol(),
                initiateDto.getOffer().getOfferId(), initiateDto.getOffer().getAssetId());
    }

    private void handleFailedResult(ServiceResult<ContractNegotiation> result, String id) {
        switch (result.reason()) {
            case NOT_FOUND:
                throw new ObjectNotFoundException(ContractNegotiation.class, id);
            case CONFLICT:
                throw new ObjectExistsException(ContractNegotiation.class, id);
            default:
                throw new EdcException("unexpected error");
        }
    }


    //// custom methods ////

    @GET
    @Path("provider/metadata/{selectedProvider}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProviderAccessInformation(@PathParam("selectedProvider") String selectedProvider) {

        String result = "";
        if (selectedProvider == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String currentDir = System.getProperty("user.dir") + METADATA_PATH;
        String filename = currentDir + "/" + selectedProvider + ".json";
        ProviderMetadata metadata = new ProviderMetadata();
        result = readFile(filename);
        //getJsonData(metadata, filename);
        if (result == "")
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.ok(result).header("Access-Control-Allow-Origin", "*").allow("OPTIONS").build();
    }

    @GET
    @Path("/")
    public Response helloWorld() {

        return Response.ok("Hello World..!!").build();

    }

    @GET
    @Path("/login")
    public Response isUserAuthenticated() {

        return Response.ok("User has been authenticated successfully..!!").build();

    }

    @GET
    @Path("passport/display/{filename}")
    public Response printProductPassport(@PathParam("filename") String filename) {

        String result = "";
        if (filename == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        String filePath = System.getProperty("user.dir") + DATA_PATH + "/" + filename;
        result = readFile(filePath);
        return Response.ok(result).build();

    }

    private String getJsonData(Object object, String filename) {

        String result = "";
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filename);
        try {
            if (object instanceof ProviderMetadata) {
                ProviderMetadata metadata = objectMapper.readValue(file, ProviderMetadata.class);
                ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
                result = objectWriter.writeValueAsString(metadata);
//                System.out.println("Id: " + metadata.getId()  + "_Provider: " + metadata.getProvider() + "_Role: " +metadata.getRole());
//                List<Battery> batteries = metadata.getBatteries();
//        		for (Battery battery : batteries) {
//        			System.out.println(battery.getId() +"_"+ battery.getName() + "_" + battery.getType() );
//        		}
            }
            else if (object instanceof BatteryPassport) {
                BatteryPassport batteryPass = objectMapper.readValue(file, BatteryPassport.class);
                ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
                result = objectWriter.writeValueAsString(batteryPass);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something went wrong..!!");
        }

        return result;
    }


    private String readFile(String filename) {

        String result = "";
        try
        {
            var list = Files.readAllLines(Paths.get(filename));
            result = String.join("", list);
            System.out.println(result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    }


}
