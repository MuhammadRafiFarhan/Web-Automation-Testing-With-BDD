package com.swaglab.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;

import com.swaglab.utils.WebDriverSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.swaglab.locators.HomePageLocators;
import com.swaglab.locators.LoginPageLocators;

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
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
    }

    @Then("username box should exist")
    public void username_box_should_exist() {
        assertTrue(driver.findElement(LoginPageLocators.usernameField).isDisplayed());
    }

    @Then("password box should exist")
    public void password_box_should_exist() {
        assertTrue(driver.findElement(LoginPageLocators.passwordField).isDisplayed());
    }

    @Then("login button should exist")
    public void login_button_should_exist() {
        assertTrue(driver.findElement(LoginPageLocators.loginButton).isDisplayed());
    }

    @Given("I am on the Swag Labs login page")
    public void I_am_on_the_Swag_Labs_login_page() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
    }

    @When("I enter the username {string}")
    public void I_enter_the_username(String username) {
        driver.findElement(LoginPageLocators.usernameField).sendKeys(username);
    }

    @And("I enter the password {string}")
    public void I_enter_the_password(String password) {
        driver.findElement(LoginPageLocators.passwordField).sendKeys(password);
    }

    @And("I click on the login button")
    public void I_click_on_the_login_button() {
        driver.findElement(LoginPageLocators.loginButton).click();
    }

    @Then("I should be taken to the products page")
    public void I_should_be_taken_to_the_products_page() {
        String url = driver.getCurrentUrl();

        assertEquals(url, HomePageLocators.INVENTORY_PAGE_URL);
        assertEquals(null != driver.findElement(HomePageLocators.inventoryList).findElements(HomePageLocators.inventoryItem) , true);
    }

    @Then("I should see an error message {string}")
    public void I_should_see_an_error_message(String errorMessage) {
        String actualErrorMessage = driver.findElement(LoginPageLocators.errorMessage).getText();

        assertEquals(errorMessage, actualErrorMessage);
    }

    @And("^(Username|Password|Username and Password) field(s)? should be highlighted in red$")
    public void fields_should_be_highlighted_in_red(String emptyField, String plural) {
        switch(emptyField) {
            case "Username":
                assertTrue(driver.findElement(LoginPageLocators.errorUsernameField).isDisplayed());
                break;
            case "Password":
                assertTrue(driver.findElement(LoginPageLocators.errorPasswordField).isDisplayed());
                break;
            case "Username and Password":
                assertTrue(driver.findElement(LoginPageLocators.usernameField).isDisplayed());
                assertTrue(driver.findElement(LoginPageLocators.passwordField).isDisplayed());
                break;
        }
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}