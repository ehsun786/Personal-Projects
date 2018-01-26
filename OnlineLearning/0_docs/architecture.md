# Software Architecture Design

## Group 10 Software Engineering Project

##### Anthony Wong, Akio Heltmann, Brent Rendall, Cecelia Wisniewska, Chengzong Yang, Danial Qamar, Ehsun M. Hanif, ChakFung Ng

## Introduction
This document provides an extensive view of the architecture, technologies, and resources that we will use in creating an eLearning system. Any alternative approaches that we will use during development will be mentioned, with reasoning, at the end of this document. 

### Architectural Representation
This section will represent the architecture using views defined by the "4+1" model.

#### <b>Use Case view</b>
<b>Audience:</b> All stakeholders including end-users.

<b>Area:</b> These are the set of scenarios that describe the central functionality of the system. It describes the actors, use-cases of the system, and presents the user's needs.

#### <b>Development view</b>
<b>Audience:</b> Back-end programmers

<b>Area:</b> This is the component diagram that gives the developers view of the system.

## Architectural Goals and Constraints

### Languages, Libraries and Testing Technologies 
* jQuery for creating interactive and attractive websites that contain original animations.
* Cucumber testing will be used to generate automated tests. 
* Bootstrap is a CSS/JS responsive framework that allows the site to be accessed on a mobile device.
* BCrypt is a password hashing function.
* ArcGIS is an external library in making interactive/scalable maps, used in integrating OpenStreetMaps.
* Amazon AWS is the cloud storage service for storing and retrieving all multimedia.
* Video JS is a web video framework used to allow users to view course videos uploaded by the lecturer as part of the course content.

### Software Design Patterns
- The software design pattern is the Model-View-Controller format. The Model layer is the domain classes which will persist as entities in the SQL database by using JPA annotations. The Controller layer will handle the business logic, such as using the domain classes to store data into the database and retrieve data from the database. The controllers will use Request-Mappings to handle logic inside of and reroute the user around web pages. The Presentation layer will consist of web pages, known as views. The user will be interacting with these views. The way the Back-End developers will access the database entities to store and retrieve data will be to use the Repository layer. Each repository will have a one to one relationship between the entities. Thus, every domain class that is persisting in the database will have its' own individual repository. An Amazon AWS bucket will be used to store and retrieve information from the database; this is visually represented by the following file structure and diagram.

<b>File structure</b>

![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/file%20structure.png)
            
<b>Spring MVC Framework</b>
![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/spring%20mvc.png "Spring MVC")

### Server side
We will use the Apache Tomcat Server to host the fully functional eLearning web application; this server will be running on a Linux platform because research suggests that Linux is relatively more secure than using Windows. Currently, the site is not being hosted as our aim is to develop a website that meets the mandatory criteria before reaching that stage. At the moment we currently access the site, after the command "./gradlew bootRun" is successful on the command line, with the following link "https<nolink>://localhost:3006". A new Secure Sockets Layer(SSL) certificate was generated to secure the transfer of information over the website.

### APIs
* Java Persistence API (JPA) will be used to create tables based on domain classes in an SQL database. The API will allow the Back-End developers to access data from the database as well as to store data into the database. The API along with the Hibernate technology will allow the Back-End developers to not worry about any lower level details regarding the structure of entities within the database as the code for creating the database is implemented in the Java classes(domains). Furthermore, the API is easy to understand and use.
* Java Mail API will be used to send emails in this system. An example of where this is currently used is if a user has forgotten his password, he is sent an email containing a temporary password to sign into his account.
* WebSockets is a communications protocol for use in the messaging feature. At the moment all development with WebSockets has been halted due to time constraints. This was part of the chat system that was considered to be implemented. The explanation is shown in the respnding to change file.
* Google charts is an interactive web service which is used for adminitrators to generate charts to represent data on Learners and Lecturers.
* We have also implemented Amazon Web Services API as the server side technolgies. The specific technolgies used, which are part of the AWS stack, are Amazon S3 Storage service for cloud storage.

### Security
Security will be implemented using Spring Security; this will allow the system to authorise and authenticate requests based on the type of user for activities such as logging in. Throughout the entire system this technique will be used, where we will place restrictions upon viewing content between different users, as well as functionality. For example, an administrator can remove a learner or lecturer account; however, learners and lecturers cannot use this functionality. Furthermore, all inputs will be validated to check that no ambiguous data gets entered. Another way in which the system would be secure is by encrypting the passwords which are stored in the database using the BCryptPasswordEncoder; this will ensure that the integrity of sensitive user information will not become compromised. We have used JPA with parameterized queries using the repositories which will help us to avoid penetration attacks such as XSS attacks and SQL injections. If we were not using parameterized queries then we would have used a mechanism to prevent a user entering escape characters. This would've been achieved by setting up a bean in the spring mvc configuration file.

