<template>
  <div class="header-container">
    <img :src="CatenaLogo" alt="logo" class="logo" />
    <img :src="Settings" alt="settings" class="buttons" />
    <img :src="Notifications" alt="profile" class="buttons" />
    <img :src="Profile" alt="profile" class="buttons" />
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

export default {
  name: "Header",
  props: {
    batteryId: String,
  },
  components: {
    CatenaLogo,
    Profile,
    Settings,
  },
  setup() {
    return {
      CatenaLogo,
      Profile,
      Notifications,
      Settings,
      QrCode,
    };
  },
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
}

h1 {
  margin-left: 130px;
}
.code {
  padding: 0 0 0 24%;
}
</style>
