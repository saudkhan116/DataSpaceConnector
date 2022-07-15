describe("e2e test", () => {
  before(() => {
    cy.visit("http://localhost:8080");
  });
  it("Check if exist sign in container", () => {
    cy.get('[data-cy="sign-in-container"]');
  });
  it("Sign in and battery select", () => {
    cy.get('[data-cy="email-input"]').type("mustermann@test-recycler.de");
    cy.get('[data-cy="password-input"]').type("mustermann");
    cy.get('[data-cy="sign-in-btn"]').click();
    cy.get('[data-cy="battery-pass-container"]');
    cy.get('[data-cy="provider-select"]').select("BMW");
    cy.get('[data-cy="battery-select"]').select("test-battery 1 (work)");
    cy.get('[data-cy="passport-btn"]').click();
  });
  it("Read passport details", () => {
    cy.get('[data-cy="battery-id"]'); // 1. General information first field check
    cy.get('[data-cy="electrolyte-composition"]'); // 2. Battery composition first field check
    cy.get('[data-cy="remaining-capacity"]'); // 3. State of health first field check
    cy.get('[data-cy="state-of-charge"]'); // 4. Parameters of the battery first field check
    cy.get('[data-cy="dismantling-procedures"]'); // 5. Dismantling Procedures first field check
    cy.get('[data-cy="occupational-safety"]'); // 6. Safety Information of the battery first field check
    cy.get('[data-cy="responsible-raw-materials-report"]'); // 7. Information responsible sourcing first field check
    cy.get('[data-cy="link-to-the-label-element"]'); // 8. Additional information first field check
    cy.get('[data-cy="footer-logo-wrapper"]'); //  Footer check
  });
});
