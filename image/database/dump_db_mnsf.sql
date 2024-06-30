-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: mini_soccer_field_db
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customerId` int NOT NULL,
  `fieldId` int NOT NULL,
  `userId` int NOT NULL,
  `status` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `note` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `timeStart` datetime NOT NULL,
  `timeEnd` datetime NOT NULL,
  `price` decimal(15,3) NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Booking_Customer` (`customerId`),
  KEY `FK_Booking_Field` (`fieldId`),
  KEY `FK_Booking_User` (`userId`),
  CONSTRAINT `FK_Booking_Customer` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_Booking_Field` FOREIGN KEY (`fieldId`) REFERENCES `field` (`id`),
  CONSTRAINT `FK_Booking_User` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,11,3,1,'completed','','2024-04-28 14:00:00','2024-04-28 15:30:00',300000.000,_binary '\0','2024-04-28 13:30:55',NULL),(2,12,6,1,'completed','','2024-04-29 21:30:00','2024-04-29 23:00:00',300000.000,_binary '\0','2024-04-29 12:04:05',NULL),(3,13,18,1,'completed','Anh Huy Trà Vinh','2024-04-29 21:00:00','2024-04-29 22:30:00',450000.000,_binary '\0','2024-04-29 12:07:49',NULL),(4,13,9,1,'completed','','2024-04-29 13:30:00','2024-04-29 15:00:00',300000.000,_binary '\0','2024-04-29 12:14:28',NULL);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `memberShipId` int NOT NULL,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `phoneNumber` char(10) NOT NULL,
  `totalSpend` decimal(20,3) DEFAULT NULL,
  `image` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`),
  KEY `FK_Customer_MemberShip` (`memberShipId`),
  CONSTRAINT `FK_Customer_MemberShip` FOREIGN KEY (`memberShipId`) REFERENCES `membership` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'test12','0123456780',0.000,NULL,_binary '\0','2024-04-27 22:15:26',NULL),(2,1,'test2','0123456781',0.000,NULL,_binary '\0','2024-04-27 22:15:26',NULL),(3,1,'test3','0123456782',0.000,NULL,_binary '\0','2024-04-27 22:15:26',NULL),(4,1,'test5','0123456783',0.000,NULL,_binary '\0','2024-04-27 22:15:26',NULL),(5,1,'test6','0123456784',0.000,NULL,_binary '\0','2024-04-26 22:15:26',NULL),(6,1,'test7','0123456785',0.000,NULL,_binary '\0','2024-04-26 22:15:26',NULL),(7,1,'test8','0123456786',0.000,NULL,_binary '\0','2024-04-25 22:15:26',NULL),(8,1,'test9','0123456787',0.000,NULL,_binary '\0','2024-04-25 22:15:26',NULL),(9,1,'test10','0123456700',0.000,NULL,_binary '\0','2024-04-24 22:15:26',NULL),(10,1,'test11','0123456771',0.000,NULL,_binary '\0','2024-04-24 22:15:26',NULL),(11,1,'Nhựt Khang','0397490429',348000.000,NULL,_binary '\0','2024-04-27 22:22:12','2024-04-28 13:31:10'),(12,1,'Thanh Huy','0123456789',300000.000,NULL,_binary '\0','2024-04-29 12:04:05','2024-04-29 12:04:21'),(13,1,'Thanh Huy','1234554321',938000.000,NULL,_binary '\0','2024-04-29 12:07:49','2024-04-29 12:14:48');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `field`
--

DROP TABLE IF EXISTS `field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `field` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `type` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `combineField1` int DEFAULT NULL,
  `combineField2` int DEFAULT NULL,
  `combineField3` int DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Field_Field_1` (`combineField1`),
  KEY `FK_Field_Field_2` (`combineField2`),
  KEY `FK_Field_Field_3` (`combineField3`),
  CONSTRAINT `FK_Field_Field_1` FOREIGN KEY (`combineField1`) REFERENCES `field` (`id`),
  CONSTRAINT `FK_Field_Field_2` FOREIGN KEY (`combineField2`) REFERENCES `field` (`id`),
  CONSTRAINT `FK_Field_Field_3` FOREIGN KEY (`combineField3`) REFERENCES `field` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `field`
