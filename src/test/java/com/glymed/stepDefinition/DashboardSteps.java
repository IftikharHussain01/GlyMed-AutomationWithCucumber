package com.glymed.stepDefinition;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glymed.pageobjects.loginandregistration.DashboardPage;

import static org.junit.Assert.assertTrue;

public class DashboardSteps {

    TextContext textContext;
    DashboardPage dashboardPage;

    public DashboardSteps(TextContext textContext) {
        this.textContext = textContext;
        dashboardPage = textContext.getPageObjectManager().getDashboardPage();
    }

    @Then("validate that user is present on dashboard Page")
    public void validateThatUserIsPresentOnDashboardPage() {
        assertTrue("Glymed Dashboard screen is not displayed", dashboardPage.validateDashboard());
    }

    @When("user click on products option from the dashboard")
    public void userClickOnProductsOptionFromTheDashboard() {
        dashboardPage.clickProductsOption();
    }

    @And("user click on glymed label from dashboard")
    public void userClickOnGlymedLabelFromDashboard() {
        dashboardPage.clickGlyMedLabel();
    }
}
