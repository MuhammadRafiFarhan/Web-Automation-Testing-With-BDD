package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.HomePageLocators;
import com.swaglab.locators.LoginPageLocators;
import com.swaglab.locators.CartPageLocators;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

public class CartSteps {
    private static WebDriver driver;
    private static HomePageLocators homepageLocator;
    private static LoginPageLocators loginPageLocators;
    private static CartPageLocators cartPageLocators;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();
            
            loginPageLocators = new LoginPageLocators();
            PageFactory.initElements(driver, loginPageLocators);
            
            homepageLocator = new HomePageLocators(driver);
            PageFactory.initElements(driver, homepageLocator);

            cartPageLocators = new CartPageLocators(driver);
            PageFactory.initElements(driver, cartPageLocators);
        }
    }

    @Given("I am currently logged in with authentic account")
    public void i_am_currently_logged_in_with_authentic_account() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @Given("I have added items to the cart")
    public void i_have_added_items_to_the_cart() {
        homepageLocator.clickAddToCartButton(0);
        homepageLocator.clickAddToCartButton(1);
    }

    @When("I hover over the cart icon in the top right corner")
    public void i_hover_over_the_cart_icon_in_the_top_right_corner() {
        Actions actions = new Actions(driver);
        actions.moveToElement(homepageLocator.getShoppingCartLink()).perform();
    }

    @When("I click on the cart icon with the item count notification")
    public void i_click_on_the_cart_icon_with_the_item_count_notification() {
        homepageLocator.clickShoppingCartLink();
    }

    @Then("I should be redirected to the cart page")
    public void i_should_be_redirected_to_the_cart_page() {
        assertTrue(driver.getCurrentUrl().contains(CartPageLocators.CART_PAGE_URL));
    }

    @Then("I should see item cards equal to the number of items in the cart notification")
    public void i_should_see_item_cards_equal_to_the_number_of_items_in_the_cart_notification() {
        int expectedItems = Integer.parseInt(homepageLocator.getShoppingCartBadge().getText());
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

    @Given("I am on the cart page")
    public void i_am_on_the_cart_page() {
        homepageLocator.clickShoppingCartLink();
        assertTrue(driver.getCurrentUrl().contains(CartPageLocators.CART_PAGE_URL));
    }

    @When("I hover over the {string} button")
    public void i_hover_over_the_button(String button) {
        WebElement element = button.equals("Continue Shopping") 
                ? cartPageLocators.getContinueShoppingButton() 
                : cartPageLocators.getCheckoutButton();
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    @When("I click the {string} button")
    public void i_click_the_button(String button) {
        if (button.equals("Continue Shopping")) {
            cartPageLocators.clickContinueShoppingButton();
        } else if (button.equals("Checkout")) {
            cartPageLocators.clickCheckoutButton();
        }
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        assertTrue(driver.getCurrentUrl().contains(HomePageLocators.INVENTORY_PAGE_URL));
    }

    @Then("I should see the inventory page displaying a catalog of six items")
    public void i_should_see_the_inventory_page_displaying_a_catalog_of_six_items() {
        List<WebElement> inventoryItems = homepageLocator.getInventoryItems();
        assertEquals(HomePageLocators.INVENTORY_ITEMS_SIZE, inventoryItems.size());
    }

    @Given("my cart is empty")
    public void my_cart_is_empty() {
        List<WebElement> removeButtons = homepageLocator.getRemoveFromCartButtons();
        for (WebElement button : removeButtons) {
            button.click();
        }
    }

    @Then("I should remain on the cart page titled {string}")
    public void i_should_remain_on_the_cart_page_titled(String title) {
        assertTrue(driver.getTitle().contains(title));
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
        System.out.println("Closing the browser after all cart scenarios are executed.");
        if (driver != null) {
            driver.quit();
        }
    }
}
