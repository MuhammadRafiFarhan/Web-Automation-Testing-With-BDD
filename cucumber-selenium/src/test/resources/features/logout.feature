Feature: As a customer I want to be able to logout from the website

  @logout
  Scenario: Successful Logout
    Given I am logged in
    When I click on the menu button
    And I click on the logout button
    Then I should be logged out