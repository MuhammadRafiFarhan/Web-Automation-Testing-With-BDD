package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.HomePageLocators;
import com.swaglab.locators.LoginPageLocators;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;


public class DashboardSteps {
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

    @Given("User currently logged in")
    public void user_currently_logged_in() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @When("I open Swaglabs inventory page")
    public void i_open_swaglabs_inventory_page() {
        driver.get(HomePageLocators.INVENTORY_PAGE_URL);
    }

    @Then("inventory list should exist")
    public void inventory_list_should_exist() {
        assertTrue(homepageLocator.getInventoryList().isDisplayed());
    }

    @Then("add to cart button should exist")
    public void add_to_cart_button_should_exist() {
        assertFalse(homepageLocator.getAddToCartButtons().isEmpty());
    }

    @Then("cart link should exist")
    public void cart_link_should_exist() {
        assertTrue(homepageLocator.getShoppingCartLink().isDisplayed());
    }

    @Then("burger menu should exist")
    public void burger_menu_should_exist() {
        assertTrue(homepageLocator.getBurgerMenu().isDisplayed());
    }

    @Then("product sort should exist")
    public void product_sort_should_exist() {
        assertTrue(homepageLocator.getProductSort().isDisplayed());
    }

    @When("User clicks the Add to Cart button on the inventory page")
    public void user_clicks_the_add_to_cart_button_on_the_inventory_page() {
        homepageLocator.clickAddToCartButton(0);
    }

    @Then("User should see the shopping cart badge")
    public void user_should_see_the_shopping_cart_badge() {
        assertTrue(homepageLocator.isShoppingCartBadgeDisplayed());
    }

    @When("User clicks another Add to Cart button on another item in the inventory page")
    public void user_clicks_another_add_to_cart_button_on_another_item_in_the_inventory_page() {
        homepageLocator.clickAddToCartButton(1);
    }

    @Then("User should see another added product in the shopping cart badge")
    public void user_should_see_another_added_product_in_the_shopping_cart_badge() {
        assertTrue(homepageLocator.isShoppingCartBadgeDisplayed());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(homepageLocator.getShoppingCartBadge()));

        String badgeText = homepageLocator.getShoppingCartBadge().getText();
        assertEquals("2", badgeText); // Check if the badge displays '2' items
    }

    @AfterAll
    public static void closeBrowser() {
        System.out.println("Closing the browser after all dashboard scenarios are executed.");
        if (driver != null) {
            driver.quit();
        }
    }
}
