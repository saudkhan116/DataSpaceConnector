<template>
  <div v-if="loading" style="padding: 18% 42% 18% 42%">
    <span>
      <img src="../assets/loading.gif" height="200" width="250"/>
    </span>
  </div>
  <div v-else>
    <Header :batteryId="data.generalInformation" />
    <div class="container">
      <GeneralInformation
        sectionTitle="General information"
        :generalInformation="data.generalInformation"
      />
      <BatteryComposition
        sectionTitle="Battery Composition"
        :batteryComposition="data.batteryComposition"
      />
      <StateOfHealth
        sectionTitle="State of Health"
        :stateOfHealth="data.stateOfHealth"
      />
      <ParametersOfTheBattery
        sectionTitle="Parameters of The Battery"
        :parametersOfTheBattery="data.parametersOfTheBattery"
      />
      <DismantlingProcedures
        sectionTitle="Dismantling procedures"
        :dismantlingProcedures="data.dismantlingProcedures"
      />
      <!-- <SafetyInformation
        sectionTitle="Safety information"
        :safetyInformation="data.safetyInformation"
      /> -->
      <InformationResponsibleSourcing
        sectionTitle="Information responsible sourcing"
        :informationResponsibleSourcing="data.informationResponsibleSourcing"
      />

      <!-- <AdditionalInformation
        sectionTitle="Additional information"
        :additionalInformation="data.additionalInformation"
      /> -->
    </div>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import GeneralInformation from "@/components/GeneralInformation.vue";
import BatteryComposition from "@/components/BatteryComposition.vue";
import StateOfHealth from "@/components/StateOfHealth.vue";
import ParametersOfTheBattery from "@/components/ParametersOfTheBattery.vue";
import DismantlingProcedures from "@/components/DismantlingProcedures.vue";
import SafetyInformation from "@/components/SafetyInformation.vue";
import InformationResponsibleSourcing from "@/components/InformationResponsibleSourcing.vue";
import AdditionalInformation from "@/components/AdditionalInformation.vue";

import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";
import axios from "axios";
import { reactive } from "vue";

