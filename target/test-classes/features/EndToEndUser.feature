Feature: End to End Test for GoRest's User Api

  Scenario: Test End to End user Functionality
    When A user is created
    And User is sent to server
    Then Verify user has been created
    When User information is updated
    Then Verify user has been updated
    When User is deleted
    Then verify user has been deleted
