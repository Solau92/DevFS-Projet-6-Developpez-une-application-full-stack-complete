# **ReadMe** 

#  -- **MDD (Monde de Dév)** --

A social network for developers ! </br>
The front part of the app uses Angular framework to run.
The back part of the app uses Java and Spring Boot framework to run.

## **Getting Started**

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

First, get [this repository](https://github.com/Solau92/DevFS-Projet-6-Developpez-une-application-full-stack-complete.git) and import the project on your computer. 


##  - **Back part of the app** - 

### **Prerequisites**

You need to install : 
* Java 11
* Maven 4.0.0
* MySQL 8.0.31

### **Installing** 

* [Install Java](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
* [Install Maven](https://maven.apache.org/install.html)
* [Install MySQL](https://dev.mysql.com/downloads/mysql/)

After downloading the mysql 8 installer and installing it, you will be asked to configure the password for the default root account.

#### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.2/reference/htmlsingle/#web)

#### Guides

The following guides illustrate how to use some features concretely:

* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)

### **Running the app** 

Post installation of MySQL, Java and Maven, you must create the database. 

The default name of the database is "mdd", but if you want to change it, modify the *application.properties* file (in the *./back/src/main/resources* folder) and replace "mdd" in the property "spring.datasource.url" by the name you choose for your database.

For demonstration purpose, data will be inserted in the database at the lauching of the app. 

To run the app, go to the folder that contains the pom.xml file (*./back*) and execute the following command in which you have to replace "*%username%*" by your username and "%*password*%" by your password required to access your database : 
 `mvn spring-boot:run "-Dspring-boot.run.arguments=--spring.datasource.username=%username% --spring.datasource.password=%password%"`


## - **Front part of the app** -

### Technology

This project uses [Angular](https://angular.io/) version 14.0.0.


### **Installing** 

Go to the folder that contains the angular.json file (*./front*) and install the node-module by running the command : `npm install`.

#### Reference Documentation

- [Angular documentation](https://angular.io/docs)
- [Npm documentation](https://docs.npmjs.com/)
- [Node JS documentation](https://nodejs.org/docs/latest/api/)

### **Running the app** 

Run the `ng serve` command.

And navigate to http://localhost:4200. 

There are for user in the demonstration database, whose email adresses are : 
   * user1@email.com, user2@email.com, user3@email.com, user4@email.com
   * passwords : 11aaAA@@


## - **Version** -

1.0.0