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
Feature: Getting object from Amazon AWS
	I want to use this to get object from the object uploaded to the Amazon Cloud for subtopics

@repo
Scenario: Downloading objects from the Amazon Cloud
When I get an object with name "Toucan-hd-photo.jpg" from an Amazon bucket with name "123testing123"
Then I receive a non-null subject from the clouds
