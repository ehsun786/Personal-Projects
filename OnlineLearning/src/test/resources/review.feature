#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: Review.Persistence
  I want to persist reviews for reviewing puproses

  @repo
  Scenario: Review.Repo.Create
    Given I have a review X with statement "Very Good"
    And I have a learner X with name "Gregg"
    When I add a new review to the learner review list repository
    Then a new review is stored in the repository for the learner with statement "Very Good"
