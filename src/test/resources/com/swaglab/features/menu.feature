Feature: Sidebar Functionality

  Rule: User can interact with the sidebar button

    @inventory @sidebar
    Scenario: Clicking the sidebar button
      Given I am currently logged in
      And I am on the Inventory page
      When I click the sidebar button in the top right corner with a hamburger icon
      Then the sidebar menu should appear

    @inventory @all_items
    Scenario: Clicking the "All Items" button on the dashboard page
      Given I am currently logged in
      And I am on the Inventory page
      When I click the sidebar button in the top right corner with a hamburger icon
      And I click the "All Items" button
      Then nothing should happen
    
    @cart @sidebar
    Scenario: Clicking the sidebar button
      Given I am currently logged in
      And I am on the Cart page
      When I click the sidebar button in the top right corner with a hamburger icon
      Then the sidebar menu should appear 