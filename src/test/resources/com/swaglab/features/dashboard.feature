Feature: Inventory Page Functionality

  Rule: User can view the catalog of items available for order on the Inventory page

    @inventory @view
    Scenario: User views the catalog of items
      Given I am currently logged in
      When I navigate to the inventory page
      Then I should see a list of items that can be ordered

  Rule: User can select an item from the catalog

    @inventory @select
    Scenario: User selects an item from the catalog
      Given I am currently logged in
      And I am on the inventory page
      When I click the "Add to cart" button for an item
      Then the button should change to "Remove" with a red color
      And a notification indicating the item is added should be visible on the Cart button

  Rule: User can select multiple items from the catalog

    @inventory @select @multiple
    Scenario: User selects multiple items from the catalog
      Given I am currently logged in
      And I am on the inventory page
      When I click the "Add to cart" button for the first item
      And I click the "Add to cart" button for the second item
      And I click the "Add to cart" button for the third item
      Then the buttons should change to "Remove" with a red color
      And a notification indicating the items are added should be visible on the Cart button
