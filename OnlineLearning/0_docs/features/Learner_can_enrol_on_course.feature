@must
Feature: Learner can enrol on a course
    As a learner
    I want to be able to sign up to and enrol on a course
    so I can access its content

Scenario: Learner can enrol on a course
    Given I am a learner
    When I choose to enrol on the course
    And the course is not full
    Then I am enrolled on the course

Scenario: Learner can't enrol on a course on a full course
    Given I am a learner
    When The course is full
    Then I cannot enrol on the course
