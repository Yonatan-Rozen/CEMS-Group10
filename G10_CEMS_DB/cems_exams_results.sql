-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cems
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `exams_results`
--

DROP TABLE IF EXISTS `exams_results`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams_results` (
  `ExamID` varchar(6) NOT NULL,
  `UsernameS` varchar(9) NOT NULL,
  `Date` datetime DEFAULT NULL,
  `TimeOfExecution` varchar(3) DEFAULT NULL,
  `AllocatedTime` varchar(3) DEFAULT NULL,
  `Submited` tinyint NOT NULL,
  PRIMARY KEY (`ExamID`,`UsernameS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams_results`
--

LOCK TABLES `exams_results` WRITE;
/*!40000 ALTER TABLE `exams_results` DISABLE KEYS */;
INSERT INTO `exams_results` VALUES ('010101','5','2021-07-20 14:30:00','125','130',1),('010102','5','2021-06-30 17:50:00','167','180',1),('010102','6','2021-06-13 17:02:40','25','180',0),('010102','7','2021-06-13 17:02:40','60','180',1),('010103','5','2021-06-01 17:20:00','110','120',1),('010103','6','2021-06-13 17:02:40','55','120',0),('010103','7','2021-06-13 17:02:40','120','120',0),('020101','5','2021-06-20 21:00:00','168','180',1),('020101','6','2021-06-20 21:00:00','160','180',1),('020101','7','2021-06-20 21:00:00','153','180',1),('020201','5','2030-06-20 21:00:00','176','180',1),('020201','6','2021-06-13 16:54:47','169','180',1),('020201','7','2021-06-13 16:54:14','183','180',1),('050101','6','2026-06-20 21:00:00','27','30',0),('050101','7','2021-06-13 17:02:40','24','30',1);
/*!40000 ALTER TABLE `exams_results` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-16 21:29:41
