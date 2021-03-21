# RABBIT-SUBSCRIBER Project
This project is a "look-around" project about connecting to RabbitMQ Broker as a subscriber.
By the way, this is test for getting properties in Spring Boot from dedicated module on JBoss EAP 7.2

### JBoss EAP 7.2 Configuration
Working configuration for this project is inside the directory jbossServerRoot under project root. 
File with configuration : host.xml should be considered as a minimal modification for fresh (in the meaning : starting, original) configuration provided with JBoss EAP 7.2 .
There is also module ( named "rabbitsubscriber-config" ) providing structure having PROPERTIES files.

#### SQL / Database structure
create table rabbit.messages
(
id number primary key ,
msg_content varchar2(2000) not null
);*/

create sequence msg_seq increment by 1 CACHE 10 NOCYCLE start with 1;