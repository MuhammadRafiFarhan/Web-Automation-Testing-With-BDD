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
 * Description:  CheckoutPageLocators file will contains UI elements or Objects
 *               present on Checkout screen along with the operations to be 
 *               performed on these elements.
 */
public class CheckoutPageLocators {
    private WebDriver driver;
    private WebDriverWait wait;

    public static final String CHECKOUT_YOUR_INFORMATION_PAGE_URL = "https://www.saucedemo.com/checkout-step-one.html";
    public static final String CHECKOUT_OVERVIEW_PAGE_URL = "https://www.saucedemo.com/checkout-step-two.html";
    public static final String CHECKOUT_COMPLETE_PAGE_URL = "https://www.saucedemo.com/checkout-complete.html";

    // HTML Elements of All Checkout-related Pages
    @FindBy(css = ".header_secondary_container .title")
    WebElement checkoutStepHeader;

    @FindBy(css = ".checkout_info .form_group #first-name")
    WebElement firstNameField;

    @FindBy(css = ".checkout_info .form_group #last-name")
    WebElement lastNameField;

    @FindBy(css = ".checkout_info .form_group #postal-code")
    WebElement postalCodeField;

    @FindBy(css = ".checkout_info .form_group #first-name.error")
    WebElement errorFirstNameField;

    @FindBy(css = ".checkout_info .form_group #last-name.error")
    WebElement errorLastNameField;

    @FindBy(css = ".checkout_info .form_group #postal-code.error")
    WebElement errorPostalCodeField;

    @FindBy(css = ".checkout_buttons #continue")
    WebElement continueButton;

    @FindBy(css = ".cart_footer #finish")
    WebElement finishButton;

    @FindBy(css = "#checkout_complete_container h2")
    WebElement thankingTextElement;

    @FindBy(css = "#checkout_complete_container .complete-text")
    WebElement orderCompleteTextElement;

    @FindBy(css = "#checkout_complete_container #back-to-products")
    WebElement backToProductsButton;

    @FindBy(css = "#shopping_cart_container .shopping_cart_link")
    WebElement shoppingCartIcon;

    @FindBy(css = "#shopping_cart_container .shopping_cart_link .shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(css = ".error-message-container.error h3")
    WebElement errorMessage;

    // Constructor
    public CheckoutPageLocators(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
        PageFactory.initElements(this.driver, this);
    }

    // Methods to interact with the elements
    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        wait.until(ExpectedConditions.visibilityOf(postalCodeField)).sendKeys(postalCode);
    }

    public void clickContinueButton() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickFinishButton() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isShoppingCartBadgeDisplayed() {
        List<WebElement> cartBadge = wait.until(ExpectedConditions.visibilityOf(shoppingCartIcon)).findElements(By.className("shopping_cart_badge"));
        
        return cartBadge.size() > 0;
    }

    // getters
    public WebElement getCheckoutStepHeader() {
        return checkoutStepHeader;
    }

    public WebElement getFirstNameField() {
        return firstNameField;
    }

    public WebElement getLastNameField() {
        return lastNameField;
    }

    public WebElement getPostalCodeField() {
        return postalCodeField;
    }

    public WebElement getErrorFirstNameField() {
        return errorFirstNameField;
    }

    public WebElement getErrorLastNameField() {
        return errorLastNameField;
    }

    public WebElement getErrorPostalCodeField() {
        return errorPostalCodeField;
    }

    public WebElement getContinueButton() {
        return continueButton;
    }

    public WebElement getFinishButton() {
        return finishButton;
    }

    public WebElement getThankingTextElement() {
        return wait.until(ExpectedConditions.visibilityOf(thankingTextElement));
    }

    public WebElement getOrderCompleteTextElement() {
        return wait.until(ExpectedConditions.visibilityOf(orderCompleteTextElement));
    }

    public WebElement getBackToProductsButton() {
        return wait.until(ExpectedConditions.visibilityOf(backToProductsButton));
    }

    public WebElement getShoppingCartIcon() {
        return wait.until(ExpectedConditions.visibilityOf(shoppingCartIcon));
    }

    public WebElement getShoppingCartBadge() {
        return wait.until(ExpectedConditions.visibilityOf(shoppingCartBadge));
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

}