# Battery Passport Use Case

- An initial PoC to show the information of the battery Passport in human readable format, after selecting the provider and battery.

- Consists of consumer and provider EDC connectors
- The consumer connector has to receive the json file from the data provider, it will agree on an contractoffer provided in a service registry file.
- The provider connector is running and providing a sample file with battery Passport data.


## Assumptions

- Service registry is hosted on cloud storage e.g., Azure, AWS bucket, ... 
- The metadata and contract offer have already been placed by the provider in the service registry.
- Consumer and provider connectors are hosted in different places.
- The final battery passport file is named after test-document.json

NOTE: All components are running in same machine (localhost), so we have all in one place. Later, they can be all distributed.


#### Step 1: Launch consumer and provider connectors in separate terminals

```bash
# consumer connector
java -Dedc.fs.config=samples/04.0-file-transfer/consumer/config.properties -jar samples/04.0-file-transfer/consumer/build/libs/consumer.jar

# provider connector
java -Dedc.fs.config=samples/04.0-file-transfer/provider/config.properties -jar samples/04.0-file-transfer/provider/build/libs/provider.jar
```

#### Step 2: Fetch the metadata of selected provider from service registry

E.g., provider: BMW and role: OEM

```bash
curl -X GET "http://localhost:9191/api/provider/metadata/BMW?role=OEM
```

#### Step 3: Copy the contractoffer from the attribute contractoffers field from the json response in previous step

#### Step 4: Fetch the provider's contract offer from service registry. Replace the placeholder {contractoffer.json} with the one copied

```bash
curl -X POST -H "Content-Type: application/json" -d @samples/04.0-file-transfer/registry/contractoffers/{contractoffer.json} "http://localhost:9191/api/negotiation?connectorAddress=http://localhost:8181/api/ids/multipart"
```
Output
```bash
DEBUG 2022-02-23T13:13:03.9388775 [Consumer] ContractNegotiation initiated. 943e4e90-3ab5-4ecb-98a3-cc8cba4c559e is now in state INITIAL.
DEBUG 2022-02-23T13:13:07.4123234 Response received from connector. Status 200
DEBUG 2022-02-23T13:13:07.4943326 [Consumer] ContractNegotiation 943e4e90-3ab5-4ecb-98a3-cc8cba4c559e is now in state REQUESTED.
DEBUG 2022-02-23T13:13:09.6621096 [Consumer] Contract agreement received. Validation successful.
DEBUG 2022-02-23T13:13:09.6621096 [Consumer] ContractNegotiation 943e4e90-3ab5-4ecb-98a3-cc8cba4c559e is now in state CONFIRMED.
```

#### Step 5: Lookup the contract agreement ID. Copy the contract negotiation UUID from the consumer end (previous output) and replace it with placeholder {UUID}

```bash
curl -X GET -H 'X-Api-Key: password' "http://localhost:9191/api/control/negotiation/{UUID}/state"
```

Output

```bash
{"status":"CONFIRMED","contractAgreementId":"1:5a4a3e65-433f-4149-a772-22a4761f331c"}
```

#### Step 6: Request the file from the provider

- Replace the placeholder {path/on/yourmachine} to the path to "data" folder (data folder resides at consumer end).
- Copy the contract agreement ID from previous the response and replace it with the placeholder {agreement ID}.

```bash
curl -X POST "http://localhost:9191/api/file/test-document?connectorAddress=http://localhost:8181/api/ids/multipart&destination={path/on/yourmachine}&contractId={agreement ID}"
```

#### Step 7: Check the logs to ensure the successful file transfer process

Provider connector

```bash
DEBUG 2022-02-23T13:13:07.3453226 [Provider] ContractNegotiation initiated. 07a909bf-783e-4fe6-9d77-db1948eb9f4d is now in state REQUESTED.
DEBUG 2022-02-23T13:13:07.3563229 [Provider] Contract offer received. Will be approved.
DEBUG 2022-02-23T13:13:07.3593223 [Provider] ContractNegotiation 07a909bf-783e-4fe6-9d77-db1948eb9f4d is now in state CONFIRMING.
DEBUG 2022-02-23T13:13:09.68011 Response received from connector. Status 200
DEBUG 2022-02-23T13:13:09.6971104 [Provider] ContractNegotiation 07a909bf-783e-4fe6-9d77-db1948eb9f4d is now in state CONFIRMED.
INFO 2022-02-23T13:19:10.0515647 Copying data from File to File
INFO 2022-02-23T13:19:10.0755698 Successfully copied file to ..\DataSpaceConnector\samples\04.0-file-transfer\data\test-document.json
```

Consumer connector

```bash
DEBUG 2022-02-23T13:13:03.9388775 [Consumer] ContractNegotiation initiated. 943e4e90-3ab5-4ecb-98a3-cc8cba4c559e is now in state INITIAL.
DEBUG 2022-02-23T13:13:07.4123234 Response received from connector. Status 200
DEBUG 2022-02-23T13:13:07.4943326 [Consumer] ContractNegotiation 943e4e90-3ab5-4ecb-98a3-cc8cba4c559e is now in state REQUESTED.
DEBUG 2022-02-23T13:13:09.6621096 [Consumer] Contract agreement received. Validation successful.
DEBUG 2022-02-23T13:13:09.6621096 [Consumer] ContractNegotiation 943e4e90-3ab5-4ecb-98a3-cc8cba4c559e is now in state CONFIRMED.
INFO 2022-02-23T13:19:03.4313635 Received request for file test-document against provider http://localhost:8181/api/ids/multipart
DEBUG 2022-02-23T13:19:07.3954669 Response received from connector. Status 200
INFO 2022-02-23T13:19:07.4014678 Object received: org.eclipse.dataspaceconnector.ids.api.multipart.dispatcher.message.MultipartRequestInProcessResponse@4fdb57d9
DEBUG 2022-02-23T13:19:12.375764 Process f1bca05e-3373-42bf-87b0-4c6ded597d7f is now COMPLETED
INFO 2022-02-23T13:19:12.3837662 Transfer Listener successfully wrote file ..\DataSpaceConnector\samples\04.0-file-transfer\data\marker.txt
```

