package com.swaglab.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Description:  DetailPageLocators file will only contain UI elements or Objects
 *               present on Detail screen along with the operations to be performed
 *               on these elements.
 */
public class DetailPageLocators {
    private WebDriver driver;
    private WebDriverWait wait;

    // ISSUE #1: Tetapkan URL detail page produk mana yang akan diuji
    public static final String DETAIL_PAGE_URL_ID_0 = "https://www.saucedemo.com/inventory-item.html?id=0";

    // Detail List
    @FindBy(className = "inventory_details_name")
    private WebElement inventoryItemNames;

    @FindBy(className = "inventory_details_desc")
    private WebElement inventoryItemDescs;

    @FindBy(className = "inventory_details_price")
    private WebElement inventoryItemPrices;

    @FindBy(className = "inventory_details_img")
    private WebElement inventoryItemImgs;

    @FindBy(id = "add-to-cart")
    private WebElement addToCartButtons;

    @FindBy(id = "remove")
    private WebElement removeFromCartButtons;

    @FindBy(id = "back-to-products")
    private WebElement backToProductsButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartIcon;

    // Constructor
    public DetailPageLocators(WebDriver driver) {
        this.driver = driver;
        
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    // Methods to interact with elements

    public void clickAddToCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons)).click();
    }

    public void clickRemoveFromCartButton() {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButtons)).click();
    }

    public void clickBackToProductsButton() {
        wait.until(ExpectedConditions.elementToBeClickable(backToProductsButton)).click();
    }

    // Getters for elements (if needed)
    public WebElement getInventoryItemNames() {
        return inventoryItemNames;
    }

    public WebElement getInventoryItemDescs() {
        return inventoryItemDescs;
    }

    public WebElement getInventoryItemPrices() {
        return inventoryItemPrices;
    }

    public WebElement getInventoryItemImgs() {
        return inventoryItemImgs;
    }

    public WebElement getAddToCartButtons() {
        return addToCartButtons;
    }

    public WebElement getRemoveFromCartButtons() {
        return removeFromCartButtons;
    }

    public WebElement getBackToProductsButton() {
        return backToProductsButton;
    }

    public boolean isShoppingCartBadgeDisplayed() {
        List<WebElement> cartBadge = wait.until(ExpectedConditions.visibilityOf(shoppingCartIcon)).findElements(By.className("shopping_cart_badge"));
        
        return cartBadge.size() > 0;
    }
}
