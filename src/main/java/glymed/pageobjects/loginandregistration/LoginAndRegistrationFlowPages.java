package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;


public class LoginAndRegistrationFlowPages {

    WebDriver driver;
    CommonFunction comfun;

    public LoginAndRegistrationFlowPages(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()='I Accept']")
    WebElement cookiesAcceptButton;

    @FindBy(xpath = "//*[contains(text(),'Personal Service')]")
    WebElement signInPageHeader;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement signInButton;

    @FindBy(xpath = "//input[@type='email']")
    WebElement emailAddressField;

    @FindBy(xpath = "//input[@type='password']")
    WebElement passwordField;

    @FindBy(xpath = "//a[contains(text(),'Forgot your password')]")
    WebElement forgotPassword;

    public void acceptCookies() throws InterruptedException, AWTException {
        try {
            if (comfun.isElementPresent(cookiesAcceptButton)) {
                cookiesAcceptButton.click();
            }
        } catch (Exception e) {
            Log.info("Cookies Message is not displayed");
        }
    }

    public boolean validateGlymedSignInPage() {
        return signInPageHeader.isDisplayed() && signInButton.isDisplayed()
                && emailAddressField.isDisplayed() && passwordField.isDisplayed() && forgotPassword.isDisplayed();
    }

    public void enterUserName(String userName) {
        emailAddressField.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    public void clickSigninButton() {
        try {
            comfun.clickElement(signInButton);
        } catch (Exception e) {
            Log.error("Sign In button is not clicked" + e.getMessage());
        }

    }
}