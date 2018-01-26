 Group 10 manual testing

# Tests

| Test | Step |
|----|:------|
| 1.1 | As a learner, locate the homepage|
| 1.2 | Click the profile picture in centre of page, a pop-up should appear|
| 1.3 | Enter first name|
| 1.4 | Enter last name|
| 1.5 | Select gender from drop-down|
| 1.6 | Enter password|
| 1.7 | Enter the same value for confirm password|
| 1.8 | Enter email|
| 1.9 | Enter the same value for confirm email|
| 2.0 | Click the button labelled "Edit"|
| 2.1 | Pop-up will close and you will see the learner homepage|
| 2.2 | If a field has been missed an error will appear asking you to enter the missing field, or if a field does not pass the validation, an error will appear asking you to correct the field|


## Edit Learner Profile Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /learner  | The learner homepage should appear  |
| 1.2 | Click the profile picture | A pop-up should appear |
| 1.3 | Enter no first name with last name `Croyden`, select gender `Male`, enter password `oliver`, confirm password `oliver`, enter email `hello@hotmail.co.uk`, confirm email `hello@hotmail.co.uk`, click `Edit`| Error appears: `First name is required`|
| 1.4 | Enter first name `James`, do not enter last name, select gender `Male`, enter password `oliver`, confirm password `oliver`, enter email `hello@hotmail.co.uk`, confirm email `hello@hotmail.co.uk`, click `Edit`| Error appears: `Last name is required`|
| 1.5 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, do not enter password field, enter in confirm password field `oliver`, enter email `hello@hotmail.co.uk`, confirm email `hello@hotmail.co.uk`, click `Edit`| Errors appear: `Please enter password` and `Passwords do not match`|
| 1.6 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, enter password `oliver`, do not enter confirm password, enter email `hello@hotmail.co.uk`, confirm email `hello@hotmail.co.uk`, click `Edit`| Error appears: `Please confirm your password`|
| 1.7 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, enter password `oliver`, confirm password `oliver`, do not enter email, enter confirm email `hello@hotmail.co.uk`, click `Edit`| Error appears: `Email is required` and `Emails do not match`|
| 1.8 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, enter password `oliver`, confirm password `oliver`, enter email `hello@hotmail.co.uk`, do not enter confirm email, click `Edit`| Error appears: `Please confirm your email`| 
| 1.9 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, enter password `olive`, confirm password `olive`, enter email `hello@hotmail.co.uk`, enter confirm email `hello@hotmail.co.uk`, click `Edit`| Error appears: `Password must be at least 6 characters`| 
| 2.0 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, enter password `oliver`, confirm password `oliver`, enter email `hello`, enter confirm email `hello@hotmail.co.uk`, click `Edit`| Errors appear: `Please enter a valid email address` and `Emails do not match`|
| 2.1 | Enter first name `James`, enter last name `Croyden`, select gender `Male`, enter password `oliver`, confirm password `oliver`, enter email `hello@hotmail.co.uk`, enter confirm email `hello@hotmail.co.uk`, click `Edit`| You are taken to learner homepage, profile information is updated|

Missing first name
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerMissingFName.png)

Missing last name
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerMissingLName.png)

Missing password
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerMissingPassword.png)

Missing confirm password
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerMissingConfirmPassword.png)

Missing email 
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerMissingEmail.png)

Missing confirm email
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerMissingConfirmEmail.png)

Password must be at least 6 characters
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerPasswordTooShort.png)

Invalid email
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/learnerEmailNotValid.png)

## Test Run 30-Apr-2017
**Edit Learner Profile Screen**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0, 2.1
**Tests Failed:** None 
