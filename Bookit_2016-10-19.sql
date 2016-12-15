# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.11)
# Database: Bookit
# Generation Time: 2016-10-19 19:47:06 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table Books
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Books`;

CREATE TABLE `Books` (
  `BookID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Version` int(11) DEFAULT NULL,
  `ISBN` double DEFAULT NULL,
  `PriceAB` double DEFAULT NULL,
  `PriceSAXO` double DEFAULT NULL,
  `PriceCDON` double DEFAULT NULL,
  `Publisher` varchar(255) DEFAULT NULL,
  `Author` varchar(255) DEFAULT NULL,
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `UpdateTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BookID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Books` WRITE;
/*!40000 ALTER TABLE `Books` DISABLE KEYS */;

INSERT INTO `Books` (`BookID`, `Title`, `Version`, `ISBN`, `PriceAB`, `PriceSAXO`, `PriceCDON`, `Publisher`, `Author`, `Created`, `Deleted`, `UpdateTs`)
VALUES
	(1,'Hvordan organisationer fungerer',3,9788741258607,469,450,516,'Hans Reitzels','Dag Ingvar Jacobsen & Jan Thorscik','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(2,'Organisationers form og funktion',4,9788759308356,195,204.75,219,'Samfundslitteratur','Niels Bo Sørensen','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(3,'Kultur i organisationer',1,9788762901681,210,243,252,'DJØF','Majken Schultz','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(4,'Business Information Systems',6,9780273736455,489,490.28,548,'Pearson Education Limited','Paul Bocij, Andrew Greasley & Simon Hickie','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(5,'The TCP/IP Guide',1,9781593270476,599,475,554,'No Starch Press,us','C. Kozierok','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(6,'Eloquent JavaScript',2,9781593275846,219,227.39,234,'No Starch Press,us','Marijn Haverbeke','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(7,'Intro to Java Programmring Brief Version',1,9781292078564,529,537.82,603,'Pearson Education Limited','Y. Daniel Liang','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(8,'Managerial Economics in a Global Economy',8,9780199397150,519,511.94,598,'Oxford University Press','Dominick Salvatore','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(9,'Opgaver i erhvervsøkonomi - med supp. noter',1,9788757410051,205,209.3,224,'DJØF','Ove Hedegaard & Michael Hansen','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(10,'Distributed Systems',1,9780273760597,509,519,580,'Pearson Education Limited','George F. Coulouris, Jean Dollimore, Tim Kindberg & Gordon Blair','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(11,'Nøglen til ledelse af forandring',2,9788702131321,345,343.14,332,'Gyldendal','Dean Anderson & Linda Ackerman Anderson','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(12,'Mere videndeling',1,9788741253893,219,226.59,242,'Gyldendal Akademisk','Peter Holdt Christensen','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(13,'Macroeconomics',1,9781464141775,699,499.16,496,'W.H.Freeman & Co Ltd','N. Gregory Mankiw & Mark P. Taylor','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(14,'Fundamentals of Corporate Finance',2,9780077164263,535,549.02,575,'Mcgraw-hill Education - Europe','David Hillier','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(15,'Accounting for Decision Making and Control',9,9781259255007,499,498.25,551,'Mcgraw-hill Education - Europe','Jerold L. Zimmerman','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(16,'Managerial Economics',14,9781473709263,485,792,584,'Cengage Learning EMEA','Mark Hirschey, Eric Bentzen','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(17,'En introduktion til investering og finansiering',2,9788799725113,157.5,348,298,'Forlaget KJER','Bentzen & Tyllesen','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(18,'Linear Algebra and its Applications',5,9781292092232,569,610,632,'Pearson Educated Limited','David C. Lay, Steven R. Lay, Judi J. McDonald','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(19,'Matematisk analyse',8,9788205407428,499,493,457,'Scanvik A/S','Knut Sydsæter','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(20,'Macroeconomics with MyEconLab',1,9780273766391,659,714.5,704,'Pearson Education Limited','Olivier Blanchard\rOlivier Blanchard','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(21,'Matematisk analyse',5,9788205301399,489,473,440,'Gyldendal Akademisk','Knut Sydsæter','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(22,'Mathematical Statistics with Applications',1,9780495385080,549,530.09,536,'NULLCengage Learning, Inc','William Mendenhall, Richard L. Scheaffer & Dennis O. Wackerly\r','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(23,'Introductory Statistics with R',2,9780387790534,399,376.79,417,'Springer-Verlag New York Inc.','Peter Dalgaard','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(24,'Accounting for Decision Making and Control',9,9781259255007,499,498,551,'McGraw-Hill Education - Europe','Jerold L. Zimmerman','2016-10-17 15:37:00',0,'2016-10-17 16:07:36'),
	(25,'Min bog',3,0,469,450,516,'Morten','Forfatter','2016-10-19 07:14:45',0,'2016-10-19 07:14:45'),
	(26,'Min bog',3,0,469,450,516,'Morten','Forfatter','2016-10-19 07:24:15',0,'2016-10-19 07:24:15'),
	(27,'Min bog',3,0,469,450,516,'Morten','Forfatter','2016-10-19 07:25:57',0,'2016-10-19 07:25:57'),
	(28,'Min bog',3,0,469,450,516,'Morten','Forfatter','2016-10-19 07:28:01',0,'2016-10-19 07:28:01');

/*!40000 ALTER TABLE `Books` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table BooksCurriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `BooksCurriculum`;

CREATE TABLE `BooksCurriculum` (
  `BookCurriculumID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `BookID` int(11) DEFAULT NULL,
  `CurriculumID` int(11) DEFAULT NULL,
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `UpdateTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`BookCurriculumID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `BooksCurriculum` WRITE;
/*!40000 ALTER TABLE `BooksCurriculum` DISABLE KEYS */;

