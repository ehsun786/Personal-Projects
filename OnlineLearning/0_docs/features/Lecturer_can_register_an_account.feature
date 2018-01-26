@must
Feature: Lecturer can register an account
    As a Lecturer
    I want to register a lecturer account
    so that I can start uploading new courses to the site

Scenario: Lecturer can register account
   Given I am a lecturer
   And I type a password
   And I type a username
   And I type an email
   And I am authorised by an administrator
   When I try to register an account
   Then I have an account
  
Scenario: Lecturer does not type a password
    Given I am a lecturer
    And I don't type a password
    When I try to register an account
    Then the system tells me to add a password

Scenario: Lecturer does not type an email
    Given I am a lecturer
    And I don't type an email
    When I try to register an account
    Then the system tells me to add an email
    
Scenario: Lecturer types in a registered email
    Given I am a lecturer
    And the email I want to register is already registered
    When I try to register an account
    Then the system tells me the email is already registered

Scenario: Lecturer does not type a username 
    Given I am a lecturer
    And I don't type a username
    When I try to register an account
    Then the system tells me to add a username

Scenario: Lecturer types in a registered username 
    Given I am a lecturer
    And the username I want to register is already used
    When I try to register an account
    Then the system tells me the username is taken
    
Scenario: Lecturer is not authorised by an administrator
    Given I am a lecturer
    And I am not authorised by an administrator
    When I try to register an account
    Then the system tells me I am not authorised
