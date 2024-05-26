package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
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


public class CheckoutSteps {
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

    @Given("I am currently logged in with authentic account to use checkout feature")
    public void i_am_currently_logged_in_with_authentic_account_to_use_checkout_feature() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @Given("I have added items to the cart to use checkout feature")
    public void i_have_added_items_to_the_cart_to_use_checkout_feature() {
        dashboardPageLocators.clickAddToCartButton(0);
        dashboardPageLocators.clickAddToCartButton(1);
    }

    @Given("I have been redirected to the checkout your information page")
    public void i_have_been_redirected_to_the_checkout_your_information_page() {
        dashboardPageLocators.clickShoppingCartLink();
        cartPageLocators.clickCheckoutButton();
    }

    @When("I fill in the {string} field with {string}")
    public void i_fill_in_the_field_with(String field, String value) {
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

    @When("I click the {string} button in the overview page")
    public void i_click_the_button_in_the_overview_page(String buttonType) {
        switch (buttonType) {
            case "finish":
                checkoutPageLocators.clickFinishButton();
                break;
        }
    }

    @When("I click the {string} button to checkout")
    public void i_click_the_button_to_checkout(String buttonType) {
        switch (buttonType) {
            case "continue":
                checkoutPageLocators.clickContinueButton();
                break;
        }
    }

    @Then("I should be redirected to the checkout complete page")
    public void i_should_be_redirected_to_the_checkout_complete_page() {
        assertEquals("Checkout: Complete!", checkoutPageLocators.getCheckoutStepHeader().getText());
        assertTrue(driver.getCurrentUrl().contains(CheckoutPageLocators.CHECKOUT_COMPLETE_PAGE_URL));
    }

    @Then("I should see the {string} message {string}")
    public void i_should_see_the_message(String messagePart, String message) {
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

    @Then("there should be Back Home button below the message")
    public void there_should_be_back_home_button_below_the_message() {
        assertTrue(checkoutPageLocators.getBackToProductsButton().isDisplayed());
    }

    @Then("shopping cart badge should be removed")
    public void shopping_cart_badge_should_be_removed() {
        assertFalse(checkoutPageLocators.isShoppingCartBadgeDisplayed());
    }

    @Then("I should still be on the checkout your information page")
    public void i_should_still_be_on_the_checkout_your_information_page() {
        assertEquals("Checkout: Your Information", checkoutPageLocators.getCheckoutStepHeader().getText());
        assertTrue(driver.getCurrentUrl().contains(CheckoutPageLocators.CHECKOUT_YOUR_INFORMATION_PAGE_URL));
    }

    @Then("{string} field should be highlighted in red")
    public void field_should_be_highlighted_in_red(String field) {
        switch (field) {
            case "Zip/Postal Code":
                assertTrue(checkoutPageLocators.getErrorPostalCodeField().isDisplayed());
                break;
            case "First Name":
                assertTrue(checkoutPageLocators.getErrorFirstNameField().isDisplayed());
                break;
            case "Last Name":
                assertTrue(checkoutPageLocators.getErrorLastNameField().isDisplayed());
                break;
        }
    }

    @AfterAll
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
