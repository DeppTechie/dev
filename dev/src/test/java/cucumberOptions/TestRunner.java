package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        features = {"src/test/java/feature/Holiday.feature"},
        glue = "stepDefinitions",
        plugin = {"pretty","html:target/cucumber-reports"},
        monochrome = true

)
public class TestRunner

{

}
	
