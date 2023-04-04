package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MoisturizerProductPage {

    WebDriver driver;
    CommonFunction comfun;

    public MoisturizerProductPage(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@value='Moisturizer']")
    WebElement moisturizerCheckMark;
    @FindBy(xpath = "//div[@id='GM114']")
    WebElement atraxiProduct;

    @FindBy(xpath = "//input[@id='product-quantity-GM114']")
    WebElement retailTextField;

    @FindBy(xpath = "//input[@id='product-quantity-GM114B']")
    WebElement blackBarTextField;

    public void clickMoisturizerCheckMark() {
        try {
            comfun.clickElement(moisturizerCheckMark);
        } catch (Exception e) {
            Log.warn("clickMoisturizerCheckMark is not clicked :" + e.getMessage());
        }
    }

    public void selectAtraxiProduct() {
        try {
            comfun.clickElement(atraxiProduct);
        } catch (Exception e) {
            Log.warn("selectAtraxiProduct is not clicked :" + e.getMessage());
        }
    }

    public void enterRetailQuantityForMoist(String value) {
        try {
            comfun.clickElement(retailTextField);
            retailTextField.clear();
            retailTextField.sendKeys(value);
        } catch (Exception e) {
            Log.warn("unable to send value to enterQuantityToPurchase" + e.getMessage());
        }
    }

    public void enterBlackBarQuantityForMoist(String value) {
        try {
            comfun.clickElement(blackBarTextField);
            blackBarTextField.clear();
            blackBarTextField.sendKeys(value);
        } catch (Exception e) {
            Log.warn("unable to send value to enterQuantityToPurchase" + e.getMessage());
        }
    }
}
