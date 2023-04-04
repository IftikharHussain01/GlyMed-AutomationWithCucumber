package com.glymed.stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glymed.pageobjects.loginandregistration.*;

import static org.junit.Assert.assertTrue;

public class ProductsSteps {
    TextContext textContext;
    ProductsPage productsPage;
    ProductSearchPage productSearchPage;
    ExfoliatorProductPurchase exfoliatorProductPurchase;
    SerumProductPage serumProductPage;
    MoisturizerProductPage moisturizerProductPage;


    public ProductsSteps(TextContext textContext) {
        this.textContext = textContext;
        productsPage = textContext.getPageObjectManager().getProductsPage();
        productSearchPage = textContext.getPageObjectManager().getProductSearchPage();
        exfoliatorProductPurchase = textContext.getPageObjectManager().getExfoliatorProductPurchase();
        serumProductPage = textContext.getPageObjectManager().getSerumProductPage();
        moisturizerProductPage = textContext.getPageObjectManager().getMoisturizerProductPage();
    }

    @When("validate that user can see addictive label")
    public void validate_that_user_can_see_addictive_label() {
        productsPage.checkCustomerNotification();
        productsPage.validateAddictivelabel();
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
        productsPage.enterRetailQuanitity(quantity);
        productsPage.setQuantityForTavel(quantity);
        productsPage.setQuantityForTrail(quantity);
    }

    @Then("move down to the back bar option")
    public void moveDownToTheBackBarOption() {
        productsPage.moveToBackBar();
    }

    @When("user click on add to cart button")
    public void userClickOnAddToCartButton() {
        productsPage.clickAddtoCartButton();
    }

    @Then("user click one more product in the cart")
    public void userClickOneMoreProductInTheCart() {
        productsPage.addOneMoreProduct();
    }

    @And("enter the quantity for retail as {string}")
    public void enterTheQuantityForRetailAs(String quantity) {
        productsPage.setQuantityForRetail(quantity);
    }

    @When("user enters {string} in the search field")
    public void userEntersInTheSearchField(String category) {
        productSearchPage.enterSearchValue(category);
    }

    @Then("validate that user is displayed no products message")
    public void validateThatUserIsDisplayedNoProductsMessage() {
        assertTrue("No Product Message is not displayed", productSearchPage.validateNoProductMessage());
        productSearchPage.clickClearFilterButton();
    }

    @And("user click exfoliator check mark")
    public void userClickExfoliatorCheckMark() {
        exfoliatorProductPurchase.clickExfoliatorCheckmark();
    }

    @And("validate that exfoliator title is displayed")
    public void validateThatExfoliatorTitleIsDisplayed() {
        assertTrue("Exfoliator title is not displayed", exfoliatorProductPurchase.validateExfoliatorTitle());

    }

    @Then("select first product from exfoliator category")
    public void selectFirstProductFromExfoliatorCategory() {
        exfoliatorProductPurchase.selectProductOneforExfoliator();
    }

    @And("enter the amount in the retail text field as {string}")
    public void enterTheAmountInTheRetailTextFieldAs(String value) {
        exfoliatorProductPurchase.enterRetailValueOfPurchase(value);
    }

    @And("enter the amount in the trial text field as {string}")
    public void enterTheAmountInTheTrialTextFieldAs(String value) {
        exfoliatorProductPurchase.enterTrialValueOfPurchase(value);
    }

    @And("move down to text field")
    public void moveDownToTextField() {
        productsPage.moveToTextField();
    }

    @Then("user click on serum product checkmark")
    public void userClickOnSerumProductCheckmark() {
        serumProductPage.clickSerumCheckMark();
    }

    @And("user select daily life serum from list")
    public void userSelectDailyLifeSerumFromList() {
        serumProductPage.clickDailyLifeSerum();
    }

    @And("user enters the value for retail purchase as {string}")
    public void userEntersTheValueForRetailPurchaseAs(String value) {
        serumProductPage.enterQuantityToPurchase(value);
    }

    @Then("user click on moisturizer product checkmark")
    public void userClickOnMoisturizerProductCheckmark() {
        moisturizerProductPage.clickMoisturizerCheckMark();
    }

    @When("user select atraxi product")
    public void userSelectAtraxiProduct() {
        moisturizerProductPage.selectAtraxiProduct();
    }

    @And("user enters the value for retail moisturiser as {string}")
    public void userEntersTheValueForRetailMoisturiserAs(String value) {
        moisturizerProductPage.enterRetailQuantityForMoist(value);
    }

    @And("user enters the value for black bar moisturiser as {string}")
    public void userEntersTheValueForBlackBarMoisturiserAs(String value) {
        moisturizerProductPage.enterBlackBarQuantityForMoist(value);
    }
}
