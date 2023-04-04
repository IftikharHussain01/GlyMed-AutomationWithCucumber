package com.glymed.stepDefinition;

import com.glymed.context.Context;
import com.glymed.context.TestContext;
import com.glymed.utilities.Log;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import glymed.pageobjects.loginandregistration.CheckOutPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckOutSteps {

    TextContext textContext;
    TestContext testContext;
    CheckOutPage checkOutPage;

    public CheckOutSteps(TextContext textContext, TestContext context) {
        this.textContext = textContext;
        this.testContext = context;
        checkOutPage = textContext.getPageObjectManager().getCheckOutPage();
    }

    @Then("validate checkout screen is displayed and validate the amount")
    public void validateCheckoutScreenIsDisplayedAndValidateTheAmount() throws InterruptedException {
        assertTrue("Cart Page is not displayed", checkOutPage.validateCartScreen());

        Log.info("Sub Total of the cutomer is : " + checkOutPage.getSubTotalAmount());
    }

    @And("user click on checkout button")
    public void userClickOnCheckoutButton() {
        checkOutPage.clickCheckOutButton();
    }

    @And("validate user is navigated to checkout screen")
    public void validateUserIsNavigatedToCheckoutScreen() {
        assertTrue("checkout screen is not displayed", checkOutPage.validateCheckOutScreen());
        testContext.getScenarioContext().setContext(Context.Total_Amount, checkOutPage.getTotalAmountBeforeOrderPlace());
        System.out.println("Total Amount before placing order : " + testContext.getScenarioContext().getContext(Context.Total_Amount).toString());
    }

    @Then("user select the shipping option as standard")
    public void userSelectTheShippingOptionAsStandard() {
        checkOutPage.clickChooseShippingOption();
        testContext.getScenarioContext().setContext(Context.Shipping_Method, checkOutPage.getShippingMethod());
    }

    @And("user click on shipping dropdown")
    public void userClickOnShippingDropdown() {
        checkOutPage.clickShippingDropdown();
    }

    @And("user enter the instruction as {string}")
    public void userEnterTheInstructionAs(String instruction) {
        checkOutPage.enterShippingInstruction(instruction);
    }

    @Then("user click on continue to payment button")
    public void userClickOnContinueToPaymentButton() {
        checkOutPage.clickContinueToPaymentButton();
    }

    @And("validate user has payment method available as card")
    public void validateUserHasPaymentMethodAvailableAsCard() {
        checkOutPage.enterCardDetails();
//        checkOutPage.enterCardName();
//        checkOutPage.enterCardNumber();
        assertTrue("payment method is not displayed", checkOutPage.validatePaymentMethod());
    }

    @When("user click review your order button")
    public void userClickReviewYourOrderButton() {
        checkOutPage.clickReviewYourOrderButton();
    }

    @Then("user click place your order button")
    public void userClickPlaceYourOrderButton() {
        checkOutPage.clickPlaceYourOrderButton();
    }

    @And("user click on dont use points button")
    public void userClickOnDontUsePointsButton() {
        checkOutPage.clickDontUsePointsButton();
    }

    @And("user agrees the terms and conditions")
    public void userAgreesTheTermsAndConditions() {
        checkOutPage.clickTnCButton();
    }

    @Then("validate user is navigated to the success screen")
    public void validateUserIsNavigatedToTheSuccessScreen() {
        assertTrue("Success screen in not displayed", checkOutPage.validateSuccessScreen());
    }

    @And("validate that amount should be equal before and after order place")
    public void validateThatAmountShouldBeEqualBeforeAndAfterOrderPlace() {
        String expected = testContext.getScenarioContext().getContext(Context.Total_Amount).toString();
        System.out.println("Expected: " + expected);
        String actual = checkOutPage.getTotalAmountAfterOrderPlace().replace(" ", "");
        System.out.println("Actual: " + actual);
        assertEquals("The Amount is not equal: ", expected, actual);
    }

    @And("user click on close button for checkout screen")
    public void userClickOnCloseButtonForCheckoutScreen() {
        checkOutPage.clickCheckoutCloseButton();
    }

    @And("user selects the shipping option by Air")
    public void userSelectsTheShippingOptionByAir() throws InterruptedException {
        checkOutPage.clickChooseAirShippingOption();
        testContext.getScenarioContext().setContext(Context.Shipping_Method, checkOutPage.getShippingMethod());
        Thread.sleep(2000);
        Log.info("Shipping and Handling Amount is :" + checkOutPage.getShippingAndHandlingAmount());
    }

    @And("user selects the shipping option by second day")
    public void userSelectsTheShippingOptionBySecondDay() throws InterruptedException {
        checkOutPage.clickChooseSecondAirShippingOption();
        testContext.getScenarioContext().setContext(Context.Shipping_Method, checkOutPage.getShippingMethod());
        Thread.sleep(2000);
        Log.info("Shipping and Handling Amount is :" + checkOutPage.getShippingAndHandlingAmount());
    }

    @Then("user get redeemable points of the customer")
    public void userGetRedeemablePointsOfTheCustomer() {
        Log.info("Customer is redeeming the points : " + checkOutPage.getRedeemablePoints());
    }

    @And("user checkMark to use the points")
    public void userCheckMarkToUseThePoints() {
        checkOutPage.setUseMyPointsCheckMark();
    }

    @And("validate that shipping method is same as per the user selection")
    public void validateThatShippingMethodIsSameAsPerTheUserSelection() {
        String expected = testContext.getScenarioContext().getContext(Context.Shipping_Method).toString();
        Log.info("Expected Shipping Method :" + expected);
        String actual = checkOutPage.getSuccessScreenShippingMethod();
        Log.info("Actual Shipping Method :" + actual);
        assertEquals("The Shipping Method is not equal: ", expected, actual);
    }
}
