package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage {
    WebDriver driver;
    CommonFunction comfun;

    public CheckOutPage(WebDriver driver, CommonFunction comfun) {
        this.driver = driver;
        this.comfun = comfun;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h4[contains(text(),'Cart')]")
    WebElement cartTitle;

    @FindBy(xpath = "//p[contains(text(),'Sub Total')]")
    WebElement subTotle;

    @FindBy(xpath = "//button[contains(text(),'Check Out')]")
    WebElement checkoutButton;

    @FindBy(xpath = "//h3[contains(text(),'Checkout')]")
    WebElement checkoutTitle;

    @FindBy(xpath = "(//h6[contains(text(),'Shipping')])[1]")
    WebElement shippingTitle;

    @FindBy(xpath = "//h5[contains(text(),'Please verify your shipping address prior to finalizing your order.')]")
    WebElement shippingDescription;

    @FindBy(xpath = "//h4[contains(text(),'In Your Cart')]")
    WebElement inYourCartTitle;

    @FindBy(xpath = "//h5[contains(text(),'REGEN Rewards')]")
    WebElement regenRewards;

    @FindBy(xpath = "//p[contains(text(),'points are redeemable')]")
    WebElement redeemablePoints;

    @FindBy(xpath = "//h6[contains(text(),'Shipping Address')]")
    WebElement shippingAddressTitle;

    @FindBy(xpath = "//h6[contains(text(),'Choose Shipping Option')]")
    WebElement chooseShipppingOption;

    @FindBy(xpath = "//div[@aria-haspopup='listbox']")
    WebElement shippingOptionDropDown;

    @FindBy(xpath = "//li[@data-value='UPS GROUND']")
    WebElement standardShippingOption;

    @FindBy(xpath = "//textarea[@id='outlined-adornment-weight']")
    WebElement shippingInstructionTextField;

    @FindBy(xpath = "//h6[contains(text(),'Payment Method')]")
    WebElement paymentMethodTitle;

    @FindBy(xpath = "//h5[@id='Total']")
    WebElement totalAmountBefore;

    @FindBy(xpath = "//div[contains(@class,'MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-10 css-1kijih2')]//b[1]")
    WebElement totalAmountAfter;

    @FindBy(xpath = "//button[contains(text(),'Continue to Payment')]")
    WebElement continueToPayment;

    @FindBy(xpath = "//button[contains(text(),'Review your order')]")
    WebElement reviewYourOrderButton;

    @FindBy(xpath = "//button[contains(text(),'Place your order')]")
    WebElement placeYourOrderButton;

    @FindBy(xpath = "//div[contains(@role,'presentation')]//div[contains(@class,'MuiGrid-root MuiGrid-container css-1d3bbye')]//button[1]")
    WebElement dontUsePoints;

    @FindBy(xpath = "//button[normalize-space()='I agree']")
    WebElement agreeTnC;

    @FindBy(xpath = "//h3[contains(text(),'Thank you for your order')]")
    WebElement successTitle;

    public boolean validateCartScreen() {
        return cartTitle.isDisplayed() && checkoutButton.isDisplayed()
                && subTotle.isDisplayed();
    }

    public void clickCheckOutButton() {
        checkoutButton.click();
    }

    public String getRedeemablePoints() {
        return redeemablePoints.getText();
    }

    public boolean validateCheckOutScreen() {
        return checkoutTitle.isDisplayed() && shippingTitle.isDisplayed()
                && shippingDescription.isDisplayed() && inYourCartTitle.isDisplayed() && regenRewards.isDisplayed()
                && shippingAddressTitle.isDisplayed() && chooseShipppingOption.isDisplayed();
    }

    public String getTotalAmountBeforeOrderPlace(){
        return totalAmountBefore.getText();
    }

    public String getTotalAmountAfterOrderPlace(){
        comfun.scrollToTheEnd();
        return totalAmountAfter.getText();
    }

    public void clickChooseShippingOption() {
        shippingOptionDropDown.click();
        standardShippingOption.click();
    }

    public void enterShippingInstruction(String instruction) {
        comfun.scrollToTheEnd();
        shippingInstructionTextField.sendKeys(instruction);
    }

    public void clickContinueToPaymentButton(){
        continueToPayment.click();
    }

    public void clickReviewYourOrderButton(){
        comfun.scrollToTheEnd();
        reviewYourOrderButton.click();
    }

    public void clickPlaceYourOrderButton(){
        comfun.scrollToTheEnd();
        placeYourOrderButton.click();
    }

    public boolean validatePaymentMethod(){
        return paymentMethodTitle.isDisplayed();
    }
    public void clickDontUsePointsButton(){
        dontUsePoints.click();
    }

    public void clickTnCButton(){
        agreeTnC.click();
    }

    public boolean validateSuccessScreen(){
        comfun.waitForElement(successTitle);
        return successTitle.isDisplayed();
    }

}
