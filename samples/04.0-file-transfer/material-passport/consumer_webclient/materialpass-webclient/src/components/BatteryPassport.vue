<template>
<div class="container">
  <div class="main">
  <div class="container" style="width:25%;">
    <label for="Provider"><strong>Battery Provider:</strong></label>
    <select class="form-select" id="selectProvider" placeholder="Select Battery Provider"  @change="getBatteriesByProvider($event)">
      <option value="" disabled selected>Select Battery Provider...</option>
      <option v-for="provider in listProviders" :value="provider.name" v-bind:key="provider.id">{{ provider.name }}</option>
    </select>
  </div>
  <br />
  <div class="container" style="width:25%;">
      <label for="Battery"><strong>Battery:</strong></label>
    <select required class="form-select" id="selectBattery" placeholder="Select Battery" @change="setSelectedBattery($event)">
      <option value="" disabled selected >Select Battery...</option>
      <option v-for="(battery, id) in provider.batteries" :value="battery.id"
              v-bind:key="id">{{ battery.name }}
      </option>
    </select>
  </div>
  <br />
   <div class="container" style="width:25%;">
      <label for="contractOffer"><strong>Contract Offer:</strong></label>
      <select required class="form-select" id="selectOffer" placeholder="Select Offer" @change="setSelectedContract($event)">
        <option value="" disabled selected>Select an Offer...</option>
        <option v-for="(offer, index) in provider.contractOffers"
                v-bind:key="index">{{ offer }}
        </option>
      </select>
    </div>
    <br />
    <div class="container" style="width:25%;">
      <label for="connectorURL"><strong>Connector URL:</strong></label>
      <input type="text" class="form-control" v-model="this.provider.providerConnector" disabled id="txtConnectorURL" placeholder="Connector URL">
    </div>
    <br />
    <div class="container">
        <button type="button" class="btn btn-success" style="width:24%;margin-left: 38%;" :disabled="isDisabled"  v-on:click="GetPassport()">Get Battery Passport</button>
    </div>
    <br />
  </div>
  <div v-if="isPassportVisible" class="container margin-top">
    <table v-bind="productPassport" class="table table-bordered table-striped" style="border:1px ghostwhite; text-align:left;">
      <thead>
        <th colspan="6" class="h3 table-heading">Battery Passport</th>
      </thead>
      <tbody>
      <!-- <tr v-for="(value, key) in this.productPassport" v-bind:key="key">
          <th scope="col">{{key}}</th>
          <td v-if="typeof value == 'object'">
            <table class="table table-bordered table-striped"><tr scope="row" v-for="(k,val) in value" v-bind:key="k"><th>{{val}}</th>
            <td>{{k}}</td></tr></table></td>
          <td v-else>{{value}}</td>
        </tr> -->
    <template v-for="(value1, key1) in this.productPassport" v-bind:key="key1">
    <tr scope="row" v-if="typeof value1 != 'object'">
      <th scope="col">{{key1}}</th>
      <td>{{value1}}</td> 
    </tr>
      <template v-if="typeof value1 == 'object'">
        <tr>
          <th scope="col">{{key1}}</th>
          <td></td>
        </tr>

        <template v-for="(value2,key2) in value1" v-bind:key="key2">
        <tr scope="row" v-if="typeof value2 != 'object'">
          <th style="padding-left: 50px;">{{key2}}</th>
          <td>
            {{value2}} 
          </td>
        </tr>
        <template v-if="typeof value2 == 'object'">
          <tr>
          <th scope="col" style="padding-left: 75px;">{{key2}}</th>
          <td></td>
        </tr>
          <tr v-for="(value3,key3) in value2" v-bind:key="key3">
            <th style="padding-left: 100px;">{{key3}}</th>
          <td>
            {{value3}} 
          </td>
          </tr>
          </template>
        </template>
      </template>
  </template>
  </tbody>
    </table>
  </div>
  <br />
</div>
<div v-if="isLoading">
  <div class="spinner-border" style="width: 3rem; height: 3rem;" role="status" >
    <span class="sr-only"></span>
  </div>
  <br />
  <div class="h5">{{contractStatus}}</div>
</div>
</template>

<script>
import axios from 'axios';

let listBatteryProviders = require('../assets/providers.json');

