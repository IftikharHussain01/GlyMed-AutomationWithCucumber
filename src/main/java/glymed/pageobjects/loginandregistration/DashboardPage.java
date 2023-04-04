package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    CommonFunction comfun;

    public DashboardPage(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class = 'HeaderProfile_button__qZqO1']")
    WebElement userNameLabel;
    @FindBy(xpath = "(//span[contains(text(),'Products')])[2]")
    WebElement productsOption;

    @FindBy(xpath = "//div[@class='Header_logo-box__0pU+5 Header_logo-box-signedin__tnRi1 scale-in-center-delay-0']//a//*[name()='svg']")
    WebElement glymedLabel;

    public boolean validateDashboard() {
        comfun.waitForElement(userNameLabel);
        return userNameLabel.isDisplayed();
    }

    public void clickProductsOption() {
        comfun.waitForElement(productsOption);
        productsOption.click();
    }

    public void clickGlyMedLabel() {
        try {
            comfun.clickElement(glymedLabel);
        } catch (Exception e) {
            Log.error("clickGlyMedLabel is not clicked" + e.getMessage());
        }
    }

}
