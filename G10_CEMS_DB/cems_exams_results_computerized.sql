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
-- Table structure for table `exams_results_computerized`
--

DROP TABLE IF EXISTS `exams_results_computerized`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exams_results_computerized` (
  `ExamID` varchar(6) NOT NULL,
  `UsernameS` varchar(9) NOT NULL,
  `GradeBySystem` varchar(3) NOT NULL,
  `ConfirmedByTeacher` tinyint NOT NULL,
  `GradeByTeacher` varchar(3) DEFAULT NULL,
  `ReasonForGradeChange` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ExamID`,`UsernameS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exams_results_computerized`
--

LOCK TABLES `exams_results_computerized` WRITE;
/*!40000 ALTER TABLE `exams_results_computerized` DISABLE KEYS */;
INSERT INTO `exams_results_computerized` VALUES ('010101','5','100',1,'100',NULL),('010102','5','75',1,'75',NULL),('010102','6','50',1,'50',NULL),('010102','7','0',1,'0','You know nothing, Jon Snow! Please take the course again'),('010103','5','25',0,NULL,NULL),('010103','6','25',0,NULL,NULL),('010103','7','50',1,'80','Factor'),('020101','5','75',0,NULL,NULL),('020101','6','25',0,NULL,NULL),('020101','7','25',1,'55','Factor'),('050101','6','0',0,NULL,NULL),('050101','7','100',1,'100','Amazing!');
/*!40000 ALTER TABLE `exams_results_computerized` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-16 21:29:42
