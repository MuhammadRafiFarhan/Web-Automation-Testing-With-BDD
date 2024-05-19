package com.swaglab.locators;

import org.openqa.selenium.By;

public class HomePageLocators {
    public static final String INVENTORY_PAGE_URL = "https://www.saucedemo.com/inventory.html";
    
    public static final By inventoryList = By.className("inventory_list");
    public static final By inventoryItem = By.className("inventory_item");

    public static final By burgerMenu = By.id("react-burger-menu-btn");
    public static final By logoutButtonLink = By.id("logout_sidebar_link");
}
