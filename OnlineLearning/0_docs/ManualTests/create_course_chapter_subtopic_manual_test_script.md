 Group 10 manual testing

# Tests

| Test | Step |
|----|:-----|
| 1.1 | On the lecturer homepage locate the create course button.    |
| 1.2 | Enter a course name.  |
| 1.3 | Enter a course price. |
| 1.4 | Enter a description.  |
| 1.5 | Upload a course image.  |
| 1.6 | Upload a badge. |
| 1.7 | Click create. |
| 1.8 | You should see the new course on your lecturer homepage.  |
| 1.9 | If there is a field that has not been entered for the form then an error should appear asking you to enter the field.  |
| 1.10 | Login to Amazon AWS |
| 1.11 | Look for the storage S3 and click on the username for the lecturer |
| 1.12 | You should find the name of the uploaded course image |
| 1.13 | You should find the name of the uploaded badge image |
| 2.1 | On the lecturer homepage locate the edit button on any course |
| 2.2 | Enter a course name and click Rename |
| 2.3 | You should see the course menu |
| 3.1 | Locate the course on the lecturer homepage and click delete course |
| 3.2 | The course should no longer exist |

| Test | Step |
|----|:-----|
| 1.1 | On the lecturer homepage locate the edit button under one of the courses.    |
| 1.2 | Click the create chapter button.  |
| 1.3 | Enter a chapter name.  |
| 1.4 | Click create.  |
| 1.5 | You should see the new chapter name in the course menu.  |
| 1.6 | If a chapter name is not entered for the form then an error should appear asking you to enter the field |
| 2.1 | Locate the chapter and click edit chapter |
| 2.2 | Type in a new chapter name and click Rename |
| 2.3 | You should see a new name for the chapter |
| 3.1 | Locate the chapter and click delete chapter |
| 3.2 | The chapter should no longer exist |

| Test | Step |
|----|:-----|
| 1.1 | On the lecturer homepage locate the edit button under one of the courses    |
| 1.2 | Click the edit button on a chapter  |
| 1.3 | Click create subtopic button  |
| 1.4 | Enter a subtopic name  |
| 1.5 | Enter a subtopic description |
| 1.6 | Upload an mp4 or pdf file |
| 1.6 | You should see the new subtopic name in the chapter menu  |
| 1.7 | If there is a field not entered for the form then an error should appear asking you to enter the field |
| 1.8 | Login to Amazon AWS
| 1.9 | Look for the storage S3 and click on the username for the lecturer
| 1.10 | You should find the name of the uploaded mp4 or pdf file
| 2.1 | Locate the chapter and click edit subtopic |
| 2.2 | Type in a new subtopic name and click Rename |
| 2.3 | You should see a new name for the subtopic |
| 3.1 | Locate the subtopic and click delete subtopic
| 3.2 | The subtopic should no longer exist

## Create/Delete/Edit Course Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 1.2 | Click create course  | The form for create course should appear |
| 1.3 | Enter no course name with course price `20`, description `stuff with computers` and upload any image. Click create. | You are told to enter a course name |
| 1.4 | Enter course name `Computer Science` with no course price, description `stuff with computers`, upload any image and upload a badge. Click create. | You are told to enter a course price |
| 1.4 | Enter course name `Computer Science` with course price `20`, description `stuff`, upload any image and upload a badge. Click create. | You are told to enter a description of at least 20 characters |
| 1.5 | Enter course name `Computer Science` with course price `20`, no description, upload any image and upload a badge. Click create. | You are told to enter a a description |
| 1.6 | Enter course name `Computer Science` with course price `20`, description `stuff with computers`, upload no image and upload a badge. | You are told to upload an image |
| 1.7 | Enter course name `Computer Science` with course price `20`, description `stuff with computers` and upload any image and upload no badge. | You are told to upload an badge |
| 1.7 | Enter course name `Computer Science` with course price `20`, description `stuff with computers` and upload any image and upload a badge. | You should see the lecturer homepage |
| 1.8 | Go to https://aws.amazon.com/ and login using the email `ch388@student.le.ac.uk` and password `elearning123`. | 
| 1.9 | Go to Storage and locate S3 | You should see a list of usernames |
| 1.10 | Go to the lecturer's username which you used to make the new course | You should see a list of files |
| 1.11 | Find the image file name that was uploaded | You should see the image file |
| 1.12 | Find the badge file name that was uploaded | You should see the badge file |
| 2.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 2.2 | Click edit course | The form for edit course name should appear |
| 2.3 | Click Rename | Nothing should happen |
| 2.4 | Type in `Renamed course`. Click Rename | The course should be called `Renamed course` |
| 3.1 | Go to /lecturer | The lecturer homepage should appear |
| 3.2 | Click delete course | The course should no longer exist on the page |

