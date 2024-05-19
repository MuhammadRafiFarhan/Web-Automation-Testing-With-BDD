Feature: Logout Functionality

  Rule: User can logout from the application gracefully

    @logout @valid
    Scenario: Successful Logout
      Given I am currently logged in
      When I click on the menu button
      And I click on the logout button
      Then I should be redirected to the login page