INSERT INTO `BooksCurriculum` (`BookCurriculumID`, `BookID`, `CurriculumID`, `Created`, `Deleted`, `UpdateTs`)
VALUES
	(1,1,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(2,2,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(3,3,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(4,4,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(5,5,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(6,6,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(7,7,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(8,8,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(9,9,1,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(10,10,2,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(11,11,2,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(12,12,2,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(13,13,2,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(14,14,2,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(15,15,3,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(16,16,4,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(17,17,4,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(18,18,4,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(19,19,4,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(20,20,5,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(21,21,5,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(22,22,5,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(23,23,5,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(24,24,6,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(25,25,6,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42'),
	(26,26,6,'2016-10-17 15:39:29',0,'2016-10-17 16:08:42');

/*!40000 ALTER TABLE `BooksCurriculum` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Curriculum
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Curriculum`;

CREATE TABLE `Curriculum` (
  `CurriculumID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `School` varchar(255) DEFAULT NULL,
  `Education` varchar(255) DEFAULT NULL,
  `Semester` int(11) DEFAULT NULL,
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `UpdateTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`CurriculumID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Curriculum` WRITE;
/*!40000 ALTER TABLE `Curriculum` DISABLE KEYS */;

INSERT INTO `Curriculum` (`CurriculumID`, `School`, `Education`, `Semester`, `Created`, `Deleted`, `UpdateTs`)
VALUES
	(1,'CBS','HA(it)',1,'2016-10-17 15:52:47',0,'2016-10-17 16:03:14'),
	(2,'CBS','HA(it)',3,'2016-10-17 15:52:47',0,'2016-10-17 16:03:14'),
	(3,'CBS','HA(it)',5,'2016-10-17 15:52:47',0,'2016-10-17 16:03:14'),
	(4,'CBS','HA(mat)',1,'2016-10-17 15:52:47',0,'2016-10-17 16:03:14'),
	(5,'CBS','HA(mat)',3,'2016-10-17 15:52:47',0,'2016-10-17 16:03:14'),
	(6,'test','HA(mat)',5,'2016-10-17 15:52:47',0,'2016-10-17 16:05:04');

/*!40000 ALTER TABLE `Curriculum` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Tokens
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Tokens`;

CREATE TABLE `Tokens` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `token` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Tokens` WRITE;
/*!40000 ALTER TABLE `Tokens` DISABLE KEYS */;

INSERT INTO `Tokens` (`id`, `token`, `user_id`)
VALUES
	(8,'f9e!zc4k4h6nk!8c!v7pn1oqr',12);

/*!40000 ALTER TABLE `Tokens` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table Users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users` (
  `UserID` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(255) NOT NULL DEFAULT '',
  `Last_Name` varchar(255) NOT NULL DEFAULT '',
  `Username` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Usertype` int(11) DEFAULT NULL,
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Deleted` tinyint(1) NOT NULL DEFAULT '0',
  `UpdateTs` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;

INSERT INTO `Users` (`UserID`, `First_Name`, `Last_Name`, `Username`, `Email`, `Password`, `Usertype`, `Created`, `Deleted`, `UpdateTs`)
VALUES
	(12,'Hans','Petersen','hans','Hans@hotmail.com','123',1,'2016-10-17 16:00:04',0,'2016-10-19 21:40:30'),
	(13,'Christoffer','Palsgaard','pals','pals@gmail.com','123456',1,'2016-10-17 16:03:34',0,'2016-10-17 16:11:23'),
	(14,'Niklas','Tastum','tasty','Tastum@Tastumnet.com','123456',1,'2016-10-17 15:02:20',0,'2016-10-19 21:40:26');

/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
