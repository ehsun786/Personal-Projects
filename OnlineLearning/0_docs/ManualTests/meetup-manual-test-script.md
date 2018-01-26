 Group 10 manual testing

# Tests

| Test | Step |
|----|:-----|
| 1.1 | On the lecturer homepage locate the create Meetup button
| 1.2 | Enter a description.
| 1.3 | Enter a building number.
| 1.4 | Enter a street.
| 1.5 | Enter a postcode.
| 1.6 | Enter a date.
| 1.7 | Enter an hour.
| 1.8 | Enter minutes.
| 1.9 | Enter an estimated hours.
| 1.10 | Enter an estimated minutes.
| 1.11 | Select a course.
| 1.12 | Click create.
| 1.13 | The meetup should now exist.

## Meetups Testing

| Test | Step | Expected Outcome |
|----|:-----|:-----|
| 1.1 | Go to /lecturer  | The lecturer homepage should appear  |
| 1.2 | Click create meetups | The form for create meetups should appear |
| 1.3 | Enter no description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter a description |
| 1.4 | Enter `meet` description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter a description of at least 10 characters long|
| 1.5 | Enter `short meeting to discuss the project` description with no building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter a building number |
| 1.6 | Enter `short meeting to discuss the project` description with `25` building number, no street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter a street name |
| 1.7 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, no postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter a postcode |
| 1.8 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, `LE1 7RH` postcode, no day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter a day |
| 1.9 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, no hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter hours |
| 1.10 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, no minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter minutes |
| 1.11 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, no estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter estimated hours |
| 1.12 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, no estimated minutes and for course `Risk Fundamentals`. Click Create. | You are told to enter estimated minutes |
| 1.13 | Enter `short meeting to discuss the project` description with `25` building number, `University road` street, `LE1 7RH` postcode, `12/04/2017` day, `15` hours, `20` minutes, `1` estimated hours, `10` estimated minutes and for course `Risk Fundamentals`. Click Create. | You should see the new meetup created |

##
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/ManualTestEvidence/LecturerCreateMeetup.png)

## Test Run 30-Apr-2017
**Meetups Testing**

**Tests Executed:** 1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9,1.10,1.11,1.12,1.13
**Tests Failed:** None
