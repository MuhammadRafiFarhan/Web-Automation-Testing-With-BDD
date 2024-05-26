package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;
import com.swaglab.locators.CartPageLocators;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

public class CartSteps {
    private static WebDriver driver;
    private static DashboardPageLocators dashboardPageLocator;
    private static LoginPageLocators loginPageLocators;
    private static CartPageLocators cartPageLocators;

    private Actions actions;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();
            
            loginPageLocators = new LoginPageLocators(driver);
            
            dashboardPageLocator = new DashboardPageLocators(driver);
            
            cartPageLocators = new CartPageLocators(driver);
        }

        actions = new Actions(driver);
    }

    @Given("I am currently logged in with authentic account to use cart feature")
    public void i_am_currently_logged_in_with_authentic_account_to_use_cart_feature() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @Given("I have added items to the cart to use cart feature")
    public void i_have_added_items_to_the_cart_to_use_cart_feature() {
        dashboardPageLocator.clickAddToCartButton(0);
        dashboardPageLocator.clickAddToCartButton(1);
    }
    
    @Given("my cart is empty")
    public void my_cart_is_empty() {
        List<WebElement> removeButtons = cartPageLocators.getRemoveFromCartButtons();
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    @Given("I am on the cart page")
    public void i_am_on_the_cart_page() {
        // driver.get(CartPageLocators.CART_PAGE_URL);
        dashboardPageLocator.clickShoppingCartLink();
    }
    
    @When("I hover over the cart icon in the top right corner")
    public void i_hover_over_the_cart_icon_in_the_top_right_corner() {
        actions.moveToElement(dashboardPageLocator.getShoppingCartLink()).perform();
    }

    @When("I click on the cart icon with the item count notification")
    public void i_click_on_the_cart_icon_with_the_item_count_notification() {
        dashboardPageLocator.clickShoppingCartLink();
    }

    @When("I hover over the {string} button")
    public void i_hover_over_the_button(String button) {
        WebElement element = null;

        switch (button) {
            case "Continue Shopping":
                element = cartPageLocators.getContinueShoppingButton();
                break;
            case "Checkout":
                element = cartPageLocators.getCheckoutButton();
                break;
        }
        
        actions.moveToElement(element).perform();
    }

    @When("I click the {string} button within cart feature")
    public void i_click_the_button_within_cart_feature(String button) {
        if (button.equals("Continue Shopping")) {
            cartPageLocators.clickContinueShoppingButton();
        } else if (button.equals("Checkout")) {
            cartPageLocators.clickCheckoutButton();
        }
    }

    @Then("I should be redirected to the cart page")
    public void i_should_be_redirected_to_the_cart_page() {
        assertTrue(driver.getCurrentUrl().contains(CartPageLocators.CART_PAGE_URL));
    }
    
    @Then("I should see item cards equal to the number of items in the cart notification")
    public void i_should_see_item_cards_equal_to_the_number_of_items_in_the_cart_notification() {
        int expectedItems = Integer.parseInt(dashboardPageLocator.getShoppingCartBadge().getText());
        List<WebElement> cartItems = cartPageLocators.getInventoryItemNames();
        assertEquals(expectedItems, cartItems.size());
    }

    @Then("each item card should display the quantity, item name, description, and price")
    public void each_item_card_should_display_the_quantity_item_name_description_and_price() {
        List<WebElement> cartItems = cartPageLocators.getInventoryItemNames();
        assertTrue(cartItems.stream().allMatch(item -> item.isDisplayed()));
    }

    @Then("there should be {string} and {string} buttons below the list of items")
    public void there_should_be_and_buttons_below_the_list_of_items(String continueShopping, String checkout) {
        assertTrue(cartPageLocators.getContinueShoppingButton().isDisplayed());
        assertTrue(cartPageLocators.getCheckoutButton().isDisplayed());
    }
    


    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        assertTrue(driver.getCurrentUrl().contains(DashboardPageLocators.INVENTORY_PAGE_URL));
    }

    @Then("I should see the inventory page displaying a catalog of six items")
    public void i_should_see_the_inventory_page_displaying_a_catalog_of_six_items() {
        List<WebElement> inventoryItems = dashboardPageLocator.getInventoryItems();
        assertEquals(DashboardPageLocators.INVENTORY_ITEMS_SIZE, inventoryItems.size());
    }

    @Then("I should remain on the cart page titled {string}")
    public void i_should_remain_on_the_cart_page_titled(String title) {
        assertTrue(driver.getCurrentUrl().contains(CartPageLocators.CART_PAGE_URL), "You are not on the cart page anymore");
        assertEquals(title, cartPageLocators.getPageTitle().getText());
    }

    @Then("a red error message box with white text should appear saying {string}")
    public void a_red_error_message_box_with_white_text_should_appear_saying(String errorMessage) {
        try {
            WebElement errorElement = cartPageLocators.getErrorMessage();
            assertTrue(errorElement.isDisplayed());
            assertEquals(errorMessage, errorElement.getText());
        } catch (AssertionError assertionError) {
            fail("Expected error message not found: " + errorMessage);
        }
    }

    @AfterAll
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
