# Group 10 manual testing

# Tests

## Manual Test for Register for Learner
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerRegTest.png)

| Test | Step |
|----|:-----|
| 1.1 | On the homepage locate the Sign up button.|
| 1.2 | Click sign up button which takes you to register page.  |
| 1.3 | On the register page locate register form for Learner and Lecturer. | 
| 1.4 | Click learner sign up form.|
| 1.5 | Enter your First Name.|
| 1.6 | Enter your Last Name.|
| 1.7 | Enter your Gender.|
| 1.8 | Enter your Username.|
| 1.9 | Enter your Password.|
| 2.1 | Enter your Email address.|
| 2.2 | Click the sign up button.|
| 2.3 | You should now be taken to the success login page.|
| 2.4 | If there is a field which has not been entered for the form then an error should appear asking you to enter the field.|

Enter less than 6 characters for password
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/leanerWrongPw1.png)
Enter different password in two fields
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/leanerWrondPw2.png)
Enter Invalid Email
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnWrongEmail1.png)
Enter different email in two fields
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnWrongEmail2.png)
Missing some fields
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/missingFields.png)



## Manual Test for Register for Lecturer

![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/lecturerRegTest.png)

| Test | Step |
|----|:-----|
| 1.1 | Click lecturer sign up form.|
| 1.2 | Enter your username.  |
| 1.3 | Enter your first name. | 
| 1.4 | Enter your last name.|
| 1.5 | Enter your gender.|
| 1.6 | Enter your password.|
| 1.7 | Enter your email address|
| 1.8 | Upload your CV.|
| 1.9 | Upload your cover letter.|
| 2.0 | Enter a desciption detailing your reason to become a lecturer on the site.|
| 2.1 | Click the sign up button.|
| 2.2 | You should now be taken to the success login page.|
| 2.3 | If there is a field which has not been entered for the form then an error should appear asking you to enter the field.|

Enter less than 6 characters for password
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/lecturerWrongPw1.png)
Enter different password in two fields
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/lecturerWrongPw2.png)
Enter Invalid Email
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/lecturerWrongEmail1.png)
Enter different email in two fields
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/lecturerWrongEmail2.png)
Missing some fields
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/lecturerMissingField.png)


## Manual Test for Admin registration

![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/adminRegTest.png)

| Test | Step |
|----|:-----|
| 1.1 | On the homepage locate the login button.|
| 1.2 | Enter your admin username.  |
| 1.3 | Enter your admin password.| 
| 1.4 | You should now be in the admin success login page.|
| 1.5 | Locate register a new admin.|
| 1.6 | Enter your username.|
| 1.7 | Enter your password.|
| 1.8 | Enter your email address.|
| 1.9 | A new admin account is created.|
| 2.0 | If you only enter one of the fields.|
| 2.1 | Then you are told to enter the other missing fields.|

The username already exists
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/newAdminError1.png)
Incorrect password is entered
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/adminResetWrongPw.png)
Username does not exist
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/adminSuspendError.png)
Username does not exist
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/adminDeleteError.png)

## Registration Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /register    | The register page should appear |
| 1.2 | Click learner sign up | The form of learner should appear |
| 1.3 | Enter no username with password `alex123`, confirm password `alex123`, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are told to fill in a username |
| 1.4 | Enter username `alex777` with no password, confirm password `alex123`, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are told to fill in a password and confirm password does not match |
| 1.5 | Enter username `alex777` with password `alex123`, no confirm password, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are told confirm password does not match |
| 1.6 | Enter username `alex777` with password `alex123`, confirm password `alex123`, no email, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are told to fill in an email and confirm email does not match |
| 1.7 | Enter username `alex777` with password `alex123`, confirm password `alex123`, email `alex777@gmail.com`, no confirm email, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are told to confirm email does not match |
| 1.8 | Enter username `alex777` with password `alex123`, confirm password `alex123`, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and no phone number. Click `Sign up` | You are told to fill in a phone number |
| 1.9 | Enter username `alex777` with password `alex123`, confirm password `alex123`, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121abc`. Click `Sign up` | You are told only numbers are allowed in the phone number |
| 1.10 | Enter username `james786` with password `alex123`, confirm password `alex123`, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are told the username has already been taken |
| 1.11 | Enter username `alex777` with password `alex123`, confirm password `alex123`, email `alex777@gmail.com`, confirm email `alex777@gmail.com`, select the gender `male` and phone number `07550789121`. Click `Sign up` | You are taken to your account settings |
| 2.1 | Click lecturer sign up | The form for lecturer should appear |
| 2.2 | Enter no username, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told to fill in a username |
| 2.3 | Enter username `bob786`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told username has already been taken |
| 2.4 | Enter username `tom541`, no firstname, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told to fill in a firstname |
| 2.5 | Enter username `tom541`, firstname `Tom`, no lastname, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told to fill in a lastname |
| 2.6 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, no password, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told to fill in a password and confirm password does not match |
| 2.7 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, no confirm password, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told confirm password does not match |
| 2.8 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, no email, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told to fill in an email and confirm email does not match |
| 2.9 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, no confirm email, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told confirm email does not match |
| 2.10 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, no phone number, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told to fill in a phone number |
| 2.11 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121abc`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are told only numbers are allowed in the phone number |
| 2.12 | Enter username `tom541`, firstname `Tom`, lastname `Hanks`, gender `Male`, password `admin2`, confirm password `admin2`, email `tom541@gmail.com`, confirm email `tom541@gmail.com`, phone number `07550789121`, attach a CV called `CV.pdf`, attach a coversheet called `coversheet.pdf`. Click `Sign up`|  You are taken to your account settings |
| 3.1 | Go to /register    | The register page should appear |
| 3.2 | Click login        | The login screen should appear |
| 3.3 | Enter username `alice786` with password `password`. Click `Login` | You are taken to /admin |
| 3.4 | Login to your email account `temp124@124com`| There should be an email with an authentication code |
| 3.5 | Copy the authentication code in the email and submit it into the authentication window | You should see the adminstrator main menu|
| 3.5 | Click on Monitor | The monitor page should appear |
| 3.6 | Under create a new administrator, enter no username  with password `handsomegeoge` and email `george652@gmail.com`. Click `submit` | You are told to fill in a username |
| 3.7 | Under create a new administrator, enter username `george652` with no password and email `george652@gmail.com`. Click `submit` | You are told to fill in a password |
| 3.8 | Under create a new administrator, enter username `george652` with password `handsomegeoge` and no email. Click `submit` | You are told to fill in an email |
| 3.9 | Under create a new administrator, enter username `george652` with password `handsomegeoge` and email `george652@gmail.com`. Click `submit` | New admin account is created | 

# Test Results

## Test Run 12-Apr-2017
**Registration screen**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 1.10, 1.11, 2.1, 2.2, 2.3, 2.4 2.5, 2.6, 2.7, 2.8, 2.9, 2.10, 2.11, 2.12, 3.1, 3.2, 3.3, 3.4, 3.5, 3.6, 3.7, 3.8, 3.9
**Tests Failed:** None
