package com.swaglab.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.swaglab.WebDriverSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {
    private WebDriverSetup setup;
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        setup = new WebDriverSetup();
        driver = setup.getDriver();
    }

    @Given("I have opened the browser")
    public void I_have_opened_the_browser() {
        
    }

    @When("I open Swaglabs website")
    public void I_open_Swaglabs_website() {
        driver.get("https://www.saucedemo.com/");
    }

    @Then("username box should exist")
    public void username_box_should_exist() {
        assertTrue(driver.findElement(By.id("user-name")).isDisplayed());
    }

    @Then("password box should exist")
    public void password_box_should_exist() {
        assertTrue(driver.findElement(By.id("password")).isDisplayed());
    }

    @Then("login button should exist")
    public void login_button_should_exist() {
        assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }

    @Given("I am on the Swag Labs login page")
    public void I_am_on_the_Swag_Labs_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter the username {string}")
    public void I_enter_the_username(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    @And("I enter the password {string}")
    public void I_enter_the_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @And("I click on the login button")
    public void I_click_on_the_login_button() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be taken to the products page")
    public void I_should_be_taken_to_the_products_page() {
        String url = driver.getCurrentUrl();
        assertTrue(url.contains("inventory.html"));
    }

    @Then("I should see an error message {string}")
    public void I_should_see_an_error_message(String errorMessage) {
        String actualErrorMessage = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        assertTrue(actualErrorMessage.equals(errorMessage));
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}