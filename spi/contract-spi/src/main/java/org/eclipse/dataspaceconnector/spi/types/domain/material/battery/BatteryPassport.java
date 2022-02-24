package org.eclipse.dataspaceconnector.spi.types.domain.material.battery;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * A Battery passport object model with attributes, used to access a passport
 */
@JsonDeserialize(builder = BatteryPassport.Builder.class)
public class BatteryPassport {
	
	@JsonProperty("Co2 Footprint Methodology")
	private String co2FootprintMethodology;
	
	@JsonProperty("Co2 Confirmation Studies")
	private String co2ConfirmationStudies;
	
	@JsonProperty("Co2 Footprint Threshold")
	private String co2FootprintThreshold;
	
	@JsonProperty("Co2 Footprint Total")
	private String co2FootprintTotal;
	
    @JsonProperty("Co2 Footprint per lifecycle")
    private String co2FootprintPerLifecycle;
    
    @JsonProperty("Co2 Footprint Verification")
    private String co2FootprintVerification;
    
    @JsonProperty("Co2 Footprint Classification from EU")
    private String co2FootprintClassificationFromEU;
    
    @JsonProperty("Recyclate cobalt content")
    private String recyclateCobaltContent;
    
    @JsonProperty("Recyclate lead content")
    private String recyclateLeadContent;
    
    @JsonProperty("Recyclate lithium content")
    private String recyclateLithiumContent;
    
    @JsonProperty("Recyclate nickel content")
    private String recyclateNickelContent;
    
    @JsonProperty("Recycling design information")
    private String recyclingDesignInformation;
    
    @JsonProperty("Operational temperature range")
    private String operationalTemperatureRange;
    
    @JsonProperty("Temperature range the battery can withstand when not in use (reference test)")
    private String temperatureRangeTheBatteryCanWithstandWhenNotInUse;
    
    @JsonProperty("Rated Capacity")
    private String ratedCapacity;
    
    @JsonProperty("Capacity fade")
    private String capacityFade;
    
    @JsonProperty("Power")
    private String power;
    
    @JsonProperty("Total Energy")
    private String totalEnergy;
    
    @JsonProperty("Usable Energy")
    private String usableEnergy;
    
    @JsonProperty("Power Fade")
    private String powerFade;
    
    @JsonProperty("Internal resistance")
    private String internalResistance;
    
    @JsonProperty("Internal resistance increase")
    private String internalResistanceIncrease;
    
    @JsonProperty("Internal resistance_")
    private String internalResistance_;
    
    @JsonProperty("Internal resistance increase_")
    private String internalResistanceIncrease_;
    
    @JsonProperty("Energy round trip efficiency")
    private String energyRoundTripEfficiency;
    
    @JsonProperty("Fade energy round trip efficiency")
    private String fadeEnergyRoundTripEfficiency;
    
    @JsonProperty("C-rate of relevant cycle-life test")
    private String c_RateOfRelevantCycle_Life; // Test
    
    @JsonProperty("Expected lifetime")
    private String expectedlifetime;
    
    @JsonProperty("Applied charge rate")
    private String appliedChargeRate;
    
    @JsonProperty("Applied discharge rate")
    private String appliedDisChargeRate;
    
    @JsonProperty("Storage humidity")
    private String storageHumidity;
    
    @JsonProperty("Composition of Cathode i.e. nickel, cobalt, lithium contents")
    private String compositionOfCathode; // I.E. Nickel, Cobalt, Lithium Contents
    
    @JsonProperty("Composition of Anode i.e. natural graphite content, lead")
    private String compositionOfAnode; // I.E. Natural Graphite Content, Lead
	
    @JsonProperty("Composition of Electrolyte")
    private String compositionOfElectrolyte;
    
    @JsonProperty("Composition of shell material / other parts of battery")
    private String compositionOfShellMaterial; /// Other Parts Of Battery
    
    @JsonProperty("Part numbers for components")
    private String partNumbersForComponents;
    
    @JsonProperty("Contact details of sources for replacement spares")
    private String contactDetailsOfSourcesForReplacementSpares;
    
    @JsonProperty("Max. allowed battery power (W) vs. battery energy (Wh)")
    private String maxQllowedBatteryPower; // (W) Vs. Batteryenergy (Wh)
    
    @JsonProperty("Depth of discharge in the cycle-life test")
    private String depthOfDischargeInTheCycle_Life; // Test
    
    @JsonProperty("Power capability at 80% charge")
    private String powerCapabilityAt80PercentCharge;
    
    @JsonProperty("Power capability at 20% charge")
    private String powerCapabilityAt20PercentCharge;
    
    @JsonProperty("Accompanying Calculations")
    private String accompanyingCalculations;
    
    @JsonProperty("Min. Voltage")
    private String minVoltage;
    
    @JsonProperty("Max. Voltage")
    private String maxVoltage;
    
    @JsonProperty("Nominal Voltage")
    private String nominalVoltage;
    
    @JsonProperty("Original power capability")
    private String originalPowerCapability;
    
    @JsonProperty("Original power capability limits")
    private String originalPowerCapabilityLimits;
    
    @JsonProperty("Recycling efficiencies")
    private String recyclingEfficiencies;
    
    @JsonProperty("Battery weight_")
    private double batteryWeight_;
    
    @JsonProperty("Battery dimensions")
    private String batteryDimensions;
    
    @JsonProperty("Passport number")
    private String passportNumber;
    
    @JsonProperty("Passport issuer")
    private String passportIssuer;
	
    @JsonProperty("State of Health (SoH")
    private String stateOfHealth_Soh;
    
    @JsonProperty("State of Charge (SoC")
    private String stateOfCharge_Soc;
    
    @JsonProperty("Change of ownership - invoice")
    private String changeOfOwnership_Invoice;
    
    @JsonProperty("Change of ownership - contract")
    private String changeOfOwnership_Contract;
    
    @JsonProperty("Packaging instructions")
    private String packagingInstructions;
    
    @JsonProperty("Transportation instructions")
    private String transportationInstructions;
    
    @JsonProperty("Handling instructions")
    private String handlingInstructions;
    
    @JsonProperty("Personal Protective Equipment (PPE)")
    private String personalProtectiveEquipment_PPE;
    
    @JsonProperty("Vehicle dismantling procedure")
    private String vehicleDismantlingProcedure;
    
    @JsonProperty("Battery Dismantling information")
    private String batteryDismantlingInformation;
    
    @JsonProperty("Number of new EV batteries sold per year per EU member state")
    private String numberOfNewEVBatteriesSoldPerYearPerEUMemberState;
    
    @JsonProperty("Number of new batteries for e.g. e-scooters etc. sold per year per EU member state")
    private String numberOfNewBatteriesForEGE_Scooters; // Etc. Sold Per Year Per EU Member State
    
    @JsonProperty("Number of EV batteries exported out of EU per year")
    private String numberOfEVBatteriesExportedOutOfEUPerYear;
    
    @JsonProperty("Number of batteries for e.g. e-scooters exported out of EU per year")
    private String numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear;
    
    @JsonProperty("Number of batteries EV batteries submitted for recycling per member state per year")
    private String numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear;
    
    @JsonProperty("Number of batteries for e.g. e-scooters submitted for recycling per member state per year")
    private String numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear;
    
    @JsonProperty("Number of waste batteries collected per member state per year")
    private String numberOfWasteBatteriesCollectedPerMemberStatePerYear;
    
    @JsonProperty("Infos on recycling processes")
    private String infosOnRecyclingProcesses;
    
    @JsonProperty("Collected waste batteries for export and subsequent recycling")
    private String collectedWasteBatteriesForExportAndSubsequentRecycling;
    
    @JsonProperty("Battery type / model")
    private String batteryType_Model;
    
    @JsonProperty("Battery serial number")
    private String batterySerialNumber;
    
    @JsonProperty("Battery batch number")
    private String batteryBatchNumber;
    
    @JsonProperty("Date of manufacture")
    private String dateOfManufacture;
    
    @JsonProperty("Name of manufacturer_")
    private String nameOfManufacturer_;
    
    @JsonProperty("Place of manufacturing / country of origin")
    private String placeOfManufacturing_CountryOfOrigin;
    
    @JsonProperty("Date of placing on market")
    private String dateOfPlacingOnMarket;
    
    @JsonProperty("Hazardous substances in battery")
    private String hazardousSubstancesInBattery;
    
    @JsonProperty("Chemical symbols for metals")
    private String chemicalSymbolsForMetals;
    
    @JsonProperty("Critical raw materials")
    private String criticalRawMaterials;
    
    @JsonProperty("Collection symbol")
    private String collectionSymbol;
    
    @JsonProperty("QR Code")
    private String QRCode;
    
    @JsonProperty("Hazard signs")
    private String hazardSigns;
    
    @JsonProperty("CE mark")
    private String CEMark;
    
    @JsonProperty("ID number of notified body")
    private String idNumberOfNotifiedBody;
    
    @JsonProperty("Name of notified body")
    private String nameOfNotifiedBody;
    
    @JsonProperty("Adress of notified body")
    private String adressOfNotifiedBody;
    
    @JsonProperty("Issued certificate")
    private String issuedCertificate;
    
    @JsonProperty("Application description and recycling possibilities, Description of collection and recycling possibilities")
    private String applicationDescriptionAndRecyclingPossibilities; // Description Of Collection And Recycling
    																// Possibilities												
    @JsonProperty("Role of EV owners in recycling batteries")
    private String roleOfEVOwnersInRecyclingBatteries;
    
    @JsonProperty("Enviromental impact of batteries")
    private String enviromentalImpactOfBatteries;
    
    @JsonProperty("Name of manufacturer")
    private String nameOfManufacturer;
    
    @JsonProperty("Adress of manufacturer")
    private String adressOfManufacturer;
    
    @JsonProperty("Telephone number of manufacturer")
    private String telephoneNumberOfManufacturer;
    
    @JsonProperty("Email adress of manufacturer")
    private String emailAdressOfManufacturer;
    
    @JsonProperty("Fax number of manufacturer")
    private String faxNumberOfManufacturer;
    
    @JsonProperty("Website of manufacturer")
    private String websiteOfManufacturer;
    
    @JsonProperty("National ID number of producer")
    private String nationalIdNumberOfProducer;
    
    @JsonProperty("Trade register number of producer")
    private String tradeRegisterNumberOfProducer;
    
    @JsonProperty("EU Tax ID number")
    private String EUTaxIdNumber;
    
    @JsonProperty("Battery type description")
    private String batteryTypeDescription;
    
    @JsonProperty("Brand name")
    private String brandName;
    
    @JsonProperty("Declaration number")
    private String declarationNumber;
    
    @JsonProperty("EU declaration of conformity")
    private String EUDeclarationOfConformity;
    
