Feature: Details Page Layout Verification

    Background:
        Given I was currently logged in into correct account
        And I was on the Inventory page
        And I have reset Swaglabs app state to its default state
        And I clicked once on the name of the product "Sauce Labs Bike Light" in the catalog on the Inventory page
        When I am on the product detail page for "Sauce Labs Bike Light"

    Rule: The layout of the product detail page is displayed correctly

        @details @layout
        Scenario: Product detail's page layout verification
            Given Product "Sauce Labs Bike Light" "is not" added to the cart
            Then the product image for "Sauce Labs Bike Light" should exist
            And the product name for "Sauce Labs Bike Light" should exist
            And the product description for "Sauce Labs Bike Light" should exist
            And the content of the product description "Sauce Labs Bike Light" should be "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."
            And the product price for "Sauce Labs Bike Light" should be "$9.99"
            And the "Add to cart" button should exist
            And the "Back to products" button should exist

    Rule: User can add the product to the cart from the product detail page

        @details @add-to-cart
        Scenario: Adding a product to the cart from the product detail page
            Given Product "Sauce Labs Bike Light" "is not" added to the cart
            When I click the "Add to cart" button on the product detail page
            Then the "Remove" button should exist
            And the encircled number of item should exist on top of the cart icon
    
    Rule: User can remove the product from the cart from the product detail page

        @details @remove-from-cart
        Scenario: Removing a product from the cart from the product detail page
            Given Product "Sauce Labs Bike Light" "has been" added to the cart
            When I click the "Remove" button on the product detail page
            Then the "Add to cart" button should exist
            And the encircled number of item should not exist on top of the cart icon
