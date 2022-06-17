<template>
<Navigation />
<h3 class="margin-top">Battery Passport Portal</h3>
<br />
<BatteryPassport />
</template>

<script type="text/jsx">
import Navigation from './Navigation.vue'
import BatteryPassport from './BatteryPassport.vue'

export default ({
  name: 'Home',
  components:{
    Navigation,
    BatteryPassport
  },
  props: {
    provider: String,
    battery: String,
  },
  mounted(){
        let user = localStorage.getItem("user-info");
        if (!user){
            this.$router.push({name:'Login'});
        }
    }
})
</script>

