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
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `questions` (
  `QuestionID` varchar(5) NOT NULL,
  `BankID` int NOT NULL,
  `Body` varchar(256) NOT NULL,
  `Answer1` varchar(256) NOT NULL,
  `Answer2` varchar(256) NOT NULL,
  `Answer3` varchar(256) NOT NULL,
  `Answer4` varchar(256) NOT NULL,
  `CorrectAnswer` enum('1','2','3','4') NOT NULL,
  `Author` varchar(45) NOT NULL,
  `ExistsInExam` tinyint NOT NULL,
  PRIMARY KEY (`QuestionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES ('01001',1,'2+15=?','16','17','25','2/15','2','Yonatan Rozen',1),('01002',1,'9+10=?','19','14','91','20','1','Yonatan Rozen',0),('01003',1,'3+5','35','53','15','8','4','Yonatan Rozen',0),('01004',1,'71 + 2','142','69','73','712','3','Yonatan Rozen',0),('01005',3,'80 + 6','83','89','86','88','3','Danielle Sarusi',1),('01006',1,'5+5','15','25','10','55','3','Yonatan Rozen',0),('01007',1,'7+2','72','14','9','3','3','Yonatan Rozen',1),('01008',3,'1234+4321=?','4444','5555','6666','7777','2','Danielle Sarusi',1),('01009',3,'9812 / 2 = ?','4906','4096','4609','3960','1','Danielle Sarusi',1),('01010',3,'(13+5)/2 = ?','18','15','11','9','4','Danielle Sarusi',1),('01011',1,'5+3','16','8','3','2','1','Yonatan Rozen',0),('02001',2,'int x = 1; int y = 2; int z = x-y; what is z\'s value?','1','2','0','-1','4','Yonatan Rozen',1),('02002',2,'int a; a=\'67\'; what will \'printf(\"%c,a);\' print?','a','67','undefined','none of the above','4','Yonatan Rozen',1),('02003',2,'int y=\'a\'. what is y\'s value?','65','64','97','98','3','Yonatan Rozen',1),('02004',2,'boolean x= (5==2); what is x\'s value?','(5==2)','5','true','false','4','Yonatan Rozen',1),('05001',4,'Who painted the famous \'Mona Lisa\' ?','Salvador Dali','Leonardo da Vinci','Pablo Picasso','Vincent Van Gogh','2','Danielle Sarusi',1);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
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
