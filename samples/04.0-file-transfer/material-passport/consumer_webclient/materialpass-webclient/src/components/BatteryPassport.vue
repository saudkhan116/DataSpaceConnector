<template>
<div class="container">
  <div class="main">
    <h5 class="center">Step # 1: Load contract offers from the battery provider</h5><br />
  <div class="container" style="width:25%;">
    <label for="Provider"><strong>Battery Provider:</strong></label>
    <select class="form-select" id="selectProvider" v-model="selectedProvider" placeholder="Select Battery Provider">
      <option value="" disabled selected>Select Battery Provider...</option>
      <option v-for="provider in listProviders" :value="provider.name" v-bind:key="provider.id">{{ provider.name }}</option>
    </select>

  </div>
  <br />
    <div class="container">
        <button type="button" class="btn btn-success" style="width:24%;margin-left: 38%;" :disabled="isDisabled"  v-on:click="GetProviderInfo">Load Contract Offers</button>
    <span class="container" id="loadContracts"></span>
    </div>
   
  <br />
  <h5 class="center">Step # 2: Negotiate the edc contract</h5><br />
   <div class="container" style="width:25%;">
      <label for="contractOffer"><strong>Contract Offers:</strong></label>
      <select required class="form-select" id="selectOffer" placeholder="Select Offer" @change="setSelectedContract($event)">
        <option value="" disabled selected>Select an Offer...</option>
        <option v-for="(offer, index) in provider.contractOffers"
                v-bind:key="index">{{ offer }}
        </option>
      </select>
      <!-- <span id="selectedBatt"></span> -->
    </div>
    <div class="container" style="width:25%;">
      <label for="Battery"><strong>Battery:</strong></label>
    <select required class="form-select"  id="selectBattery" placeholder="Select Battery" @change="setSelectedBattery($event)">
      <option value="" disabled selected >Select Battery...</option>
      <option v-for="(battery, id) in provider.batteries" :value="battery.id"
              v-bind:key="id">{{ battery.name }}
      </option>
    </select>
  </div>
    <br />

    <div class="container">
        <button type="button" class="btn btn-success" style="width:24%;margin-left: 38%;" :disabled="isDisabled"  v-on:click="doNegotiation">Start Negotiation</button>
        <span class="container" id="negotiateContract"></span>
    </div>
  <!-- <div class="container" style="width:25%;">
      <label for="Battery"><strong>Battery:</strong></label>
    <select required class="form-select" id="selectBattery" placeholder="Select Battery" @change="setSelectedBattery($event)">
      <option value="" disabled selected >Select Battery...</option>
      <option v-for="(battery, id) in provider.batteries" :value="battery.id"
              v-bind:key="id">{{ battery.name }}
      </option>
    </select>
  </div> -->
    <!-- <br />
    <div class="container" style="width:25%;">
      <label for="connectorURL"><strong>Connector URL:</strong></label>
      <input type="text" class="form-control" v-model="this.provider.providerConnector" disabled id="txtConnectorURL" placeholder="Connector URL">
    </div> -->
    <br />
    <h5 class="center">Step # 3: Get battery passport from the provider</h5><br />
    <div class="container">
        <button type="button" class="btn btn-success" style="width:24%;margin-left: 38%;" :disabled="isDisabled"  v-on:click="initiateTransfer">Get Battery Passport</button>
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
  <div class="h5">{{currentStatus}}</div>
</div>
</template>

<script>
import axios from 'axios';

let listBatteryProviders = require('../assets/providers.json');

