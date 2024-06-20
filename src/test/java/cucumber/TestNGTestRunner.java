package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="shonautomations.stepDefinitions",
monochrome=true,tags= "@ErrorValidation" ,plugin= {"html:target/cucumber.html"}) // @cucumberOptions have alot of attribute we need to provide inside it
public class TestNGTestRunner extends AbstractTestNGCucumberTests { // all what this extrend provide (testNG stuff) (saw in douments)
	

}
