package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertTrue;

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

    @FindBy(xpath = "//div[@id='GM16']")
    WebElement cleanserProductTwo;

    @FindBy(xpath = "//input[@id='product-quantity-GM1B']")
    WebElement backBarQuantityField;

    @FindBy(xpath = "//h5[normalize-space()='Size']")
    WebElement sizeTitle;

    @FindBy(xpath = "//input[@id='product-quantity-GM1T']")
    WebElement travelQuantityField;

    @FindBy(xpath = "//input[@id='product-quantity-GM1']")
    WebElement retailQuantityField;

    @FindBy(xpath = "//input[@id='product-quantity-GM16']")
    WebElement retailQuanityP2;

    @FindBy(xpath = "//input[@id='product-quantity-GM1S']")
    WebElement trailQuantityField;

    @FindBy(xpath = "//button[contains(text(),'Add To Cart')]")
    WebElement addToCardButton;

    @FindBy(xpath = "//h6[contains(text(),'GlyMed Plus Customer Notification')]")
    WebElement customerNotification;

    @FindBy(xpath = "(//*[@data-testid='CloseIcon' and @class='MuiSvgIcon-root MuiSvgIcon-fontSizeMedium css-vubbuv'])[2]")
    WebElement customerNotificationCloseBtn;

    public void validateAddictivelabel() {
        try {
            if (addictiveLabel.isDisplayed()) {
                assertTrue("Addictive label is not displayed", addictiveLabel.isDisplayed());
            }
        } catch (Exception e) {
            Log.info("Addictive Label is not displayed");
        }
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
        try {
            comfun.clickElement(cleanserProductOne);
        } catch (Exception e) {
            Log.error("cleanser Product is not clicked" + e.getMessage());
        }
    }

    public void addOneMoreProduct() {
        try {
            comfun.clickElement(cleanserProductTwo);
        } catch (Exception e) {
            Log.error("cleanser Product is not clicked" + e.getMessage());
        }
    }

    public void moveToBackBar() {
//        comfun.scrollIntoView(backBarQuantityField);
        comfun.scrollIntoView(sizeTitle);
        comfun.driverWait(5000);
    }

    public void moveToTextField() {
        comfun.scrollIntoView(sizeTitle);
        comfun.driverWait(2000);
    }

    public void setQuantityForTavel(String quantity) {
        try {
            comfun.clickElement(travelQuantityField);
            travelQuantityField.clear();
            travelQuantityField.sendKeys(quantity);
        } catch (Exception e) {
            Log.warn("Unable to enter quantity in travel" + e.getMessage());
        }
    }

    public void setQuantityForTrail(String quantity) {
        try {
            comfun.clickElement(trailQuantityField);
            trailQuantityField.clear();
            trailQuantityField.sendKeys(quantity);
        } catch (Exception e) {
            Log.warn("Unable to enter quantity in trial" + e.getMessage());
        }
    }

    public void enterRetailQuanitity(String quantity) {
        try {
            comfun.clickElement(retailQuantityField);
            retailQuantityField.clear();
            retailQuantityField.sendKeys(quantity);
        } catch (Exception e) {
            Log.warn("Unable to enter quantity in retail" + e.getMessage());
        }
    }

    public void setQuantityForRetail(String quantity) {
        comfun.scrollIntoView(retailQuanityP2);
        comfun.clickElement(retailQuanityP2);
        retailQuanityP2.clear();
        retailQuanityP2.sendKeys(quantity);
//        trailQuantityField.sendKeys(quantity);
    }

    public void clickAddtoCartButton() {
        comfun.scrollIntoView(addToCardButton);
        comfun.clickElement(addToCardButton);
    }
}
