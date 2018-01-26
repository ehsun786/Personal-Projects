@must
Feature: Administrator can delete accounts

  @repo
  Scenario: Administrator account deletion
    Given I have a user X in the database with name "Jonny"
    When I delete the user from the repository
    Then The user with name "Jonny" no longer exists in the repository

  @repo
  Scenario: Administrator account suspension
    Given There is a user X in the database withe name "Jerry"
    When I suspend the user then
    Then the user with name "Jerry" is suspended from using the website
