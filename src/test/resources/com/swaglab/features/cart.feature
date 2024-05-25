Feature: Cart Page Functionality

  Rule: User can view the list of items added to the cart on the Cart page

    @cart @view
    Scenario: User views the list of items in the cart
      Given I am currently logged in
      And I have added items to the cart
      When I hover over the cart icon in the top right corner
      And I click on the cart icon with the item count notification
      Then I should be redirected to the cart page titled "Your Cart"
      And I should see item cards equal to the number of items in the cart notification
      And each item card should display the quantity, item name, description, and price
      And there should be "Continue Shopping" and "Checkout" buttons below the list of items

 Rule: User can return to the inventory page from the Cart page

    @cart @continue_shopping
    Scenario: User clicks "Continue Shopping" button from the cart page
      Given I am currently logged in
      And I am on the cart page titled "Your Cart"
      When I hover over the "Continue Shopping" button
      And I click the "Continue Shopping" button
      Then I should be redirected to the inventory page
      And I should see the inventory page displaying a catalog of six items

 Rule: User cannot proceed to checkout with an empty cart

    @cart @checkout @empty_cart
    Scenario: User clicks "Checkout" button with an empty cart
      Given I am currently logged in
      And I am on the cart page titled "Your Cart"
      And my cart is empty
      When I hover over the "Checkout" button
      And I click the "Checkout" button
      Then I should remain on the cart page titled "Your Cart"
      And a red error message box with white text should appear saying "You Need Item In Cart To Proceed Checkout Process"