### Persistence
Data about entities will be stored in a database using MySQL. MySQL database servers are scalable, and the open source nature of MySQL allows to customise unique requirements to suit both the admin and the client's needs. Java Persistence API will bridge the gap between (Model-View-Controller) MVC framework and the database; this is because it will help us to save time in writing code to create the databases and the tables for those databases.

### Performance
The following are the initial plans to maximising the performance of the system. 
- Linking external stylesheets, instead of using the import statement, we will use the link statement instead. The link statement allows resources to be downloaded in parallel with others, allowing faster page loading times
- For data on the site such as images, we will be employing a technique called the "data URI scheme". URI(uniform resource identifier) allows us to include data as if they were external resources, by fetching the image data from the CSS sheet directly rather than from other resources. This technique efficiently reduces the number of HTTP requests done when accessing the app; thus it improves overall performance. This method is particularly useful for devices accessing the site through mobile data and underpowered devices. 
- Amazon AWS services will be used to store information in the cloud, so as to avoid always requesting and retrieving from an SQL database. This approach reduces stress on the database for fetching the BLOB objects from the database thus improving the performance immensely
- Code will continually be reviewed to reduce any unnecessary code such as white-space, to maximise the performance of the system in its entirety.

### Use-case view
The following is a Use Case view that shows the final functionality of the mandatory requirements

![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/Use%20Case%20Diagram.png "Use Case Viewm")

### Development view
The following is a component diagram of the mandatory requirements of the system

![alt text](https://github.com/UOL-CS/co2015-16-17-group-10/blob/master/0_docs/Component%20diagram.png "Component View")

## New Changes

### Changed files
Updated [Risk-log](https://github.com/UOL-CS/co2015-16-17-group-10/commit/89b9d8e4c5b4d7788bc22dfd1ecf659c7cb68b28#diff-e1b5d3df89671d472400ca80dff1907e)

Updated [Product back-log](https://github.com/UOL-CS/co2015-16-17-group-10/commit/89b9d8e4c5b4d7788bc22dfd1ecf659c7cb68b28#diff-e1b5d3df89671d472400ca80dff1907e) however viewing from this [commit](https://github.com/UOL-CS/co2015-16-17-group-10/commit/a77e8b9732045d7fbdc30c57091ac37944f87b68) is recommended due to a problem with the earlier commit, the changes are present between Orders 290 and 300.

### Changed Database Architecture
The relationships between database entities such as Courses and Learners. Lecturers and Meetups have been changed few times in order to achieve a better database design and also to avoid getting errors while running the web application. The ammendments to the relationships lead to more efficient code as the ability to retrieve information from the database through multiple entity relationships was easier.

### Changed The Technology for Two Factor Authentication
We also planned to use Amazon Web Services SNS(Simple Notification Service) in order to send the security code for two factor authentication. But due to the complexity of the service and time constraints, we instead used the Java Mail API service which we were already using for Worksheet 2.

### Decided not to implement the chat system
We decided to build the chat system and the technolgies we thought to use were AJAX and JSON. As this was taking too long, we decided that we will instead work on building a personalised notificatian system for learners and implement a support for learners that have a would have accessibility issues. So a learner who is colour blind, now has the option to change their pages to grayscale.

### Changed The Map Technology for Open Street Maps
We initially decided to use Leaflet JS in order to implement the Open Street Maps but we used ArcGIS instead. This was because implementing Leaflet JS required us to implement complex route finding algorithms. ArcGIS saved us time to work on other aspects of the websites such as implementing a reviews feature and a ratings feature for the web application. Also as ArcGIS does not permit developers to access the iframe, this meant we could not access the input type to automatically show the user their location when the page loads. Instead we provide the user with a pop up, which contains all the information regarding the meetup that they can enter and recieve turn by turn directions on the web application.

###Java Mail API
Java Mail API was used before worksheet 3 for the development of features such as restting of passwords in worksheet 2. This was a decision that was successful as it was used for many other features such as 2 factor authentication, allowing lecturers to be able to notify all their learners about anything they wish, notifications for new courses as well as the contact us section on the homepage. 
