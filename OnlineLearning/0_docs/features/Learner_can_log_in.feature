@must
Feature: Learner can log in
    As a learner
    I want to log in to my account
    so that I have access to my account and courses

Scenario: Learner can log in
   Given I am a learner
   When I log in to my account
   And I type a username
   And I type the correct password associated with the username
   Then I am using my account
  
Scenario: Learner does not type a password
    Given I am a learner
    When I try to log in to my account
    And I don't type a password
    Then the system tells me to type a password

Scenario: Learner does not type a username
    Given I am a learner
    When I try to log in to my account
    And I don't type an username
    Then the system tells me to type an email or a username

Scenario: Learner types the wrong password for a username
    Given I am a learner
    When I try to log in to my account
    And I type an username
    And I type the wrong password associated with the username
    Then the system tells me my passwords is wrong

Scenario: Learner types in a non-registered username
    Given I am a learner
    When I try to log in my account
    And I type a non-registered username
    Then the system tells me the username does not exist


