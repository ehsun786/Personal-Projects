@should
Feature: Lecturer can add a course chapter

@repo
Scenario: Add course chapter
Given I have a lecturer X in the database with name "Jones"
And I have a course X with name "Spring" in the database
When I add a new chapter with name "Security"
Then A new chapter is added to the repository
