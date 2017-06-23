-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: ProgettoIngSW
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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
-- Table structure for table `ARBITRO`
--

DROP TABLE IF EXISTS `ARBITRO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ARBITRO` (
  `CFARBITRO` varchar(45) NOT NULL,
  `NOMEARBITRO` varchar(45) DEFAULT NULL,
  `COGNOMEARBITRO` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CFARBITRO`),
  KEY `fk_ARBITRO_1_idx` (`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ARBITRO`
--

LOCK TABLES `ARBITRO` WRITE;
/*!40000 ALTER TABLE `ARBITRO` DISABLE KEYS */;
INSERT INTO `ARBITRO` VALUES ('a','a','a','a'),('b','b','b','b'),('c','c','c','c');
/*!40000 ALTER TABLE `ARBITRO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CARTELLINO`
--

DROP TABLE IF EXISTS `CARTELLINO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CARTELLINO` (
  `IDPARTITA` int(11) NOT NULL,
  `COLORECARTELLLINO` varchar(7) NOT NULL,
  `MINUTO` int(11) NOT NULL,
  `NUMEROGIOCATORE` int(11) NOT NULL,
  `NOMESQUADRA` varchar(45) NOT NULL,
  `CITTASQUADRA` varchar(45) NOT NULL,
  `NOMETORNEO` varchar(45) NOT NULL,
  `ANNOTORNEO` int(11) NOT NULL,
  PRIMARY KEY (`IDPARTITA`,`COLORECARTELLLINO`,`MINUTO`,`NUMEROGIOCATORE`,`NOMESQUADRA`,`CITTASQUADRA`,`NOMETORNEO`,`ANNOTORNEO`),
  KEY `fk_CARTELLINO_5_idx` (`ANNOTORNEO`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CARTELLINO`
--

LOCK TABLES `CARTELLINO` WRITE;
/*!40000 ALTER TABLE `CARTELLINO` DISABLE KEYS */;
/*!40000 ALTER TABLE `CARTELLINO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GIOCATORE`
--

DROP TABLE IF EXISTS `GIOCATORE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GIOCATORE` (
  `NUMERO` int(11) NOT NULL,
  `NOMEGIOCATORE` varchar(45) DEFAULT NULL,
  `COGNOMEGIOCATORE` varchar(45) DEFAULT NULL,
  `NOMESQUADRA` varchar(45) NOT NULL,
  `CITTASQUADRA` varchar(45) NOT NULL,
  PRIMARY KEY (`NUMERO`,`NOMESQUADRA`,`CITTASQUADRA`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GIOCATORE`
--

LOCK TABLES `GIOCATORE` WRITE;
/*!40000 ALTER TABLE `GIOCATORE` DISABLE KEYS */;
INSERT INTO `GIOCATORE` VALUES (1,'Samir','Handanovic','Inter','Milano'),(1,'Prova','Prova','S1','Milano'),(2,'Prova2','Prova2','S1','Milano'),(3,'Prova3','Porva3','S2','Milano'),(3,'Prova3','Porva3','S3','Milano'),(4,'Mario','Shehu','Inter','Milano'),(4,'Prova4','Prova4','S2','Milano'),(4,'Prova4','Prova4','S3','Milano'),(6,'Joao','Mario','Inter','Milano'),(9,'Mauro','Icardi','Inter','Milano'),(24,'Jeison','Murillo','Inter','Milano'),(44,'Ivan','Perisic','Inter','Milano');
/*!40000 ALTER TABLE `GIOCATORE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GOAL`
--

DROP TABLE IF EXISTS `GOAL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GOAL` (
  `IDPARTITA` int(11) NOT NULL,
  `MINUTO` int(11) NOT NULL,
  `NUMEROGIOCATORE` int(11) NOT NULL,
  `NOMESQUADRA` varchar(45) NOT NULL,
  `CITTASQUADRA` varchar(45) NOT NULL,
  `NOMETORNEO` varchar(45) NOT NULL,
  `ANNOTORNEO` int(11) NOT NULL,
  PRIMARY KEY (`IDPARTITA`,`MINUTO`,`NUMEROGIOCATORE`,`NOMESQUADRA`,`CITTASQUADRA`,`NOMETORNEO`,`ANNOTORNEO`),
  KEY `fk_GOAL_6_idx` (`ANNOTORNEO`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GOAL`
--

LOCK TABLES `GOAL` WRITE;
/*!40000 ALTER TABLE `GOAL` DISABLE KEYS */;
/*!40000 ALTER TABLE `GOAL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LOGIN`
--

DROP TABLE IF EXISTS `LOGIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LOGIN` (
  `USERNAME` varchar(45) NOT NULL,
  `PASSWORD` varchar(45) NOT NULL,
  PRIMARY KEY (`USERNAME`,`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LOGIN`
--

LOCK TABLES `LOGIN` WRITE;
/*!40000 ALTER TABLE `LOGIN` DISABLE KEYS */;
/*!40000 ALTER TABLE `LOGIN` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PARTITA`
--

DROP TABLE IF EXISTS `PARTITA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PARTITA` (
  `IDPARTITA` int(11) NOT NULL AUTO_INCREMENT,
  `NOMESQUADRACASA` varchar(45) DEFAULT NULL,
  `NOMESQUADRAOSPITE` varchar(45) DEFAULT NULL,
  `GOALCASA` int(11) DEFAULT NULL,
  `GOALOSPITE` int(11) DEFAULT NULL,
  `STATOPARTITA` varchar(45) NOT NULL,
  `CFARBITRO` varchar(45) DEFAULT NULL,
  `NOMETORNEO` varchar(45) NOT NULL,
  `ANNOTORNEO` int(11) NOT NULL,
  `CITTAPARTITA` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`IDPARTITA`,`NOMETORNEO`,`ANNOTORNEO`),
  KEY `fk_PARTITA_5_idx` (`ANNOTORNEO`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PARTITA`
--

LOCK TABLES `PARTITA` WRITE;
/*!40000 ALTER TABLE `PARTITA` DISABLE KEYS */;
/*!40000 ALTER TABLE `PARTITA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SQUADRA`
--

DROP TABLE IF EXISTS `SQUADRA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SQUADRA` (
  `NOMESQUADRA` varchar(45) NOT NULL,
  `CITTASQUADRA` varchar(45) NOT NULL,
  `COLORESQUADRA` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NOMESQUADRA`,`CITTASQUADRA`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SQUADRA`
--

LOCK TABLES `SQUADRA` WRITE;
/*!40000 ALTER TABLE `SQUADRA` DISABLE KEYS */;
INSERT INTO `SQUADRA` VALUES ('Atalanta','Bergamo','NeroBlu'),('Inter','Milano','NeroAzzurro'),('Juventus','Torino','Bianconero'),('Leggende','Mondo','NeroOro'),('Milan','Milano','RossoNero'),('S1','Milano','blu'),('S2','Milano','rosso'),('S3','Milano','rosso');
/*!40000 ALTER TABLE `SQUADRA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TORNEO`
--

DROP TABLE IF EXISTS `TORNEO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TORNEO` (
  `NOME` varchar(45) NOT NULL,
  `ANNO` int(11) NOT NULL,
  PRIMARY KEY (`NOME`,`ANNO`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TORNEO`
--

LOCK TABLES `TORNEO` WRITE;
/*!40000 ALTER TABLE `TORNEO` DISABLE KEYS */;
INSERT INTO `TORNEO` VALUES ('Calcio 1',2017),('Prova',2017);
/*!40000 ALTER TABLE `TORNEO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TORNEO_ELIMINAZIONEDIRETTA`
--

DROP TABLE IF EXISTS `TORNEO_ELIMINAZIONEDIRETTA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TORNEO_ELIMINAZIONEDIRETTA` (
  `NOMESQUADRA` varchar(45) NOT NULL,
  `CITTASQUADRA` varchar(45) NOT NULL,
  `FASETORNEO` int(11) NOT NULL,
  `PASSAGGIOFASE` smallint(1) DEFAULT NULL,
  `NOMETORNEO` varchar(45) NOT NULL,
  `ANNOTORNEO` int(11) NOT NULL,
  PRIMARY KEY (`NOMESQUADRA`,`CITTASQUADRA`,`FASETORNEO`,`NOMETORNEO`,`ANNOTORNEO`),
  KEY `fk_TORNEO_ELIMINAZIONEDIRETTA_3_idx` (`CITTASQUADRA`),
  KEY `fk_TORNEO_ELIMINAZIONEDIRETTA_2_idx1` (`ANNOTORNEO`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TORNEO_ELIMINAZIONEDIRETTA`
--

LOCK TABLES `TORNEO_ELIMINAZIONEDIRETTA` WRITE;
/*!40000 ALTER TABLE `TORNEO_ELIMINAZIONEDIRETTA` DISABLE KEYS */;
INSERT INTO `TORNEO_ELIMINAZIONEDIRETTA` VALUES ('S1','Milano',3,1,'Calcio 1',2017),('S2','Milano',3,1,'Calcio 1',2017),('S3','Milano',3,1,'Calcio 1',2017);
/*!40000 ALTER TABLE `TORNEO_ELIMINAZIONEDIRETTA` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TORNEO_ITALIANA`
--

DROP TABLE IF EXISTS `TORNEO_ITALIANA`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TORNEO_ITALIANA` (
  `NOMESQUADRA` varchar(45) NOT NULL,
  `CITTASQUADRA` varchar(45) NOT NULL,
  `PUNTI` int(11) NOT NULL,
  `NOMETORNEO` varchar(20) DEFAULT NULL,
  `ANNOTORNEO` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`NOMESQUADRA`,`CITTASQUADRA`,`PUNTI`)
) ENGINE=InnoDB DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TORNEO_ITALIANA`
--

LOCK TABLES `TORNEO_ITALIANA` WRITE;
/*!40000 ALTER TABLE `TORNEO_ITALIANA` DISABLE KEYS */;
INSERT INTO `TORNEO_ITALIANA` VALUES ('S1','Milano',0,'Calcio 1','2017'),('S2','Milano',1,'Calcio 1','2017'),('S3','Milano',1,'Calcio 1','2017');
/*!40000 ALTER TABLE `TORNEO_ITALIANA` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-23 23:04:09
