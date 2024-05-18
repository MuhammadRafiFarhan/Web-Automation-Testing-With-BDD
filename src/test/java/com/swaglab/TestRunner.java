package com.swaglab;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
//import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:com/swaglab/features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.swaglab.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty,junit:target/cucumber-reports/Cucumber.xml,json:target/cucumber-reports/Cucumber.json,html:target/cucumber-reports/Cucumber.html,timeline:target/cucumber-reports/CucumberTimeline")
public class TestRunner {
    
}
