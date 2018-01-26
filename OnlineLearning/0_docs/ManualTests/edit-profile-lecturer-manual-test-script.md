 Group 10 manual testing

# Tests

| Test | Step |
|----|:------|
| 1.1 | As a lecturer, locate the homepage|
| 1.2 | Click the button labelled with your username where it says "Welcome {username}", a pop-up should appear|
| 1.3 | Enter first name|
| 1.4 | Enter last name|
| 1.5 | Select gender from drop-down|
| 1.6 | Enter password|
| 1.7 | Enter the same value for confirm password|
| 1.8 | Enter email|
| 1.9 | Enter the same value for confirm email|
| 2.0 | Click the button labelled "Edit"|
| 2.1 | Pop-up will close and you will see the lecturer homepage|
| 2.2 | If a field has been missed an error will appear asking you to enter the missing field, or if a field does not pass the validation, an error will appear asking you to correct the field|


## Edit Lecturer Profile Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 1.2 | Click the button labelled with username | A pop-up should appear |
| 1.3 | Enter no first name with last name `Smith`, select gender `Male`, enter password `admin1`, confirm password `admin1`, enter email `BoatyMcBoatFace@gmail.com`, confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Error appears: `First name is required`|
| 1.4 | Enter first name `Bob`, do not enter last name, select gender `Male`, enter password `admin1`, confirm password `admin1`, enter email `BoatyMcBoatFace@gmail.com`, confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Error appears: `Last name is required`|
| 1.5 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, do not enter password field, enter in confirm password field `admin1`, enter email `BoatyMcBoatFace@gmail.com`, confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Errors appear: `Please enter password` and `Passwords do not match`|
| 1.6 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, enter password `admin1`, do not enter confirm password, enter email `BoatyMcBoatFace@gmail.com`, confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Error appears: `Please confirm your password`|
| 1.7 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, enter password `admin1`, confirm password `admin1`, do not enter email, enter confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Error appears: `Email is required` and `Emails do not match`|
| 1.8 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, enter password `admin1`, confirm password `admin1`, enter email `BoatyMcBoatFace@gmail.com`, do not enter confirm email, click `Edit`| Error appears: `Please confirm your email`| 
| 1.9 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, enter password `admin`, confirm password `admin`, enter email `BoatyMcBoatFace@gmail.com`, enter confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Error appears: `Password must be at least 6 characters`| 
| 2.0 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, enter password `admin1`, confirm password `admin1`, enter email `BoatyMcBoatFace`, enter confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| Errors appear: `Please enter a valid email address` and `Emails do not match`|
| 2.1 | Enter first name `Bob`, enter last name `Smith`, select gender `Male`, enter password `admin1`, confirm password `admin1`, enter email `BoatyMcBoatFace@gmail.com`, enter confirm email `BoatyMcBoatFace@gmail.com`, click `Edit`| You are taken to lecturer homepage, profile information is updated|

Missing first name
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerMissingFName.png)

Missing last name
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerMissingLName.png)

Missing password
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerMissingPassword.png)

Missing confirm password
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerMissingConfirmPassword.png)

Missing email 
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerMissingEmail.png)

Missing confirm email
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerMissingConfirmEmail.png)

Password must be at least 6 characters
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerPasswordTooShort.png)

Invalid email
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/309a5128a03134d51494f1807b323c13eab5c692/0_docs/ManualTestEvidence/lecturerEmailNotValid.png)

## Test Run 29-Apr-2017
**Edit Lecturer Profile Screen**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6, 1.7, 1.8, 1.9, 2.0, 2.1
**Tests Failed:** None 
