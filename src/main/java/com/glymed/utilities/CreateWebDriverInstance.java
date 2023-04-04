package com.glymed.utilities;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

public class CreateWebDriverInstance {
    static WebDriver  webdriver = null;
    static Proxy proxy=null;
    public static WebDriver getWebDriver() {
        return webdriver;
    }
    public static void createDriver(Properties config, String testCaseName) {

        if(config.getProperty("RUN_ENV").equalsIgnoreCase("REMOTE")) {
            webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if(config.getProperty("WIDTH").equalsIgnoreCase("MAX") && config.getProperty("HEIGHT").equalsIgnoreCase("MAX")) {
                webdriver.manage().window().maximize();
            }
            else {
                Dimension d = new Dimension(Integer.parseInt(config.getProperty("WIDTH")),Integer.parseInt(config.getProperty("HEIGHT")));
                webdriver.manage().window().setSize(d);
                webdriver.manage().window().maximize();
            }
            webdriver.get(config.getProperty("URL"));
        }else {
            webdriver = createLocalDriver(config,testCaseName);
            webdriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webdriver.manage().window().maximize();
            webdriver.get(config.getProperty("URL"));
        }
    }

    private static WebDriver createLocalDriver(Properties config,String testCaseName){
        String strBrowser = config.getProperty("BROWSER");
        String headless = config.getProperty("HEADLESS");
        switch (strBrowser.toUpperCase()) {
            case "CHROME":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                chromeOptions.setHeadless(headless.equalsIgnoreCase("true"));
                if(proxy!=null) {
                    chromeOptions.setCapability("proxy", proxy);
                }
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.port","8080");

                try {
                    webdriver = new ChromeDriver(chromeOptions);

                    Log.debug("ChromeDriver instance created");
                } catch (Exception objException) {
                    Log.debug("ChromeDriver instance for older version");
                    WebDriverManager.chromedriver().setup();
                    webdriver = new ChromeDriver(chromeOptions);
                }
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.firefox.port","7046");
                try {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addPreference("browser.download.folderList", 2);
                    firefoxOptions.addPreference("browser.download.dir", "~/PDFDownloads/"+testCaseName);
                    firefoxOptions.addPreference("browser.download.useDownloadDir", true);
                    firefoxOptions.addPreference("browser.download.viewableInternally.enabledTypes", "application/pdf");
                    firefoxOptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf");
                    firefoxOptions.addPreference("browser.helperApps.neverAsk.openFile", "application/pdf, application/octet-stream, application/x-winzip, application/x-pdf, application/x-gzip");
                    firefoxOptions.addPreference("pdfjs.disabled", true);
                    firefoxOptions.addPreference("plugin.scan.Acrobat", "99.0");
                    firefoxOptions.addPreference("plugin.scan.plid.all", false);
                    firefoxOptions.setHeadless(headless.equalsIgnoreCase("true"));
                    if(proxy!=null) {
                        firefoxOptions.setCapability("proxy", proxy);
                    }
                    webdriver = new FirefoxDriver(firefoxOptions);

                    Log.debug("FirefoxDriver instance created");
                } catch (Exception objException) {
                    Log.error("Line....56..." + objException.getMessage());
                }
                break;

            case "EDGE":
                Log.info("Starting Edge");
                WebDriverManager.edgedriver().setup();
                System.setProperty("webdriver.edge.port", "7047");
                try {
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
                    if (proxy != null) {
                        edgeOptions.setCapability("proxy", proxy);
                    }
                    webdriver = new EdgeDriver();

                    Log.debug("Edge Drive Instance instance created");
                } catch (Exception objException) {
                    Log.error("Driver creation issue for Edge" + objException.getMessage());
                }
                break;

            case "IE":
                WebDriverManager.iedriver().setup();
                System.setProperty("webdriver.ie.port","7048");
                DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                if(proxy!=null) {
                    ieCapabilities.setCapability("proxy", proxy);
                }
                try {
                    webdriver = new InternetExplorerDriver(ieCapabilities);

                    Log.debug("InternetExplorerDriver instance created");
                } catch (Exception objException) {
                    Log.error("Line....56..." + objException.getMessage());
                }
                break;
            case "SAFARI":

                SafariOptions options = new SafariOptions();
                options.setUseTechnologyPreview(true);
                if(proxy!=null) {
                    options.setCapability("proxy", proxy);
                }
                webdriver = new SafariDriver(options);
                break;
        }

        return webdriver;
    }

    public static void closeDriver() {
        webdriver.quit();
    }

}

