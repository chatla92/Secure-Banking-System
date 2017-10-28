-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `internal_users`
--

DROP TABLE IF EXISTS `internal_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `internal_users` (
  `emp_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `ssn` varchar(9) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `address` varchar(400) DEFAULT NULL,
  `zipcode` varchar(5) DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `contact_no` varchar(10) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `salary` varchar(10) DEFAULT NULL,
  `threshold` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `internal_users`
--

LOCK TABLES `internal_users` WRITE;
/*!40000 ALTER TABLE `internal_users` DISABLE KEYS */;
INSERT INTO `internal_users` VALUES (123,'Tier1 Employee','123456789','Tier1@gmail.com','East Uni','85281','F','943876765','Tier1','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Tier1','20000','2000'),(124,'Tier2 Employee','123456789','Tier2@gmail.com','East Uni','85281','M','943876765','Tier2','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','Tier2','20000','2000'),(125,'Admin Employee','123456789','Admin@gmail.com','East Uni','85281','M','943876765','Admin','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','IA','20000','2000'),(127,'Admin2','3456789:2','Admin2@gmail.com','East Uni','98765','M','0987654321','Admin2','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','IA','80000','2000'),(128,'Admin3','3456789:;','Admin3@gmail.com','India','09877','M','9876543215','Admin3','a1159e9df3670d549d04524532629f5477ceb7deec9b45e47e8c009506ecb2c8','IA','80000','2000');
/*!40000 ALTER TABLE `internal_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-27 22:57:58
