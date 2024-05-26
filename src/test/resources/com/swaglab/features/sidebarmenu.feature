Feature: Sidebar Functionality

    Rule: User can open the sidebar

        @sidebar @open
        Scenario: User clicks the sidebar button
            Given User is currently logged in
            And User is on the Dashboard page
            When User clicks the Sidebar button in the top left corner with a hamburger icon
            Then Sidebar menu should appear

    Rule: User can click "About" on the sidebar menu
        
        @sidebar @about
        Scenario: User clicks "About" on the sidebar menu
            Given User is currently logged in
            And User is on the Dashboard page
            And Sidebar menu is open
            When User clicks About
            Then User should be redirected to the corresponding page

    Rule: User can close the sidebar

        @sidebar @close
        Scenario: User clicks the cross button on the sidebar
            Given User is currently logged in
            And User is on the Dashboard page
            And Sidebar menu is open
            When User clicks the cross button on the sidebar
            Then Sidebar menu should be closed
