package com.glymed.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {"@rerun/failedscenarios.txt"},
		plugin = { "pretty","json:target/cucumber_reports/Cucumber.json","html:target/cucumber_reports/html","io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm" },

		glue = {"com/adib/stepDefinition"},
//		tags = {"@IB"},


		dryRun= false,
		monochrome = true
		)
public class FailedReRunRunner {
	
}