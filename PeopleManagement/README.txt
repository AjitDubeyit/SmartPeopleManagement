Step 1> create Database and table insert records
CREATE DATABASE "People";
USE People;

CREATE TABLE PEOPLE (people_id int(20) NOT NULL AUTO_INCREMENT, first_name varchar(20) NOT NULL, last_name varchar(20) NOT NULL, email_address varchar(20) NOT NULL, PRIMARY KEY (people_id));

insert into PEOPLE values(1,'Ajit','Dubey','ajitd774@gmail.com');


Step 2> Import maven project

step 3> add tomcat as server and start the application

step 4> hit below mentioned url: http://localhost:8080/PeopleManagement/list

