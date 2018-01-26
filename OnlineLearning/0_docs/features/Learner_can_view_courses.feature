@must
Feature: Learner can view courses 
    As a learner
    I want to view courses 
    so that I can study

Scenario: Learner can view a course 
    Given I am a learner
    When I try to view the course
    And I am signed up to the course
    Then I should be able to see the content of the course
 
Scenario: Learner tries to view a course that they are not signed up to 
    Given I am a learner
    When I try to view the course
    And I am not signed to the course
    Then the system tells me I am not signed up
    And I am redirected back to the homepage
