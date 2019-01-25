Feature: Test Badges quality
  Verify if we can create badges


  Scenario: Create a badge
    Given user has API token and badge
    When a badge is created
    Then the server should send a 201


  Scenario: Get the badge
    Given user has API token and badge
    When the badge is fetched
    Then the server should send a 200
    And the user get the previous badge

