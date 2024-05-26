Feature: Checkout Functionality

    Background:
        Given I am currently logged in with authentic account to use checkout feature
        And I have added items to the cart to use checkout feature
        And I have been redirected to the checkout your information page

    Rule: User should be able to checkout successfully

        @checkout @positive @pass
        Scenario: Checkout for product that already added in cart until payment successful
            When I fill in the "first name" field with "John"
            And I fill in the "last name" field with "Doe"
            And I fill in the "postal code" field with "12345"
            And I click the "continue" button to checkout
            And I click the "finish" button in the overview page
            Then I should be redirected to the checkout complete page
            And I should see the "header" message "Thank you for your order!"
            And I should see the "text" message "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
            And there should be Back Home button below the message
            And shopping cart badge should be removed

    Rule: User should not be able to checkout successfully when they leave the required information field empty

        @checkout @negative @fail
        Scenario: Checkout attempt by leaving zip/postal code field empty on Checkout: Your Information page
            When I fill in the "first name" field with "John"
            And I fill in the "last name" field with "Doe"
            And I click the "continue" button to checkout
            Then I should still be on the checkout your information page
            And "Zip/Postal Code" field should be highlighted in red
            And I should see the "error" message "Zip/Postal Code is required!"

        @checkout @negative @fail
        Scenario: Checkout attempt by leaving all fields empty on Checkout: Your Information page
            When I click the "continue" button to checkout
            Then I should still be on the checkout your information page
            And "First Name" field should be highlighted in red
            And "Last Name" field should be highlighted in red
            And "Zip/Postal Code" field should be highlighted in red
            And I should see the "error" message "All fields are required!"