package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.utils.WebDriverSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import com.swaglab.locators.HomePageLocators;
import com.swaglab.locators.LoginPageLocators;

public class LogoutSteps {
    private static WebDriver driver;
    private static HomePageLocators homepageLocator;
    private static LoginPageLocators loginPageLocators;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();
            
            loginPageLocators = new LoginPageLocators();
            PageFactory.initElements(driver, loginPageLocators);
            
            homepageLocator = new HomePageLocators(driver);
            PageFactory.initElements(driver, homepageLocator);
        }
    }

    @Given("I am currently logged in")
    public void I_am_currently_logged_in() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @When("I click on the menu button")
    public void I_click_on_the_menu_button() {
        homepageLocator.clickBurgerMenu();
    }

    @And("I click on the logout button")
    public void I_click_on_the_logout_button() {
        homepageLocator.clickLogoutMenu();
    }

    @Then("I should be redirected to the login page")
    public void I_should_be_redirected_to_the_login_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // page is displayed
        assertTrue(loginPageLocators.getLoginContainer().isDisplayed());
    }

    @AfterAll
    public static void closeBrowser() {
        System.out.println("Closing the browser after all logout scenarios are executed.");
        driver.quit();
    }
}
