Feature: Checkout Page Functionality

  Rule: User can complete the checkout process by following all required steps

    @checkout @complete
    Scenario: User completes the checkout process following all required steps
      Given I am currently logged in
      And I have items in my cart
      And I am on the checkout page step one
      When I fill in the first name field with "Rafi"
      And I fill in the last name field with "Farhan"
      And I fill in the zip/postal code field with "40522"
      And I click the "Continue" button
      Then I should be on the checkout page step two
      And I should see the total payment amount
      When I click the "Finish" button
      Then I should see a confirmation message indicating the order is complete
      And I should be on the page with the message "Thank you for your order!"
      And I should see a "Back Home" button

    @checkout @failed
    Scenario: Input Zip/Postal Code is missing or incorrect
      Given I am currently logged in 
      And I have items in my cart
      And I am on the checkout page step one
      When I fill in the first name field with "Rafi"
      And I fill in the last name field with "Farhan"
      And I click the "Continue" button
      Then I should see a warning message "Zip/Postal Code is required!"

    @checkout @failed
    Scenario: Input Last Name is missing or incorrect
      Given I am currently logged in
      And I have items in my cart
      And I am on the checkout page step one
      When I fill in the first name field with "Rafi"
      And I fill in the zip/postal code field with "40522"
      And I click the "Continue" button
      Then I should see a warning message "Last Name is required!"