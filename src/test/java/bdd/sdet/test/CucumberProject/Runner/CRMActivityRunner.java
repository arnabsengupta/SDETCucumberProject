package bdd.sdet.test.CucumberProject.Runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/bdd/sdet/test/Features",glue = {"bdd/sdet/test/CucumberProject/stepsdef"},plugin={"pretty","html:target/cucumber-reports"},monochrome = true,tags="@CRMActivity",strict=true)
public class CRMActivityRunner {}

