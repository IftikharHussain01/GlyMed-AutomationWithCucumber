package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {

    WebDriver driver;
    CommonFunction comfun;

    public ProductsPage(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h3[contains(text(),'Additive')]")
    WebElement addictiveLabel;

    @FindBy(xpath = "//input[@type='checkbox' and @value='Cleanser']")
    WebElement cleanserCheckMark;

    @FindBy(xpath = "//h3[contains(text(),'Cleanser')]")
    WebElement cleansertitle;

    @FindBy(xpath = "//div[@id='GM1']")
    WebElement cleanserProductOne;

    @FindBy(xpath = "//input[@id='product-quantity-GM1B']")
    WebElement backBarQuantityField;

    @FindBy(xpath = "//input[@id='product-quantity-GM1T']")
    WebElement travelQuantityField;

    @FindBy(xpath = "//input[@id='product-quantity-GM1S']")
    WebElement trailQuantityField;

    @FindBy(xpath = "//button[contains(text(),'Add To Cart')]")
    WebElement addToCardButton;

    @FindBy(xpath = "//h6[contains(text(),'GlyMed Plus Customer Notification')]")
    WebElement customerNotification;

    @FindBy(xpath = "(//*[@data-testid='CloseIcon' and @class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-vubbuv'])[2]")
    WebElement customerNotificationCloseBtn;

    public boolean validateAddictivelabel() {
        return addictiveLabel.isDisplayed();
    }

    public void checkCustomerNotification() {
        try {
            if (customerNotification.isDisplayed()) {
                customerNotificationCloseBtn.click();
            }
        } catch (Exception e) {
            Log.info("Notification is not displayed");
        }
    }
    public void clickCleanserCheckMark() {
        cleanserCheckMark.click();
    }

    public boolean validateCleanserTitle() {
        return cleansertitle.isDisplayed();
    }

    public void selectFirstProduct() {
        comfun.moveToElement(cleanserProductOne);
        cleanserProductOne.click();
    }

    public void moveToBackBar() {
        comfun.scrollIntoView(backBarQuantityField);
    }

    public void setQuantityFortrialAndTavel(String quantity){
        travelQuantityField.sendKeys(quantity);
//        trailQuantityField.sendKeys(quantity);
    }

    public void clickAddtoCartButton(){
        comfun.scrollIntoView(addToCardButton);
        addToCardButton.click();
    }
}