export default {
  name: 'batteryPassport',
  data() {
    return {
      listProviders: listBatteryProviders,
      provider: [],
      productPassport:'',
      selectedProvider:'',
      selectedBattery:'',
      selectedContract:'',
      contractStatus: '',
      isDisabled: false,
      isPassportVisible: false,
      isLoading:'',
      errors: []
    }
  },
  methods:{
    getBatteriesByProvider(event) {
      this.selectedProvider = event.target.value
       let user = localStorage.getItem("user-info")
       let role = JSON.parse(user).role
      //alert(role)
      axios.get('/api/provider/metadata/' + this.selectedProvider + '?role=' + role)
      .then(response => {
          this.provider = response.data
      })
      .catch(e => {
        this.errors.push(e)
      })
    },
    resetFields: function () {

      // TODO
      this.provider.providerConnector = ''
      this.negotiationStatus =''
      this.selectedProvider = ''
      this.provider.contractOffers = ''
      
      this.selectedBattery = ''
      this.selectedContract = ''
      alert('Hello')

      
    },
    setSelectedBattery(event){
      this.selectedBattery = event.target.value
    },
    setSelectedContract(event){
      this.selectedContract = event.target.value
    },
    GetPassport: function () {
      if ( this.selectedProvider != '' &&this.selectedBattery != '' && this.selectedContract != ''){
        this.isLoading = true
        this.isDisabled = true
        this.isPassportVisible = false 
        this.doTransferData()
      }
      else{
        alert("Please fill out all fields...!")
        this.isDisabled = false
      }
    },
    async doTransferData(){
      const destinationPath = 'C:/Users/muhammadsaud.khan/Documents/Workspace/MP-CatenaX/DataSpaceConnector/samples/04.0-file-transfer/data'
      var contractId = ''

      // Do contract negotiation //
      var uuid = await this.negotiateContract()
      if (uuid != null)
        this.contractStatus = "Contract negotiated successfully..."
      else
        this.contractStatus = "Something went wrong in contract negotiation process..."

      // Check agreement status //
      // Status: INITIAL, REQUESTED, CONFIRMED
      // first call to get the initial status
      var data = await this.getAgreementId(uuid)
      if (data == "rejected")
        return;

      // TODO - Include loading bar to get the status

      // Check the agreement status until it is of status CONFIRMED
      while(data.status != "CONFIRMED"){        
        data = await this.getAgreementId(uuid)
        // setTimeout(
        //   function(){console.log("Wait for 5 seconds...")},
        //   5000);
        this.contractStatus = "Agreement status: " + data.status + "..."
        console.log(data.status + '_' + data.contractAgreementId)
        contractId = data.contractAgreementId
      }
      
      // Initiate transfer request //
      let asset = ''
      let user = localStorage.getItem("user-info")
      let role = JSON.parse(user).role
      if (role == "Dismantler")
        asset = "test-document_dismantler"
      else
        asset = "test-document_oem"

      var res = await this.getProductPassport(asset, destinationPath, contractId)
      console.log(res)
      if (res == null)
        this.contractStatus = "Something went wrong in finalizing product process..."
      else {
        this.contractStatus = "Finalizing product passport..."

        // Display the product passport //
        var productPass = await this.displayProductPassport(asset+ '.json')
    
        this.productPassport = productPass
        this.isPassportVisible = true;
        this.isLoading = false;
        this.isDisabled = false;
      } 
    },
    negotiateContract(){

      // TODO dynamic contractoffer file name
       let contractOffer = require('C:/Users/muhammadsaud.khan/Documents/Workspace/MP-CatenaX/DataSpaceConnector/samples/04.0-file-transfer/registry/contractoffers/' + this.selectedContract);
       //let contractOffer = require('../assets/12345_contractoffer.json');
        //var contractOffer = $JSON.parse('../../../../MP-CatenaX/DataSpaceConnector/samples/04.0-file-transfer/registry/12345_contractoffer.json');
        //console.log(contractOffer)

        return new Promise(resolve => {

      axios.post('/api/negotiation?connectorAddress=' + this.provider.providerConnector, contractOffer)
        .then((response) => {
          resolve(response.data);
        })
        .catch((e) => {
          this.errors.push(e)
          resolve('rejected');
        });
    });
    },
    getAgreementId(uuid){
      
      return new Promise(resolve => {

      axios.get('/api/control/negotiation/' + uuid + '/state', {
          headers: {
            'X-Api-Key': 'password'
          }})
        .then((response) => {
          console.log(response.data)
          this.contractStatus = response.data.status
          resolve(response.data);
        })
        .catch((e) => {
          this.errors.push(e)
          resolve('rejected');
        });
    })
    },
  getProductPassport(asset, destinationPath, contractId){
    
    return new Promise(resolve => {

      axios.post('/api/file/' + asset + '?connectorAddress=http://localhost:8181/api/ids/multipart&destination=' + destinationPath + '&contractId=' + contractId)
        .then((response) => {
          console.log(response.data)
          resolve(response.data);
        })
        .catch((e) => {
          this.errors.push(e)
          resolve('rejected');
        });
    })    
    
  },
  displayProductPassport(filename){

     return new Promise(resolve => {

      axios.get('/api/passport/display/' + filename)
        .then((response) => {
          console.log(response.data)
          resolve(response.data);
        })
        .catch((e) => {
          this.errors.push(e)
          resolve('rejected');
        });
    })
  },
  // Fetches provider when the component is created.
  created() {
  }
}
}
</script>

<style>
.md-dialog-container {
  padding: 20px;
}
.md-content.md-table.md-theme-default {
  width: 95%;
  margin: 0 auto;
}

.table-heading{
  background: #E9ECEC;
  text-align: center;
}

.main{
  text-align:left;
  background: aliceblue;
  padding: 50px 0px 0px 0px;
}
</style>