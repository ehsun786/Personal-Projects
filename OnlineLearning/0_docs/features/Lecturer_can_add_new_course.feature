@must
Feature: Lecturer can add a new course 
    As a lecturer
    I want to add a new course 
    so that learners know what they are studying

Scenario: Lecturer can add a new course 
    Given I am a lecturer
    When I add a new course
    And the new course has a name
    Then the course should be shown on the website
 
Scenario: Lecturer tries to add a new course with no name
    Given I am a lecturer
    When I try to add a new course
    And I leave the new course name blank
    Then the system tells me to type a name

