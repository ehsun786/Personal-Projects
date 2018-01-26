@must
Feature: Learner can register an account
    As a learner
    I want to register a learner account
    so that I have access to the learning platform

Scenario: Learner can open account
   Given I am a learner
   When I try to register an account
   And I type a password
   And I type a username
   And I type an email
   Then I have an account
  
Scenario: Learner does not type a password
    Given I am a learner
    When I try to register an account
    And I don't type a password
    Then the system tells me to add a password

Scenario: Learner does not type an email
    Given I am a learner
    When I try to register an account
    And I don't type an email
    Then the system tells me to add an email
    
Scenario: Learner types in a registered email
    Given I am a learner
    When I try to register an account
    And the email I want to register is already registered
    Then the system tells me the email is already registered

Scenario: Learner does not type a username 
    Given I am a learner
    When I try to register an account
    And I don't type a username
    Then the system tells me to add a username

Scenario: Learner types in a registered username 
    Given I am a learner
    When I try to register an account
    And the username I want to register is already used
    Then the system tells me the username is taken
