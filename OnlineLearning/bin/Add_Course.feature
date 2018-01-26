@should
Feature: Lecturer can add a course

@repo
Scenario: Add course
Given I have a lecturer with name "Jones"
When I add a new course X for the lecturer with name "Spring"
Then A new course is added in the database for the lecturer
