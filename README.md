# ChatSystem_Java

**To install mysql client, server and the jdbc connector, write in a terminal :

$ sudo apt-get install mysql-server mysql-client libmysql-java

**Set up the password for the root:

$ sudo mysqladmin -u root password [NEWPASSWORD]



**In order to create the database of our application, launch a terminal in the folder ChatSystem_Java/ChatSystem/src/database and write:

$ sudo mysql -u root -p 

[enter root password]

mysql> source database.sql

mysql> exit


**Open the directory "ChatSystem" in Eclipse and run the class "Chat" (in the folder ChatSystem/src/main)
