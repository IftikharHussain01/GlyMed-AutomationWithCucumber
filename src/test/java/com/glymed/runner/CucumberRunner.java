package com.glymed.runner;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/com/glymed/features"},
        plugin = {"pretty", "json:target/cucumber_reports/Cucumber.json", "html:target/cucumber_reports/html", "io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm", "rerun:rerun/failedscenarios.txt"},
        glue = {"com/glymed/stepDefinition"},
        tags = {"@proUserLogin"},
        dryRun = false,
        monochrome = true
)
public class CucumberRunner {
    @BeforeClass
    public static void setup() {
        System.out.println("Ran the before");
    }

    @SuppressWarnings("unused")
    public static void teardown() {
        System.out.println("Ran the after");
    }


}