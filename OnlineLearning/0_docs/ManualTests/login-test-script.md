# Group 10 manual testing

# Tests

## Manual Test for Login



| Test | Step |
|----|:-----|
| 1.1 | On the homepage locate the login form.    |
| 1.2 | Enter username. |
| 1.3 | Enter your password. | 
| 1.4 | Click the login in button.|
| 1.5 | If you have only entered one of the fields and clicked the login button.|
| 1.6 | Then you are told to input the missing field.|
| 1.7 | If you have entered a correct username and password.|
| 1.8 | Then you are taken to the success login page.|
| 1.9 | If your credentials are incorrect then you are redirected to an error page.|

##
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/552666111b04e84304eeebd5b2415d3d5c94b311/0_docs/ManualTestEvidence/login.png)

## Login Screen

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Locate the homepage and find the login window    | There should be a login screen |
| 1.2 | Enter username `james786` with password `wrongpassword`. Click `Login` | Message says "Username or password is invalid" |
| 1.3 | Enter username `peter542` with password `oliver`. Click `Login` | Message says "Username or password is invalid" |
| 1.4 | Enter no username with password `oliver`. Click `Login` | Message says "Username or password is invalid"
| 1.5 | Enter username `james786` with no password. Click `Login` | Message says "Username or password is invalid" |
| 1.6 | Enter username `james786` with password `oliver`. Click `Login` | You are shown an authentication window |



## Test Run 12-Apr-2017
**Login screen**

**Tests Executed:** 1.1, 1.2, 1.3, 1.4, 1.5, 1.6
**Tests Failed:** None 
