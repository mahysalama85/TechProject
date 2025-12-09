Feature: OrangeHRM Admin User Management

  Scenario: Add and delete a user in Admin module
    Given I navigate to OrangeHRM login page
    When I login with username "Admin" and password "admin123"
    And I click on Admin tab
    And I get the current number of records
    And I click on Add button
    And I fill new user details and save
    Then the number of records should increase by 1
    When I search for the newly added user
    And I delete the user
    Then the number of records should decrease by 1
