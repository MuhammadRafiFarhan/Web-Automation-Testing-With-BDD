package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.WebDriver;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.CartPageLocators;
import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;
import com.swaglab.locators.CheckoutPageLocators;

public class EndtoEndSteps {
    private static WebDriver driver;
    private static LoginPageLocators loginPageLocators;
    private static DashboardPageLocators dashboardPageLocators;
    private static CartPageLocators cartPageLocators;
    private static CheckoutPageLocators checkoutPageLocators;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();
            loginPageLocators = new LoginPageLocators(driver);
            dashboardPageLocators = new DashboardPageLocators(driver);
            cartPageLocators = new CartPageLocators(driver);
            checkoutPageLocators = new CheckoutPageLocators(driver);
        }
    }

    @Given("User is currently logged in to the site")
    public void user_is_currently_logged_in_to_the_site() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @Then("User have been should be redirected to the products page")
    public void user_should_have_been_redirected_to_the_products_page() {
        assertEquals(DashboardPageLocators.INVENTORY_PAGE_URL, driver.getCurrentUrl());
    }

    @When("User selects a product from the catalog and clicks the {string} button")
    public void user_selects_a_product_from_the_catalog_and_clicks_the_button(String buttonName) {
        dashboardPageLocators.clickAddToCartButton(0);
        dashboardPageLocators.clickAddToCartButton(1);
    }

    @Then("User have been redirected to the checkout page")
    public void user_have_been_redirected_to_the_checkout_your_information_page() {
        dashboardPageLocators.clickShoppingCartLink();
        cartPageLocators.clickCheckoutButton();
    }

    @When("User fill in the {string} field with {string}")
    public void user_fill_in_the_field_with(String field, String value) {
        switch (field) {
            case "first name":
                checkoutPageLocators.enterFirstName(value);
                break;
            case "last name":
                checkoutPageLocators.enterLastName(value);
                break;
            case "postal code":
                checkoutPageLocators.enterPostalCode(value);
                break;
        }
    }

    @When("User click the {string} button in the overview page")
    public void user_click_the_button_in_the_overview_page(String buttonType) {
        switch (buttonType) {
            case "finish":
                checkoutPageLocators.clickFinishButton();
                break;
        }
    }

    @When("User click the {string} button to checkout")
    public void user_click_the_button_to_checkout(String buttonType) {
        switch (buttonType) {
            case "continue":
                checkoutPageLocators.clickContinueButton();
                break;
        }
    }

    @Then("User should be redirected to the checkout complete page")
    public void user_should_be_redirected_to_the_checkout_complete_page() {
        assertEquals("Checkout: Complete!", checkoutPageLocators.getCheckoutStepHeader().getText());
        assertTrue(driver.getCurrentUrl().contains(CheckoutPageLocators.CHECKOUT_COMPLETE_PAGE_URL));
    }

    @Then("User should see the {string} message {string}")
    public void user_should_see_the_message(String messagePart, String message) {
        switch (messagePart) {
            case "header":
                assertEquals(message, checkoutPageLocators.getThankingTextElement().getText());
                break;
            case "text":
                assertEquals(message, checkoutPageLocators.getOrderCompleteTextElement().getText());
                break;
            case "error":
                assertEquals(message, checkoutPageLocators.getErrorMessage().getText());
                break;
        }
    }

    @Then("there should be Back Home button below the purchase message")
    public void there_should_be_back_home_button_below_the_purchase_message() {
        assertTrue(checkoutPageLocators.getBackToProductsButton().isDisplayed());
    }

    @Then("shopping cart badge should be removed afterward")
    public void shopping_cart_badge_should_be_removed_afterward() {
        assertFalse(checkoutPageLocators.isShoppingCartBadgeDisplayed());
    }

    @AfterAll
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
