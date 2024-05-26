package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;

import com.swaglab.utils.WebDriverSetup;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;

public class LogoutSteps {
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

    @Given("I am currently logged in")
    public void I_am_currently_logged_in() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @When("I click on the menu button")
    public void I_click_on_the_menu_button() {
        dashboardPageLocator.clickBurgerMenu();
    }

    @And("I click on the logout button")
    public void I_click_on_the_logout_button() {
        dashboardPageLocator.clickLogoutButtonLink();
    }

    @Then("I should be redirected to the login page")
    public void I_should_be_redirected_to_the_login_page() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // page is displayed
        assertTrue(loginPageLocators.getLoginContainer().isDisplayed());
    }

    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }
}
