package com.swaglab.locators;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Description:  HomePageLocators file will only contain UI elements or Objects
 *               present on Home Page/Dashboard screen along with the operations to be performed
 *               on these elements.
 */
public class DashboardPageLocators {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public static final int INVENTORY_ITEMS_SIZE = 6;

    // Inventory List
    @FindBy(className = "inventory_list")
    private WebElement inventoryList;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    @FindBy(className = "product_sort_container")
    private WebElement productSort;

    @FindBy(className = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(className = "inventory_item_img")
    private List<WebElement> inventoryItemImgs;

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;

    @FindBy(className = "btn_secondary")
    private List<WebElement> removeFromCartButtons;

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
    public DashboardPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    // Methods to interact with elements
    public void clickItemNames(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(inventoryItemNames.get(index))).click();
    }

    public void clickItemImgs(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(inventoryItemImgs.get(index))).click();
    }

    public void clickAddToCartButton(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons.get(index))).click();
    }

    public void clickRemoveFromCartButton(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButtons.get(index))).click();
    }

    public void clickProductSort() {
        wait.until(ExpectedConditions.elementToBeClickable(productSort)).click();
    }

    public void clickShoppingCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink)).click();
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

    public void clickCloseBurgerButton() {
        wait.until(ExpectedConditions.elementToBeClickable(burgerMenu)).click();
    }

    public boolean isShoppingCartBadgeDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(shoppingCartBadge)).isDisplayed();
    }

    // Getters for elements (if needed)
    public WebElement getInventoryList() {
        return inventoryList;
    }

    public List<WebElement> getInventoryItems() {
        return inventoryItems;
    }

    public WebElement getProductSort() {
        return productSort;
    }

    public WebElement getShoppingCartLink() {
        return shoppingCartLink;
    }

    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }

    public List<WebElement> getInventoryItemNames() {
        return inventoryItemNames;
    }

    public List<WebElement> getInventoryItemImgs() {
        return inventoryItemImgs;
    }

    public List<WebElement> getAddToCartButtons() {
        return addToCartButtons;
    }

    public List<WebElement> getRemoveFromCartButtons() {
        return removeFromCartButtons;
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
