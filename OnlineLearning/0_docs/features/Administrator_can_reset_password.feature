@must
Feature: Administrator can reset password
	As an administrator
	I want to be able to reset my password
	So that I can access the system if I lose my password

Scenario: Password reset
	Given I am an administrator
	When I forget my password
	Then I must be able to reset it

