<template>
<Navigation />
<h3 class="margin-top">Welcome to the Battery Passport Portal</h3>
<br />
<BatteryPassport />

  <!-- <div class="home">
    <div class="center">
      <h1>Address Book Home</h1>
    </div>
    <md-table>
      <md-table-row>
        <md-table-head md-numeric>ID</md-table-head>
        <md-table-head>First Name</md-table-head>
        <md-table-head>Last Name</md-table-head>
        <md-table-head>Address Line 1</md-table-head>
        <md-table-head>Address Line 2</md-table-head>
        <md-table-head>City</md-table-head>
        <md-table-head>Country</md-table-head>
        <md-table-head>Postal Code</md-table-head>
        <md-table-head>Gender</md-table-head>
        <md-table-head>Age</md-table-head>
        <md-table-head>Email</md-table-head>
        <md-table-head></md-table-head>
        <md-table-head></md-table-head>
      </md-table-row>
<md-table-row v-for="c in contacts" :key="c.id">
        <md-table-cell md-numeric>{{c.id}}</md-table-cell>
        <md-table-cell>{{c.firstName}}</md-table-cell>
        <md-table-cell>{{c.lastName}}</md-table-cell>
        <md-table-cell>{{c.addressLineOne}}</md-table-cell>
        <md-table-cell>{{c.addressLineTwo}}</md-table-cell>
        <md-table-cell>{{c.city}}</md-table-cell>
        <md-table-cell>{{c.country}}</md-table-cell>
        <md-table-cell>{{c.postalCode}}</md-table-cell>
        <md-table-cell>{{c.gender}}</md-table-cell>
        <md-table-cell md-numeric>{{c.age}}</md-table-cell>
        <md-table-cell>{{c.email}}</md-table-cell>
        <md-table-cell>
          <md-button class="md-primary" @click="selectedContactId = c.id; showDialog = true">Edit</md-button>
        </md-table-cell>
        <md-table-cell>
          <md-button class="md-accent" @click="removeContact(c.id)">Delete</md-button>
        </md-table-cell>
      </md-table-row>
    </md-table>
<md-dialog :md-active.sync="showDialog">
      <md-dialog-content>
        <ContactForm
          :editing="true"
          :contactId="selectedContactId"
          @contactSaved="selectedContactId = undefined; showDialog = false"
        />
      </md-dialog-content>
    </md-dialog>
  </div> -->
</template>

<script>
import Navigation from './Navigation.vue'
import BatteryPassport from './BatteryPassport.vue'

export default ({
  name: 'Home',
  components:{
    Navigation,
    BatteryPassport
  },
  mounted(){
        let user = localStorage.getItem("user-info");
        if (!user){
            this.$router.push({name:'Login'});
        }
    }
})
</script>

