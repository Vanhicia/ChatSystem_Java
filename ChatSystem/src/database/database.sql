-- commands :
-- sudo mysql -u root
-- source database.sql
-- exit
-- mysql chat_system -u chat_user -p

CREATE DATABASE chat_system;
CREATE USER 'chat_user'@'localhost' IDENTIFIED BY 'pwd';
GRANT ALL ON chat_system.* TO 'chat_user'@'localhost' IDENTIFIED BY 'pwd';
