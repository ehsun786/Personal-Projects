@should
Feature: Lecturer can add a sub topic

  @repo
  Scenario: Add sub topic
    Given I have a lecturer X with name "Jones" in the database
    And I have a course Y with name "Spring" in the database for the lecturer
    And I have a chapter Z with name "Security"
    When I add a new sub topic in the database for the chapter
Then A new sub topic is added to the database for that course