##
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/47b71510eb7271643e3a2adf76eb36587bb3036d/0_docs/ManualTestEvidence/LecturerCreateNewCourseValidation.png)
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/901b2a46b7cf472d923497d4dbd9526174028282/0_docs/ManualTestEvidence/LecturerEditCourse.png)
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/47b71510eb7271643e3a2adf76eb36587bb3036d/0_docs/ManualTestEvidence/LecturerDeleteCourse.png)



## Create/Delete/Edit Chapter Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 1.2 | Click edit under one of the existing courses | You should see the course menu  |
| 1.3 | Click create chapter | The form for create chapter should appear |
| 1.4 | Enter no chapter name. Click create. | You are told to enter a chapter name |
| 1.5 | Enter chapter name `Fundamentals of Java`. Click create. |  You should see the course menu with `Fundamentals of Java`  |
| 2.1 | Go to /lecturer | The lecturer homepage should appear |
| 2.2 | Go to any course  | The course menu should appear  |
| 2.3 | Click edit chapter | The form for edit chapter name should appear |
| 2.4 | Click Rename | Nothing should happen |
| 2.5 | Type in `Renamed chapter`. Click Rename | The course should be called `Renamed chapter` |
| 3.1 | Go to /lecturer | The lecturer homepage should appear |
| 3.2 | Go to any course | The course menu should appear |
| 3.3 | Click delete chapter | The chapter should no longer exist on the page |
##
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/7da617c8f4484b0b1876d23eaedfad0cc669d8dc/0_docs/ManualTestEvidence/LecturerCreateChapter.png)
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/7da617c8f4484b0b1876d23eaedfad0cc669d8dc/0_docs/ManualTestEvidence/LecturerRenameChapter.png)
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/b4830cfcc703590a8c5b9060204cc6847e8998d0/0_docs/ManualTestEvidence/LecturerDeleteChapter.png)
## Create/Delete/Edit Subtopic Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 1.2 | Click edit under one of the existing courses | You should see the course menu  |
| 1.3 | Click edit under one of the existing chapters | You should see the chapter menu |
| 1.4 | Click create subtopic | The form for create subtopic should appear |
| 1.5 | Enter no subtopic name with description `starting the first java file` and upload any mp4 or pdf file. Click create. | You are told to enter a subtopic name |
| 1.6 | Enter subtopic name `Java classes`, with no description and upload any mp4 or pdf file. Click create. | You are told to enter a description |
| 1.7 | Enter subtopic name `Java classes`, with description `starting` and upload any mp4 or pdf file. Click create. | You are told to enter a description of at least 20 characters |
| 1.8 | Enter subtopic name `Java classes`, with description `starting the first java file` and upload no mp4 or pdf file. Click create. | You are told to upload an mp4 or pdf file |
| 1.9 | Enter subtopic name `Java classes`, with description `starting the first java file` and upload a jpg file. Click create. | You are told to upload an mp4 or pdf file |
| 1.10 | Enter subtopic name `Java classes`, with description `starting the first java file` and upload any mp4 or pdf file. Click create. | You should see the chapter menu with `Java Classes`  |
| 1.11 | Go to https://aws.amazon.com/ and login using the email `ch388@student.le.ac.uk` and password `elearning123`. | 
| 1.12 | Go to Storage and locate S3 | You should see a list of usernames |
| 1.13 | Go to the lecturer's username which you used to make the new course | You should see a list of files |
| 1.14 | Find the mp4 or pdf file name that was uploaded | You should see the mp4 or pdf file |
| 2.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 2.2 | Go to any course | The course menu should appear |
| 2.3 | Go to any chapter | The chapter menu should appear |
| 2.4 | Click edit subtopic | The form for edit subtopic name should appear |
| 2.5 | Click Rename | Nothing should happen |
| 2.6 | Type in `Renamed subtopic`. Click Rename | The course should be called `Renamed subtopic` |
| 3.1 | Go to /lecturer | The lecturer homepage should appear |
| 3.2 | Go to any course | The course menu should appear |
| 3.3 | Go to any chapter | The chapter menu should appear |
| 3.4 | Click delete subtopic | The subtopic should no longer exist on the page |
##
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/a84ca4904fdb295cc5b3d6eaf7b79dab9e52f88e/0_docs/ManualTestEvidence/LecturerCreateSubTopics.png)
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/LecturerEditSubTopics.png)
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/a76d3d6c013a4a0d4187fc72cd0a8feff2b3bc5e/0_docs/ManualTestEvidence/LecturerDeleteSubTopics.png)


## Test Run 28-Apr-2017
**Create/Delete/Edit Course**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 1.10, 1.11, 1.12, 2.1, 2.2, 2.3, 2.4, 3.1, 3.2
**Tests Failed:** None 

**Create/Delete/Edit Chapter**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 2.1, 2.2, 2.3, 2.4, 2.5, 3.1, 3.2, 3.3
**Tests Failed:** None 

**Create/Delete/Edit Subtopic**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 1.10, 1.11, 1.12, 1.13, 1.14, 2.1, 2.2, 2.3, 2.4, 2.5, 2.6, 3.1, 3.2, 3.3, 3.4
**Tests Failed:** None 
