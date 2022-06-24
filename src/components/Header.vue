<template>
  <div class="header-container">
    <img :src="CatenaLogo" alt="logo" class="logo" />
    <img :src="Settings" alt="settings" class="buttons" title="Settings" />
    <img :src="Notifications" alt="notifications" class="buttons" title="Notifications" />
    <img :src="Profile" alt="profile" class="buttons" title="User Profile" />
    <img :src="Logout" alt="logout" class="buttons" title="Logout" v-on:click="logout"/>
  </div>
  <div class="id-container">
    <h1>BatteryID: {{ batteryId }}</h1>
    <img :src="QrCode" alt="profile" class="code" />
  </div>
</template>

<script>
import CatenaLogo from "../assets/logotype.png";
import Profile from "../assets/profile.svg";
import Notifications from "../assets/notifications.svg";
import Settings from "../assets/settings.svg";
import QrCode from "../assets/qrcode.svg";
import Logout from "../assets/logout.png";

export default {
  name: "Header",
  props: {
    batteryId: String,
  },
  components: {
    CatenaLogo,
    Profile,
    Settings,
    Logout,
  },
  setup() {
    return {
      CatenaLogo,
      Profile,
      Notifications,
      Settings,
      QrCode,
      Logout,
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
.header-container {
  display: flex;
}
.id-container {
  display: flex;
  align-items: center;
  margin-bottom: 80px;
}
.logo {
  display: block;
  width: 209px;
  height: 49px;
  margin: 0 54% 70px 70px;
}
.buttons {
  width: 26px;
  height: 26px;
  margin: 15px;
  cursor: pointer;
}

h1 {
  margin-left: 130px;
}
.code {
  padding: 0 0 0 24%;
}
</style>
