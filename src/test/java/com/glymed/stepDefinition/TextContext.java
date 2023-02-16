package com.glymed.stepDefinition;

import com.glymed.config.Config;
import com.glymed.commonFunctions.CommonFunction;
import com.glymed.utilities.CreateWebDriverInstance;
import com.glymed.utilities.Log;
import com.glymed.utilities.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import glymed.pageobjects.manager.PageObjectManager;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Properties;


public class TextContext extends RunListener {

    public static Properties config;
    public static Properties constants;
    public static PageObjectManager pageObjectManager;
    public static CommonFunction com_fun;
    public static Scenario scenario = null;
    public static String directoryPath = null;
    public static WebDriver driver = null;
    static int rowCount = 0;
    public static Reporter reporter = new Reporter();

    public TextContext() {
        try {
            Log.info("config initialization");
            config = Config.initializeConfig();
            constants = Config.initializeConstants();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    @Before()
    public void beforeMethod(Scenario scenario) {
        TextContext.scenario = scenario;
        Log.startTestCase(scenario.getName());
    }

    @Before()
    public void beforeMethodForIB(Scenario scenario) {
        if (config.getProperty("row") == null) {
            config.setProperty("row", "1");
        }
        rowCount = Integer.parseInt(config.getProperty("row"));

        Log.info("rowCount - " + rowCount);
        CreateWebDriverInstance.createDriver(config, scenario.getName());

        driver = CreateWebDriverInstance.getWebDriver();

        com_fun = new CommonFunction(driver);

        pageObjectManager = new PageObjectManager(driver, com_fun);

        // Creating screenshot Directory
        directoryPath = com_fun.directoryCreation(config, scenario.getName());
        System.out.println(directoryPath);
    }

    @After()
    public void afterMethod(Scenario scenario) throws IOException {
        File[] file = new File[]{new File(com_fun.takeScreenshots(directoryPath, String.valueOf(System.currentTimeMillis())))};

        reporter.addAllureAttachment("Screen", file);

        Log.info("################## " + scenario.getName() + " ---- " + scenario.getStatus() + scenario.getLines());

        reporter.wordReportCreator(scenario.getName().replaceAll("/", " or ") + "_" + scenario.getStatus(), System.getProperty("user.dir") + config.getProperty("REG_EXCEL"),
                directoryPath, directoryPath, 1, 11);

        rowCount = Integer.parseInt(config.getProperty("row")) + 1;
        config.setProperty("row", String.valueOf(rowCount));
        Log.info("rowCount - " + config.getProperty("row"));

        if (driver != null) {
            CreateWebDriverInstance.closeDriver();
        }
        Log.endTestCase();
    }

    @AfterStep()
    public static void screenShotAfterEachStep() throws IOException {
        File[] file = new File[]{new File(com_fun.takeScreenshots(directoryPath, String.valueOf(System.currentTimeMillis())))};
        reporter.addAllureAttachment("Screen", file);
    }
}