    @JsonProperty("Carbon Footprint (CFP) Declaration")
    private String carbonFootprint_CFP_Declaration;
    
    @JsonProperty("Geographic location of manufacturing facility")
    private String geographicLocationOfManufacturingFacility;

//    private String ESGDueDiligence 
//    private String ten Principles Of The United Nations Global Compact
//    private String uNEP Guidelines For Social Life Cycle Assessment Of Products;
//    private String convention On Biological Diversity Decision COP VIII/28- Voluntary Guidelines On Biodiversity-Inclusive Impact Assessment;
//    private String iLO Tripartite Declaration Of Principles Concerning Multinational Enterprises And Social Policy
//    private String oECD Due Diligence Guidance For Responsible Business Conduct
//    private String oECD Due Diligence Guidance For Responsible Supply Chains Of Minerals From Conflict-Affected And High-Risk Areas.

    @JsonProperty("Battery producer/Supplier")
    private String batteryProducer_Supplier;
    
    @JsonProperty("Production site")
    private String productionSite;
    
    @JsonProperty("Date of production")
    private String dateOfProduction;
    
    @JsonProperty("Begin of Life @vehicle/Production date vehicle ")
    private String beginOfLifeVehicle_ProductionDateVehicle;
    
    @JsonProperty("Original equipment of/ vehicle type")
    private String originalEquipmentOf_VehicleType;
    
    @JsonProperty("Vehicle ID (VIN)")
    private String vehicleId_VIN;
    
    @JsonProperty("Battery ID (serial number)")
    private String batteryId_SerialNumber;
    
    @JsonProperty("Battery weight")
    private String batteryWeight;
    
    @JsonProperty("Number of battery cells")
    private String numberOfBatteryCells;
    
    @JsonProperty("Battery cell connection, serial")
    private String batteryCellConnection_Serial;
    
    @JsonProperty("Battery capacity")
    private String batteryCapacity;
    
    @JsonProperty("Part number + Change Index")
    private String partNumberPlusChangeIndex;
    
    @JsonProperty("Brand")
    private String brand;
    
    @JsonProperty("Battery type")
    private String batteryType;
    
    @JsonProperty("Max. Power")
    private String maxPower;
    
    @JsonProperty("Supplier partNumber")
    private String supplierPartnumber;
    
    @JsonProperty("Component Description")
    private String componentDescription;
    
    @JsonProperty("Battery voltage")
    private double batteryVoltage;
    
    @JsonProperty("Battery SOC")
    private double batterySOC;
    
    @JsonProperty("Battery SOH")
    private double batterySOH;
    
    @JsonProperty("Total mass of aluminum (alloys, purity?)")
    private String totalMassOfAluminum; // (Alloys, Purity?)
    
    @JsonProperty("Total mass of copper (alloys, purity?)")
    private String totalMassOfCopper; // (Alloys, Purity?)
    
    @JsonProperty("Total mass of plastics excluding battery cell (different types)")
    private String totalMassOfPlasticsExcludingBatteryCell; // (DifferentTypes)
    
    @JsonProperty("Total mass of plastics excluding battery cell (different types)_")
    private String totalMassOfPlasticsExcludingBatteryCell_; // (Different Types)
    
    @JsonProperty("Total mass of carbon ( graphite , conductive additive)")
    private String totalMassOfCarbon; // ( Graphite , Conductive Additive)
    
    @JsonProperty("Total mass of silicon")
    private String totalMassOfSilicon;
    
    @JsonProperty("Total mass of nickel")
    private String totalMassOfNickel;
    
    @JsonProperty("Total mass of cobalt")
    private String totalMassOfCobalt;
    
    @JsonProperty("Total mass of manganese")
    private String totalMassOfManganese;
    
    @JsonProperty("Total mass of iron")
    private String totalMassOfIron;
    
    @JsonProperty("Cathode chemistry")
    private String cathodeChemistry;
    
    @JsonProperty("Binder")
    private String binder;
    
    @JsonProperty("Conductive additive mass")
    private double conductiveAdditiveMass;
	
	@JsonProperty("Anode chemistry")
    private String anodeChemistry;
    
    @JsonProperty("Binder mass")
    private double binderMass;
    
    @JsonProperty("Conductive additive")
    private double conductiveAdditive;
    
    @JsonProperty("Conductive additive mass_")
    private double conductiveAdditiveMass_;
    
    @JsonProperty("Active material")
    private String activeMaterial;
    
    @JsonProperty("Active material mass")
    private double activeMaterialMass;
    
    @JsonProperty("Electrolyte solvent")
    private String electrolyteSolvent;
    
    @JsonProperty("Electrolyte solvent mass")
    private double electrolyteSolventMass;
    
    @JsonProperty("Electrolyte salt")
    private String electrolyteSalt;
    
    @JsonProperty("Electrolyte salt mass")
    private double electrolyteSaltMass;
    
    @JsonProperty("Workshop manual battery (dismantling)")
    private String workshopManualBattery; // (Dismantling)
    
    @JsonProperty("Transportation manual")
    private String transportationManual;
    
    @JsonProperty("Battery discharging manual with required can/com protocol")
    private String batteryDischargingManualWithRequiredCan_ComProtocol;
    
    @JsonProperty("CO2 footprint")
    private String co2Footprint;
    
    @JsonProperty("Recyclate use")
    private String recyclateUse;

	public void setCo2FootprintMethodology(String co2FootprintMethodology) {
		this.co2FootprintMethodology = co2FootprintMethodology;
	}

	public void setCo2ConfirmationStudies(String co2ConfirmationStudies) {
		this.co2ConfirmationStudies = co2ConfirmationStudies;
	}

	public void setCo2FootprintThreshold(String co2FootprintThreshold) {
		this.co2FootprintThreshold = co2FootprintThreshold;
	}

	public void setCo2FootprintTotal(String co2FootprintTotal) {
		this.co2FootprintTotal = co2FootprintTotal;
	}

	public void setCo2FootprintPerLifecycle(String co2FootprintPerLifecycle) {
		this.co2FootprintPerLifecycle = co2FootprintPerLifecycle;
	}

	public void setCo2FootprintVerification(String co2FootprintVerification) {
		this.co2FootprintVerification = co2FootprintVerification;
	}

	public void setCo2FootprintClassificationFromEU(String co2FootprintClassificationFromEU) {
		this.co2FootprintClassificationFromEU = co2FootprintClassificationFromEU;
	}

	public void setRecyclateCobaltContent(String recyclateCobaltContent) {
		this.recyclateCobaltContent = recyclateCobaltContent;
	}

	public void setRecyclateLeadContent(String recyclateLeadContent) {
		this.recyclateLeadContent = recyclateLeadContent;
	}

	public void setRecyclateLithiumContent(String recyclateLithiumContent) {
		this.recyclateLithiumContent = recyclateLithiumContent;
	}

	public void setRecyclateNickelContent(String recyclateNickelContent) {
		this.recyclateNickelContent = recyclateNickelContent;
	}

	public void setRecyclingDesignInformation(String recyclingDesignInformation) {
		this.recyclingDesignInformation = recyclingDesignInformation;
	}

	public void setOperationalTemperatureRange(String operationalTemperatureRange) {
		this.operationalTemperatureRange = operationalTemperatureRange;
	}

	public void setTemperatureRangeTheBatteryCanWithstandWhenNotInUse(
			String temperatureRangeTheBatteryCanWithstandWhenNotInUse) {
		this.temperatureRangeTheBatteryCanWithstandWhenNotInUse = temperatureRangeTheBatteryCanWithstandWhenNotInUse;
	}

	public void setRatedCapacity(String ratedCapacity) {
		this.ratedCapacity = ratedCapacity;
	}

