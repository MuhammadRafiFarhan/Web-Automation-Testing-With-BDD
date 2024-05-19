package com.swaglab.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSetup {

    private static WebDriver driver;

    private WebDriverSetup() {
        WebDriverManager.chromedriver().setup();
    }
    
    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverSetup.driver = new ChromeDriver();
        }
        
        return driver;
    }
}
