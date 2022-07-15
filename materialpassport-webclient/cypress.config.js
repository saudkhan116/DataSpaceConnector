const { defineConfig } = require("cypress");

module.exports = defineConfig({
  e2e: {
    viewportHeight: 800,
    viewportWidth: 1280,
    defaultCommandTimeout: 400000,
    setupNodeEvents(on, config) {
      // implement node event listeners here
    },
  },
});
