# Group 10 manual testing

# Tests

## Manual Test for Add Course
| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /login     | The login screen should appear |
| 1.2 | Enter username `james786` with password `oliver`. Click `Login` | You are taken to your Course List |
| 1.3 | Click on add new course | The form for add new course should appear |
| 1.4 | Fill in no information and click submit | The error page should appear |
| 1.5 | Enter no course name with description 'Computer stuff' | Message says 'please enter a course name' | 
| 1.6 | Enter course name 'CS' with no description | Message says 'please enter a course description' |
| 1.7 | Enter course name 'CS' with description 'Computer stuff' | You should see /addCourse |


## Manual Test for Add Course chapter
| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /login     | The login screen should appear |
| 1.2 | Enter username `james786` with password `oliver`. Click `Login` | You are taken to your Course List |
| 1.3 | Click on add new course| The form for add new course should appear |
| 1.4 | Click on add course chapter | The form for add course chapter should appear |
| 1.5 | Fill in no information and click submit | The error page should appear |
| 1.6 | Enter no course name with description 'Computer stuff' | Message says 'please enter a course name' | 
| 1.7 | Enter course name 'CS' with no description | Message says 'please enter a course description' |
| 1.8 | Enter course name 'CS' with description 'Computer stuff' | You should see /addCourseChapter |


## Manual Test for Add sub topic
| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /login     | The login screen should appear |
| 1.2 | Enter username `james786` with password `oliver`. Click `Login` | You are taken to your Course List |
| 1.3 | Click on add new course| The form for add new course should appear |
| 1.4 | Click on add course chapter | The form for add course chapter should appear |
| 1.5 | Click on add sub topic | The form for add sub topic should appear |
| 1.6 | Fill in no information and click submit | The error page should appear |
| 1.7 | Enter no course name with description 'Computer stuff' | Message says 'please enter a course name' | 
| 1.8 | Enter course name 'CS' with no description | Message says 'please enter a course description' |
| 1.9 | Enter course name 'CS' with description 'Computer stuff' | You should see /addSubTopic |


