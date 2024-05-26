package com.swaglab.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Description:  DetailPageLocators file will only contain UI elements or Objects
 *               present on Detail screen along with the operations to be performed
 *               on these elements.
 */
public class DetailPageLocators {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String DETAIL_PAGE_URL = "https://www.saucedemo.com/inventory-item.html?id=0";

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

   // Burger Menu
   @FindBy(id = "react-burger-menu-btn")
   private WebElement burgerMenu;

   @FindBy(id = "inventory_sidebar_link")
   private WebElement inventoryButtonLink;

   @FindBy(id = "about_sidebar_link")
   private WebElement aboutButtonLink;

   @FindBy(id = "logout_sidebar_link")
   private WebElement logoutButtonLink;

   @FindBy(id = "reset_sidebar_link")
   private WebElement resetButtonLink;


    // Constructor
    public DetailPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
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

    public void clickBurgerMenu() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu)).click();
    }

    public void clickInventoryButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(inventoryButtonLink)).click();
    }

    public void clickAboutButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(aboutButtonLink)).click();
    }

    public void clickLogoutButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButtonLink)).click();
    }

    public void clickResetButtonLink() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButtonLink)).click();
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

    public WebElement getBurgerMenu() {
        return burgerMenu;
    }

    public WebElement getInventoryButtonLink() {
        return inventoryButtonLink;
    }

    public WebElement getAboutButtonLink() {
        return aboutButtonLink;
    }

    public WebElement getLogoutButtonLink() {
        return logoutButtonLink;
    }

    public WebElement getResetButtonLink() {
        return resetButtonLink;
    }
}
