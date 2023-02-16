package com.glymed.stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glymed.pageobjects.loginandregistration.ProductsPage;

import static org.junit.Assert.assertTrue;

public class ProductsSteps {
    TextContext textContext;
    ProductsPage productsPage;

    public ProductsSteps(TextContext textContext) {
        this.textContext = textContext;
        productsPage = textContext.getPageObjectManager().getProductsPage();
    }

    @When("validate that user can see addictive label")
    public void validate_that_user_can_see_addictive_label() {
        productsPage.checkCustomerNotification();
        assertTrue("Addictive label is not displayed", productsPage.validateAddictivelabel());
    }

    @Then("user selects the category as Cleanser")
    public void user_selects_the_category_as_Cleanser() {
        productsPage.clickCleanserCheckMark();
    }

    @Then("validate cleanser title is displayed")
    public void validate_cleanser_title_is_displayed() {
        assertTrue("cleanser title is not displayed", productsPage.validateCleanserTitle());
    }

    @Then("user selects first cleanser product")
    public void user_selects_first_cleanser_product() {
        productsPage.selectFirstProduct();
    }

    @And("set the trial and travel values as {string}")
    public void setTheTrialAndTravelValuesAs(String quantity) {
        productsPage.setQuantityFortrialAndTavel(quantity);
    }

    @Then("move down to the back bar option")
    public void moveDownToTheBackBarOption() {
        productsPage.moveToBackBar();
    }

    @When("user click on add to cart button")
    public void userClickOnAddToCartButton() {
        productsPage.clickAddtoCartButton();
    }
}
