<template>
    <nav class="navbar fixed-top navbar-expand-lg navbar-light" style="background-color: #ffffff;">
  <a class="navbar-brand" href="#">
    <a class="d-inline-block align-top" href="#" style="padding: 0px 0px 0px 30px;"><img src="../assets/catenaXlogo.png" style="width: 265px; margin: 0px 0px 0px 60px;"></a>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup" style="padding-left:100px;">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link active" href="#">Home <span class="sr-only"> 
    (current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">About </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#" v-on:click="logout">Logout </a>
      </li>
    </ul>
    <ul>
     <li id="userDetailsItem" class="nav-item" style="list-style: none; margin: 10px 0px 0px 400px;">
          Logged in as <strong>{{username}}</strong>   | <span>Role: <strong>{{role}}</strong>
      </span>
      </li>
    </ul>
  </div>
  
</nav>
</template>

<script type="text/jsx">
export default {
  name: "Navigation",
  data() {
    return {
      username: '',
      role: '',
    };
  },
  methods: {
      logout(){
          localStorage.clear();
          this.$router.push({ name: "Login" });
      },
      scanQRCode(){
          this.$router.push({ name: "ScanPassport" });
      }
  },
   mounted() {
    let user = localStorage.getItem("user-info");
    if (user) {
      this.username = JSON.parse(user).name
      this.role = JSON.parse(user).role
    }
  }
};
</script>

<style scoped>
nav{
    background-color: white;
    font-size: medium;
}

@media screen and (max-width: 992px) {
 ul li {
    margin: 10px 0px 0px 0px !important;
    list-style: none;

  }
}
</style>
