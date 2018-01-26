@must
Feature: Administrator can register another administrator

  @repo
  Scenario: Administration registration
    When I add a new administrator to the database with name "Drake"
    Then The database contains a new administrator with name "Drake"
