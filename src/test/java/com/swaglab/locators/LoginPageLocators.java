package com.swaglab.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Description:  LoginPageLocators file will only contain UI elements or Objects
 *               present on Login screen along with the operations to be performed
 *               on these elements.
 */
public class LoginPageLocators {

    public static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    @FindBy(css = ".login_container")
    public WebElement loginContainer;
    @FindBy(id = "user-name")
    public WebElement usernameField;
    @FindBy(id = "password")
    public WebElement passwordField;
    @FindBy(id = "login-button")
    public WebElement loginButton;
    @FindBy(css = "#user-name.input_error")
    public WebElement errorUsernameField;
    @FindBy(css = "#password.input_error")
    public WebElement errorPasswordField;
    @FindBy(css = ".login_wrapper-inner #login_button_container .login-box .error-message-container h3")
    public WebElement errorMessage;
    
    // Constructor
    public LoginPageLocators() {  }

    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }
    
    public void clickLoginButton() {
        loginButton.click();
    }
    
    public void log_in(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public WebElement getLoginContainer() {
        return loginContainer;
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getErrorUsernameField() {
        return errorUsernameField;
    }

    public WebElement getErrorPasswordField() {
        return errorPasswordField;
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
}
