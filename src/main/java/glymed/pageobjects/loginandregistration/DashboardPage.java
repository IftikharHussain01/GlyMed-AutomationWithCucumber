package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
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

    public boolean validateDashboard(){
        comfun.waitForElement(userNameLabel);
        return userNameLabel.isDisplayed();
    }

    public void clickProductsOption(){
        comfun.waitForElement(productsOption);
        productsOption.click();
    }

}
