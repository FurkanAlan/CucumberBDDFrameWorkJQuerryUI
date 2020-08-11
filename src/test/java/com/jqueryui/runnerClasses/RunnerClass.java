package com.jqueryui.runnerClasses;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"

        , glue = "com/jqueryui/stepDefinitions"

        //ikinci metod
//        features = "classpath:features"
//        , glue = {"stepDefinitions"}
        , dryRun = false
        , plugin = {"pretty", "html:target/html/cucumber-default", "json:target/cucumber.json", "rerun:target/failed.txt"}
//        , monochrome = true
//        , tags = {"@smoke"}
)

public class RunnerClass {

}
