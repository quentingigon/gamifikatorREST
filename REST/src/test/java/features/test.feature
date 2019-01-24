Feature: Test Badges quality
  Verify if we can create badges


  Scenario: Create a badge
    Given user has API token
    When a badge is created
    Then the server should send a 200 OK