export default {
  name: "PassportView",
  components: {
    Header,
    GeneralInformation,
    BatteryComposition,
    StateOfHealth,
    ParametersOfTheBattery,
    DismantlingProcedures,
    SafetyInformation,
    InformationResponsibleSourcing,
    AdditionalInformation,
    Footer,
  },
  provide() {
    return {
      loadingState: new reactive(() => this.loadingState),
    };
  },
  data() {
    return {
      data: null,
      loading: true,
      digitalTwinId: "urn:uuid:365e6fbe-bb34-11ec-8422-0242ac120001",
      digitalTwinSubmodelId: "urn:uuid:61125dc3-5e6f-4f4b-838d-447432b97919",
      twinRegistryUrl: "http://localhost:4243",
      aasProxyUrl: "http://localhost:4245",
    };
  },
  methods: {
    async fetchData() {
      const res = await fetch("http://localhost:3000/334593247");
      const data = await res.json();
      console.log(data);
      return data;
    },
    getProductPassport(asset, destinationPath, contractId) {
      return new Promise((resolve) => {
        var jsonData = {
          protocol: "ids-multipart",
          assetId: asset,
          contractId: contractId,
          dataDestination: {
            properties: {
              path: destinationPath + "/" + asset + ".json",
              keyName: "keyName",
              type: "File",
            },
          },
          transferType: {
            contentType: "application/octet-stream",
            isFinite: true,
          },
          managedResources: false,
          connectorAddress: "http://edc-provider:8282/api/v1/ids/data",
          connectorId: "consumer",
        };

        // "connectorAddress": "http://edc-provider:8282/api/v1/ids/data",

        //axios.post('/consumer/data/transferprocess', jsonData, {
        axios
          .post("/consumer/data/transferprocess", jsonData, {
            headers: {
              "X-Api-Key": "password",
            },
          })
          .then((response) => {
            console.log(response.data);
            resolve(response.data);
          })
          .catch((e) => {
            this.errors.push(e);
            resolve("rejected");
          });
      });
    },
    getDigitalTwinId: function(){
      return new Promise((resolve) => {
        let specificAssetIds = '[{ "key": "batteryId", "value": "334593247" },{ "key": "passportNumber", "value": "12345" }]'
        let encodedAssetIds = encodeURIComponent(specificAssetIds);
        axios
          .get( this.aasProxyUrl + '/lookup/shells?assetIds=' + encodedAssetIds )
          .then((response) => {
            console.log(response.data);
            resolve(response.data);
          })
          .catch((e) => {
            this.errors.push(e);
            resolve("rejected");
          });
      });
      
      // let specificAssetIds = '[{ "key": "batteryId", "value": "334593247" },{ "key": "passportNumber", "value": "12345" }]'
      // let encodedAssetIds = encodeURIComponent(specificAssetIds);
      // const res =  axios.get( this.aasProxyUrl + '/lookup/shells?assetIds=' + encodedAssetIds );
      // const uuid = res.json();
      // console.log('Digital Twin uuid' + uuid);
      // return uuid;
    },
    getDigitalTwinObjectById: function(digitalTwinId){
      //const res =  axios.get("http://localhost:4243/registry/shell-descriptors/urn:uuid:365e6fbe-bb34-11ec-8422-0242ac120001"); // Without AAS Proxy
      return new Promise((resolve) => {
        axios
          .get( this.aasProxyUrl + '/registry/shell-descriptors/' + digitalTwinId )   //Calling with AAS Proxy
          .then((response) => {
            console.log(response.data);
            resolve(response.data);
          })
          .catch((e) => {
            this.errors.push(e);
            resolve("rejected");
          });
      });
      
      
      
      
      // //const res =  axios.get("http://localhost:4243/registry/shell-descriptors/urn:uuid:365e6fbe-bb34-11ec-8422-0242ac120001"); // Without AAS Proxy
      // //Calling with AAS Proxy
      // const res =  axios.get( this.aasProxyUrl + '/registry/shell-descriptors/' + digitalTwinId );
      // const digitalTwin = res.json();
      // console.log(digitalTwin);
      // return digitalTwin;
    },
    getSubmodelData: function(digitalTwin){
      //const res =  axios.get("http://localhost:8193/api/service/urn:uuid:365e6fbe-bb34-11ec-8422-0242ac120001-urn:uuid:61125dc3-5e6f-4f4b-838d-447432b97919/submodel?provider-connector-url=http://provider-control-plane:8282"); // Without AAS Proxy
      //Calling with AAS Proxy 
      return new Promise((resolve) => {
        axios.get( this.aasProxyUrl + '/shells/' + digitalTwin.identification + '/aas/' + digitalTwin.submodelDescriptors[0].identification + '/submodel?content=value&extent=withBlobValue',{
          auth: {
              username: 'someuser',
              password: 'somepassword'
            }
        })
          .then((response) => {
            console.log(response.data);
            resolve(response.data);
          })
          .catch((e) => {
            this.errors.push(e);
            resolve("rejected");
          });
      });
    },
    async getPassport(){
      const digitalTwinId = await this.getDigitalTwinId();
      const digitalTwin = await this.getDigitalTwinObjectById(digitalTwinId);
      const response = await this.getSubmodelData(digitalTwin);
      return response;
    },
    displayProductPassport(filename) {
      return new Promise((resolve) => {
        //axios.get('/consumer/data/contractnegotiations/passport/display/' + filename, {
        axios
          .get(
            "/consumer/data/contractnegotiations/passport/display/" + filename,
            {
              headers: {
                "X-Api-Key": "password",
              },
            }
          )
          .then((response) => {
            console.log(response.data);
            resolve(response.data);
          })
          .catch((e) => {
            this.errors.push(e);
            resolve("rejected");
          });
      });
    },
  },
  async created() {
    //this.data = await this.fetchData();
    //console.log(data);
    let contractId = this.$route.params.contractId;
    const destinationPath = "/app/samples/04.0-file-transfer/data"; // set different path for containers
    let asset = "";
    let user = localStorage.getItem("user-info");
    let role = JSON.parse(user).role;
    if (role.toLowerCase() == "dismantler") asset = "test-document_dismantler";
    else if (role.toLowerCase() == "oem") asset = "test-document_oem";
    else if (role.toLowerCase() == "recycler") asset = "test-document_recycler";
    else if (role.toLowerCase() == "Battery Producer")
      asset = "test-document_battery_producer";
    //let uuid = await this.getProductPassport(
    // asset,
    // destinationPath,
    // contractId
    //);
    const response = await this.getPassport();
    this.data = response;
    this.loading = false;
    // if (uuid == null)
    //  alert("Something went wrong in finalizing product process");
    // else {
    //   //Display the product passport //
    //   let response = await this.displayProductPassport(asset + ".json");
    //   this.data = response;
  // }
  },
};
</script>

<style scoped>
.container {
  width: 76%;
  margin: 0 12% 0 12%;
}
</style>
