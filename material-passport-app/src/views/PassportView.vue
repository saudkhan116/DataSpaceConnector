<template>
  <div v-if="loading">Spinner</div>
  <div v-else>
    <Header :batteryId="data.generalInformation" />

    <div class="container">
      <GeneralInformation
        sectionTitle="General information"
        :generalInformation="data.generalInformation"
      />
      <BatteryComposition
        sectionTitle="Battery Composition"
        :batteryComposition="data.batteryComposition"
      />
      <StateOfHealth
        sectionTitle="State of Health"
        :stateOfHealth="data.stateOfHealth"
      />
      <ParametersOfTheBattery
        sectionTitle="Parameters of The Battery"
        :parametersOfTheBattery="data.parametersOfTheBattery"
      />
      <DismantlingProcedures
        sectionTitle="Dismantling procedures"
        :dismantlingProcedures="data.dismantlingProcedures"
      />
      <SafetyInformation
        sectionTitle="Safety information"
        :safetyInformation="data.safetyInformation"
      />
      <InformationResponsibleSourcing
        sectionTitle="Information responsible sourcing"
        :informationResponsibleSourcing="data.informationResponsibleSourcing"
      />

      <AdditionalInformation
        sectionTitle="Additional information"
        :additionalInformation="data.additionalInformation"
      />
    </div>
    <Footer />
  </div>
</template>

<script>
// @ is an alias to /src
import GeneralInformation from "@/components/GeneralInformation.vue";
import BatteryComposition from "@/components/BatteryComposition.vue";
import StateOfHealth from "@/components/StateOfHealth.vue";
import ParametersOfTheBattery from "@/components/ParametersOfTheBattery.vue";
import DismantlingProcedures from "@/components/DismantlingProcedures.vue";
import SafetyInformation from "@/components/SafetyInformation.vue";
import InformationResponsibleSourcing from "@/components/InformationResponsibleSourcing.vue";
import AdditionalInformation from "@/components/AdditionalInformation.vue";

import Header from "@/components/Header.vue";
import Footer from "@/components/Footer.vue";

export default {
  name: "PassportView",
  components: {
    Header,
    GeneralInformation,
    BatteryComposition,
    StateOfHealth,
    ParametersOfTheBattery,
    DismantlingProcedures,
    SafetyInformation,
    InformationResponsibleSourcing,
    AdditionalInformation,
    Footer,
  },
  data() {
    return {
      data: null,
      loading: true,
    };
  },
  methods: {
    async fetchData() {
      const res = await fetch("http://localhost:5000/334593247");

      const data = await res.json();

      return data;
    },
  },
  async created() {
    this.data = await this.fetchData();
    this.loading = false;
  },
};
</script>

<style scoped>
.container {
  width: 76%;
  margin: 0 12% 0 12%;
}
</style>
