/* Create database with table values */

CREATE DATABASE IF NOT EXISTS bank;
USE bank;
GRANT ALL PRIVILEGES ON bank.* To 'root'@'%' IDENTIFIED BY 'user' WITH GRANT OPTION;
CREATE TABLE IF NOT EXISTS internal_users (
  emp_id int(10) NOT NULL,
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
  salary mediumtext,
  priv varchar(2) DEFAULT NULL,
  threshold mediumtext,
  PRIMARY KEY (emp_id)
);
CREATE TABLE IF NOT EXISTS external_users (
  user_id int(10) NOT NULL,
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
  balance mediumtext,
  user_id int(10) DEFAULT NULL,
  PRIMARY KEY (acc_id),
  FOREIGN KEY (user_id) REFERENCES external_users (user_id)
) ;
CREATE TABLE IF NOT EXISTS cards (
  card_no varchar(16) NOT NULL,
  type varchar(10) DEFAULT NULL,
  acc_id varchar(16) DEFAULT NULL,
  cvv int(11) DEFAULT NULL,
  isActive boolean,
  PRIMARY KEY (card_no),
  FOREIGN KEY (acc_id) REFERENCES accounts (acc_id)
);
CREATE TABLE IF NOT EXISTS transactions (
  trans_id int(11) NOT NULL,
  amount mediumtext,
  type varchar(2) DEFAULT NULL,
  from_acc mediumtext,
  to_acc mediumtext,
  isActive boolean DEFAULT NULL,
  emp_id int(10) DEFAULT NULL,
  init_id int(11) DEFAULT NULL,
  PRIMARY KEY (trans_id),
  FOREIGN KEY (emp_id) REFERENCES internal_users (emp_id)
);
CREATE TABLE IF NOT EXISTS pending_auth (
  trans_id int(11) DEFAULT NULL,
  emp_id int(10) DEFAULT NULL,
  auth_state boolean,
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  FOREIGN KEY (emp_id) REFERENCES internal_users (emp_id),
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

/* Insert into dummy tuples into database*/

INSERT INTO internal_users VALUES (123,'Default Employee','123456789','DefaultEmployee@gmail.com','East Uni','85281','M','943876765','Default_Employee','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Tier1','20000','IA','2000');
INSERT INTO external_users VALUES (456,'Dummy user','123456789','Dummy_user@gmail.com','West Uni','85281','F','943876765','Dummy_user','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Ind','9000',3);
INSERT INTO accounts VALUES ('123456','Saving','8000',456);
INSERT INTO cards VALUES ('98798767656546','Debit','123456',675, true);
INSERT INTO transactions VALUES (1234567,'800','In','456','123',1,123,456);
INSERT INTO pending_auth VALUES (1234567,123,1,1);
INSERT INTO pending_auth_ext VALUES (1234567,'123456',1,1);