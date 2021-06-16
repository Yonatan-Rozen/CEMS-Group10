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
-- Table structure for table `answered_questions`
--

DROP TABLE IF EXISTS `answered_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answered_questions` (
  `ExamID` varchar(6) NOT NULL,
  `QuestionID` varchar(5) NOT NULL,
  `UsernameS` varchar(9) NOT NULL,
  `StudentAnswer` enum('0','1','2','3','4') NOT NULL,
  `IsCorrect` tinyint NOT NULL,
  `TeacherComment` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ExamID`,`QuestionID`,`UsernameS`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answered_questions`
--

LOCK TABLES `answered_questions` WRITE;
/*!40000 ALTER TABLE `answered_questions` DISABLE KEYS */;
INSERT INTO `answered_questions` VALUES ('010101','01001','5','2',1,NULL),('010101','01007','5','3',1,NULL),('010102','01002','5','1',1,NULL),('010102','01002','6','2',0,NULL),('010102','01002','7','3',0,NULL),('010102','01006','5','3',1,NULL),('010102','01006','6','3',1,NULL),('010102','01006','7','1',0,NULL),('010102','01007','5','0',0,'It\'s better to guess!'),('010102','01007','6','2',0,NULL),('010102','01007','7','4',0,NULL),('010103','01005','5','3',1,NULL),('010103','01005','6','2',0,NULL),('010103','01005','7','0',0,'Come on Jon! You must know something...'),('010103','01008','5','4',0,NULL),('010103','01008','6','1',0,NULL),('010103','01008','7','2',1,NULL),('010103','01009','5','2',0,NULL),('010103','01009','6','1',1,NULL),('010103','01009','7','4',0,NULL),('020101','02001','5','4',1,''),('020101','02001','6','4',1,''),('020101','02001','7','2',1,''),('020101','02002','5','4',1,NULL),('020101','02002','6','3',0,NULL),('020101','02002','7','1',0,NULL),('020101','02003','5','2',0,NULL),('020101','02003','6','0',0,'It\'s better to guess!'),('020101','02003','7','0',0,'It\'s better to guess!'),('020101','02004','5','4',1,NULL),('020101','02004','6','0',0,'It\'s better to guess!'),('020101','02004','7','3',0,NULL),('050101','05001','6','0',0,'Please do your homework!'),('050101','05001','7','3',1,'excellent!');
/*!40000 ALTER TABLE `answered_questions` ENABLE KEYS */;
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
