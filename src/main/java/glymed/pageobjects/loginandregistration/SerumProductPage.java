package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SerumProductPage {
    WebDriver driver;
    CommonFunction comfun;

    public SerumProductPage(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@value='Serum']")
    WebElement serumCheckMark;
    @FindBy(xpath = "//h6[normalize-space()='Daily Lift Serum (GM100)']")
    WebElement dailyLifeSerum;

    @FindBy(xpath = "//input[@id='product-quantity-GM100']")
    WebElement retailTextField;

    public void clickSerumCheckMark() {
        try {
            comfun.clickElement(serumCheckMark);
        } catch (Exception e) {
            Log.warn("clickSerumCheckMark is not clicked : " + e.getMessage());
        }
    }

    public void clickDailyLifeSerum() {
        try {
            comfun.clickElement(dailyLifeSerum);
        } catch (Exception e) {
            Log.warn("clickSerumCheckMark is not clicked : " + e.getMessage());
        }
    }

    public void enterQuantityToPurchase(String value) {
        try {
            comfun.clickElement(retailTextField);
            retailTextField.clear();
            retailTextField.sendKeys(value);
        } catch (Exception e) {
            Log.warn("unable to send value to enterQuantityToPurchase" + e.getMessage());
        }
    }

}
