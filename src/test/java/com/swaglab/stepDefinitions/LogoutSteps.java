package com.swaglab.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.swaglab.utils.WebDriverSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import com.swaglab.locators.HomePageLocators;
import com.swaglab.locators.LoginPageLocators;

public class LogoutSteps {
    private WebDriverSetup setup;
    private WebDriver driver;

    @Before
    public void initializeDriver() {
        setup = new WebDriverSetup();
        driver = setup.getDriver();
    }

    @Given("I am currently logged in")
    public void I_am_currently_logged_in() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        driver.findElement(LoginPageLocators.usernameField).sendKeys("standard_user");
        driver.findElement(LoginPageLocators.passwordField).sendKeys("secret_sauce");
        driver.findElement(LoginPageLocators.loginButton).click();
    }

    @When("I click on the menu button")
    public void I_click_on_the_menu_button() {
        driver.findElement(HomePageLocators.burgerMenu).click();
        // wait for the menu to be displayed
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    @And("I click on the logout button")
    public void I_click_on_the_logout_button() {
        driver.findElement(HomePageLocators.logoutButtonLink).click();
    }

    @Then("I should be redirected to the login page")
    public void I_should_be_redirected_to_the_login_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        final WebElement loginContainer = driver.findElement(LoginPageLocators.loginContainer);

        // page is displayed
        assertTrue(loginContainer.isDisplayed());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
