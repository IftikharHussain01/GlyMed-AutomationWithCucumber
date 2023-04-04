package glymed.pageobjects.loginandregistration;

import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.Log;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//input[@type='checkbox']")
    WebElement useMyPointsCheckMark;

    @FindBy(xpath = "//h6[contains(text(),'Shipping Address')]")
    WebElement shippingAddressTitle;

    @FindBy(xpath = "//h6[normalize-space()='Choose Shipping Option:']")
    WebElement chooseShipppingOption;

    @FindBy(xpath = "//div[@aria-label='Without label']")
    WebElement shippingOptionDropDown;

    @FindBy(xpath = "//p[contains(text(),'Standard')]")
    WebElement standardShippingOption;

    @FindBy(xpath = "//p[contains(text(),'Next Day Air AM')]")
    WebElement nextDayAirShippingOption;

    @FindBy(xpath = "//p[contains(text(),'2nd Day Air')]")
    WebElement secondDayAirShippingOption;

    @FindBy(xpath = "//textarea[@id='outlined-adornment-weight']")
    WebElement shippingInstructionTextField;

    @FindBy(xpath = "//h6[contains(text(),'Payment Method')]")
    WebElement paymentMethodTitle;

    @FindBy(xpath = "//h6[contains(text(),'Add New Payment')]")
    WebElement addNewPayment;

    @FindBy(xpath = "//button[normalize-space()='Add']")
    WebElement addPaymentButton;

    @FindBy(xpath = "//h4[normalize-space()='ADD NEW PAYMENT METHOD']")
    WebElement addPaymentTitle;

    @FindBy(xpath = "//input[@id ='cardNumber' and @placeholder='Card number']")
    WebElement cardNumberTextField;

    @FindBy(xpath = "//input[@id = 'outlined-basic' and @placeholder='Name on card']")
    WebElement cardNameTextField;

    @FindBy(xpath = "//input[@id = 'expirationDate' and @placeholder='MM/YY']")
    WebElement expiryDateTextField;

    @FindBy(xpath = "//input[@id = 'cvv' and @placeholder='CVV']")
    WebElement cvvTextField;

    @FindBy(xpath = "//button[@id='rswp-card-button']")
    WebElement savePaymentMethodButton;

    @FindBy(xpath = "//h5[@id='Total']")
    WebElement totalAmountBefore;

    @FindBy(xpath = "//h6[@id='Shipping & Handling']")
    WebElement shippingAmount;

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

    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[4]/div[1]/div[3]/p[1]")
    WebElement successScreenShippingMethod;

    @FindBy(xpath = "//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeMedium cart-close css-1yxmbwk']//*[name()='svg']")
    WebElement checkoutCloseBtn;

    public boolean validateCartScreen() {
        comfun.waitForElement(cartTitle);
        return cartTitle.isDisplayed() && checkoutButton.isDisplayed() && subTotle.isDisplayed();
    }

    public String getSubTotalAmount() throws InterruptedException {
        Thread.sleep(5000);
        return subTotle.getText();
    }

    public void clickCheckOutButton() {
        checkoutButton.click();
    }

    public String getRedeemablePoints() {
        return redeemablePoints.getText();
    }

    public void setUseMyPointsCheckMark() {
        comfun.clickElement(useMyPointsCheckMark);
    }

    public boolean validateCheckOutScreen() {
        return checkoutTitle.isDisplayed() && shippingTitle.isDisplayed() && shippingDescription.isDisplayed() && inYourCartTitle.isDisplayed()
                && regenRewards.isDisplayed()
                && shippingAddressTitle.isDisplayed() && chooseShipppingOption.isDisplayed();
    }

    public String getTotalAmountBeforeOrderPlace() {
        return totalAmountBefore.getText();
    }

    public String getTotalAmountAfterOrderPlace() {
        comfun.scrollToTheEnd();
        return totalAmountAfter.getText();
    }

    public void clickShippingDropdown() {
        comfun.clickElement(shippingOptionDropDown);
    }

    public String getShippingMethod() {
        return shippingOptionDropDown.getText();
    }

    public void clickChooseShippingOption() {
        comfun.waitForElement(standardShippingOption);
        comfun.clickElement(standardShippingOption);
        comfun.minimumScroll();
    }

    public void clickChooseAirShippingOption() {
        comfun.waitForElement(nextDayAirShippingOption);
        comfun.clickElement(nextDayAirShippingOption);
        comfun.minimumScroll();
    }

    public void clickChooseSecondAirShippingOption() {
        comfun.waitForElement(secondDayAirShippingOption);
        comfun.clickElement(secondDayAirShippingOption);
        comfun.minimumScroll();
    }

    public void enterShippingInstruction(String instruction) {
        shippingInstructionTextField.sendKeys(instruction);
    }

    public void clickContinueToPaymentButton() {
        try {
            comfun.clickElement(continueToPayment);
        } catch (Exception e) {
            Log.info("Continue to payment button is not displayed");
        }

    }

    public void clickReviewYourOrderButton() {
        try {
            comfun.clickElement(reviewYourOrderButton);
        } catch (Exception e) {
            Log.info("Review Your Order button is not displayed");
        }
    }

    public void clickPlaceYourOrderButton() {
        try {
            comfun.clickElement(placeYourOrderButton);
        } catch (Exception e) {
            Log.info("Place Your Order button is not displayed");
        }
    }

    public void enterCardName() {
        addNewPayment.isDisplayed();
        comfun.clickElement(addPaymentButton);
        comfun.driverWait(2000);

        cardNameTextField.click();
        cardNameTextField.sendKeys("ABC ABC");
        comfun.driverWait(2000);
        cardNameTextField.sendKeys(Keys.TAB);
    }

    public void enterCardNumber() {
        comfun.sendKeys(cardNumberTextField, "4111111111111111");
        cardNumberTextField.sendKeys(Keys.TAB);
    }

    public void enterCardDetails() {
        try {
            addNewPayment.isDisplayed();
            comfun.clickElement(addPaymentButton);
            comfun.driverWait(2000);
            addPaymentTitle.isDisplayed();
            comfun.driverWait(2000);
            cardNameTextField.click();
            cardNameTextField.sendKeys("ABC ABC");
            comfun.driverWait(2000);
            cardNameTextField.sendKeys(Keys.TAB);
//            cardNumberTextField.click();
            comfun.sendKeys(cardNumberTextField, "4111111111111111");
            cardNumberTextField.sendKeys("4111111111111111");
            comfun.driverWait(2000);
            expiryDateTextField.click();
            expiryDateTextField.sendKeys("0325");
            comfun.driverWait(2000);
            cvvTextField.click();
            cvvTextField.sendKeys("123");
            comfun.driverWait(2000);
            comfun.driverWait(2000);
            comfun.clickElement(savePaymentMethodButton);
        } catch (Exception e) {
            Log.info("Card Details Already Available");
        }
    }

    public boolean validatePaymentMethod() {
        comfun.scrollIntoView(paymentMethodTitle);
        return paymentMethodTitle.isDisplayed();
    }


    public void clickDontUsePointsButton() {
        try {
            comfun.clickElement(dontUsePoints);
        } catch (Exception e) {
            Log.info("Dont want to use points button is not displayed");
        }
    }

    public void clickTnCButton() {
        try {
            comfun.clickElement(agreeTnC);
        } catch (Exception e) {
            Log.info("TnC Agree button is not displayed");
        }
    }

    public boolean validateSuccessScreen() {
        comfun.waitForElement(successTitle);
        return successTitle.isDisplayed();
    }

    public void clickCheckoutCloseButton() {
        try {
            comfun.clickElement(checkoutCloseBtn);
        } catch (Exception e) {
            Log.error("clickCheckoutCloseButton is not clicked" + e.getMessage());
        }
    }

    public String getShippingAndHandlingAmount() {
        comfun.waitForElement(shippingAmount);
        return shippingAmount.getText();
    }

    public String getSuccessScreenShippingMethod(){
        comfun.scrollToTheEnd();
        return successScreenShippingMethod.getText();
    }

}