--

LOCK TABLES `field` WRITE;
/*!40000 ALTER TABLE `field` DISABLE KEYS */;
INSERT INTO `field` VALUES (1,'Field 5-1','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(2,'Field 5-2','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(3,'Field 5-3','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(4,'Field 5-4','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(5,'Field 5-5','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(6,'Field 5-6','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(7,'Field 5-7','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(8,'Field 5-8','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(9,'Field 5-9','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(10,'Field 5-10','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(11,'Field 5-11','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(12,'Field 5-12','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(13,'Field 5-13','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(14,'Field 5-14','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(15,'Field 5-15','active','5 a side',NULL,NULL,NULL,NULL,_binary '\0','2024-04-27 22:14:12',NULL),(16,'Field 7-1','active','7 a side',NULL,1,2,3,_binary '\0','2024-04-27 22:14:12',NULL),(17,'Field 7-2','active','7 a side',NULL,4,5,6,_binary '\0','2024-04-27 22:14:12',NULL),(18,'Field 7-3','active','7 a side',NULL,7,8,9,_binary '\0','2024-04-27 22:14:12',NULL),(19,'Field 7-4','active','7 a side',NULL,10,11,12,_binary '\0','2024-04-27 22:14:12',NULL),(20,'Field 7-5','active','7 a side',NULL,13,14,15,_binary '\0','2024-04-27 22:14:12',NULL);
/*!40000 ALTER TABLE `field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match`
--

DROP TABLE IF EXISTS `match`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `match` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookingId` int NOT NULL,
  `checkIn` datetime NOT NULL,
  `checkOut` datetime DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `bookingId` (`bookingId`),
  CONSTRAINT `FK_Match_Booking` FOREIGN KEY (`bookingId`) REFERENCES `booking` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match`
--

LOCK TABLES `match` WRITE;
/*!40000 ALTER TABLE `match` DISABLE KEYS */;
INSERT INTO `match` VALUES (1,1,'2024-04-28 13:31:00','2024-04-28 13:31:10',_binary '\0','2024-04-28 13:31:00',NULL),(2,2,'2024-04-29 12:04:11','2024-04-29 12:04:21',_binary '\0','2024-04-29 12:04:11',NULL),(3,3,'2024-04-29 12:07:55','2024-04-29 12:08:12',_binary '\0','2024-04-29 12:07:55',NULL),(4,4,'2024-04-29 12:14:32','2024-04-29 12:14:48',_binary '\0','2024-04-29 12:14:32',NULL);
/*!40000 ALTER TABLE `match` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membership`
--

DROP TABLE IF EXISTS `membership`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membership` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `discountRate` int NOT NULL,
  `minimumSpendAmount` decimal(20,3) NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `discountRate` (`discountRate`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membership`
--

LOCK TABLES `membership` WRITE;
/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
INSERT INTO `membership` VALUES (1,'Standard',0,0.000,_binary '\0','2024-04-27 22:14:12',NULL),(2,'Silver',2,1000000.000,_binary '\0','2024-04-27 22:14:12',NULL),(3,'Gold',4,5000000.000,_binary '\0','2024-04-27 22:14:12',NULL),(4,'Platinum',5,10000000.000,_binary '\0','2024-04-27 22:14:12',NULL),(5,'Diamond',6,20000000.000,_binary '\0','2024-04-27 22:14:12',NULL);
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pricelist`
--

DROP TABLE IF EXISTS `pricelist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pricelist` (
  `id` int NOT NULL AUTO_INCREMENT,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `typeField` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `dateOfWeek` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `unitPricePer30Minutes` decimal(12,3) NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pricelist`
--

LOCK TABLES `pricelist` WRITE;
/*!40000 ALTER TABLE `pricelist` DISABLE KEYS */;
INSERT INTO `pricelist` VALUES (1,'06:00:00','08:00:00','5 a side','Monday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(2,'08:00:00','10:00:00','5 a side','Monday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(3,'10:00:00','14:00:00','5 a side','Monday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(4,'14:00:00','16:00:00','5 a side','Monday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(5,'16:00:00','18:00:00','5 a side','Monday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(6,'18:00:00','23:00:00','5 a side','Monday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(7,'06:00:00','08:00:00','7 a side','Monday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(8,'08:00:00','10:00:00','7 a side','Monday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(9,'10:00:00','14:00:00','7 a side','Monday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(10,'14:00:00','16:00:00','7 a side','Monday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(11,'16:00:00','18:00:00','7 a side','Monday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(12,'18:00:00','23:00:00','7 a side','Monday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(13,'06:00:00','08:00:00','5 a side','Tuesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(14,'08:00:00','10:00:00','5 a side','Tuesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(15,'10:00:00','14:00:00','5 a side','Tuesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(16,'14:00:00','16:00:00','5 a side','Tuesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(17,'16:00:00','18:00:00','5 a side','Tuesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(18,'18:00:00','23:00:00','5 a side','Tuesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(19,'06:00:00','08:00:00','7 a side','Tuesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(20,'08:00:00','10:00:00','7 a side','Tuesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(21,'10:00:00','14:00:00','7 a side','Tuesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(22,'14:00:00','16:00:00','7 a side','Tuesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(23,'16:00:00','18:00:00','7 a side','Tuesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(24,'18:00:00','23:00:00','7 a side','Tuesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(25,'06:00:00','08:00:00','5 a side','Wednesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(26,'08:00:00','10:00:00','5 a side','Wednesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(27,'10:00:00','14:00:00','5 a side','Wednesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(28,'14:00:00','16:00:00','5 a side','Wednesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(29,'16:00:00','18:00:00','5 a side','Wednesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(30,'18:00:00','23:00:00','5 a side','Wednesday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(31,'06:00:00','08:00:00','7 a side','Wednesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(32,'08:00:00','10:00:00','7 a side','Wednesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(33,'10:00:00','14:00:00','7 a side','Wednesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(34,'14:00:00','16:00:00','7 a side','Wednesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(35,'16:00:00','18:00:00','7 a side','Wednesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(36,'18:00:00','23:00:00','7 a side','Wednesday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(37,'06:00:00','08:00:00','5 a side','Thursday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(38,'08:00:00','10:00:00','5 a side','Thursday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(39,'10:00:00','14:00:00','5 a side','Thursday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(40,'14:00:00','16:00:00','5 a side','Thursday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(41,'16:00:00','18:00:00','5 a side','Thursday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(42,'18:00:00','23:00:00','5 a side','Thursday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(43,'06:00:00','08:00:00','7 a side','Thursday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(44,'08:00:00','10:00:00','7 a side','Thursday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(45,'10:00:00','14:00:00','7 a side','Thursday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(46,'14:00:00','16:00:00','7 a side','Thursday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(47,'16:00:00','18:00:00','7 a side','Thursday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(48,'18:00:00','23:00:00','7 a side','Thursday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(49,'06:00:00','08:00:00','5 a side','Friday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(50,'08:00:00','10:00:00','5 a side','Friday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(51,'10:00:00','14:00:00','5 a side','Friday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(52,'14:00:00','16:00:00','5 a side','Friday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(53,'16:00:00','18:00:00','5 a side','Friday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(54,'18:00:00','23:00:00','5 a side','Friday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(55,'06:00:00','08:00:00','7 a side','Friday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(56,'08:00:00','10:00:00','7 a side','Friday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(57,'10:00:00','14:00:00','7 a side','Friday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(58,'14:00:00','16:00:00','7 a side','Friday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(59,'16:00:00','18:00:00','7 a side','Friday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(60,'18:00:00','23:00:00','7 a side','Friday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(61,'06:00:00','08:00:00','5 a side','Saturday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(62,'08:00:00','10:00:00','5 a side','Saturday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(63,'10:00:00','14:00:00','5 a side','Saturday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(64,'14:00:00','16:00:00','5 a side','Saturday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(65,'16:00:00','18:00:00','5 a side','Saturday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(66,'18:00:00','23:00:00','5 a side','Saturday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(67,'06:00:00','08:00:00','7 a side','Saturday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(68,'08:00:00','10:00:00','7 a side','Saturday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(69,'10:00:00','14:00:00','7 a side','Saturday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(70,'14:00:00','16:00:00','7 a side','Saturday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(71,'16:00:00','18:00:00','7 a side','Saturday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(72,'18:00:00','23:00:00','7 a side','Saturday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(73,'06:00:00','08:00:00','5 a side','Sunday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(74,'08:00:00','10:00:00','5 a side','Sunday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(75,'10:00:00','14:00:00','5 a side','Sunday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(76,'14:00:00','16:00:00','5 a side','Sunday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(77,'16:00:00','18:00:00','5 a side','Sunday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(78,'18:00:00','23:00:00','5 a side','Sunday',100000.000,_binary '\0','2024-04-27 22:14:12',NULL),(79,'06:00:00','08:00:00','7 a side','Sunday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(80,'08:00:00','10:00:00','7 a side','Sunday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(81,'10:00:00','14:00:00','7 a side','Sunday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(82,'14:00:00','16:00:00','7 a side','Sunday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(83,'16:00:00','18:00:00','7 a side','Sunday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL),(84,'18:00:00','23:00:00','7 a side','Sunday',150000.000,_binary '\0','2024-04-27 22:14:12',NULL);
/*!40000 ALTER TABLE `pricelist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `price` decimal(15,3) NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `unit` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `quantity` int NOT NULL,
  `sold` int NOT NULL,
  `status` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Nước ngọt Coca Cola',12000.000,'cocacola_chai.jpg','Nước ngọt Coca Cola 500ml','Chai',98,2,'Active',_binary '\0','2024-04-27 22:15:26','2024-04-29 12:08:00'),(2,'Nước ngọt Coca Cola',70000.000,'cocacola_6_chai.jpg','6 chai nước ngọt Coca Cola 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(3,'Nước ngọt Coca Cola',12000.000,'cocacola_lon.jpg','Nước ngọt Coca Cola 500ml','Lon',94,6,'Active',_binary '\0','2024-04-27 22:15:26','2024-04-29 06:49:37'),(4,'Nước ngọt Coca Cola Zero',12000.000,'coca_zero_6_lon.jpg','Nước ngọt Coca Cola 500ml','Lon',99,1,'Active',_binary '\0','2024-04-27 22:15:26','2024-04-27 22:22:14'),(5,'Nước ngọt Coca Cola',70000.000,'cocacola_6_lon.jpg','6 lon nước ngọt Coca Cola 500ml','Lon',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(6,'Nước ngọt Coca Cola',280000.000,'cocacola_24_lon.jpg','24 lon nước ngọt Coca Cola 500ml','Lon',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(7,'Nước ngọt Pepsi',12000.000,'pepsi_lon.jpg','Nước ngọt Pepsi 500ml','Lon',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(8,'Nước ngọt Pepsi',12000.000,'pepsi_chai.jpg','Nước ngọt Pepsi 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(9,'Nước ngọt Pepsi',70000.000,'pepsi_6_chai.jpg','6 chai nước ngọt Pepsi 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(10,'Nước ngọt Pepsi',70000.000,'pepsi_6_lon.jpg','6 lon nước ngọt Pepsi 500ml','Lon',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(11,'Nước ngọt Pepsi',280000.000,'pepsi_24_lon.jpg','24 lon nước ngọt Pepsi 500ml','Lon',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(12,'Revive chanh muối',12000.000,'revive_chai.jpg','Revive chanh muối 500ml','Chai',97,3,'Active',_binary '\0','2024-04-27 22:15:26','2024-04-29 12:08:05'),(13,'Revive chanh muối',70000.000,'revive_6_chai.jpg','6 chai Revive chanh muối 500ml','Chai',98,2,'Active',_binary '\0','2024-04-27 22:15:26','2024-04-29 12:14:42'),(14,'Revive chanh muối',12000.000,'revive1_chai.jpg','Revive chanh muối 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(15,'Revive chanh muối',70000.000,'revive1_6_chai.jpg','6 chai Revive chanh muối 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(16,'Nước suối Aquafina',8000.000,'aquafina_chai.jpg','Nước suối Aquafina 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(17,'Nước suối Aquafina',45000.000,'aquafina_6_chai.jpg','6 chai nước suối Aquafina 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(18,'Nước suối Satori',10000.000,'satori_chai.jpg','Nước suối Satori 500ml','Chai',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(19,'Bánh Snack khoai tây',10000.000,'banh_snack_khoai_tay.jpg','Bánh Snack khoai tây vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(20,'Bánh Snack khoai tây',10000.000,'banh_snack_khoai_tay1.jpg','Bánh Snack khoai tây vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(21,'Bánh Snack khoai tây',10000.000,'banh_snack_khoai_tay2.jpg','Bánh Snack khoai tây vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(22,'Bánh Snack khoai tây',10000.000,'banh_snack_khoai_tay3.jpg','Bánh Snack khoai tây vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(23,'Bánh Snack Oshi',10000.000,'banh_snack_oshi.jpg','Bánh Snack Oshi vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(24,'Bánh Snack Oshi',10000.000,'banh_snack_oshi1.jpg','Bánh Snack Oshi vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(25,'Bánh mì Kinh Đô',10000.000,'banh_mi_kinh_do.jpg','Bánh mì Kinh Đô vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(26,'Bánh mì Kinh Đô',10000.000,'banh_mi_kinh_do1.jpg','Bánh mì Kinh Đô vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(27,'Bánh mì Staff',10000.000,'banh_mi_staff.jpg','Bánh mì Staff vị gà','Bịch',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(28,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(29,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(30,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(31,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(32,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(33,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(34,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(35,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(36,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(37,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(38,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(39,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(40,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(41,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL),(42,'Sản phẩm khác',10000.000,NULL,'','Unit',100,0,'Active',_binary '\0','2024-04-27 22:15:26',NULL);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceitems`
--

DROP TABLE IF EXISTS `serviceitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serviceitems` (
  `id` int NOT NULL AUTO_INCREMENT,
  `serviceUsageId` int NOT NULL,
  `serviceId` int NOT NULL,
  `quantity` int NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ServiceItems_ServiceUsage` (`serviceUsageId`),
  KEY `FK_ServiceItems_Service` (`serviceId`),
  CONSTRAINT `FK_ServiceItems_Service` FOREIGN KEY (`serviceId`) REFERENCES `service` (`id`),
  CONSTRAINT `FK_ServiceItems_ServiceUsage` FOREIGN KEY (`serviceUsageId`) REFERENCES `serviceusage` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceitems`
--

LOCK TABLES `serviceitems` WRITE;
/*!40000 ALTER TABLE `serviceitems` DISABLE KEYS */;
INSERT INTO `serviceitems` VALUES (1,1,3,2,_binary '\0','2024-04-27 22:16:53',NULL),(2,2,12,1,_binary '\0','2024-04-27 22:18:17',NULL),(3,3,4,1,_binary '\0','2024-04-27 22:22:14',NULL),(4,4,3,2,_binary '\0','2024-04-28 13:30:33',NULL),(5,5,1,1,_binary '\0','2024-04-28 13:31:07',NULL),(6,6,3,1,_binary '\0','2024-04-28 14:45:26',NULL),(7,7,3,1,_binary '\0','2024-04-29 06:49:37',NULL),(8,9,1,1,_binary '\0','2024-04-29 12:08:00',NULL),(9,9,12,2,_binary '\0','2024-04-29 12:08:05',NULL),(10,10,13,2,_binary '\0','2024-04-29 12:14:42',NULL);
/*!40000 ALTER TABLE `serviceitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `serviceusage`
--

DROP TABLE IF EXISTS `serviceusage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `serviceusage` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matchId` int DEFAULT NULL,
  `customerId` int DEFAULT NULL,
  `note` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ServiceUsage_Match` (`matchId`),
  KEY `FK_ServiceUsage_Customer` (`customerId`),
  CONSTRAINT `FK_ServiceUsage_Customer` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_ServiceUsage_Match` FOREIGN KEY (`matchId`) REFERENCES `match` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `serviceusage`
--

LOCK TABLES `serviceusage` WRITE;
/*!40000 ALTER TABLE `serviceusage` DISABLE KEYS */;
INSERT INTO `serviceusage` VALUES (1,NULL,NULL,'',_binary '\0','2024-04-27 22:16:53',NULL),(2,NULL,NULL,'',_binary '\0','2024-04-27 22:18:17',NULL),(3,NULL,11,'',_binary '\0','2024-04-27 22:22:14',NULL),(4,NULL,11,'',_binary '\0','2024-04-28 13:30:33',NULL),(5,1,11,'',_binary '\0','2024-04-28 13:31:03',NULL),(6,NULL,NULL,'',_binary '\0','2024-04-28 14:45:26',NULL),(7,NULL,NULL,'',_binary '\0','2024-04-29 06:49:37',NULL),(8,2,12,'',_binary '\0','2024-04-29 12:04:14',NULL),(9,3,13,'',_binary '\0','2024-04-29 12:07:57',NULL),(10,4,13,'',_binary '\0','2024-04-29 12:14:37',NULL);
/*!40000 ALTER TABLE `serviceusage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` int NOT NULL AUTO_INCREMENT,
  `userId` int NOT NULL,
  `serviceUsageId` int NOT NULL,
  `type` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `totalAmount` decimal(15,3) NOT NULL,
  `additionalFee` decimal(15,3) NOT NULL,
  `discountAmount` decimal(15,3) NOT NULL,
  `finalAmount` decimal(15,3) NOT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Transaction_User` (`userId`),
  KEY `FK_Transaction_ServiceUsage` (`serviceUsageId`),
  CONSTRAINT `FK_Transaction_ServiceUsage` FOREIGN KEY (`serviceUsageId`) REFERENCES `serviceusage` (`id`),
  CONSTRAINT `FK_Transaction_User` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,1,1,'Retail',24000.000,0.000,0.000,24000.000,_binary '\0','2024-04-27 22:16:53',NULL),(2,1,2,'Retail',12000.000,0.000,0.000,12000.000,_binary '\0','2024-04-27 22:18:17',NULL),(3,1,3,'Retail',12000.000,0.000,0.000,12000.000,_binary '\0','2024-04-27 22:22:14',NULL),(4,1,4,'Retail',24000.000,0.000,0.000,24000.000,_binary '\0','2024-04-28 13:30:33',NULL),(5,1,5,'Booking Service',312000.000,0.000,0.000,312000.000,_binary '\0','2024-04-28 13:31:09',NULL),(6,1,6,'Retail',12000.000,0.000,0.000,12000.000,_binary '\0','2024-04-28 14:45:26',NULL),(7,1,7,'Retail',12000.000,0.000,0.000,12000.000,_binary '\0','2024-04-29 06:49:37',NULL),(8,1,8,'Booking Service',300000.000,0.000,0.000,300000.000,_binary '\0','2024-04-29 12:04:19',NULL),(9,1,9,'Booking Service',498000.000,0.000,0.000,498000.000,_binary '\0','2024-04-29 12:08:10',NULL),(10,1,10,'Booking Service',440000.000,0.000,0.000,440000.000,_binary '\0','2024-04-29 12:14:45',NULL);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `gender` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `dateOfBirth` date NOT NULL,
  `image` varchar(500) DEFAULT NULL,
  `phoneNumber` char(10) NOT NULL,
  `userName` varchar(500) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `type` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `isDeleted` bit(1) NOT NULL DEFAULT b'0',
  `createdAt` datetime NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `phoneNumber` (`phoneNumber`),
  UNIQUE KEY `userName` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Admin','Male','1990-01-01',NULL,'1234567890','admin','admin','admin',NULL,_binary '\0','2024-04-27 22:15:26',NULL),(2,'Staff','Male','1990-01-01',NULL,'0987654321','staff','staff','staff',NULL,_binary '\0','2024-04-27 22:15:26',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-29 13:33:55
