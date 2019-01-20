Chat System
===================================
This repository contains a Java chat application. It is a 4th-year project for students in Computer Science at INSA Toulouse (France)

Autors
-------------------
* [Kim-Anh TRAN](mailto:katran@etud.insa-toulouse.fr)
* [Alicia Vanhulle](mailto:vanhulle@etud.insa-toulouse.fr)

Prerequisites
-------------------
#### 1. OS & Java vers.
Linux, Window or MacOS
Java 1.8.0 (1.8.0_191 preferred)

#### 2. MySQL

To install mysql client, server and the jdbc connector, write in a terminal :
```bash
$ sudo apt-get install mysql-server mysql-client libmysql-java
```

Set up the password for the root:
```bash
$ sudo mysqladmin -u root password [NEWPASSWORD]
```
In order to create the database of our application, launch a terminal in the folder ChatSystem_Java/ChatSystem/src/database and write:
```bash
$ sudo mysql -u root -p 

[enter root password]
```
In MySQL, write : 
```mysql
mysql> source database.sql
mysql> exit
```
#### 3. Presence server 

*NOTE : If you use Eclipse, check that it supports dynamic web projects in other words, check if you use Eclipse for Java EE or not.
If not, you should install it. Go to this following link to see how to install it : `https://www.eclipse.org/downloads/`*

Install a server such as Tomcat. `Servers` folder should to do this. 

If it does not work, unzip `apache-tomcat-9.0.14.zip` file.

When you run `userLogin.jsp` file, you will have `Run On Server` window that will pop up. 

Click on `Manually define a new server`. 

Select Apache > Tomcat v9.0 Server. 

Click on `Next` then `Browse` and add the directory Apache that you have just unzipped. 

Click on `Finish`.

#### 4. Recurrent errors

__Error :__`The superclass "javax.servlet.http.HttpServlet" was not found on the Java Build Path`

You should add `javax.servlet_3.0.0.v201112011016.jar` in your libaries.

In Eclipse, Project > Properties > Java Build Path > Libraries > Add External JARs 


Application
-------------------
#### 1. Installation

#### 2. Execution
Open `ChatSystem` directory in Eclipse and run `Chat.java` (You will find it in `ChatSystem/src/main` folder. )



#### 3. Usage
*NOTE : To chat you should have at least two computers*
