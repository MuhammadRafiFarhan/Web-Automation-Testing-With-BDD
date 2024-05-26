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
 * Description:  CartPageLocators file will only contain UI elements or Objects
 *               present on Cart screen along with the operations to be performed
 *               on these elements.
 */
public class CartPageLocators {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String CART_PAGE_URL = "https://www.saucedemo.com/cart.html";

    // Header page title
    @FindBy(css = ".header_secondary_container .title")
    private WebElement pageTitle;

    // Cart List
    @FindBy(className = "inventory_item_name")
    private List<WebElement> inventoryItemNames;

    @FindBy(className = "cart_button")
    private List<WebElement> removeFromCartButtons;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;  

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

    // Error Message
    @FindBy(css = ".error-message-container")
    private WebElement errorMessage;

    // Constructor
    public CartPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        PageFactory.initElements(this.driver, this);
    }

    // Methods to interact with elements
    public void clickRemoveFromCartButton(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(removeFromCartButtons.get(index))).click();
    }

    public void clickContinueShoppingButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }

    public void clickCheckoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
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

    public WebElement getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage));
    }

    // Getters for elements (if needed)
    public WebElement getPageTitle() {
        return wait.until(ExpectedConditions.visibilityOf(pageTitle));
    }

    public List<WebElement> getInventoryItemNames() {
        return inventoryItemNames;
    }

    public List<WebElement> getRemoveFromCartButtons() {
        return removeFromCartButtons;
    }

    public WebElement getContinueShoppingButton() {
        return wait.until(ExpectedConditions.visibilityOf(continueShoppingButton));
    }

    public WebElement getCheckoutButton() {
        return wait.until(ExpectedConditions.visibilityOf(checkoutButton));
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
