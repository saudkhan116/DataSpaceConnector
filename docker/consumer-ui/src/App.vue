<template>
  <!-- <img alt="Vue logo" src="./assets/logo.png"> -->
  <!-- <div id="nav">
      <router-link to="/">Home</router-link>
      <router-link to="/about">About</router-link>
  </div>
  <router-view /> -->
  <!-- <ProviderDropdown msg="Welcome to Your Vue.js App"/> -->
  <!-- <SignUp /> -->
  <router-view />

</template>

<script type="text/jsx">
// import ProviderDropdown from './components/ProviderDropdown.vue'
// import Login from './components/Login.vue'


export default {
  name: 'App'
  // components: {
  //   Login
  // }
}
</script>

<style>
body{
  margin: 0px;
  padding: 0px;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}
#nav{
  padding: 30px;
}

#nav a{
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active{
  color: #42b983;
}

/* .center {
  text-align: center;
} */

.margin-top {
  margin-top: 30px;
}

.register input {
  width: 300px !important;
}

.center {
  margin-left: 33%;
  margin-top: inherit;
}

.btn-signup {
  width: -webkit-fill-available;
}

.logo {
  width: 150px;
}

.btn-login{
    width: -webkit-fill-available;
}

</style>
