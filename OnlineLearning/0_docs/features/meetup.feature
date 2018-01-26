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

Feature: Meetup.Persistence
	I want to persist meetups for lecturing puproses

@repo
Scenario: Meetup.Repo.Create
	Given I have a meetup X with description "Java 3D"
	And I have a lecturer X with name "Bob" in the repository
	When I add the meetup to the repository for the lecturer
	Then a new meetup is stored in the repository for the lecturer with name "Java 3D"

@repo
Scenario: Meetup Repo.Delete
	Given I have a meetup X with description "Java 3D" in the database
	And I have a lecturer X with name "Bob"
	When I delete the meetup X from the database for the lecturer
Then The meetup with description "Java 3D" no longer exists in the repository for the repository
