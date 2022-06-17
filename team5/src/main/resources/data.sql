DROP SCHEMA IF EXISTS caps;

CREATE SCHEMA caps;

USE caps;

CREATE TABLE caps.user(
user_id VARCHAR(15) NOT NULL,
role enum('ADMIN', 'STUDENT', 'LECTURER') NOT NULL,
password VARCHAR(8) NULL,
PRIMARY KEY (user_id));

CREATE TABLE caps.admin(
user_id int NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NULL,
PRIMARY KEY (user_id));

CREATE TABLE caps.lecturer (
  lecturer_id VARCHAR(15) NOT NULL,
  lecturer_name VARCHAR(45) NULL,
  email VARCHAR(50) NULL,
  PRIMARY KEY (lecturer_id));
 
CREATE TABLE caps.course (
course_id int NOT NULL AUTO_INCREMENT,
code VARCHAR(45) NULL,
course_name VARCHAR(50) NULL,
course_days VARCHAR(50) NULL,
start_date DATE NULL,
description VARCHAR(300) NULL,
room VARCHAR(50) NULL,
credits DOUBLE NULL,
lesson_day VARCHAR(50) NULL,
fee DOUBLE NULL,
class_pax int NULL,
max_cap int NULL,
PRIMARY KEY (course_id));   
 
  
CREATE TABLE caps.lecturer_course(
    lecturer_id VARCHAR(15) NOT NULL,
    course_id int NOT NULL AUTO_INCREMENT,
    FOREIGN KEY (lecturer_id) REFERENCES caps.lecturer(lecturer_id), 
    FOREIGN KEY (course_id) REFERENCES caps.course(course_id),
    UNIQUE (lecturer_id, course_id)
);

CREATE TABLE caps.student(
student_id VARCHAR(15) NOT NULL,
student_name VARCHAR(50) NULL,
student_email VARCHAR(50) NULL,
start_date DATE NULL,
status_type enum('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRUADATEDs') NOT NULL,
GPA DOUBLE NULL,
PRIMARY KEY (student_id));


CREATE TABLE caps.Student_Course(
stu_course_id int NOT NULL AUTO_INCREMENT,
student_id VARCHAR(15) NOT NULL,
course_id int NOT NULL ,
FOREIGN KEY (student_id) REFERENCES caps.student(student_id),
FOREIGN KEY (course_id) REFERENCES caps.course(course_id),
UNIQUE (student_id, course_id),
score int NULL,
status_type enum('INPROGRESS','SUBMITTED', 'APPROVED', 'WITHDRAWN', 'UPDATED', 'REJECTED','GRUADATEDs') NOT NULL,
PRIMARY KEY (stu_course_id));

INSERT INTO caps.student
(student_id,
student_name,
student_email,
status_type)
VALUES
("S1",
"Mark Zuckerberg",
"Mark@team5caps.com",
"APPROVED"
);

INSERT INTO caps.student
(student_id,
student_name,
student_email,
status_type)
VALUES
("S2",
"Bill Gates",
"Bill@team5caps.com",
"APPROVED"
);

INSERT INTO caps.student
(student_id,
student_name,
student_email,
status_type)
VALUES
("S3",
"Dwayne Johnson",
"Dwayne@team5caps.com",
"APPROVED"
);

INSERT INTO caps.student
(student_id,
student_name,
student_email,
status_type)
VALUES
("S4",
"Elon Musk",
"Elon@team5caps.com",
"APPROVED"
);

INSERT INTO caps.student
(student_id,
student_name,
student_email,
status_type)
VALUES
("S5",
"LeBron James",
"LeBron@team5caps.com",
"APPROVED"
);

INSERT INTO caps.course
(code,
course_name,
course_days,
start_date,
description,
room,
credits,
lesson_day,
fee,
max_cap)
VALUES
("SA4102",
"Enterprise Solutions Design and Development",
"80",
"20220506",
"Attain knowledge on basic programming and objected oriented programming with C#",
"Beacon",
"8.0",
"Mon to Fri",
"3520",
"100");

INSERT INTO caps.course
(code,
course_name,
course_days,
start_date,
description,
room,
credits,
lesson_day,
fee,
max_cap)
VALUES
("SA4101",
"Software Analysis and Design",
"80",
"20220809",
"Ability to conduct user requirement through user experience design and agile practices; Analyse and design software solutions to solve business problems",
"Beacon",
"6.0",
"Mon to Fri",
"3520",
"100");

INSERT INTO caps.course
(code,
course_name,
course_days,
start_date,
description,
room,
credits,
lesson_day,
fee,
max_cap)
VALUES
("SA4110",
" Machine Learning Application Development",
"80",
"20221214",
"Understands popular machine learning models such as k-nearest neighbours, random forest, logistic regression, k-means, naïve Bayes and artificial neural network to build and evaluate performance of machine learning models using Python",
"Beacon",
"6.0",
"Mon to Fri",
"3520",
"100");

INSERT INTO caps.course
(code,
course_name,
course_days,
start_date,
description,
room,
credits,
lesson_day,
fee,
max_cap)
VALUES
("SA4105",
"Web Application Development",
"80",
"20230105",
"Learns the techniques and engineering skills needed for the end-to-end design, architecture, implementation, persistence and testing an enterprise web application",
"Beacon",
"6.0",
"Mon to Fri",
"3520",
"100");

INSERT INTO caps.course
(code,
course_name,
course_days,
start_date,
description,
room,
credits,
lesson_day,
fee,
max_cap)
VALUES
("SA4106",
" Mobile Application Development",
"80",
"20230405",
"Understanding Android programming framework, developing and packaging Android application using Android Studio",
"Beacon",
"6.0",
"Mon to Fri",
"3520",
"100");


INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("A1",
"admin",
"admin");  

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("S1",
"STUDENT",
"S1");

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("S2",
"STUDENT",
"S2");

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("S3",
"STUDENT",
"S3");

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("S4",
"STUDENT",
"S4");

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("S5",
"STUDENT",
"S5");


INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("L1",
"LECTURER",
"L1");


INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("L2",
"LECTURER",
"L2");
  
  
INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("L3",
"LECTURER",
"L3");

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("L4",
"LECTURER",
"L4");

INSERT INTO caps.user
(user_id,
role,
password)
VALUES
("L5",
"LECTURER",
"L5");


INSERT INTO caps.admin
(
name)
VALUES
(
"admin");  
  
INSERT INTO caps.lecturer
(lecturer_id,
lecturer_name,
email)
VALUES
("L1",
"Suria R Asai",
"Suria@team5caps.com");

INSERT INTO caps.lecturer
(lecturer_id,
lecturer_name,
email)
VALUES
("L2",
"Nguyen Tri Tin",
"Nguyen@team5caps.com");

INSERT INTO caps.lecturer
(lecturer_id,
lecturer_name,
email)
VALUES
("L3",
"Tan Cher Wah",
"Tan@team5caps.com");

INSERT INTO caps.lecturer
(lecturer_id,
lecturer_name,
email)
VALUES
("L4",
"Yuen Kwan",
"Yuen@team5caps.com");


INSERT INTO caps.lecturer
(lecturer_id,
lecturer_name,
email)
VALUES
("L5",
"Tan Meng Yoke, Esther",
"Esther@team5caps.com");