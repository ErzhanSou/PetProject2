Feature: test log in functionality


  @loginDemo
  Scenario: check log in is successful with valid credentials
    Given  user is on sauce demo login page
    When user provides a valid username
    And user provides a valid password
    And user clicks on login button
    Then verify user successfully logged in

  @loginDemo
  Scenario: check log in is unsuccessful with wrong credentials
    Given  user is on sauce demo login page
    When user provides a invalid username
    And user provides a invalid password
    And user clicks on login button
    Then verify user sees an error message