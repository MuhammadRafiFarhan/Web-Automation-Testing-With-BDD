package com.swaglab.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogoutSteps {
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Given("I am logged in")
    public void I_am_logged_in() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @When("I click on the menu button")
    public void I_click_on_the_menu_button() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
        // wait for the menu to be displayed
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @And("I click on the logout button")
    public void I_click_on_the_logout_button() {
        driver.findElement(By.id("logout_sidebar_link")).click();
    }

    @Then("I should be logged out")
    public void I_should_be_logged_out() {
        assertTrue(driver.findElement(By.cssSelector("[data-test='login-container']")).isDisplayed());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
