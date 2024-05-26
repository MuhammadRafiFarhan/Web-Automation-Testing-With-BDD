package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;

import com.swaglab.utils.WebDriverSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;

public class LoginSteps {
    private static WebDriver driver;
    private static DashboardPageLocators dashboardPageLocator;
    private static LoginPageLocators loginPageLocators;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();

            loginPageLocators = new LoginPageLocators(driver);

            dashboardPageLocator = new DashboardPageLocators(driver);
        }
    }

    @Given("I have opened the browser")
    public void I_have_opened_the_browser() {
    }

    @When("I am on the Swag Labs login page")
    public void I_am_on_the_Swag_Labs_login_page() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
    }

    @When("I enter the username {string}")
    public void I_enter_the_username(String username) {
        loginPageLocators.enterUsername(username);
    }

    @When("I enter the password {string}")
    public void I_enter_the_password(String password) {
        loginPageLocators.enterPassword(password);
    }

    @When("I click on the login button")
    public void I_click_on_the_login_button() {
        loginPageLocators.clickLoginButton();
    }

    @Then("username box should exist")
    public void username_box_should_exist() {
        assertTrue(loginPageLocators.getUsernameField().isDisplayed());
    }

    @Then("password box should exist")
    public void password_box_should_exist() {
        assertTrue(loginPageLocators.getPasswordField().isDisplayed());
    }

    @Then("login button should exist")
    public void login_button_should_exist() {
        assertTrue(loginPageLocators.getLoginButton().isDisplayed());
    }

    @Then("I should be taken to the products page")
    public void I_should_be_taken_to_the_products_page() {
        String url = driver.getCurrentUrl();

        assertEquals(url, DashboardPageLocators.INVENTORY_PAGE_URL);
        assertTrue(dashboardPageLocator.getInventoryList().isDisplayed());
        assertEquals(DashboardPageLocators.INVENTORY_ITEMS_SIZE, dashboardPageLocator.getInventoryItems().size());
    }

    @Then("I should see an error message {string}")
    public void I_should_see_an_error_message(String errorMessage) {
        String actualErrorMessage = loginPageLocators.getErrorMessage().getText();

        assertEquals(errorMessage, actualErrorMessage);
    }

    @Then("^(Username|Password|Username and Password) field(s)? should be highlighted in red$")
    public void fields_should_be_highlighted_in_red(String emptyField, String plural) {
        switch (emptyField) {
            case "Username":
                assertTrue(loginPageLocators.getErrorUsernameField().isDisplayed());
                break;
            case "Password":
                assertTrue(loginPageLocators.getErrorPasswordField().isDisplayed());
                break;
            case "Username and Password":
                assertTrue(loginPageLocators.getErrorUsernameField().isDisplayed());
                assertTrue(loginPageLocators.getErrorPasswordField().isDisplayed());
                break;
        }
    }

    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }
}