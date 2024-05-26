package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SidebarMenuSteps {
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

    //TC1
    @Given("User is currently logged in")
    public void user_is_currently_logged_in() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @And("User is on the Dashboard page")
    public void i_open_swaglabs_inventory_page() {
        driver.get(dashboardPageLocator.INVENTORY_PAGE_URL);
    }

    @When("User clicks the Sidebar button in the top left corner with a hamburger icon")
    public void inventory_list_should_exist() {
        dashboardPageLocator.clickBurgerMenu();
    }

    @Then("Sidebar menu should appear")
    public void sidebar_menu_should_exist() {
        assertTrue(dashboardPageLocator.getBurgerMenu().isDisplayed());
    }

    //TC2
    @And("Sidebar menu is open")
    public void sidebar_menu_is_open() {
        dashboardPageLocator.clickBurgerMenu();
    }

    @When("User clicks About")
    public void user_clicks_about() {
        dashboardPageLocator.clickAboutButtonLink();
    }

    @Then("User should be redirected to the corresponding page")
    public void user_redirected_to_saucelabs_website() {
        assertTrue(driver.getCurrentUrl().contains("https://saucelabs.com/"));
    }

    //TC3
    @When("User clicks the cross button on the sidebar")
    public void user_clicks_cross_button() {
        dashboardPageLocator.clickCloseBurgerMenu();
    }

    @Then("Sidebar menu should be closed")
    public void sidebar_menu_should_close() {
        assertFalse(dashboardPageLocator.getBurgerMenu().isDisplayed());
    }


    @AfterAll
    public static void closeBrowser() {
        System.out.println("Closing the browser after all dashboard scenarios are executed.");
        if (driver != null) {
            driver.quit();
        }
    }
}
