# Group 10 manual testing

# Tests

![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/authenticationTest.png)

| Test | Step |
|----|:-----|
| 1.1 | On the homepage locate the login form.    |
| 1.2 | Enter username. |
| 1.3 | Enter your password. | 
| 1.4 | Click the login in button.|
| 1.5 | Check the email in which you registered the account.|
| 1.6 | Copy the authetication code.|
| 1.7 | If you have entered an incorrect authentication code then you are told it is wrong.|
| 1.8 | If you have entered a correct authentication code.|
| 1.9 | Then you are taken to the success login page.|

## Authentication Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /login    | The login screen should appear |
| 1.2 | Enter username `james786` with password `oliver`. Click `Login` | An authentication prompt should appear |
| 1.3 | Login to your email `temp@124com`. | You should see an email with the authentication code |
| 1.4 | Copy the code | |
| 1.5 | Enter a wrong code into the authentication prompt | You are told the code is incorrrect |
| 1.6 | Enter the code found in the email | You are taken to the success login page |

## Test Run 12-Apr-2017
**Two Factor Authentication**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6
**Tests Failed:** None 
