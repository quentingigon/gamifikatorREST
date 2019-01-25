Feature: Test Rules responses and sassiness
  Check the whole and the small


  Scenario: Create a rule
    Given user has API token and rule
    When a rule is created
    Then the server should send a 201


  Scenario: Get the rule
    Given user has API token and rule
    When the rule is fetched
    Then the server should send a 200
    And the user get the previous rule

  Scenario: Delete the rule
    Given user has API token and rule
    When the rule is deleted
    Then the server should send a 200
