@must
Feature: Administrator can register an admin account
    As an administrator
    I want to register an admin account
    So that I can manage the system

Scenario: Register an admin
    Given I am an administrator
    When I add a new administrator account onto the system
    And I type a password
    And I type a username
    And I type an email
    Then there should be a new account with administrator privileges
  
Scenario: Administrator does not type a password
    Given I am a administrator
    When I try to register an administrator account
    And I don't type a password
    Then the system tells me to add a password

Scenario: Administrator does not type an email
    Given I am a administrator
    When I try to register an administrator account
    And I don't type an email
    Then the system tells me to add an email
    
Scenario: Administrator types in a registered email
    Given I am a administrator
    When I try to register an administrator account
    And the email I want to register is already registered
    Then the system tells me the email is already registered

Scenario: Administrator does not type a username 
    Given I am a administrator
    When I try to register an administrator account
    And I don't type a username
    Then the system tells me to add a username

Scenario: Administrator types in a registered username 
    Given I am a administrator
    When I try to register an administrator account
    And the username I want to register is already used
    Then the system tells me the username is taken
    
