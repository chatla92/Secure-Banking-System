/* Create database with table values */

CREATE DATABASE IF NOT EXISTS bank;
USE bank;
GRANT ALL PRIVILEGES ON bank.* To 'root'@'%' IDENTIFIED BY 'user' WITH GRANT OPTION;
CREATE TABLE IF NOT EXISTS internal_users (
  emp_id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  ssn varchar(9) DEFAULT NULL,
  email varchar(30) DEFAULT NULL,
  address varchar(400) DEFAULT NULL,
  zipcode varchar(5) DEFAULT NULL,
  gender varchar(1) DEFAULT NULL,
  contact_no varchar(10) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  role varchar(10) DEFAULT NULL,
  salary varchar(10),
  threshold varchar(10),
  PRIMARY KEY (emp_id)
);
CREATE TABLE IF NOT EXISTS external_users (
  user_id int(10) NOT NULL AUTO_INCREMENT,
  name varchar(50) DEFAULT NULL,
  ssn varchar(9) DEFAULT NULL,
  email varchar(30) DEFAULT NULL,
  address varchar(400) DEFAULT NULL,
  zipcode varchar(5) DEFAULT NULL,
  gender varchar(1) DEFAULT NULL,
  contact_no varchar(10) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL,
  password varchar(100) DEFAULT NULL,
  role varchar(10) DEFAULT NULL,
  threshold mediumtext,
  attempt int(11) DEFAULT '3',
  PRIMARY KEY (user_id),
  UNIQUE KEY user_name (user_name)
);
CREATE TABLE IF NOT EXISTS accounts (
  acc_id varchar(16) NOT NULL,
  type varchar(10) DEFAULT NULL,
  balance float check( balance>=0),
  user_id int(10) DEFAULT NULL,
  PRIMARY KEY (acc_id),
  FOREIGN KEY (user_id) REFERENCES external_users (user_id)
) ;
CREATE TABLE IF NOT EXISTS creditcards(
  card_no varchar(16) NOT NULL,
  acc_id varchar(16) DEFAULT NULL,
  cvv int(3) DEFAULT NULL,
  isActive boolean DEFAULT TRUE,
  maxlimit int default 2000,
  outstandingamount float NOT NULL,
  dueDate int(2) default 2,
  PRIMARY KEY (card_no),
  FOREIGN KEY (acc_id) REFERENCES accounts (acc_id)
);
CREATE TABLE IF NOT EXISTS debitcards  (
  card_no varchar(16) NOT NULL,
  acc_id varchar(16) DEFAULT NULL,
  cvv int(3) DEFAULT NULL,
  isActive boolean DEFAULT TRUE,
  PRIMARY KEY (card_no),
  FOREIGN KEY (acc_id) REFERENCES accounts (acc_id)
);
CREATE TABLE IF NOT EXISTS transactions(
  trans_id int(11) NOT NULL auto_increment,
  amount float(10),
  type varchar(3) check(type in("DEB" or "CRE" or "ACC")),
  from_acc varchar(16),
  to_acc varchar(16),
  iscritical boolean DEFAULT false,
  trans_status boolean Default false,
  approvedBy int(10) DEFAULT NULL,
  init_id int(10) DEFAULT NULL,
  PRIMARY KEY (trans_id)
);
CREATE TABLE IF NOT EXISTS pending_auth (
  trans_id int(11) DEFAULT NULL,
  role varchar(10) DEFAULT NULL,
  auth_state boolean,
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  FOREIGN KEY (trans_id) REFERENCES transactions (trans_id)
);
CREATE TABLE IF NOT EXISTS pending_auth_ext (
  trans_id int(11) DEFAULT NULL,
  acc_id varchar(16) DEFAULT NULL,
  isAuth boolean,
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  FOREIGN KEY (trans_id) REFERENCES transactions (trans_id),
  FOREIGN KEY (acc_id) REFERENCES accounts (acc_id)
);

CREATE TABLE IF NOT EXISTS modify_user_info (
  email varchar(30) DEFAULT NULL,
  address varchar(400) DEFAULT NULL,
  zipcode varchar(5) DEFAULT NULL,
  gender varchar(1) DEFAULT NULL,
  contact_no varchar(10) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL,
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  FOREIGN KEY (user_name) REFERENCES external_users (user_name)
);
/* Insert into dummy tuples into database*/

INSERT INTO internal_users VALUES (123,'Tier1 Employee','123456789','Tier1@gmail.com','East Uni','85281','M','943876765','Tier1','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Tier1','20000','2000');
INSERT INTO internal_users VALUES (NULL,'Tier2 Employee','123456789','Tier2@gmail.com','East Uni','85281','M','943876765','Tier2','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Tier2','20000','2000');
INSERT INTO internal_users VALUES (NULL,'Admin Employee','987654211','Admin@gmail.com','East Uni','85281','M','943876761','Admin','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','IA','20000','2000');
INSERT INTO external_users VALUES (123456789,'Dummy user','123456789','group.4.sbs@gmail.com','West Uni','85281','F','943876765','Dummyuser','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Ind','9000',3);
INSERT INTO external_users VALUES (123456790,'Amazon','123456789','Amazon@gmail.com','West Uni','85281','F','943876765','Amazon','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','MR','9000',3);
INSERT INTO accounts VALUES ('123456','Saving','8000',123456789);
INSERT INTO accounts VALUES ('456456','Saving','6000',123456789);
INSERT INTO accounts VALUES ('800000','Saving','80000',123456790);
INSERT INTO creditcards VALUES ('3212876765654612','123456',675,true,2000,0,5);
INSERT INTO debitcards VALUES ('9879876765654612','456456',235, true);
INSERT INTO transactions VALUES (120030,'800','ACC','456456','123',true, false,0,123456789);
INSERT INTO transactions VALUES (NULL,'800','ACC','456456','123',false, true,0,123456789);
INSERT INTO pending_auth VALUES (120030,123,1, NULL);
INSERT INTO pending_auth_ext VALUES (120031,'123456',1, NULL);
/*INSERT INTO modify_user_info VALUES ('Dummy_user@gmail.com', 'Changed Uni', '85281', 'F', '9444444444', 'Dummyuser', 1000)*/