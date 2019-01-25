Feature: Test events
  Check the whole and the small


  Scenario: Send an event correct
    Given user has API token and rule
    When an event is created
    Then the server should send a 200

  Scenario: Send an event with false rule name
    Given user has API token and rule
    When an event is created with false rule name
    Then the server should send a 404

  Scenario: Send an event with false user id
    Given user has API token and rule
    When an event is created with false user id
    Then the server should send a 404

  Scenario: Send an event with false property name
    Given user has API token and rule
    When an event is created with false property name
    Then the server should send a 404

  Scenario: Send an event with false api token
    Given user has API token and rule
    When an event is created with false api token
    Then the server should send a 404
