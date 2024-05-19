package com.swaglab.locators;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Description:  HomePageLocators file will only contain UI elements or Objects
 *               present on Home Page/Dashboard screen along with the operations to be performed
 *               on these elements.
 */
public class HomePageLocators {
    WebDriver driver;

    public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    public static final int INVENTORY_ITEMS_SIZE = 6;
    
    @FindBy(className = "inventory_list")
    WebElement inventoryList;
    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;
    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenu;
    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButtonLink;

    // Constructor
    public HomePageLocators(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getInventoryList() {
        return inventoryList;
    }

    public List<WebElement> getInventoryItems() {
        return inventoryItems;
    }

    public void clickBurgerMenu() {
        burgerMenu.click();
        // wait for the menu to be displayed
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
    }

    public void clickLogoutMenu() {
        logoutButtonLink.click();
    }
}
