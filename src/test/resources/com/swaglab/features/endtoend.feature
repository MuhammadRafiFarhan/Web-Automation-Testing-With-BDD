Feature: End-to-End Functionality

  Background:
    Given User is currently logged in to the site
    Then User have been should be redirected to the products page

  Rule: User can add a product to the cart and proceed to checkout

    @end-to-end @add-to-cart
    Scenario: Adding a product to the cart and proceeding to checkout
      When User selects a product from the catalog and clicks the "Add to cart" button
      Then User have been redirected to the checkout page
      When User fill in the "first name" field with "John"
      And User fill in the "last name" field with "Doe"
      And User fill in the "postal code" field with "12345"
      And User click the "continue" button to checkout
      And User click the "finish" button in the overview page
      Then User should be redirected to the checkout complete page
      And User should see the "header" message "Thank you for your order!"
      And User should see the "text" message "Your order has been dispatched, and will arrive just as fast as the pony can get there!"
      And there should be Back Home button below the purchase message
      And shopping cart badge should be removed afterward