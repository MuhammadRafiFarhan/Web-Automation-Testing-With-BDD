Feature: Login Functionality

  Background:
    Given I have opened the browser
    When I am on the Swag Labs login page

  Rule: User can login to Swag Labs with valid credential

    @login @valid
    Scenario: Login Functionality Exists
      Then username box should exist
      And password box should exist
      And login button should exist

    @login @valid
    Scenario: Successful Login
      When I enter the username "standard_user"
      And I enter the password "secret_sauce"
      And I click on the login button
      Then I should be taken to the products page
    
  Rule: User cannot login to Swag Labs with invalid credential

    @login @invalid @failed
    Scenario: Login with Empty Username Field Only
      When I enter the username ""
      And I enter the password "secret_sauce"
      And I click on the login button
      Then I should see an error message "Username is required"
      And Username field should be highlighted in red


    @login @invalid @failed
    Scenario: Login with Empty Password Field Only
      When I enter the username "standard_user"
      And I enter the password ""
      And I click on the login button
      Then I should see an error message "Password is required"
      And Password field should be highlighted in red

    @login @invalid @failed
    Scenario: Login with Empty Username and Password Fields
      When I enter the username ""
      And I enter the password ""
      And I click on the login button
      Then I should see an error message "Username and password are required"
      And Username and Password fields should be highlighted in red

    @login @invalid @failed
    Scenario: Entering Incorrect Username Field with Correct Password
      When I enter the username "incorrect_user"
      And I enter the password "secret_sauce"
      And I click on the login button
      Then I should see an error message "Username and password do not match any user in this service"
      And Username field should be highlighted in red

    @login @invalid @failed
    Scenario: Entering Incorrect Password Field with Correct Username
      When I enter the username "standard_user"
      And I enter the password "incorrect_password"
      And I click on the login button
      Then I should see an error message "Username and password do not match any user in this service"
      And Password field should be highlighted in red
    
    @login @invalid @new-test-case @failed
    Scenario: Entering Incorrect Both Username and Password Fields
      When I enter the username "incorrect_user"
      And I enter the password "incorrect_password"
      And I click on the login button
      Then I should see an error message "Username and password do not match any user in this service"
      And Username and Password fields should be highlighted in red
