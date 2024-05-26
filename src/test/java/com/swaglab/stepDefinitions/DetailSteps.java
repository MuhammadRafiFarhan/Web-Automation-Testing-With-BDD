package com.swaglab.stepDefinitions;

import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.utils.WebDriverSetup;
import com.swaglab.locators.DashboardPageLocators;
import com.swaglab.locators.LoginPageLocators;
import com.swaglab.locators.CartPageLocators;
import com.swaglab.locators.DetailPageLocators;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class DetailSteps {
    private static WebDriver driver;
    private static DashboardPageLocators dashboardPageLocators;
    private static LoginPageLocators loginPageLocators;
    private static CartPageLocators cartPageLocators;
    private static DetailPageLocators detailPageLocators;

    @Before
    public void initializeDriver() {
        if (driver == null) {
            driver = WebDriverSetup.getDriver();

            loginPageLocators = new LoginPageLocators(driver);
            dashboardPageLocators = new DashboardPageLocators(driver);
            cartPageLocators = new CartPageLocators(driver);
            detailPageLocators = new DetailPageLocators(driver);

            PageFactory.initElements(driver, loginPageLocators);
            PageFactory.initElements(driver, dashboardPageLocators);
            PageFactory.initElements(driver, cartPageLocators);
            PageFactory.initElements(driver, detailPageLocators);
        }
    }

    @Given("I am currently logged in into correct account")
    public void iAmCurrentlyLoggedInIntoCorrectAccount() {
        driver.get(LoginPageLocators.LOGIN_PAGE_URL);
        loginPageLocators.log_in("standard_user", "secret_sauce");
    }

    @Given("I am on the Inventory page")
    public void iAmOnTheInventoryPage() {
        driver.get(DashboardPageLocators.INVENTORY_PAGE_URL);
    }

    @When("I hover over the image or title of the product {string} in the product catalog")
    public void iHoverOverTheImageOrTitleOfTheProductInTheProductCatalog(String productName) {
        // Hover over the product image or title with idx 0
        List<WebElement> inventoryItems = dashboardPageLocators.getInventoryItems();
        if (!inventoryItems.isEmpty()) {
            WebElement firstItem = inventoryItems.get(3);
            // Determine whether to hover over the item's name or image based on the product name
            if (productName.equals(firstItem.getText())) {
                Actions actions = new Actions(driver);
                actions.moveToElement(firstItem).perform();
            } else {
                // Assuming the product name is associated with the image, you may need to adjust this logic based on your specific HTML structure
                WebElement imageElement = dashboardPageLocators.getInventoryItemImgs().get(3);
                Actions actions = new Actions(driver);
                actions.moveToElement(imageElement).perform();
            }
        } else {
            // Handle the case when the inventory items list is empty
            System.out.println("No inventory items found on the page.");
        }
    }
    

    @When("I click once on the image or title of the product {string} in the catalog on the Inventory page")
    public void iClickOnceOnTheImageOrTitleOfTheProductInTheCatalogOnTheInventoryPage(String productName) {
        // Click once over the product image or title with idx 0
        List<WebElement> inventoryItems = dashboardPageLocators.getInventoryItems();
        if (!inventoryItems.isEmpty()) {
            WebElement firstItem = inventoryItems.get(3);
            // Determine whether to click the item's name or image based on the product name
            if (productName.equals(firstItem.getText())) {
                dashboardPageLocators.clickItemNames(3);
            } else {
                dashboardPageLocators.clickItemImgs(3);
            }
        } else {
            // Handle the case when the inventory items list is empty
            System.out.println("No inventory items found on the page.");
        }
    }
    

    @Then("I should be on the product detail page for {string}")
    public void iShouldBeOnTheProductDetailPageFor(String productName) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(DetailPageLocators.DETAIL_PAGE_URL, actualUrl);
    }

    @Then("the product image {string} should exist")
    public void theProductImageShouldExist(String productName) {
        WebElement productElement = detailPageLocators.getInventoryItemImgs();
        assertTrue(productElement.isDisplayed());
    }

    @Then("the product name {string} should exist")
    public void theProductNameShouldExist(String productName) {
        WebElement productElement = detailPageLocators.getInventoryItemNames();
        String actualProductName = productElement.getText();
        assertEquals(productName, actualProductName);
    }

    @Then("the product description for {string} should exist")
    public void theProductDescriptionForShouldExist(String productName) {
        WebElement productElement = detailPageLocators.getInventoryItemDescs();
        assertTrue(productElement.isDisplayed());
    }

    @Then("the content of the product description {string} is: {string}")
    public void theContentOfTheProductDescriptionIs(String productName, String description) {
        WebElement productElement = detailPageLocators.getInventoryItemDescs();
        String actualDescription = productElement.getText();
        assertEquals(description, actualDescription);
    }

    @AfterAll
    public static void closeBrowser() {
        System.out.println("Closing the browser after all detail scenarios are executed.");
        if (driver != null) {
            driver.quit();
        }
    }
}
