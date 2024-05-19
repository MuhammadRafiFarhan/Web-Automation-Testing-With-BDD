package com.swaglab.locators;

import org.openqa.selenium.By;

public class LoginPageLocators {
    public static final String LOGIN_PAGE_URL = "https://www.saucedemo.com/";

    public static final By loginContainer = By.cssSelector(".login_container");

    public static final By usernameField = By.id("user-name");
    public static final By passwordField = By.id("password");
    public static final By loginButton = By.id("login-button");

    public static final By errorUsernameField = By.cssSelector("#user-name.input_error");
    public static final By errorPasswordField = By.cssSelector("#password.input_error");

    public static final By errorMessage = By.cssSelector(".login_wrapper-inner #login_button_container .login-box .error-message-container h3");
}
