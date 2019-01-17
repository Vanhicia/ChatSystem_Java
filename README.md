ChatSystem_Java
===================================

Prerequisites
-------------------

#### 1. MySQL

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
#### 2. Presence server 

*NOTE : If you use Eclipse, check that supports dynamic projects in others word, check if you use Eclipse for Java EE or not.
If not, you should install it. Go to this following link, to see how to install it : `https://www.eclipse.org/downloads/` *

Application
-------------------
Open `ChatSystem` directory in Eclipse and run `Chat.java` (You will find it in `ChatSystem/src/main` folder. )

*NOTE : To chat you should have at least two computers*
