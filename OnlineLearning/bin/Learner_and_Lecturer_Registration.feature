@must
Feature: User can register a account

  @repo
  Scenario: Learner registration
    When I add a new learner to the database with name "Sunny"
    Then A new learner is added to the database with name "Sunny"

  @repo
  Scenario: Lecturer registration
    When I add a new lecturer to the database with name "Garret"
    Then A new lecturer is added to the database with name "Garret"
