package com.glymed.commonFunctions;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.glymed.utilities.Log;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunction {
    /**
     * The driver.
     */
    WebDriver driver;

    /**
     * Instantiates a new common function android impl.
     *
     * @param driver the driver
     */
    public CommonFunction(WebDriver driver) {
        this.driver = driver;
    }


    /**
     * Method to scroll down to given element height.
     */
    public void scrollDownBodySection(By tagname, int height) {
        WebElement ae = driver.findElement(tagname);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ae.getSize().getHeight();
        if (height > 0) {
            js.executeScript("arguments[0].scrollTo(0," + height + ");", ae);
        } else {
            js.executeScript("window.scrollTo(0,arguments[0].scrollHeight);", ae);
        }
    }

    public void minimumScroll() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,200)");
    }

    /**
     * Method to scroll down to given element height.
     */
    public void scrollDown(WebElement ae) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTo(0,arguments[0].scrollHeight);", ae);

    }

    /**
     * Method to scroll into view to given element height.
     */
    public void scrollIntoView(WebElement ae) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ae);

    }

    public void scrollLtoR(WebElement ae) {
        Actions action = new Actions(driver);
        action.clickAndHold(ae);
        Point location = ae.getLocation();
        int x = location.getX();
        int y = location.getY();
        action.moveByOffset(x - (driver.manage().window().getSize().getWidth()), y).release();
        action.build().perform();
    }

    /**
     * Method to set access token in storage.
     */
    public void setAccessTokenInStorage(String token) {
        if (System.getProperty("BROWSER").equalsIgnoreCase("Safari")) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("localStorage.setItem(arguments[0],arguments[1])", "access_token", token);
        } else {
            LocalStorage local = ((WebStorage) driver).getLocalStorage();
            local.setItem("access_token", token);
        }
    }

    /**
     * Method to wait the tread.
     *
     * @param duration wait time in long
     */
    public void driverWait(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
        }
    }

    public void waitForLoader(By tagname) {
        try {
            WebElement spinner = driver.findElement(tagname);

            WebDriverWait wait = new WebDriverWait(driver, 20);

            wait.until(ExpectedConditions.attributeToBeNotEmpty(spinner, "hidden"));
        } catch (TimeoutException e) {
            System.out.println("--------------App loader timed out------------------------------");
        }
    }

    public void waitForElement(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.visibilityOf(element));
        }  catch (TimeoutException e) {
        System.out.println("--------------Element is not displayed------------------------------");
    }

    }

    /**
     * Method to wait for visibility of element
     *
     * @param ae web element to wait for
     */

    public void waitCondition(WebElement ae) {
        WebElement spinner = driver.findElement(By.tagName("app-loader"));
        WebDriverWait wait = new WebDriverWait(driver, 90);
        wait.until(ExpectedConditions.and(ExpectedConditions.attributeToBeNotEmpty(spinner, "hidden"), ExpectedConditions.visibilityOf(ae)));

    }


    public String numberFormatter(String val) {
        NumberFormat formatter = new DecimalFormat("##,###");
        return formatter.format(Float.parseFloat(val));
    }

    public String numberFormatterDecimal(String val) {
        NumberFormat formatter = new DecimalFormat("##,###.00");
        return formatter.format(Float.parseFloat(val));
    }

    public int randomNumber(int val, int min, int max) {
        Random ran = new Random();
        return ran.ints(val, min, max).findFirst().getAsInt();
    }

    public long randomNumber() {
        return (long) (Math.random() * 10000000000000000L);
    }

    public WebElement findElementByAccessibility(WebDriver driver, String accessibility, int timeOut) {
        try {

            return new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOfElementLocated(By.id(accessibility)));
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementByXpath(WebDriver driver, String xpath, int timeOut) {
        try {
            return new WebDriverWait(driver, timeOut).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (NullPointerException e) {
            System.out.println("---------------------Element not found----------------------------");
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public WebElement findElementById(WebDriver driver, String id) {
        try {
            return driver.findElement(By.id(id));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method to compare two integer values
     *
     * @param actual   actual int value
     * @param expected expected int value
     * @return boolean result
     */
    public boolean validateIntegers(int actual, int expected) {
        if (actual == expected) {
            return true;
        }
        return false;
    }

    /**
     * Method to valdate text.
     *
     * @param: Element
     * for the text field
     * @param: Text
     * to verify
     */
    public boolean validateText(WebElement ae, String text) {
        if (ae.getText().equals(text)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Validate integer values
     *
     * @param ae   webelement for text
     * @param text value to assert for
     * @return boolean value
     */
    public boolean validateInt(WebElement ae, String text) {
        try {
            if (NumberFormat.getNumberInstance(Locale.ENGLISH).parse(ae.getText())
                    .intValue() == (int) Double.parseDouble(text)) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("Number format exception occoured");
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Parse Exception occoured");
            return false;
        }
    }

    public int getIntegerValue(String text) {
        int number = 0;
        try {
            number = NumberFormat.getNumberInstance(Locale.ENGLISH).parse(text).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return number;
    }

    /**
     * Method to navigate back.
     */
    public void navigateBack() {
        driver.navigate().back();

    }

    /**
     * Method to navigate to url.
     */
    public void navigateToUrl(String url) {
        driver.navigate().to(url);

    }

    public String directoryCreation(Properties prop, String scenarioName) {

        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MMM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        Calendar now = Calendar.getInstance();
        String dirPath = null;

        try {

            dirPath = prop.getProperty("SCREENSHOT_PATH") + "/" + day.format(now.getTime()) + "-" + month.format(now.getTime()) + "-" + year.format(now.getTime());
            if (dirPath.contains("@")) {
                dirPath = dirPath.replace("@", "");
            }
            File theDir = new File(dirPath);
            if (!theDir.exists()) {
                boolean result = false;
                try {
                    if (theDir.mkdirs())
                        result = true;
                } catch (SecurityException localSecurityException) {
                    return dirPath;
                }
                if (result) {
                    System.out.println("False");
                }
            }


        } catch (Exception e) {
            Log.error(e.getMessage());
        }

        return dirPath;
    }

    /**
     * Method to take a Web screenshot.
     */
    public String takeScreenshots(String dirPath, String imageName) {
        try {
            dirPath = dirPath + "/" + imageName + ".png";
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(dirPath));
        } catch (IOException e) {
            Log.error(e.getMessage());
        }
        return dirPath;
    }

    public boolean isElementPresent(WebElement element) {
        try {
            element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void scrollToTheEnd() {
        long initialHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
        while (true) {
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            long newHeight = (Long) ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
            if (newHeight == initialHeight) {
                break;
            }
            initialHeight = newHeight;
        }
    }

    public String getIntegersFromString(String input) {
        String finaltext = null;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            finaltext = matcher.group(0);
        }
        return finaltext;
    }

}
