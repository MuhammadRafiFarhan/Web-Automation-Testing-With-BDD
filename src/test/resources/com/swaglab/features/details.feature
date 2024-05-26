Feature: Details Page Layout Verification

  Rule: The layout of the product image is positioned on the left side of the product detail card

    @details @layout
    Scenario: Product image layout verification
      Given I am currently logged in into correct account
      And I am on the Inventory page
      When I hover over the image or title of the product "Sauce Labs Bike Light" in the product catalog
      And I click once on the image or title of the product "Sauce Labs Bike Light" in the catalog on the Inventory page
      Then I should be on the product detail page for "Sauce Labs Bike Light"
      And the product image "Sauce Labs Bike Light" should exist

  Rule: The layout of the product name is positioned on the right side of the product detail card in bold text

    @details @layout @font 
    Scenario: Product name layout verification
      Given I am currently logged in into correct account 
      And I am on the Inventory page
      When I hover over the image or title of the product "Sauce Labs Bike Light" in the product catalog
      And I click once on the image or title of the product "Sauce Labs Bike Light" in the catalog on the Inventory page
      Then I should be on the product detail page for "Sauce Labs Bike Light"
      And the product name "Sauce Labs Bike Light" should exist

  Rule: The layout of the product description is positioned on the right side of the product detail card in normal text

    @details @layout @font 
    Scenario: Product description layout verification
      Given I am currently logged in into correct account
      And I am on the Inventory page
      When I hover over the image or title of the product "Sauce Labs Bike Light" in the product catalog
      And I click once on the image or title of the product "Sauce Labs Bike Light" in the catalog on the Inventory page
      Then I should be on the product detail page for "Sauce Labs Bike Light"
      And the product description for "Sauce Labs Bike Light" should exist
      And the content of the product description "Sauce Labs Bike Light" is: "A red light isn't the desired state in testing but it sure helps when riding your bike at night. Water-resistant with 3 lighting modes, 1 AAA battery included."