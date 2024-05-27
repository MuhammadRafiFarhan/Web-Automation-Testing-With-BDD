package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;
import com.swaglab.locators.CartPageLocators;
import com.swaglab.locators.DetailPageLocators;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class DetailSteps {
    private static WebDriver driver;
    private static DashboardPageLocators dashboardPageLocators;
    private static LoginPageLocators loginPageLocators;
    private static CartPageLocators cartPageLocators;
    private static DetailPageLocators detailPageLocators;

    private Actions actions;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();

            loginPageLocators = new LoginPageLocators(driver);

            dashboardPageLocators = new DashboardPageLocators(driver);

            cartPageLocators = new CartPageLocators(driver);

            detailPageLocators = new DetailPageLocators(driver);
        }

        actions = new Actions(driver);
    }

    @Given("I was currently logged in into correct account")
    public void iWasCurrentlyLoggedInIntoCorrectAccount() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @Given("I was on the Inventory page")
    public void iWasOnTheInventoryPage() {
        driver.get(DashboardPageLocators.INVENTORY_PAGE_URL);
    }    

    @Given("I have reset Swaglabs app state to its default state")
    public void iHaveResetSwaglabsAppStateToItsDefaultState() {
        dashboardPageLocators.clickBurgerMenu();
        dashboardPageLocators.clickResetButtonLink();
        dashboardPageLocators.clickCloseBurgerButton();
    }

    @Given("I clicked once on the name of the product {string} in the catalog on the Inventory page")
    public void iClickOnceOnTheNameOfTheProductInTheCatalogOnTheInventoryPage(String productName) {
        dashboardPageLocators.getInventoryItemNames().forEach(item -> {
            if (item.getText().equals(productName)) {
                actions.moveToElement(item).click();
            }
        });
    }

    @Given("Product {string} {string} added to the cart")
    public void productIsNotAddedToTheCart(String productName, String condition) {
        if (condition.equals("is not")) {
            boolean[] isProductInCart = {false};

            cartPageLocators.getInventoryItemNames().forEach(item -> {
                if (item.getText().equals(productName)) {
                    isProductInCart[0] = true;
                } 
            });
        
            assertEquals(false, isProductInCart[0], "Product has been in the cart.");
        } else if (condition.equals("has been")) {
            dashboardPageLocators.getInventoryItems().forEach(item -> {
                if (item.findElement(By.className("inventory_item_name")).getText().equals(productName)) {
                    item.findElement(By.className("btn_primary")).click();
                }
            });
        }
    }

    @When("I am on the product detail page for {string}")
    public void iAmOnTheProductDetailPageFor(String productName) {        
        switch (productName) {
            case "Sauce Labs Bike Light":
                driver.get(DetailPageLocators.DETAIL_PAGE_URL_ID_0);
                break;
        }
    }
    
    @When("I click the {string} button on the product detail page")
    public void iClickTheButtonOnTheProductDetailPage(String buttonName) {
        switch (buttonName) {
            case "Add to cart":
                detailPageLocators.clickAddToCartButton();
                break;
            case "Back to products":
                detailPageLocators.clickBackToProductsButton();
                break;
        }
    }


    @Then("the product image for {string} should exist")
    public void theProductImageForShouldExist(String productName) {
        WebElement productElement = detailPageLocators.getInventoryItemImgs();
        
        assertTrue(productElement.isDisplayed());
    }

    @Then("the product name for {string} should exist")
    public void theProductNameShouldExist(String productName) {
        WebElement productElement = detailPageLocators.getInventoryItemNames();
        String actualProductName = productElement.getText();
        
        assertEquals(productName, actualProductName);
    }

    @Then("the product description for {string} should exist")
    public void theProductDescriptionForShouldExist(String productName) {
        WebElement productDescElement = detailPageLocators.getInventoryItemDescs();
        
        assertTrue(productDescElement.isDisplayed());
    }

    @Then("the content of the product description {string} should be {string}")
    public void theContentOfTheProductDescriptionShouldBe(String productName, String description) {
        WebElement productElement = detailPageLocators.getInventoryItemDescs();
        String actualDescription = productElement.getText();

        assertEquals(description, actualDescription);
    }

    @Then("the product price for {string} should be {string}")
    public void theProductPriceForShouldBe(String productName, String price) {
        WebElement productPriceElement = detailPageLocators.getInventoryItemPrices();
        String actualPrice = productPriceElement.getText();

        assertEquals(price, actualPrice);
    }

    @Then("the {string} button should exist")
    public void theButtonShouldExist(String buttonName) {
        WebElement buttonElement = null;

        switch (buttonName) {
            case "Add to cart":
                buttonElement = detailPageLocators.getAddToCartButtons();
                break;
            case "Remove":
                buttonElement = detailPageLocators.getRemoveFromCartButtons();
                break;
            case "Back to products":
                buttonElement = detailPageLocators.getBackToProductsButton();
                break;
        }

        assertTrue(buttonElement.isDisplayed());
    }

    @Then("^the encircled number of item should( not)? exist on top of the cart icon$")
    public void theEncircledNumberOfItemShouldExistOnTopOfTheCartIcon(String condition) {
        if (condition != null) {
            assertFalse(detailPageLocators.isShoppingCartBadgeDisplayed());
        } else {
            assertTrue(detailPageLocators.isShoppingCartBadgeDisplayed());
        }
    }

    @AfterAll
    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
