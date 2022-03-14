<template>
    <nav class="navbar navbar-expand-lg navbar-light">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="#" style="padding: 0px 0px 0px 30px;"><img src="../assets/catenaXlogo.png" style="width: 265px; margin: 0px 0px 0px 60px;"></a>
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0" style="padding-left: 92px">
          <li class="nav-item active">
            <a class="nav-link active" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#" v-on:click="logout">Logout</a>
          </li>
        </ul>
        <!-- <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form> -->
    <span style="padding-left: 430px;">
        Logged in as <strong>{{username}}</strong>   | <span>Role: <strong>{{role}}</strong></span> 
    </span>
      </div>
    </nav>
</template>

<script>
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

<style>
nav{
    background-color: white;
    font-size: medium;
}

</style>
