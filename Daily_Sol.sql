CREATE DATABASE  IF NOT EXISTS `milkmanassistant` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `milkmanassistant`;
-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: milkmanassistant
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `billgeneration`
--

DROP TABLE IF EXISTS `billgeneration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `billgeneration` (
  `cname` varchar(30) DEFAULT NULL,
  `sdate` date DEFAULT NULL,
  `edate` date DEFAULT NULL,
  `cowq` float DEFAULT NULL,
  `buffq` float DEFAULT NULL,
  `cowamnt` float DEFAULT NULL,
  `buffamnt` float DEFAULT NULL,
  `total` float DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `paydate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billgeneration`
--

LOCK TABLES `billgeneration` WRITE;
/*!40000 ALTER TABLE `billgeneration` DISABLE KEYS */;
INSERT INTO `billgeneration` VALUES ('Aman','2017-07-01','2017-07-07',16,7,640,266,906,0,NULL),('Deep','2017-07-01','2017-07-07',0,3,0,105,105,0,NULL),('Aman','2017-06-01','2017-06-30',12,0,420,0,420,0,NULL),('Sham','2017-06-01','2017-06-30',50,49,1750,1568,3318,0,NULL),('Sham','2017-07-01','2017-07-31',42,45,1470,1440,2910,1,'2017-07-10');
/*!40000 ALTER TABLE `billgeneration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `cname` varchar(30) NOT NULL DEFAULT '',
  `mobile` varchar(10) DEFAULT NULL,
  `address` varchar(300) DEFAULT NULL,
  `locality` varchar(50) DEFAULT NULL,
  `cowq` float(11,0) DEFAULT NULL,
  `buffq` float(11,0) DEFAULT NULL,
  `dos` date DEFAULT NULL,
  PRIMARY KEY (`cname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('Aman','9872514150','784, Bharat Nagar, Bathinda','Bharat Nagar',3,0,'2017-07-05'),('Deep','9972544130','450,Baba Farid Nagar,Bathinda','Baba Farid Nagar',0,3,'2017-07-02'),('Kavi','7888713278','373, Shant Nagar, Bathinda	','Shant Nagar',0,4,'2017-07-14'),('Rajeev','9978523150','470,Baba Farid Nagar,Bathinda','Baba Farid Nagar',4,0,'2017-07-03'),('Raman','9872514178','790, Bharat Nagar, Bathinda','Bharat Nagar',3,2,'2017-07-05'),('Ravi','9988442021','420,Baba Farid Nagar,Bathinda','Baba Farid Nagar',5,0,'2017-07-04'),('Sham','7888714520','455, Shant Nagar, Bathinda','Shant Nagar',2,0,'2017-07-05');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dailyupdate`
--

DROP TABLE IF EXISTS `dailyupdate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `dailyupdate` (
  `cname` varchar(30) NOT NULL DEFAULT '',
  `cowq` float(11,2) DEFAULT NULL,
  `buffq` float(11,2) DEFAULT NULL,
  `date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dailyupdate`
--

LOCK TABLES `dailyupdate` WRITE;
/*!40000 ALTER TABLE `dailyupdate` DISABLE KEYS */;
INSERT INTO `dailyupdate` VALUES ('Aman',3.00,0.00,'2017-07-05'),('Aman',4.50,0.00,'2017-07-04'),('Aman',0.00,3.50,'2017-07-03'),('Aman',3.50,3.50,'2017-07-02'),('Aman',5.00,0.00,'2017-07-01'),('Sham',5.00,6.00,'2017-07-06'),('Deep',0.00,3.00,'2017-07-06'),('Aman',3.00,0.00,'2017-06-07'),('Aman',3.00,0.00,'2017-06-06'),('Aman',3.00,0.00,'2017-06-07'),('Aman',3.00,0.00,'2017-06-07'),('Sham',8.00,5.00,'2017-07-06'),('Sham',10.00,10.00,'2017-07-06'),('Sham',6.00,10.00,'2017-07-06'),('Sham',9.00,10.00,'2017-07-06'),('Sham',2.00,4.00,'2017-07-06'),('Sham',2.00,0.00,'2017-07-06'),('Sham',10.00,5.00,'2017-06-06'),('Sham',12.00,8.00,'2017-06-06'),('Sham',5.00,15.00,'2017-06-06'),('Sham',11.00,13.00,'2017-06-06'),('Sham',10.00,5.00,'2017-06-06'),('Sham',2.00,3.00,'2017-06-06');
/*!40000 ALTER TABLE `dailyupdate` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-23 22:46:52