export default {
  name: 'batteryPassport',
  created(){
    //alert('21212')
    if ( localStorage.getItem("battery-info") == null){
        this.isDataFromQRCode = false
    }
    else{
      this.isDataFromQRCode = true
      let batteryInfo = localStorage.getItem("battery-info")
      this.selectedProvider = JSON.parse(batteryInfo).provider
      this.selectedBattery = JSON.parse(batteryInfo).battery
      this.selectedContract = "LRP_contractoffer"
      //this.doTransferData()
    }
  },
  data() {
    return {
      isDataFromQRCode: localStorage.getItem("battery-info") == null ? false : true,
      listProviders: listBatteryProviders,
      provider: [],
      productPassport:'',
      selectedProvider:'',
      selectedBattery:'',
      selectedContract:'',
      currentStatus: '',
      uuid: '',
      contractId:'',
      isLoading:'',
      isDisabled: false,
      isPassportVisible: false,
      errors: []
    }    
  },
  methods:{
    GetProviderInfo(event) {
      //this.selectedProvider = event.target.value
       let user = localStorage.getItem("user-info")
       let role = JSON.parse(user).role
      //alert(role)
      axios.get('/api/provider/metadata/' + this.selectedProvider + '?role=' + role)
      .then(response => {
          this.provider = response.data
          console.log(this.provider)
          if (this.provider != '' )
            document.getElementById('loadContracts').innerHTML='Contract offers loaded successfully..'
          else
            document.getElementById('loadContracts').innerHTML='No contract offers'
      })
      .catch(e => {
        this.errors.push(e)
        document.getElementById('loadContracts').innerHTML='Something went wrong!..'
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
      //alert('Hello')

      
    },
    setSelectedBattery(event){
      this.selectedBattery = event.target.value

      let battery = this.selectedContract.split('_')[0]
      /*if (this.provider.batteries.some(el => el.id === battery)){
        this.selectedBattery = battery
        //document.getElementById('selectedBatt').innerHTML = this.selectedBattery + ' is selected'
      }*/
      //if (battery != this.selectedBattery)
      //  document.getElementById('negotiateContract').innerHTML = 'No contract exists for the selected battery..'
     // else
      //  document.getElementById('negotiateContract').innerHTML = 'Contract exists for the selected battery..'
    },
    setSelectedContract(event){
      this.selectedContract = event.target.value
      
    },
    GetPassport: function () {
      // if ( this.selectedProvider != '' &&this.selectedBattery != '' && this.selectedContract != ''){
      if ( this.selectedProvider != '' && this.selectedContract != ''){
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
    async doNegotiation(){

      let battery = this.selectedContract.split('_')[0]
      if (battery != this.selectedBattery)
        return

      this.uuid = await this.negotiateContract()
      this.isLoading = true
      this.currentStatus = "Negotiating contract..."
      // Check agreement status //
      // Status: INITIAL, REQUESTED, CONFIRMED
      // first call to get the initial status
      var data = await this.getAgreementId(this.uuid)
      if (data == "rejected")
        return;
      else
        this.contractId = data.contractAgreementId

      // Check the agreement status until it is of status CONFIRMED
      while(data.status != "CONFIRMED"){        
        data = await this.getAgreementId(this.uuid)
        // setTimeout(
        //   function(){console.log("Wait for 5 seconds...")},
        //   5000);
        this.currentStatus = "Agreement status: " + data.status + "..."
        console.log(data.status + '_' + data.contractAgreementId)
        this.contractId = data.contractAgreementId
      }
      this.isLoading = false
      document.getElementById('negotiateContract').innerHTML='Finished with uuid:  ' + this.uuid
    },
    async initiateTransfer(){
      this.isLoading = true
      const destinationPath = 'C:/Users/muhammadsaud.khan/Documents/Workspace/catenax-edc-mp/DataSpaceConnector/samples/04.0-file-transfer/data'

      // Initiate transfer request //
      let asset = ''
      let user = localStorage.getItem("user-info")
      let role = JSON.parse(user).role
      if (role == "Dismantler")
        asset = "test-document_dismantler"
      else if (role == "OEM")
        asset = "test-document_oem"
      else
        asset = "test-document_recycler"
      var res = await this.getProductPassport(asset, destinationPath, this.contractId)
      console.log(res)
      if (res == null)
        this.currentStatus = "Something went wrong in finalizing product process..."
      else {
        this.currentStatus = "Finalizing product passport..."

        // Display the product passport //
        var productPass = await this.displayProductPassport(asset+ '.json')
        if (productPass == ''){
          setTimeout(this.displayProductPassport(asset+ '.json'), 60000);
        }

        this.productPassport = productPass
        this.isPassportVisible = true;
        this.isLoading = false;
        this.isDisabled = false;
      }
    },
    async doTransferData(){
      const destinationPath = 'C:/Users/muhammadsaud.khan/Documents/Workspace/catenax-edc-mp/DataSpaceConnector/samples/04.0-file-transfer/data'

      // Do contract negotiation //
      // If contract is already negotiated in previous calls then no need to send API calls again //
      //alert(this.uuid)
      if (this.uuid == ''){
        this.uuid = await this.negotiateContract()
        
        if (this.uuid != ''){
          this.currentStatus = "Contract negotiated successfully..."
          
          // Check agreement status //
          // Status: INITIAL, REQUESTED, CONFIRMED
          // first call to get the initial status
          var data = await this.getAgreementId(this.uuid)
          if (data == "rejected")
            return;
          else
            this.contractId = data.contractAgreementId

          // Check the agreement status until it is of status CONFIRMED
          while(data.status != "CONFIRMED"){        
            data = await this.getAgreementId(this.uuid)
            // setTimeout(
            //   function(){console.log("Wait for 5 seconds...")},
            //   5000);
            this.currentStatus = "Agreement status: " + data.status + "..."
            console.log(data.status + '_' + data.contractAgreementId)
            this.contractId = data.contractAgreementId
          }
        }
        else{
          this.currentStatus = "Something went wrong in contract negotiation step..."
        }
      }
      else{
        this.currentStatus = "Contract is already negotiated..."
      }
      
      // Initiate transfer request //
      let asset = ''
      let user = localStorage.getItem("user-info")
      let role = JSON.parse(user).role
      if (role == "Dismantler")
        asset = "test-document_dismantler"
      else
        asset = "test-document_oem"
      var res = await this.getProductPassport(asset, destinationPath, this.contractId)
      console.log(res)
      if (res == null)
        this.currentStatus = "Something went wrong in finalizing product process..."
      else {
        this.currentStatus = "Finalizing product passport..."

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
       let contractOffer = require('C:/Users/muhammadsaud.khan/Documents/Workspace/catenax-edc-mp/DataSpaceConnector/samples/04.0-file-transfer/registry/contractoffers/' + this.selectedContract);
       //let contractOffer = require('../assets/12345_contractoffer.json');
        //var contractOffer = $JSON.parse('../../../../MP-CatenaX/DataSpaceConnector/samples/04.0-file-transfer/registry/12345_contractoffer.json');
        //console.log(contractOffer)
        //alert('in negotiate')
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
          console.log('Agreement Id: ' + response.data.status)
          this.currentStatus = 'Agreement status: ' + response.data.status
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

      axios.post('/api/file/' + asset + '?connectorAddress=http://localhost:8282/api/v1/ids/data&destination=' + destinationPath + '/' + asset + '.json&contractId=' + contractId)
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