	public void setCapacityFade(String capacityFade) {
		this.capacityFade = capacityFade;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public void setTotalEnergy(String totalEnergy) {
		this.totalEnergy = totalEnergy;
	}

	public void setUsableEnergy(String usableEnergy) {
		this.usableEnergy = usableEnergy;
	}

	public void setPowerFade(String powerFade) {
		this.powerFade = powerFade;
	}

	public void setInternalResistance(String internalResistance) {
		this.internalResistance = internalResistance;
	}

	public void setInternalResistanceIncrease(String internalResistanceIncrease) {
		this.internalResistanceIncrease = internalResistanceIncrease;
	}

	public void setInternalResistance_(String internalResistance_) {
		this.internalResistance_ = internalResistance_;
	}

	public void setInternalResistanceIncrease_(String internalResistanceIncrease_) {
		this.internalResistanceIncrease_ = internalResistanceIncrease_;
	}

	public void setEnergyRoundTripEfficiency(String energyRoundTripEfficiency) {
		this.energyRoundTripEfficiency = energyRoundTripEfficiency;
	}

	public void setFadeEnergyRoundTripEfficiency(String fadeEnergyRoundTripEfficiency) {
		this.fadeEnergyRoundTripEfficiency = fadeEnergyRoundTripEfficiency;
	}

	public void setC_RateOfRelevantCycle_Life(String c_RateOfRelevantCycle_Life) {
		this.c_RateOfRelevantCycle_Life = c_RateOfRelevantCycle_Life;
	}

	public void setExpectedlifetime(String expectedlifetime) {
		this.expectedlifetime = expectedlifetime;
	}

	public void setAppliedChargeRate(String appliedChargeRate) {
		this.appliedChargeRate = appliedChargeRate;
	}

	public void setAppliedDisChargeRate(String appliedDisChargeRate) {
		this.appliedDisChargeRate = appliedDisChargeRate;
	}

	public void setStorageHumidity(String storageHumidity) {
		this.storageHumidity = storageHumidity;
	}

	public void setCompositionOfCathode(String compositionOfCathode) {
		this.compositionOfCathode = compositionOfCathode;
	}

	public void setCompositionOfAnode(String compositionOfAnode) {
		this.compositionOfAnode = compositionOfAnode;
	}

	public void setCompositionOfElectrolyte(String compositionOfElectrolyte) {
		this.compositionOfElectrolyte = compositionOfElectrolyte;
	}

	public void setCompositionOfShellMaterial(String compositionOfShellMaterial) {
		this.compositionOfShellMaterial = compositionOfShellMaterial;
	}

	public void setPartNumbersForComponents(String partNumbersForComponents) {
		this.partNumbersForComponents = partNumbersForComponents;
	}

	public void setContactDetailsOfSourcesForReplacementSpares(String contactDetailsOfSourcesForReplacementSpares) {
		this.contactDetailsOfSourcesForReplacementSpares = contactDetailsOfSourcesForReplacementSpares;
	}

	public void setMaxQllowedBatteryPower(String maxQllowedBatteryPower) {
		this.maxQllowedBatteryPower = maxQllowedBatteryPower;
	}

	public void setDepthOfDischargeInTheCycle_Life(String depthOfDischargeInTheCycle_Life) {
		this.depthOfDischargeInTheCycle_Life = depthOfDischargeInTheCycle_Life;
	}

	public void setPowerCapabilityAt80PercentCharge(String powerCapabilityAt80PercentCharge) {
		this.powerCapabilityAt80PercentCharge = powerCapabilityAt80PercentCharge;
	}

	public void setPowerCapabilityAt20PercentCharge(String powerCapabilityAt20PercentCharge) {
		this.powerCapabilityAt20PercentCharge = powerCapabilityAt20PercentCharge;
	}

	public void setAccompanyingCalculations(String accompanyingCalculations) {
		this.accompanyingCalculations = accompanyingCalculations;
	}

	public void setMinVoltage(String minVoltage) {
		this.minVoltage = minVoltage;
	}

	public void setMaxVoltage(String maxVoltage) {
		this.maxVoltage = maxVoltage;
	}

	public void setNominalVoltage(String nominalVoltage) {
		this.nominalVoltage = nominalVoltage;
	}

	public void setOriginalPowerCapability(String originalPowerCapability) {
		this.originalPowerCapability = originalPowerCapability;
	}

	public void setOriginalPowerCapabilityLimits(String originalPowerCapabilityLimits) {
		this.originalPowerCapabilityLimits = originalPowerCapabilityLimits;
	}

	public void setRecyclingEfficiencies(String recyclingEfficiencies) {
		this.recyclingEfficiencies = recyclingEfficiencies;
	}

	public void setBatteryWeight_(double batteryWeight_) {
		this.batteryWeight_ = batteryWeight_;
	}

	public void setBatteryDimensions(String batteryDimensions) {
		this.batteryDimensions = batteryDimensions;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public void setPassportIssuer(String passportIssuer) {
		this.passportIssuer = passportIssuer;
	}

	public void setStateOfHealth_Soh(String stateOfHealth_Soh) {
		this.stateOfHealth_Soh = stateOfHealth_Soh;
	}

	public void setStateOfCharge_Soc(String stateOfCharge_Soc) {
		this.stateOfCharge_Soc = stateOfCharge_Soc;
	}

	public void setChangeOfOwnership_Invoice(String changeOfOwnership_Invoice) {
		this.changeOfOwnership_Invoice = changeOfOwnership_Invoice;
	}

	public void setChangeOfOwnership_Contract(String changeOfOwnership_Contract) {
		this.changeOfOwnership_Contract = changeOfOwnership_Contract;
	}

	public void setPackagingInstructions(String packagingInstructions) {
		this.packagingInstructions = packagingInstructions;
	}

	public void setTransportationInstructions(String transportationInstructions) {
		this.transportationInstructions = transportationInstructions;
	}

	public void setHandlingInstructions(String handlingInstructions) {
		this.handlingInstructions = handlingInstructions;
	}

	public void setPersonalProtectiveEquipment_PPE(String personalProtectiveEquipment_PPE) {
		this.personalProtectiveEquipment_PPE = personalProtectiveEquipment_PPE;
	}

	public void setVehicleDismantlingProcedure(String vehicleDismantlingProcedure) {
		this.vehicleDismantlingProcedure = vehicleDismantlingProcedure;
	}

	public void setBatteryDismantlingInformation(String batteryDismantlingInformation) {
		this.batteryDismantlingInformation = batteryDismantlingInformation;
	}

	public void setNumberOfNewEVBatteriesSoldPerYearPerEUMemberState(
			String numberOfNewEVBatteriesSoldPerYearPerEUMemberState) {
		this.numberOfNewEVBatteriesSoldPerYearPerEUMemberState = numberOfNewEVBatteriesSoldPerYearPerEUMemberState;
	}

	public void setNumberOfNewBatteriesForEGE_Scooters(String numberOfNewBatteriesForEGE_Scooters) {
		this.numberOfNewBatteriesForEGE_Scooters = numberOfNewBatteriesForEGE_Scooters;
	}

	public void setNumberOfEVBatteriesExportedOutOfEUPerYear(String numberOfEVBatteriesExportedOutOfEUPerYear) {
		this.numberOfEVBatteriesExportedOutOfEUPerYear = numberOfEVBatteriesExportedOutOfEUPerYear;
	}

	public void setNumberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear(
			String numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear) {
		this.numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear = numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear;
	}

	public void setNumberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear(
			String numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear) {
		this.numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear = numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear;
	}

	public void setNumberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear(
			String numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear) {
		this.numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear = numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear;
	}

	public void setNumberOfWasteBatteriesCollectedPerMemberStatePerYear(
			String numberOfWasteBatteriesCollectedPerMemberStatePerYear) {
		this.numberOfWasteBatteriesCollectedPerMemberStatePerYear = numberOfWasteBatteriesCollectedPerMemberStatePerYear;
	}

	public void setInfosOnRecyclingProcesses(String infosOnRecyclingProcesses) {
		this.infosOnRecyclingProcesses = infosOnRecyclingProcesses;
	}

	public void setCollectedWasteBatteriesForExportAndSubsequentRecycling(
			String collectedWasteBatteriesForExportAndSubsequentRecycling) {
		this.collectedWasteBatteriesForExportAndSubsequentRecycling = collectedWasteBatteriesForExportAndSubsequentRecycling;
	}

	public void setBatteryType_Model(String batteryType_Model) {
		this.batteryType_Model = batteryType_Model;
	}

	public void setBatterySerialNumber(String batterySerialNumber) {
		this.batterySerialNumber = batterySerialNumber;
	}

	public void setBatteryBatchNumber(String batteryBatchNumber) {
		this.batteryBatchNumber = batteryBatchNumber;
	}

	public void setDateOfManufacture(String dateOfManufacture) {
		this.dateOfManufacture = dateOfManufacture;
	}

	public void setNameOfManufacturer_(String nameOfManufacturer_) {
		this.nameOfManufacturer_ = nameOfManufacturer_;
	}

	public void setPlaceOfManufacturing_CountryOfOrigin(String placeOfManufacturing_CountryOfOrigin) {
		this.placeOfManufacturing_CountryOfOrigin = placeOfManufacturing_CountryOfOrigin;
	}

	public void setDateOfPlacingOnMarket(String dateOfPlacingOnMarket) {
		this.dateOfPlacingOnMarket = dateOfPlacingOnMarket;
	}

	public void setHazardousSubstancesInBattery(String hazardousSubstancesInBattery) {
		this.hazardousSubstancesInBattery = hazardousSubstancesInBattery;
	}

	public void setChemicalSymbolsForMetals(String chemicalSymbolsForMetals) {
		this.chemicalSymbolsForMetals = chemicalSymbolsForMetals;
	}

	public void setCriticalRawMaterials(String criticalRawMaterials) {
		this.criticalRawMaterials = criticalRawMaterials;
	}

	public void setCollectionSymbol(String collectionSymbol) {
		this.collectionSymbol = collectionSymbol;
	}

	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}

	public void setHazardSigns(String hazardSigns) {
		this.hazardSigns = hazardSigns;
	}

	public void setCEMark(String cEMark) {
		CEMark = cEMark;
	}

	public void setIdNumberOfNotifiedBody(String idNumberOfNotifiedBody) {
		this.idNumberOfNotifiedBody = idNumberOfNotifiedBody;
	}

	public void setNameOfNotifiedBody(String nameOfNotifiedBody) {
		this.nameOfNotifiedBody = nameOfNotifiedBody;
	}

	public void setAdressOfNotifiedBody(String adressOfNotifiedBody) {
		this.adressOfNotifiedBody = adressOfNotifiedBody;
	}

	public void setIssuedCertificate(String issuedCertificate) {
		this.issuedCertificate = issuedCertificate;
	}

	public void setApplicationDescriptionAndRecyclingPossibilities(
			String applicationDescriptionAndRecyclingPossibilities) {
		this.applicationDescriptionAndRecyclingPossibilities = applicationDescriptionAndRecyclingPossibilities;
	}

	public void setRoleOfEVOwnersInRecyclingBatteries(String roleOfEVOwnersInRecyclingBatteries) {
		this.roleOfEVOwnersInRecyclingBatteries = roleOfEVOwnersInRecyclingBatteries;
	}

	public void setEnviromentalImpactOfBatteries(String enviromentalImpactOfBatteries) {
		this.enviromentalImpactOfBatteries = enviromentalImpactOfBatteries;
	}

	public void setNameOfManufacturer(String nameOfManufacturer) {
		this.nameOfManufacturer = nameOfManufacturer;
	}

	public void setAdressOfManufacturer(String adressOfManufacturer) {
		this.adressOfManufacturer = adressOfManufacturer;
	}

	public void setTelephoneNumberOfManufacturer(String telephoneNumberOfManufacturer) {
		this.telephoneNumberOfManufacturer = telephoneNumberOfManufacturer;
	}

	public void setEmailAdressOfManufacturer(String emailAdressOfManufacturer) {
		this.emailAdressOfManufacturer = emailAdressOfManufacturer;
	}

	public void setFaxNumberOfManufacturer(String faxNumberOfManufacturer) {
		this.faxNumberOfManufacturer = faxNumberOfManufacturer;
	}

	public void setWebsiteOfManufacturer(String websiteOfManufacturer) {
		this.websiteOfManufacturer = websiteOfManufacturer;
	}

	public void setNationalIdNumberOfProducer(String nationalIdNumberOfProducer) {
		this.nationalIdNumberOfProducer = nationalIdNumberOfProducer;
	}

	public void setTradeRegisterNumberOfProducer(String tradeRegisterNumberOfProducer) {
		this.tradeRegisterNumberOfProducer = tradeRegisterNumberOfProducer;
	}

	public void setEUTaxIdNumber(String eUTaxIdNumber) {
		EUTaxIdNumber = eUTaxIdNumber;
	}

	public void setBatteryTypeDescription(String batteryTypeDescription) {
		this.batteryTypeDescription = batteryTypeDescription;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public void setDeclarationNumber(String declarationNumber) {
		this.declarationNumber = declarationNumber;
	}

	public void setEUDeclarationOfConformity(String eUDeclarationOfConformity) {
		EUDeclarationOfConformity = eUDeclarationOfConformity;
	}

	public void setCarbonFootprint_CFP_Declaration(String carbonFootprint_CFP_Declaration) {
		this.carbonFootprint_CFP_Declaration = carbonFootprint_CFP_Declaration;
	}

	public void setGeographicLocationOfManufacturingFacility(String geographicLocationOfManufacturingFacility) {
		this.geographicLocationOfManufacturingFacility = geographicLocationOfManufacturingFacility;
	}

	public void setBatteryProducer_Supplier(String batteryProducer_Supplier) {
		this.batteryProducer_Supplier = batteryProducer_Supplier;
	}

	public void setProductionSite(String productionSite) {
		this.productionSite = productionSite;
	}

	public void setDateOfProduction(String dateOfProduction) {
		this.dateOfProduction = dateOfProduction;
	}

	public void setBeginOfLifeVehicle_ProductionDateVehicle(String beginOfLifeVehicle_ProductionDateVehicle) {
		this.beginOfLifeVehicle_ProductionDateVehicle = beginOfLifeVehicle_ProductionDateVehicle;
	}

	public void setOriginalEquipmentOf_VehicleType(String originalEquipmentOf_VehicleType) {
		this.originalEquipmentOf_VehicleType = originalEquipmentOf_VehicleType;
	}

	public void setVehicleId_VIN(String vehicleId_VIN) {
		this.vehicleId_VIN = vehicleId_VIN;
	}

	public void setBatteryId_SerialNumber(String batteryId_SerialNumber) {
		this.batteryId_SerialNumber = batteryId_SerialNumber;
	}

	public void setBatteryWeight(String batteryWeight) {
		this.batteryWeight = batteryWeight;
	}

	public void setNumberOfBatteryCells(String numberOfBatteryCells) {
		this.numberOfBatteryCells = numberOfBatteryCells;
	}

	public void setBatteryCellConnection_Serial(String batteryCellConnection_Serial) {
		this.batteryCellConnection_Serial = batteryCellConnection_Serial;
	}

	public void setBatteryCapacity(String batteryCapacity) {
		this.batteryCapacity = batteryCapacity;
	}

	public void setPartNumberPlusChangeIndex(String partNumberPlusChangeIndex) {
		this.partNumberPlusChangeIndex = partNumberPlusChangeIndex;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public void setMaxPower(String maxPower) {
		this.maxPower = maxPower;
	}

	public void setSupplierPartnumber(String supplierPartnumber) {
		this.supplierPartnumber = supplierPartnumber;
	}

	public void setComponentDescription(String componentDescription) {
		this.componentDescription = componentDescription;
	}

	public void setBatteryVoltage(double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}

	public void setBatterySOC(double batterySOC) {
		this.batterySOC = batterySOC;
	}

	public void setBatterySOH(double batterySOH) {
		this.batterySOH = batterySOH;
	}

	public void setTotalMassOfAluminum(String totalMassOfAluminum) {
		this.totalMassOfAluminum = totalMassOfAluminum;
	}

	public void setTotalMassOfCopper(String totalMassOfCopper) {
		this.totalMassOfCopper = totalMassOfCopper;
	}

	public void setTotalMassOfPlasticsExcludingBatteryCell(String totalMassOfPlasticsExcludingBatteryCell) {
		this.totalMassOfPlasticsExcludingBatteryCell = totalMassOfPlasticsExcludingBatteryCell;
	}

	public void setTotalMassOfPlasticsExcludingBatteryCell_(String totalMassOfPlasticsExcludingBatteryCell_) {
		this.totalMassOfPlasticsExcludingBatteryCell_ = totalMassOfPlasticsExcludingBatteryCell_;
	}

	public void setTotalMassOfCarbon(String totalMassOfCarbon) {
		this.totalMassOfCarbon = totalMassOfCarbon;
	}

	public void setTotalMassOfSilicon(String totalMassOfSilicon) {
		this.totalMassOfSilicon = totalMassOfSilicon;
	}

	public void setTotalMassOfNickel(String totalMassOfNickel) {
		this.totalMassOfNickel = totalMassOfNickel;
	}

	public void setTotalMassOfCobalt(String totalMassOfCobalt) {
		this.totalMassOfCobalt = totalMassOfCobalt;
	}

	public void setTotalMassOfManganese(String totalMassOfManganese) {
		this.totalMassOfManganese = totalMassOfManganese;
	}

	public void setTotalMassOfIron(String totalMassOfIron) {
		this.totalMassOfIron = totalMassOfIron;
	}

	public void setCathodeChemistry(String cathodeChemistry) {
		this.cathodeChemistry = cathodeChemistry;
	}

	public void setBinder(String binder) {
		this.binder = binder;
	}

	public void setConductiveAdditiveMass(double conductiveAdditiveMass) {
		this.conductiveAdditiveMass = conductiveAdditiveMass;
	}

	public void setAnodeChemistry(String anodeChemistry) {
		this.anodeChemistry = anodeChemistry;
	}

	public void setBinderMass(double binderMass) {
		this.binderMass = binderMass;
	}

	public void setConductiveAdditive(double conductiveAdditive) {
		this.conductiveAdditive = conductiveAdditive;
	}

	public void setConductiveAdditiveMass_(double conductiveAdditiveMass_) {
		this.conductiveAdditiveMass_ = conductiveAdditiveMass_;
	}

	public void setActiveMaterial(String activeMaterial) {
		this.activeMaterial = activeMaterial;
	}

	public void setActiveMaterialMass(double activeMaterialMass) {
		this.activeMaterialMass = activeMaterialMass;
	}

	public void setElectrolyteSolvent(String electrolyteSolvent) {
		this.electrolyteSolvent = electrolyteSolvent;
	}

	public void setElectrolyteSolventMass(double electrolyteSolventMass) {
		this.electrolyteSolventMass = electrolyteSolventMass;
	}

	public void setElectrolyteSalt(String electrolyteSalt) {
		this.electrolyteSalt = electrolyteSalt;
	}

	public void setElectrolyteSaltMass(double electrolyteSaltMass) {
		this.electrolyteSaltMass = electrolyteSaltMass;
	}

	public void setWorkshopManualBattery(String workshopManualBattery) {
		this.workshopManualBattery = workshopManualBattery;
	}

	public void setTransportationManual(String transportationManual) {
		this.transportationManual = transportationManual;
	}

	public void setBatteryDischargingManualWithRequiredCan_ComProtocol(
			String batteryDischargingManualWithRequiredCan_ComProtocol) {
		this.batteryDischargingManualWithRequiredCan_ComProtocol = batteryDischargingManualWithRequiredCan_ComProtocol;
	}

	public void setCo2Footprint(String co2Footprint) {
		this.co2Footprint = co2Footprint;
	}

	public void setRecyclateUse(String recyclateUse) {
		this.recyclateUse = recyclateUse;
	}

	public String getCo2FootprintMethodology() {
		return co2FootprintMethodology;
	}

	public String getCo2ConfirmationStudies() {
		return co2ConfirmationStudies;
	}

	public String getCo2FootprintThreshold() {
		return co2FootprintThreshold;
	}

	public String getCo2FootprintTotal() {
		return co2FootprintTotal;
	}

	public String getCo2FootprintPerLifecycle() {
		return co2FootprintPerLifecycle;
	}

	public String getCo2FootprintVerification() {
		return co2FootprintVerification;
	}

	public String getCo2FootprintClassificationFromEU() {
		return co2FootprintClassificationFromEU;
	}

	public String getRecyclateCobaltContent() {
		return recyclateCobaltContent;
	}

	public String getRecyclateLeadContent() {
		return recyclateLeadContent;
	}

	public String getRecyclateLithiumContent() {
		return recyclateLithiumContent;
	}

	public String getRecyclateNickelContent() {
		return recyclateNickelContent;
	}

	public String getRecyclingDesignInformation() {
		return recyclingDesignInformation;
	}

	public String getOperationalTemperatureRange() {
		return operationalTemperatureRange;
	}

	public String getTemperatureRangeTheBatteryCanWithstandWhenNotInUse() {
		return temperatureRangeTheBatteryCanWithstandWhenNotInUse;
	}

	public String getRatedCapacity() {
		return ratedCapacity;
	}

	public String getCapacityFade() {
		return capacityFade;
	}

	public String getPower() {
		return power;
	}

	public String getTotalEnergy() {
		return totalEnergy;
	}

	public String getUsableEnergy() {
		return usableEnergy;
	}

	public String getPowerFade() {
		return powerFade;
	}

	public String getInternalResistance() {
		return internalResistance;
	}

	public String getInternalResistanceIncrease() {
		return internalResistanceIncrease;
	}

	public String getInternalResistance_() {
		return internalResistance_;
	}

	public String getInternalResistanceIncrease_() {
		return internalResistanceIncrease_;
	}

	public String getEnergyRoundTripEfficiency() {
		return energyRoundTripEfficiency;
	}

	public String getFadeEnergyRoundTripEfficiency() {
		return fadeEnergyRoundTripEfficiency;
	}

	public String getC_RateOfRelevantCycle_Life() {
		return c_RateOfRelevantCycle_Life;
	}

	public String getExpectedlifetime() {
		return expectedlifetime;
	}

	public String getAppliedChargeRate() {
		return appliedChargeRate;
	}

	public String getAppliedDisChargeRate() {
		return appliedDisChargeRate;
	}

	public String getStorageHumidity() {
		return storageHumidity;
	}

	public String getCompositionOfCathode() {
		return compositionOfCathode;
	}

	public String getCompositionOfAnode() {
		return compositionOfAnode;
	}

	public String getCompositionOfElectrolyte() {
		return compositionOfElectrolyte;
	}

	public String getCompositionOfShellMaterial() {
		return compositionOfShellMaterial;
	}

	public String getPartNumbersForComponents() {
		return partNumbersForComponents;
	}

	public String getContactDetailsOfSourcesForReplacementSpares() {
		return contactDetailsOfSourcesForReplacementSpares;
	}

	public String getMaxQllowedBatteryPower() {
		return maxQllowedBatteryPower;
	}

	public String getDepthOfDischargeInTheCycle_Life() {
		return depthOfDischargeInTheCycle_Life;
	}

	public String getPowerCapabilityAt80PercentCharge() {
		return powerCapabilityAt80PercentCharge;
	}

	public String getPowerCapabilityAt20PercentCharge() {
		return powerCapabilityAt20PercentCharge;
	}

	public String getAccompanyingCalculations() {
		return accompanyingCalculations;
	}

	public String getMinVoltage() {
		return minVoltage;
	}

	public String getMaxVoltage() {
		return maxVoltage;
	}

	public String getNominalVoltage() {
		return nominalVoltage;
	}

	public String getOriginalPowerCapability() {
		return originalPowerCapability;
	}

	public String getOriginalPowerCapabilityLimits() {
		return originalPowerCapabilityLimits;
	}

	public String getRecyclingEfficiencies() {
		return recyclingEfficiencies;
	}

	public double getBatteryWeight_() {
		return batteryWeight_;
	}

	public String getBatteryDimensions() {
		return batteryDimensions;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public String getPassportIssuer() {
		return passportIssuer;
	}

	public String getStateOfHealth_Soh() {
		return stateOfHealth_Soh;
	}

	public String getStateOfCharge_Soc() {
		return stateOfCharge_Soc;
	}

	public String getChangeOfOwnership_Invoice() {
		return changeOfOwnership_Invoice;
	}

	public String getChangeOfOwnership_Contract() {
		return changeOfOwnership_Contract;
	}

	public String getPackagingInstructions() {
		return packagingInstructions;
	}

	public String getTransportationInstructions() {
		return transportationInstructions;
	}

	public String getHandlingInstructions() {
		return handlingInstructions;
	}

	public String getPersonalProtectiveEquipment_PPE() {
		return personalProtectiveEquipment_PPE;
	}

	public String getVehicleDismantlingProcedure() {
		return vehicleDismantlingProcedure;
	}

	public String getBatteryDismantlingInformation() {
		return batteryDismantlingInformation;
	}

	public String getNumberOfNewEVBatteriesSoldPerYearPerEUMemberState() {
		return numberOfNewEVBatteriesSoldPerYearPerEUMemberState;
	}

	public String getNumberOfNewBatteriesForEGE_Scooters() {
		return numberOfNewBatteriesForEGE_Scooters;
	}

	public String getNumberOfEVBatteriesExportedOutOfEUPerYear() {
		return numberOfEVBatteriesExportedOutOfEUPerYear;
	}

	public String getNumberOfBatteriesForEGEScootersExportedOutOfEUPerYear() {
		return numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear;
	}

	public String getNumberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear() {
		return numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear;
	}

	public String getNumberOfBatteriesForEGEScootersSubmittedForRecyclingPerMemberStatePerYear() {
		return numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear;
	}

	public String getNumberOfWasteBatteriesCollectedPerMemberStatePerYear() {
		return numberOfWasteBatteriesCollectedPerMemberStatePerYear;
	}

	public String getInfosOnRecyclingProcesses() {
		return infosOnRecyclingProcesses;
	}

	public String getCollectedWasteBatteriesForExportAndSubsequentRecycling() {
		return collectedWasteBatteriesForExportAndSubsequentRecycling;
	}

	public String getBatteryType_Model() {
		return batteryType_Model;
	}

	public String getBatterySerialNumber() {
		return batterySerialNumber;
	}

	public String getBatteryBatchNumber() {
		return batteryBatchNumber;
	}

	public String getDateOfManufacture() {
		return dateOfManufacture;
	}

	public String getNameOfManufacturer_() {
		return nameOfManufacturer_;
	}

	public String getPlaceOfManufacturing_CountryOfOrigin() {
		return placeOfManufacturing_CountryOfOrigin;
	}

	public String getDateOfPlacingOnMarket() {
		return dateOfPlacingOnMarket;
	}

	public String getHazardousSubstancesInBattery() {
		return hazardousSubstancesInBattery;
	}

	public String getChemicalSymbolsForMetals() {
		return chemicalSymbolsForMetals;
	}

	public String getCriticalRawMaterials() {
		return criticalRawMaterials;
	}

	public String getCollectionSymbol() {
		return collectionSymbol;
	}

	public String getQRCode() {
		return QRCode;
	}

	public String getHazardSigns() {
		return hazardSigns;
	}

	public String getCEMark() {
		return CEMark;
	}

	public String getIdNumberOfNotifiedBody() {
		return idNumberOfNotifiedBody;
	}

	public String getNameOfNotifiedBody() {
		return nameOfNotifiedBody;
	}

	public String getAdressOfNotifiedBody() {
		return adressOfNotifiedBody;
	}

	public String getIssuedCertificate() {
		return issuedCertificate;
	}

	public String getApplicationDescriptionAndRecyclingPossibilities() {
		return applicationDescriptionAndRecyclingPossibilities;
	}

	public String getRoleOfEVOwnersInRecyclingBatteries() {
		return roleOfEVOwnersInRecyclingBatteries;
	}

	public String getEnviromentalImpactOfBatteries() {
		return enviromentalImpactOfBatteries;
	}

	public String getNameOfManufacturer() {
		return nameOfManufacturer;
	}

	public String getAdressOfManufacturer() {
		return adressOfManufacturer;
	}

	public String getTelephoneNumberOfManufacturer() {
		return telephoneNumberOfManufacturer;
	}

	public String getEmailAdressOfManufacturer() {
		return emailAdressOfManufacturer;
	}

	public String getFaxNumberOfManufacturer() {
		return faxNumberOfManufacturer;
	}

	public String getWebsiteOfManufacturer() {
		return websiteOfManufacturer;
	}

	public String getNationalIdNumberOfProducer() {
		return nationalIdNumberOfProducer;
	}

	public String getTradeRegisterNumberOfProducer() {
		return tradeRegisterNumberOfProducer;
	}

	public String getEUTaxIdNumber() {
		return EUTaxIdNumber;
	}

	public String getBatteryTypeDescription() {
		return batteryTypeDescription;
	}

	public String getBrandName() {
		return brandName;
	}

	public String getDeclarationNumber() {
		return declarationNumber;
	}

	public String getEUDeclarationOfConformity() {
		return EUDeclarationOfConformity;
	}

	public String getCarbonFootprint_CFP_Declaration() {
		return carbonFootprint_CFP_Declaration;
	}

	public String getGeographicLocationOfManufacturingFacility() {
		return geographicLocationOfManufacturingFacility;
	}

	public String getBatteryProducer_Supplier() {
		return batteryProducer_Supplier;
	}

	public String getProductionSite() {
		return productionSite;
	}

	public String getDateOfProduction() {
		return dateOfProduction;
	}

	public String getBeginOfLifeVehicle_ProductionDateVehicle() {
		return beginOfLifeVehicle_ProductionDateVehicle;
	}

	public String getOriginalEquipmentOf_VehicleType() {
		return originalEquipmentOf_VehicleType;
	}

	public String getVehicleId_VIN() {
		return vehicleId_VIN;
	}

	public String getBatteryId_SerialNumber() {
		return batteryId_SerialNumber;
	}

	public String getBatteryWeight() {
		return batteryWeight;
	}

	public String getNumberOfBatteryCells() {
		return numberOfBatteryCells;
	}

	public String getBatteryCellConnection_Serial() {
		return batteryCellConnection_Serial;
	}

	public String getBatteryCapacity() {
		return batteryCapacity;
	}

	public String getPartNumberPlusChangeIndex() {
		return partNumberPlusChangeIndex;
	}

	public String getBrand() {
		return brand;
	}

	public String getBatteryType() {
		return batteryType;
	}

	public String getMaxPower() {
		return maxPower;
	}

	public String getSupplierPartnumber() {
		return supplierPartnumber;
	}

	public String getComponentDescription() {
		return componentDescription;
	}

	public double getBatteryVoltage() {
		return batteryVoltage;
	}

	public double getBatterySOC() {
		return batterySOC;
	}

	public double getBatterySOH() {
		return batterySOH;
	}

	public String getTotalMassOfAluminum() {
		return totalMassOfAluminum;
	}

	public String getTotalMassOfCopper() {
		return totalMassOfCopper;
	}

	public String getTotalMassOfPlasticsExcludingBatteryCell() {
		return totalMassOfPlasticsExcludingBatteryCell;
	}

	public String getTotalMassOfPlasticsExcludingBatteryCell_() {
		return totalMassOfPlasticsExcludingBatteryCell_;
	}

	public String getTotalMassOfCarbon() {
		return totalMassOfCarbon;
	}

	public String getTotalMassOfSilicon() {
		return totalMassOfSilicon;
	}

	public String getTotalMassOfNickel() {
		return totalMassOfNickel;
	}

	public String getTotalMassOfCobalt() {
		return totalMassOfCobalt;
	}

	public String getTotalMassOfManganese() {
		return totalMassOfManganese;
	}

	public String getTotalMassOfIron() {
		return totalMassOfIron;
	}

	public String getCathodeChemistry() {
		return cathodeChemistry;
	}

	public String getBinder() {
		return binder;
	}

	public double getConductiveAdditiveMass() {
		return conductiveAdditiveMass;
	}

	public String getAnodeChemistry() {
		return anodeChemistry;
	}

	public double getBinderMass() {
		return binderMass;
	}

	public double getConductiveAdditive() {
		return conductiveAdditive;
	}

	public double getConductiveAdditiveMass_() {
		return conductiveAdditiveMass_;
	}

	public String getActiveMaterial() {
		return activeMaterial;
	}

	public double getActiveMaterialMass() {
		return activeMaterialMass;
	}

	public String getElectrolyteSolvent() {
		return electrolyteSolvent;
	}

	public double getElectrolyteSolventMass() {
		return electrolyteSolventMass;
	}

	public String getElectrolyteSalt() {
		return electrolyteSalt;
	}

	public double getElectrolyteSaltMass() {
		return electrolyteSaltMass;
	}

	public String getWorkshopManualBattery() {
		return workshopManualBattery;
	}

	public String getTransportationManual() {
		return transportationManual;
	}

	public String getBatteryDischargingManualWithRequiredCan_ComProtocol() {
		return batteryDischargingManualWithRequiredCan_ComProtocol;
	}

	public String getCo2Footprint() {
		return co2Footprint;
	}

	public String getRecyclateUse() {
		return recyclateUse;
	}

    @Override
	public int hashCode() {
		return Objects.hash(CEMark, EUDeclarationOfConformity, EUTaxIdNumber, QRCode, accompanyingCalculations,
				activeMaterial, activeMaterialMass, adressOfManufacturer, adressOfNotifiedBody, anodeChemistry,
				applicationDescriptionAndRecyclingPossibilities, appliedChargeRate, appliedDisChargeRate,
				batteryBatchNumber, batteryCapacity, batteryCellConnection_Serial, batteryDimensions,
				batteryDischargingManualWithRequiredCan_ComProtocol, batteryDismantlingInformation,
				batteryId_SerialNumber, batteryProducer_Supplier, batterySOC, batterySOH, batterySerialNumber,
				batteryType, batteryTypeDescription, batteryType_Model, batteryVoltage, batteryWeight, batteryWeight_,
				beginOfLifeVehicle_ProductionDateVehicle, binder, binderMass, brand, brandName,
				c_RateOfRelevantCycle_Life, capacityFade, carbonFootprint_CFP_Declaration, cathodeChemistry,
				changeOfOwnership_Contract, changeOfOwnership_Invoice, chemicalSymbolsForMetals, co2ConfirmationStudies,
				co2Footprint, co2FootprintClassificationFromEU, co2FootprintMethodology, co2FootprintPerLifecycle,
				co2FootprintThreshold, co2FootprintTotal, co2FootprintVerification,
				collectedWasteBatteriesForExportAndSubsequentRecycling, collectionSymbol, componentDescription,
				compositionOfAnode, compositionOfCathode, compositionOfElectrolyte, compositionOfShellMaterial,
				conductiveAdditive, conductiveAdditiveMass, conductiveAdditiveMass_,
				contactDetailsOfSourcesForReplacementSpares, criticalRawMaterials, dateOfManufacture,
				dateOfPlacingOnMarket, dateOfProduction, declarationNumber, depthOfDischargeInTheCycle_Life,
				electrolyteSalt, electrolyteSaltMass, electrolyteSolvent, electrolyteSolventMass,
				emailAdressOfManufacturer, energyRoundTripEfficiency, enviromentalImpactOfBatteries, expectedlifetime,
				fadeEnergyRoundTripEfficiency, faxNumberOfManufacturer, geographicLocationOfManufacturingFacility,
				handlingInstructions, hazardSigns, hazardousSubstancesInBattery, idNumberOfNotifiedBody,
				infosOnRecyclingProcesses, internalResistance, internalResistanceIncrease, internalResistanceIncrease_,
				internalResistance_, issuedCertificate, maxPower, maxQllowedBatteryPower, maxVoltage, minVoltage,
				nameOfManufacturer, nameOfManufacturer_, nameOfNotifiedBody, nationalIdNumberOfProducer, nominalVoltage,
				numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear,
				numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear,
				numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear, numberOfBatteryCells,
				numberOfEVBatteriesExportedOutOfEUPerYear, numberOfNewBatteriesForEGE_Scooters,
				numberOfNewEVBatteriesSoldPerYearPerEUMemberState, numberOfWasteBatteriesCollectedPerMemberStatePerYear,
				operationalTemperatureRange, originalEquipmentOf_VehicleType, originalPowerCapability,
				originalPowerCapabilityLimits, packagingInstructions, partNumberPlusChangeIndex,
				partNumbersForComponents, passportIssuer, passportNumber, personalProtectiveEquipment_PPE,
				placeOfManufacturing_CountryOfOrigin, power, powerCapabilityAt20PercentCharge,
				powerCapabilityAt80PercentCharge, powerFade, productionSite, ratedCapacity, recyclateCobaltContent,
				recyclateLeadContent, recyclateLithiumContent, recyclateNickelContent, recyclateUse,
				recyclingDesignInformation, recyclingEfficiencies, roleOfEVOwnersInRecyclingBatteries,
				stateOfCharge_Soc, stateOfHealth_Soh, storageHumidity, supplierPartnumber,
				telephoneNumberOfManufacturer, temperatureRangeTheBatteryCanWithstandWhenNotInUse, totalEnergy,
				totalMassOfAluminum, totalMassOfCarbon, totalMassOfCobalt, totalMassOfCopper, totalMassOfIron,
				totalMassOfManganese, totalMassOfNickel, totalMassOfPlasticsExcludingBatteryCell,
				totalMassOfPlasticsExcludingBatteryCell_, totalMassOfSilicon, tradeRegisterNumberOfProducer,
				transportationInstructions, transportationManual, usableEnergy, vehicleDismantlingProcedure,
				vehicleId_VIN, websiteOfManufacturer, workshopManualBattery);
	}

	@JsonPOJOBuilder(withPrefix = "")
    public static final class Builder {
    	
    	@JsonProperty("Co2 Footprint Methodology")
    	private String co2FootprintMethodology;
    	
    	@JsonProperty("Co2 Confirmation Studies")
    	private String co2ConfirmationStudies;
    	
    	@JsonProperty("Co2 Footprint Threshold")
    	private String co2FootprintThreshold;
    	
    	@JsonProperty("Co2 Footprint Total")
    	private String co2FootprintTotal;
    	
        @JsonProperty("Co2 Footprint per lifecycle")
        private String co2FootprintPerLifecycle;
        
        @JsonProperty("Co2 Footprint Verification")
        private String co2FootprintVerification;
        
        @JsonProperty("Co2 Footprint Classification from EU")
        private String co2FootprintClassificationFromEU;
        
        @JsonProperty("Recyclate cobalt content")
        private String recyclateCobaltContent;
        
        @JsonProperty("Recyclate lead content")
        private String recyclateLeadContent;
        
        @JsonProperty("Recyclate lithium content")
        private String recyclateLithiumContent;
        
        @JsonProperty("Recyclate nickel content")
        private String recyclateNickelContent;
        
        @JsonProperty("Recycling design information")
        private String recyclingDesignInformation;
        
        @JsonProperty("Operational temperature range")
        private String operationalTemperatureRange;
        
        @JsonProperty("Temperature range the battery can withstand when not in use (reference test)")
        private String temperatureRangeTheBatteryCanWithstandWhenNotInUse;
        
        @JsonProperty("Rated Capacity")
        private String ratedCapacity;
        
        @JsonProperty("Capacity fade")
        private String capacityFade;
        
        @JsonProperty("Power")
        private String power;
        
        @JsonProperty("Total Energy")
        private String totalEnergy;
        
        @JsonProperty("Usable Energy")
        private String usableEnergy;
        
        @JsonProperty("Power Fade")
        private String powerFade;
        
        @JsonProperty("Internal resistance")
        private String internalResistance;
        
        @JsonProperty("Internal resistance increase")
        private String internalResistanceIncrease;
        
        @JsonProperty("Internal resistance_")
        private String internalResistance_;
        
        @JsonProperty("Internal resistance increase_")
        private String internalResistanceIncrease_;
        
        @JsonProperty("Energy round trip efficiency")
        private String energyRoundTripEfficiency;
        
        @JsonProperty("Fade energy round trip efficiency")
        private String fadeEnergyRoundTripEfficiency;
        
        @JsonProperty("C-rate of relevant cycle-life test")
        private String c_RateOfRelevantCycle_Life; // Test
        
        @JsonProperty("Expected lifetime")
        private String expectedlifetime;
        
        @JsonProperty("Applied charge rate")
        private String appliedChargeRate;
        
        @JsonProperty("Applied discharge rate")
        private String appliedDisChargeRate;
        
        @JsonProperty("Storage humidity")
        private String storageHumidity;
        
        @JsonProperty("Composition of Cathode i.e. nickel, cobalt, lithium contents")
        private String compositionOfCathode; // I.E. Nickel, Cobalt, Lithium Contents
        
        @JsonProperty("Composition of Anode i.e. natural graphite content, lead")
        private String compositionOfAnode; // I.E. Natural Graphite Content, Lead
        
        
        @JsonProperty("Composition of Electrolyte")
        private String compositionOfElectrolyte;
        
        @JsonProperty("Composition of shell material / other parts of battery")
        private String compositionOfShellMaterial; /// Other Parts Of Battery
        
        @JsonProperty("Part numbers for components")
        private String partNumbersForComponents;
        
        @JsonProperty("Contact details of sources for replacement spares")
        private String contactDetailsOfSourcesForReplacementSpares;
        
        @JsonProperty("Max. allowed battery power (W) vs. battery energy (Wh)")
        private String maxQllowedBatteryPower; // (W) Vs. Batteryenergy (Wh)
        
        @JsonProperty("Depth of discharge in the cycle-life test")
        private String depthOfDischargeInTheCycle_Life; // Test
        
        @JsonProperty("Power capability at 80% charge")
        private String powerCapabilityAt80PercentCharge;
        
        @JsonProperty("Power capability at 20% charge")
        private String powerCapabilityAt20PercentCharge;
        
        @JsonProperty("Accompanying Calculations")
        private String accompanyingCalculations;
        
        @JsonProperty("Min. Voltage")
        private String minVoltage;
        
        @JsonProperty("Max. Voltage")
        private String maxVoltage;
        
        @JsonProperty("Nominal Voltage")
        private String nominalVoltage;
        
        @JsonProperty("Original power capability")
        private String originalPowerCapability;
        
        @JsonProperty("Original power capability limits")
        private String originalPowerCapabilityLimits;
        
        @JsonProperty("Recycling efficiencies")
        private String recyclingEfficiencies;
        
        @JsonProperty("Battery weight_")
        private double batteryWeight_;
        
        @JsonProperty("Battery dimensions")
        private String batteryDimensions;
        
        @JsonProperty("Passport number")
        @JsonAlias("passportNumber")
        private String passportNumber;
        
        @JsonProperty("Passport issuer")
        private String passportIssuer;
        
        @JsonProperty("State of Health (SoH")
        private String stateOfHealth_Soh;
        
        @JsonProperty("State of Charge (SoC")
        private String stateOfCharge_Soc;
        
        @JsonProperty("Change of ownership - invoice")
        private String changeOfOwnership_Invoice;
        
        @JsonProperty("Change of ownership - contract")
        private String changeOfOwnership_Contract;
        
        @JsonProperty("Packaging instructions")
        private String packagingInstructions;
        
        @JsonProperty("Transportation instructions")
        private String transportationInstructions;
        
        @JsonProperty("Handling instructions")
        private String handlingInstructions;
        
        @JsonProperty("Personal Protective Equipment (PPE)")
        private String personalProtectiveEquipment_PPE;
        
        @JsonProperty("Vehicle dismantling procedure")
        private String vehicleDismantlingProcedure;
        
        @JsonProperty("Battery Dismantling information")
        private String batteryDismantlingInformation;
        
        @JsonProperty("Number of new EV batteries sold per year per EU member state")
        private String numberOfNewEVBatteriesSoldPerYearPerEUMemberState;
        
        @JsonProperty("Number of new batteries for e.g. e-scooters etc. sold per year per EU member state")
        private String numberOfNewBatteriesForEGE_Scooters; // Etc. Sold Per Year Per EU Member State
        
        @JsonProperty("Number of EV batteries exported out of EU per year")
        private String numberOfEVBatteriesExportedOutOfEUPerYear;
        
        @JsonProperty("Number of batteries for e.g. e-scooters exported out of EU per year")
        private String numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear;
        
        @JsonProperty("Number of batteries EV batteries submitted for recycling per member state per year")
        private String numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear;
        
        @JsonProperty("Number of batteries for e.g. e-scooters submitted for recycling per member state per year")
        private String numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear;
        
        @JsonProperty("Number of waste batteries collected per member state per year")
        private String numberOfWasteBatteriesCollectedPerMemberStatePerYear;
        
        @JsonProperty("Infos on recycling processes")
        private String infosOnRecyclingProcesses;
        
        @JsonProperty("Collected waste batteries for export and subsequent recycling")
        private String collectedWasteBatteriesForExportAndSubsequentRecycling;

        @JsonProperty("Battery type / model")
        private String batteryType_Model;
        
        @JsonProperty("Battery serial number")
        private String batterySerialNumber;
        
        @JsonProperty("Battery batch number")
        private String batteryBatchNumber;
        
        @JsonProperty("Date of manufacture")
        private String dateOfManufacture;
        
        @JsonProperty("Name of manufacturer_")
        private String nameOfManufacturer_;
        
        @JsonProperty("Place of manufacturing / country of origin")
        private String placeOfManufacturing_CountryOfOrigin;
        
        @JsonProperty("Date of placing on market")
        private String dateOfPlacingOnMarket;
        
        @JsonProperty("Hazardous substances in battery")
        private String hazardousSubstancesInBattery;
        
        @JsonProperty("Chemical symbols for metals")
        private String chemicalSymbolsForMetals;
        
        @JsonProperty("Critical raw materials")
        private String criticalRawMaterials;
        
        @JsonProperty("Collection symbol")
        private String collectionSymbol;
        
        @JsonProperty("QR Code")
        private String QRCode;
        
        @JsonProperty("Hazard signs")
        private String hazardSigns;
        
        @JsonProperty("CE mark")
        private String CEMark;
        
        @JsonProperty("ID number of notified body")
        private String idNumberOfNotifiedBody;
        
        @JsonProperty("Name of notified body")
        private String nameOfNotifiedBody;
        
        @JsonProperty("Adress of notified body")
        private String adressOfNotifiedBody;
        
        @JsonProperty("Issued certificate")
        private String issuedCertificate;
        
        @JsonProperty("Application description and recycling possibilities, Description of collection and recycling possibilities")
        private String applicationDescriptionAndRecyclingPossibilities; // Description Of Collection And Recycling
        																// Possibilities												
        @JsonProperty("Role of EV owners in recycling batteries")
        private String roleOfEVOwnersInRecyclingBatteries;
        
        @JsonProperty("Enviromental impact of batteries")
        private String enviromentalImpactOfBatteries;
        
        @JsonProperty("Name of manufacturer")
        private String nameOfManufacturer;
        
        @JsonProperty("Adress of manufacturer")
        private String adressOfManufacturer;
        
        @JsonProperty("Telephone number of manufacturer")
        private String telephoneNumberOfManufacturer;
        
        @JsonProperty("Email adress of manufacturer")
        private String emailAdressOfManufacturer;
        
        @JsonProperty("Fax number of manufacturer")
        private String faxNumberOfManufacturer;
        
        @JsonProperty("Website of manufacturer")
        private String websiteOfManufacturer;
        
        @JsonProperty("National ID number of producer")
        private String nationalIdNumberOfProducer;
        
        @JsonProperty("Trade register number of producer")
        private String tradeRegisterNumberOfProducer;
        
        @JsonProperty("EU Tax ID number")
        private String EUTaxIdNumber;
        
        @JsonProperty("Battery type description")
        private String batteryTypeDescription;
        
        @JsonProperty("Brand name")
        private String brandName;
        
        @JsonProperty("Declaration number")
        private String declarationNumber;
        
        @JsonProperty("EU declaration of conformity")
        private String EUDeclarationOfConformity;
        
        @JsonProperty("Carbon Footprint (CFP) Declaration")
        private String carbonFootprint_CFP_Declaration;
        
        @JsonProperty("Geographic location of manufacturing facility")
        private String geographicLocationOfManufacturingFacility;

//        private String ESGDueDiligence 
//        private String ten Principles Of The United Nations Global Compact
//        private String uNEP Guidelines For Social Life Cycle Assessment Of Products;
//        private String convention On Biological Diversity Decision COP VIII/28- Voluntary Guidelines On Biodiversity-Inclusive Impact Assessment;
//        private String iLO Tripartite Declaration Of Principles Concerning Multinational Enterprises And Social Policy
//        private String oECD Due Diligence Guidance For Responsible Business Conduct
//        private String oECD Due Diligence Guidance For Responsible Supply Chains Of Minerals From Conflict-Affected And High-Risk Areas.
        
        
        @JsonProperty("Battery producer/Supplier")
        private String batteryProducer_Supplier;
        
        @JsonProperty("Production site")
        private String productionSite;
        
        @JsonProperty("Date of production")
        private String dateOfProduction;
        
        @JsonProperty("Begin of Life @vehicle/Production date vehicle ")
        private String beginOfLifeVehicle_ProductionDateVehicle;
        
        @JsonProperty("Original equipment of/ vehicle type")
        private String originalEquipmentOf_VehicleType;
        
        @JsonProperty("Vehicle ID (VIN)")
        private String vehicleId_VIN;
        
        @JsonProperty("Battery ID (serial number)")
        private String batteryId_SerialNumber;
        
        @JsonProperty("Battery weight")
        private String batteryWeight;
        
        @JsonProperty("Number of battery cells")
        private String numberOfBatteryCells;
        
        @JsonProperty("Battery cell connection, serial")
        private String batteryCellConnection_Serial;
        
        @JsonProperty("Battery capacity")
        private String batteryCapacity;
        
        @JsonProperty("Part number + Change Index")
        private String partNumberPlusChangeIndex;
        
        @JsonProperty("Brand")
        private String brand;
        
        @JsonProperty("Battery type")
        private String batteryType;
        
        @JsonProperty("Max. Power")
        private String maxPower;
        
        @JsonProperty("Supplier partNumber")
        private String supplierPartnumber;
        
        @JsonProperty("Component Description")
        private String componentDescription;
        
        @JsonProperty("Battery voltage")
        private double batteryVoltage;
        
        @JsonProperty("Battery SOC")
        private double batterySOC;
        
        @JsonProperty("Battery SOH")
        private double batterySOH;
        
        @JsonProperty("Total mass of aluminum (alloys, purity?)")
        private String totalMassOfAluminum; // (Alloys, Purity?)
        
        @JsonProperty("Total mass of copper (alloys, purity?)")
        private String totalMassOfCopper; // (Alloys, Purity?)
        
        @JsonProperty("Total mass of plastics excluding battery cell (different types)")
        private String totalMassOfPlasticsExcludingBatteryCell; // (DifferentTypes)
        
        @JsonProperty("Total mass of plastics excluding battery cell (different types)_")
        private String totalMassOfPlasticsExcludingBatteryCell_; // (Different Types)
        
        @JsonProperty("Total mass of carbon ( graphite , conductive additive)")
        private String totalMassOfCarbon; // ( Graphite , Conductive Additive)
        
        @JsonProperty("Total mass of silicon")
        private String totalMassOfSilicon;
        
        @JsonProperty("Total mass of nickel")
        private String totalMassOfNickel;
        
        @JsonProperty("Total mass of cobalt")
        private String totalMassOfCobalt;
        
        @JsonProperty("Total mass of manganese")
        private String totalMassOfManganese;
        
        @JsonProperty("Total mass of iron")
        private String totalMassOfIron;
        
        @JsonProperty("Cathode chemistry")
        private String cathodeChemistry;
        
        @JsonProperty("Binder")
        private String binder;
        
        @JsonProperty("Conductive additive mass")
        private double conductiveAdditiveMass;
        
        @JsonProperty("Anode chemistry")
        private String anodeChemistry;
        
        @JsonProperty("Binder mass")
        private double binderMass;
        
        @JsonProperty("Conductive additive")
        private double conductiveAdditive;
        
        @JsonProperty("Conductive additive mass_")
        private double conductiveAdditiveMass_;
        
        @JsonProperty("Active material")
        private String activeMaterial;
        
        @JsonProperty("Active material mass")
        private double activeMaterialMass;
        
        @JsonProperty("Electrolyte solvent")
        private String electrolyteSolvent;
        
        @JsonProperty("Electrolyte solvent mass")
        private double electrolyteSolventMass;
        
        @JsonProperty("Electrolyte salt")
        private String electrolyteSalt;
        
        @JsonProperty("Electrolyte salt mass")
        private double electrolyteSaltMass;
        
        @JsonProperty("Workshop manual battery (dismantling)")
        private String workshopManualBattery; // (Dismantling)
        
        @JsonProperty("Transportation manual")
        private String transportationManual;
        
        @JsonProperty("Battery discharging manual with required can/com protocol")
        private String batteryDischargingManualWithRequiredCan_ComProtocol;
        
        @JsonProperty("CO2 footprint")
        private String co2Footprint;
        
        @JsonProperty("Recyclate use")
        private String recyclateUse;

        private Builder() {
        }

        @JsonCreator
        public static Builder newInstance() {
            return new Builder();
        }

        public Builder co2FootprintMethodology(String co2FootprintMethodology) {
            this.co2FootprintMethodology = co2FootprintMethodology;
            return this;
        }

        // ...
        
        
        public Builder co2Footprint(String co2Footprint) {
            this.co2Footprint = co2Footprint;
            return this;
        }

        public Builder recyclateUse(String recyclateUse) {
            this.recyclateUse = recyclateUse;
            return this;
        }

        
        public BatteryPassport build() {

            BatteryPassport batterypass = new BatteryPassport();
            batterypass.co2FootprintMethodology = this.co2FootprintMethodology;
            batterypass.co2ConfirmationStudies  = this.co2ConfirmationStudies;
            batterypass.co2FootprintThreshold  = this.co2FootprintThreshold;
            batterypass.co2FootprintTotal  = this.co2FootprintTotal;
            
            batterypass.co2FootprintPerLifecycle  = this.co2FootprintPerLifecycle;
            batterypass.co2FootprintVerification  = this.co2FootprintVerification;

            batterypass.co2FootprintClassificationFromEU  = this.co2FootprintClassificationFromEU;
            batterypass.recyclateCobaltContent  = this.recyclateCobaltContent;
            batterypass.recyclateLeadContent  = this.recyclateLeadContent;
            batterypass.recyclateLithiumContent  = this.recyclateLithiumContent;
            batterypass.recyclateNickelContent  = this.recyclateNickelContent;
            batterypass.recyclingDesignInformation  = this.recyclingDesignInformation;
            batterypass.operationalTemperatureRange  = this.operationalTemperatureRange;
            batterypass.temperatureRangeTheBatteryCanWithstandWhenNotInUse  = this.temperatureRangeTheBatteryCanWithstandWhenNotInUse;
            batterypass.ratedCapacity  = this.ratedCapacity;
            batterypass.capacityFade  = this.capacityFade; 
            batterypass.power  = this.power; 
            batterypass.totalEnergy  = this.totalEnergy; 
            batterypass.usableEnergy  = this.usableEnergy;
            batterypass.powerFade  = this.powerFade;
            batterypass.internalResistance  = this.internalResistance; 
            batterypass.internalResistanceIncrease  = this.internalResistanceIncrease; 
            batterypass.internalResistance_  = this.internalResistance_; 
            batterypass.internalResistanceIncrease_  = this.internalResistanceIncrease_;
            batterypass.energyRoundTripEfficiency  = this.energyRoundTripEfficiency;
            batterypass.fadeEnergyRoundTripEfficiency  = this.fadeEnergyRoundTripEfficiency; 
            batterypass.c_RateOfRelevantCycle_Life  = this.c_RateOfRelevantCycle_Life; 
            batterypass.expectedlifetime  = this.expectedlifetime; 
            batterypass.appliedChargeRate  = this.appliedChargeRate;
            batterypass.appliedDisChargeRate  = this.appliedDisChargeRate;
            batterypass.storageHumidity  = this.storageHumidity; 
            batterypass.compositionOfCathode  = this.compositionOfCathode; 
            batterypass.compositionOfAnode  = this.compositionOfAnode;
            
            batterypass.compositionOfElectrolyte  = this.compositionOfElectrolyte;
            batterypass.compositionOfShellMaterial  = this.compositionOfShellMaterial;
            batterypass.partNumbersForComponents  = this.partNumbersForComponents;
            batterypass.contactDetailsOfSourcesForReplacementSpares  = this.contactDetailsOfSourcesForReplacementSpares;
            batterypass.maxQllowedBatteryPower  = this.maxQllowedBatteryPower;
            batterypass.depthOfDischargeInTheCycle_Life  = this.depthOfDischargeInTheCycle_Life;
            batterypass.powerCapabilityAt80PercentCharge  = this.powerCapabilityAt80PercentCharge;
            batterypass.powerCapabilityAt20PercentCharge  = this.powerCapabilityAt20PercentCharge;
            batterypass.accompanyingCalculations  = this.accompanyingCalculations;
            batterypass.minVoltage  = this.minVoltage;
            batterypass.maxVoltage  = this.maxVoltage;
            batterypass.nominalVoltage  = this.nominalVoltage;
            batterypass.originalPowerCapability  = this.originalPowerCapability;
            batterypass.originalPowerCapabilityLimits  = this.originalPowerCapabilityLimits;
            batterypass.recyclingEfficiencies  = this.recyclingEfficiencies;
            batterypass.batteryWeight_  = this.batteryWeight_;
            batterypass.batteryDimensions  = this.batteryDimensions;
            batterypass.passportNumber  = this.passportNumber;
            batterypass.passportIssuer  = this.passportIssuer;
            
            batterypass.stateOfHealth_Soh  = this.stateOfHealth_Soh;
            batterypass.stateOfCharge_Soc  = this.stateOfCharge_Soc;
            batterypass.changeOfOwnership_Invoice  = this.changeOfOwnership_Invoice;
            batterypass.changeOfOwnership_Contract  = this.changeOfOwnership_Contract;
            batterypass.packagingInstructions  = this.packagingInstructions;
            batterypass.transportationInstructions  = this.transportationInstructions;
            batterypass.handlingInstructions  = this.handlingInstructions;
            batterypass.personalProtectiveEquipment_PPE  = this.personalProtectiveEquipment_PPE;
            batterypass.vehicleDismantlingProcedure  = this.vehicleDismantlingProcedure;
            batterypass.batteryDismantlingInformation  = this.batteryDismantlingInformation;
            
            batterypass.numberOfNewEVBatteriesSoldPerYearPerEUMemberState  = this.numberOfNewEVBatteriesSoldPerYearPerEUMemberState;
            batterypass.numberOfNewBatteriesForEGE_Scooters  = this.numberOfNewBatteriesForEGE_Scooters;
            batterypass.numberOfEVBatteriesExportedOutOfEUPerYear  = this.numberOfEVBatteriesExportedOutOfEUPerYear;
            batterypass.numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear  = this.numberOfBatteriesForEGE_ScootersExportedOutOfEUPerYear;
            batterypass.numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear  = this.numberOfBatteriesEVBatteriesSubmittedForRecyclingPerMemberStatePerYear;
            batterypass.numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear  = this.numberOfBatteriesForEGE_ScootersSubmittedForRecyclingPerMemberStatePerYear;
            batterypass.numberOfWasteBatteriesCollectedPerMemberStatePerYear  = this.numberOfWasteBatteriesCollectedPerMemberStatePerYear;
            batterypass.infosOnRecyclingProcesses  = this.infosOnRecyclingProcesses;
            batterypass.collectedWasteBatteriesForExportAndSubsequentRecycling  = this.collectedWasteBatteriesForExportAndSubsequentRecycling;
            
            batterypass.batteryType_Model  = this.batteryType_Model;
            batterypass.batterySerialNumber  = this.batterySerialNumber;
            batterypass.batteryBatchNumber  = this.batteryBatchNumber;
            batterypass.dateOfManufacture  = this.dateOfManufacture;
            batterypass.nameOfManufacturer_  = this.nameOfManufacturer_;
            batterypass.placeOfManufacturing_CountryOfOrigin  = this.placeOfManufacturing_CountryOfOrigin;
            batterypass.dateOfPlacingOnMarket  = this.dateOfPlacingOnMarket;
            batterypass.hazardousSubstancesInBattery  = this.hazardousSubstancesInBattery;
            batterypass.chemicalSymbolsForMetals  = this.chemicalSymbolsForMetals;
            batterypass.criticalRawMaterials  = this.criticalRawMaterials;
            batterypass.collectionSymbol  = this.collectionSymbol;

            batterypass.QRCode  = this.QRCode;
            batterypass.hazardSigns  = this.hazardSigns;
            batterypass.CEMark  = this.CEMark;
            batterypass.idNumberOfNotifiedBody  = this.idNumberOfNotifiedBody;
            batterypass.nameOfNotifiedBody  = this.nameOfNotifiedBody;
            batterypass.adressOfNotifiedBody  = this.adressOfNotifiedBody;
            batterypass.issuedCertificate  = this.issuedCertificate;
            batterypass.applicationDescriptionAndRecyclingPossibilities  = this.applicationDescriptionAndRecyclingPossibilities;
            batterypass.roleOfEVOwnersInRecyclingBatteries  = this.roleOfEVOwnersInRecyclingBatteries;
            batterypass.enviromentalImpactOfBatteries  = this.enviromentalImpactOfBatteries;
            batterypass.nameOfManufacturer  = this.nameOfManufacturer;

            batterypass.adressOfManufacturer  = this.adressOfManufacturer;
            batterypass.telephoneNumberOfManufacturer  = this.telephoneNumberOfManufacturer;
            batterypass.emailAdressOfManufacturer  = this.emailAdressOfManufacturer;
            batterypass.faxNumberOfManufacturer  = this.faxNumberOfManufacturer;
            batterypass.websiteOfManufacturer  = this.websiteOfManufacturer;
            batterypass.nationalIdNumberOfProducer  = this.nationalIdNumberOfProducer;
            batterypass.tradeRegisterNumberOfProducer  = this.tradeRegisterNumberOfProducer;
            batterypass.EUTaxIdNumber  = this.EUTaxIdNumber;
            batterypass.batteryTypeDescription  = this.batteryTypeDescription;
            batterypass.brandName  = this.brandName;
            batterypass.declarationNumber  = this.declarationNumber;

            batterypass.EUDeclarationOfConformity  = this.EUDeclarationOfConformity;
            batterypass.carbonFootprint_CFP_Declaration  = this.carbonFootprint_CFP_Declaration;
            batterypass.geographicLocationOfManufacturingFacility  = this.geographicLocationOfManufacturingFacility;
            
            batterypass.batteryProducer_Supplier  = this.batteryProducer_Supplier;
            batterypass.productionSite  = this.productionSite;
            batterypass.dateOfProduction  = this.dateOfProduction;
            batterypass.beginOfLifeVehicle_ProductionDateVehicle  = this.beginOfLifeVehicle_ProductionDateVehicle;
            batterypass.originalEquipmentOf_VehicleType  = this.originalEquipmentOf_VehicleType;
            batterypass.vehicleId_VIN  = this.vehicleId_VIN;


            batterypass.batteryId_SerialNumber  = this.batteryId_SerialNumber;
            batterypass.batteryWeight  = this.batteryWeight;
            batterypass.numberOfBatteryCells  = this.numberOfBatteryCells;
            batterypass.batteryCellConnection_Serial  = this.batteryCellConnection_Serial;
            batterypass.batteryCapacity  = this.batteryCapacity;
            batterypass.partNumberPlusChangeIndex  = this.partNumberPlusChangeIndex;
            batterypass.brand  = this.brand;
            batterypass.batteryType  = this.batteryType;
            batterypass.maxPower  = this.maxPower;
            batterypass.supplierPartnumber  = this.supplierPartnumber;

            batterypass.componentDescription  = this.componentDescription;
            batterypass.batteryVoltage  = this.batteryVoltage;
            batterypass.batterySOC  = this.batterySOC;
            batterypass.batterySOH  = this.batterySOH;
            batterypass.totalMassOfAluminum  = this.totalMassOfAluminum;
            batterypass.totalMassOfCopper  = this.totalMassOfCopper;
            batterypass.totalMassOfPlasticsExcludingBatteryCell  = this.totalMassOfPlasticsExcludingBatteryCell;
            batterypass.totalMassOfPlasticsExcludingBatteryCell_  = this.totalMassOfPlasticsExcludingBatteryCell_;

            batterypass.totalMassOfCarbon  = this.totalMassOfCarbon;
            batterypass.totalMassOfSilicon  = this.totalMassOfSilicon;
            batterypass.totalMassOfNickel  = this.totalMassOfNickel;
            batterypass.totalMassOfCobalt  = this.totalMassOfCobalt;
            batterypass.totalMassOfManganese  = this.totalMassOfManganese;
            batterypass.totalMassOfIron  = this.totalMassOfIron;
            batterypass.cathodeChemistry  = this.cathodeChemistry;
            batterypass.binder  = this.binder;
            batterypass.conductiveAdditiveMass  = this.conductiveAdditiveMass;
            
            batterypass.anodeChemistry  = this.anodeChemistry;
            batterypass.binderMass  = this.binderMass;
            batterypass.conductiveAdditive  = this.conductiveAdditive;
            batterypass.conductiveAdditiveMass_  = this.conductiveAdditiveMass_;
            batterypass.activeMaterial  = this.activeMaterial;
            batterypass.activeMaterialMass  = this.activeMaterialMass;
            batterypass.electrolyteSolvent  = this.electrolyteSolvent;
            batterypass.electrolyteSolventMass  = this.electrolyteSolventMass;
            batterypass.electrolyteSalt  = this.electrolyteSalt;
            batterypass.electrolyteSaltMass  = this.electrolyteSaltMass;
            batterypass.workshopManualBattery  = this.workshopManualBattery;
            batterypass.transportationManual  = this.transportationManual;
            batterypass.batteryDischargingManualWithRequiredCan_ComProtocol  = this.batteryDischargingManualWithRequiredCan_ComProtocol;
            batterypass.co2Footprint  = this.co2Footprint;
            batterypass.recyclateUse  = this.recyclateUse;

            return batterypass;
        }
    }
}