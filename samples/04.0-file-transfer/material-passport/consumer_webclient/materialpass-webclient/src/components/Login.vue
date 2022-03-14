<template>
  <img src="../assets/catenaX-logo.png" />
  <br />
  <div class="margin-top">
    <p class="h1">Login</p>
  </div>
  <div class="margin-top">
    <div class="container">
      <div class="col-md-4 center">
        <input
          class="form-control"
          v-model="email"
          type="text"
          placeholder="Enter email"
        />
      </div>
      <br />
      <div class="col-md-4 center">
        <input
          class="form-control"
          v-model="password"
          type="password"
          placeholder="Enter Password"
        />
      </div>
      <br />
      <div class="col-md-4 center">
        <button class="btn btn-success btn-login" v-on:click="login">
          Login
        </button>
      </div>
    </div>
    <div class="col-md-4 center margin-top">
      <p>
        <span> <router-link to="/sign-up">Sign Up</router-link></span>
        <span> | </span>
        <span> <router-link to="#">Forgot Password</router-link></span>
      </p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "LoginPage",
  data() {
    return {
      email: "",
      password: "",
    };
  },
  methods: {
    async login() {
      let result = await axios.get(
        `http://localhost:3000/users?email=${this.email}&password=${this.password}`
      );
      if (result.status == 200 && result.data.length > 0) {
        //alert("login successful..!")
        localStorage.setItem("user-info", JSON.stringify(result.data[0]));
        this.$router.push({ name: "Home" });
      }
      else {
          alert("user is not registered or invalid credentails..!")
      }
    },
  },
  mounted() {
    let user = localStorage.getItem("user-info");
    if (user) {
      this.$router.push({ name: "Home" });
    }
  },
};
</script>
