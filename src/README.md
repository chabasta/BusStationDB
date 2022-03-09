# How to use this skeleton in your assignment 

## Introduction
This is a maven project, the configuration of lybrary/jar dependecies 
can be found in the pom.xml file in the root folder. Both NetBeans and 
IntelliJ IDEA IDEs support import of maven projects.

## Change pom.xml 
First change the pom.xml configuration, change the groupId, artifactId, 
name and description elements to match your assignment. This file defines
the project dependencies. 

## Configure database  
The connection to the database can be configured in the persistence unit 
configuration file found at: 
* src/main/resources/META-INF/persistence.xml

## Code Structure
The application has some sample code to test the configuration of the JPA.
* **cvut.fel.dbs.lib.model** - contains the entities mapped to the 
database tables
* **cvut.fel.dbs.lib.dao** - contains data access objects. 
* **cvut.fel.dbs.Application** - this is the main application which 
lists all users in the DB and then generates 10 new users.

## Testing
The tests have their own persistent unit config file:
* src/test/resources/META-INF/persistence.xml

The tests are configured to use the in-memory H2 database. 

