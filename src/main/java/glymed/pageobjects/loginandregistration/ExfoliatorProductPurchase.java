package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExfoliatorProductPurchase {

    WebDriver driver;
    CommonFunction comfun;

    public ExfoliatorProductPurchase(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[contains(@value,'Exfoliator')]")
    WebElement exfoliatorCheckmark;

    @FindBy(xpath = "//h3[normalize-space()='Exfoliator']")
    WebElement exfoliatorTitle;

    @FindBy(xpath = "//div[@id='GM12']")
    WebElement exfoliatorP1;

    @FindBy(xpath = "//input[@id='product-quantity-GM12']")
    WebElement retailTextField;

    @FindBy(xpath = "//input[@id='product-quantity-GM12S']")
    WebElement trialTextField;

    public void clickExfoliatorCheckmark() {
        try {
            comfun.clickElement(exfoliatorCheckmark);
            comfun.driverWait(2000);
        } catch (Exception e) {
            Log.warn("clickExfoliatorCheckmark is not clicked :" + e.getMessage());
        }
    }

    public boolean validateExfoliatorTitle() {
        comfun.waitForElement(exfoliatorTitle);
        return exfoliatorTitle.isDisplayed();
    }

    public void selectProductOneforExfoliator() {
        try {
            comfun.clickElement(exfoliatorP1);
            comfun.driverWait(2000);
        } catch (Exception e) {
            Log.warn("selectProductOneforExfoliator is not clicked :" + e.getMessage());
        }
    }

    public void enterRetailValueOfPurchase(String value) {
        try {
            comfun.clickElement(retailTextField);
            retailTextField.clear();
            retailTextField.sendKeys(value);
        } catch (Exception e) {
            Log.warn("unable to enter value in enterRetailValueOfPurchase :" + e.getMessage());
        }

    }

    public void enterTrialValueOfPurchase(String value) {
        try {
            comfun.clickElement(trialTextField);
            trialTextField.clear();
            trialTextField.sendKeys(value);
        } catch (Exception e) {
            Log.warn("unable to enter value in enterRetailValueOfPurchase :" + e.getMessage());
        }

    }
}
