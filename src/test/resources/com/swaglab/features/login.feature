Feature: As a customer I want to be able to login onto the website, so I can access my account and shop

  @login
  Scenario: Login Functionality Exists
    Given I have opened the browser
    When I open Swaglabs website
    Then username box should exist
    And password box should exist
    And login button should exist

  @login
  Scenario: Successful Login
    Given I am on the Swag Labs login page
    When I enter the username "standard_user"
    And I enter the password "secret_sauce"
    And I click on the login button
    Then I should be taken to the products page

  @login
  Scenario: Empty Username
    Given I am on the Swag Labs login page
    When I enter the username ""
    And I enter the password "secret_sauce"
    And I click on the login button
    Then I should see an error message "Username is required"


  @login
  Scenario: Empty Password
    Given I am on the Swag Labs login page
    When I enter the username "standard_user"
    And I enter the password ""
    And I click on the login button
    Then I should see an error message "Password is required"

  @login
  Scenario: Empty Username and Password
  Given I am on the Swag Labs login page
  When I enter the username ""
  And I enter the password ""
  And I click on the login button
  Then I should see an error message "Username and Password is required"

  @login
  Scenario: Incorrect Username
    Given I am on the Swag Labs login page
    When I enter the username "incorrect_user"
    And I enter the password "secret_sauce"
    And I click on the login button
    Then I should see an error message "Username and password do not match any user in this service"

  @login
  Scenario: Incorrect Password
    Given I am on the Swag Labs login page
    When I enter the username "standard_user"
    And I enter the password "incorrect_password"
    And I click on the login button
    Then I should see an error message "Username and password do not match any user in this service"
