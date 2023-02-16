package com.glymed.stepDefinition;

import cucumber.api.java.en.And;
import glymed.pageobjects.loginandregistration.LoginAndRegistrationFlowPages;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.assertTrue;

public class LoginRegistrationFlowSteps {
    TextContext textContext;
    LoginAndRegistrationFlowPages loginRegistration;

    public LoginRegistrationFlowSteps(TextContext textContext) {
        this.textContext = textContext;
        loginRegistration = textContext.getPageObjectManager().getLoginAndRegistrationFlowPages();
    }

    @Given("user navigate to glymed home page")
    public void userNavigateToGlymedHomePage() {
        TextContext.com_fun.navigateToUrl(TextContext.config.getProperty("URL"));
        loginRegistration.acceptCookies();
    }

    @Then("validate user is present on sign in screen")
    public void validateUserIsPresentOnSignInScreen() {
        assertTrue("Glymed Sign In screen is not displayed", loginRegistration.validateGlymedSignInPage());
    }

    @When("user enters the user name as {string}")
    public void userEntersTheUserNameAs(String userName) {
        loginRegistration.enterUserName(TextContext.config.getProperty(userName));
    }

    @Then("user enters the password as {string}")
    public void userEntersThePasswordAs(String password) {
        loginRegistration.enterPassword(TextContext.config.getProperty(password));
    }

    @And("user click on sign in button")
    public void userClickOnSignInButton() {
        loginRegistration.clickSigninButton();
    }
}