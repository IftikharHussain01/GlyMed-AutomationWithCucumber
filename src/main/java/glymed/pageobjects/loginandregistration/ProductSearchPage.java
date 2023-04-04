package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductSearchPage {

    WebDriver driver;
    CommonFunction comfun;

    public ProductSearchPage(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='MuiContainer-root MuiContainer-maxWidthLg css-v9fvjl']//div//input[@id='filled-hidden-label-small']")
    WebElement searchField;

    @FindBy(xpath = "//p[contains(text(),'There are no products that match your selected criteria')]")
    WebElement searchNoProductMessage;

    @FindBy(xpath = "//button[normalize-space()='Clear Filters']")
    WebElement clearFilterButton;

    public void enterSearchValue(String category) {
        try {
            searchField.click();
            comfun.driverWait(2000);
            searchField.sendKeys(category);
            searchField.sendKeys(Keys.ENTER);
            comfun.driverWait(2000);
        } catch (Exception e) {
            Log.warn("value in enterSearchValue is not sent" + e.getMessage());
        }
    }

    public boolean validateNoProductMessage() {
        comfun.waitForElement(searchNoProductMessage);
        return searchNoProductMessage.isDisplayed();
    }

    public void clickClearFilterButton() {
        try {
            comfun.clickElement(clearFilterButton);
            comfun.driverWait(3000);
        } catch (Exception e) {
            Log.warn("clickClearFilterButton is not clicked :" + e.getMessage());
        }
    }
}
