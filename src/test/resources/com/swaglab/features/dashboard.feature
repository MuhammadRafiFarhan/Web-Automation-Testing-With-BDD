Feature: Inventory Page Functionality

  Background:
    Given User currently logged in to use dashboard

  Rule: User can view the catalog of items available for order on the Inventory page

    @inventory @view
    Scenario: User views the catalog of items
      When I open Swaglabs inventory page
      Then inventory list should exist
      And add to cart button should exist
      And cart link should exist
      And burger menu should exist
      And product sort should exist


  Rule: User can select an item from the catalog

    @inventory @select
    Scenario: User adds an item to the cart
      When User clicks the Add to Cart button on the inventory page
      Then User should see the shopping cart badge  

  Rule: User can select multiple items from the catalog

    @inventory @select @multiple
    Scenario Outline: User adds multiple items to the cart
      When User clicks the Add to Cart button on the inventory page
      And User clicks another Add to Cart button on another item in the inventory page
      Then User should see another added product in the shopping cart badge
