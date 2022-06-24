<template>
  <div class="footer-container">
    <img :src="CatenaLogo" alt="logo" class="logo" />
    <h3 class="buttons">Contact</h3>
    <h3 class="buttons">Legal Notice</h3>
    <h3 class="buttons">Privacy Policy</h3>
  </div>
</template>

<script>
import CatenaLogo from "../assets/logo.png";

export default {
  name: "Footer",

  components: {
    CatenaLogo,
  },
  setup() {
    return {
      CatenaLogo,
    };
  },
};
</script>

<style scoped>
.footer-container {
  display: flex;
  background-color: #e7e7e7;
  height: 114px;
  padding-top: 30px;
}
.logo {
  display: block;
  height: 49px;
  margin: 0 43% 70px 120px;
}
.buttons {
  color: #3d3d3d;
  margin: 15px;
}
</style>
