CREATE DATABASE  IF NOT EXISTS `studenthome` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `studenthome`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: studenthome
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `annuncio`
--

DROP TABLE IF EXISTS `annuncio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `annuncio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `utente_id` int(11) NOT NULL,
  `id_quartiere` int(11) DEFAULT NULL,
  `data` datetime DEFAULT current_timestamp(),
  `prezzo` decimal(10,2) NOT NULL,
  `descrizione` text DEFAULT NULL,
  `locali` int(11) DEFAULT NULL,
  `mq` int(11) DEFAULT NULL,
  `piano` int(11) DEFAULT NULL,
  `indirizzo` varchar(255) DEFAULT NULL,
  `stato` enum('attivo','non attivo') DEFAULT 'non attivo',
  `foto_annuncio` text DEFAULT NULL,
  `thumbnails` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `utente_id` (`utente_id`),
  CONSTRAINT `annuncio_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utente` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annuncio`
--

LOCK TABLES `annuncio` WRITE;
/*!40000 ALTER TABLE `annuncio` DISABLE KEYS */;
INSERT INTO `annuncio` VALUES (1,1,1,'2025-01-02 09:00:00',500.00,'Camera singola vicino al centro.',1,20,2,'via nervi 10, cesena','attivo','Appartamento1.jpeg','Appartamento1.jpeg, Appartamento2.jpeg'),(2,2,2,'2023-12-12 11:30:00',800.00,'Appartamento bilocale arredato.',2,60,1,'Via Cerchia di Sant\'Egidio, 807, Cesena','attivo','Appartamento2.jpeg','Appartamento1.jpeg, Appartamento2.jpeg'),(3,1,3,'2023-12-05 15:00:00',300.00,'Posto letto in stanza doppia.',1,15,3,'Via Portofino, 280, Cesena','attivo','postoletto.jpeg','Appartamento1.jpeg, Appartamento2.jpeg'),(25,17,4,'2025-01-16 18:18:45',500.00,'Ampio appartamento in zona Ippodromo',5,60,0,'Via L.C. Farini, 380','attivo','1737047925302-DJI_0840.JPG',NULL);
/*!40000 ALTER TABLE `annuncio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `annuncioservizio`
--

DROP TABLE IF EXISTS `annuncioservizio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `annuncioservizio` (
  `annuncio_id` int(11) NOT NULL,
  `servizio_id` int(11) NOT NULL,
  PRIMARY KEY (`annuncio_id`,`servizio_id`),
  KEY `servizio_id` (`servizio_id`),
  CONSTRAINT `annuncioservizio_ibfk_1` FOREIGN KEY (`annuncio_id`) REFERENCES `annuncio` (`id`) ON DELETE CASCADE,
  CONSTRAINT `annuncioservizio_ibfk_2` FOREIGN KEY (`servizio_id`) REFERENCES `servizio` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annuncioservizio`
--

LOCK TABLES `annuncioservizio` WRITE;
/*!40000 ALTER TABLE `annuncioservizio` DISABLE KEYS */;
INSERT INTO `annuncioservizio` VALUES (1,1),(1,2),(2,1),(2,3),(3,1),(3,4),(25,6);
/*!40000 ALTER TABLE `annuncioservizio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dettagli_annuncio`
--

DROP TABLE IF EXISTS `dettagli_annuncio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dettagli_annuncio` (
  `annuncio_id` int(11) NOT NULL,
  `tipologia_id` int(11) NOT NULL,
  `numero_inquilini` int(11) DEFAULT NULL,
  `contratto_min` int(11) DEFAULT NULL,
  `contratto_max` int(11) DEFAULT NULL,
  PRIMARY KEY (`annuncio_id`,`tipologia_id`),
  KEY `tipologia_id` (`tipologia_id`),
  CONSTRAINT `dettagli_annuncio_ibfk_1` FOREIGN KEY (`annuncio_id`) REFERENCES `annuncio` (`id`) ON DELETE CASCADE,
  CONSTRAINT `dettagli_annuncio_ibfk_2` FOREIGN KEY (`tipologia_id`) REFERENCES `tipologia` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettagli_annuncio`
--

LOCK TABLES `dettagli_annuncio` WRITE;
/*!40000 ALTER TABLE `dettagli_annuncio` DISABLE KEYS */;
INSERT INTO `dettagli_annuncio` VALUES (1,1,1,6,12),(2,2,2,12,24),(3,3,2,3,6),(25,1,0,0,0);
/*!40000 ALTER TABLE `dettagli_annuncio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titolo` varchar(255) NOT NULL,
  `contenuto` text DEFAULT NULL,
  `data_pubblicazione` datetime DEFAULT current_timestamp(),
  `foto_news` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'Nuovi appartamenti disponibili!','Nel centro storico di Cesena, sono disponibili gli ultimi appartamenti, un’occasione imperdibile per chi sogna di vivere in una zona ricca di storia e comodità. Le soluzioni abitative offrono finiture moderne, efficienza energetica e, in alcuni casi, splendide viste panoramiche sulla città.\n\nVivere in centro significa avere tutto a portata di mano: negozi, ristoranti, cultura e servizi, il tutto in un contesto esclusivo e ben collegato.\n\nNon lasciarti sfuggire questa opportunità unica! Contatta subito le agenzie immobiliari per una visita.','2023-12-20 10:00:00','news1.jpeg'),(2,'Promozione Inverno','L\'inverno porta con sé grandi opportunità di risparmio grazie alla nuova promozione invernale! Per un periodo limitato, puoi approfittare di sconti esclusivi su una vasta gamma di prodotti, dai capi d\'abbigliamento agli articoli per la casa.\n\nNon lasciarti sfuggire l’occasione di rinnovare il tuo guardaroba o di acquistare quel prodotto che desideravi da tempo, a prezzi imperdibili.\n\nCorri nei negozi aderenti o visita i loro shop online: l’inverno non è mai stato così conveniente!','2023-12-18 09:30:00','news2.jpeg'),(3,'Da oggi StudentHome è anche su mobile!','Siamo entusiasti di annunciare una grande novità:\n\nStudentHome è finalmente disponibile anche su dispositivi mobile! \n\nLa nostra piattaforma è stata ottimizzata per offrirti un’esperienza semplice, fluida e accessibile direttamente dal tuo smartphone o tablet.\n\nChe tu sia in viaggio, in pausa tra le lezioni o comodamente seduto sul divano, potrai cercare appartamenti o camere in affitto con la stessa facilità della versione desktop. Navigare tra gli annunci, salvare le tue opzioni preferite e contattare i proprietari non è mai stato così comodo e veloce.\n\nNon importa dove ti trovi: con StudentHome la tua nuova casa è sempre a portata di mano. Prova subito la nostra versione mobile e scopri quanto è facile trovare il tuo prossimo alloggio!','2025-01-13 08:08:08','news3.jpg'),(4,'Qualche imperdibile notizia!','StudentHome è lieta di annunciare una nuova partnership con il Cinema Aurora! \n\nA partire da oggi, tutti gli studenti registrati sulla nostra piattaforma potranno beneficiare di uno sconto del 20% sui biglietti per i film in programmazione.\n\nChe tu voglia rilassarti dopo una lunga giornata di studio o organizzare una serata con i coinquilini, questa è l’occasione perfetta per vivere momenti di svago a prezzi vantaggiosi.\n\nNon dimenticare di mostrare il tuo badge digitale StudentHome alla cassa per ottenere lo sconto.\n\nBuona visione!','2025-01-13 08:08:08','news4.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferiti`
--

DROP TABLE IF EXISTS `preferiti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferiti` (
  `utente_id` int(11) NOT NULL,
  `annuncio_id` int(11) NOT NULL,
  PRIMARY KEY (`utente_id`,`annuncio_id`),
  KEY `annuncio_id` (`annuncio_id`),
  CONSTRAINT `preferiti_ibfk_1` FOREIGN KEY (`utente_id`) REFERENCES `utente` (`id`) ON DELETE CASCADE,
  CONSTRAINT `preferiti_ibfk_2` FOREIGN KEY (`annuncio_id`) REFERENCES `annuncio` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferiti`
--

LOCK TABLES `preferiti` WRITE;
/*!40000 ALTER TABLE `preferiti` DISABLE KEYS */;
/*!40000 ALTER TABLE `preferiti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quartierezona`
--

DROP TABLE IF EXISTS `quartierezona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quartierezona` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_quartiere` varchar(100) NOT NULL,
  `descrizione` text DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_quartiere` (`nome_quartiere`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quartierezona`
--

LOCK TABLES `quartierezona` WRITE;
/*!40000 ALTER TABLE `quartierezona` DISABLE KEYS */;
INSERT INTO `quartierezona` VALUES (1,'Centro','Quartiere centrale con tutti i servizi a portata di mano.'),(2,'Oltresavio','Zona residenziale tranquilla con ottimi collegamenti.'),(3,'Valle Savio','Area immersa nel verde, ideale per chi cerca pace.'),(4,'Ippodromo','Zona vicino all\'ippodromo'),(5,'Fiorenzuola','Situato a sud del centro, include aree residenziali e commerciali.'),(6,'Cesuola','Quartiere collinare e residenziale che si estende a ovest del centro storico.'),(7,'Vigne','Quartiere popoloso con diverse infrastrutture, parchi e aree residenziali.\n\n'),(8,'Ravennate','Comprende aree a nord della città, lungo la strada verso Ravenna.'),(9,'Rubicone','Zona situata a nord-est verso il fiume Rubicone.');
/*!40000 ALTER TABLE `quartierezona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servizio`
--

DROP TABLE IF EXISTS `servizio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servizio` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome_servizio` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_servizio` (`nome_servizio`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servizio`
--

LOCK TABLES `servizio` WRITE;
/*!40000 ALTER TABLE `servizio` DISABLE KEYS */;
INSERT INTO `servizio` VALUES (4,'Aria Condizionata'),(3,'Ascensore'),(9,'Asciugatrice'),(10,'Forno'),(5,'Garage'),(11,'Lavastoviglie'),(8,'Lavatrice'),(7,'Netflix'),(2,'Riscaldamento autonomo'),(6,'Sky'),(1,'Wi-Fi');
/*!40000 ALTER TABLE `servizio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipologia`
--

DROP TABLE IF EXISTS `tipologia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipologia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipologia`
--

LOCK TABLES `tipologia` WRITE;
/*!40000 ALTER TABLE `tipologia` DISABLE KEYS */;
INSERT INTO `tipologia` VALUES (1,'stanza'),(2,'appartamento'),(3,'posto letto');
/*!40000 ALTER TABLE `tipologia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cognome` varchar(50) DEFAULT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `mail` varchar(100) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `ruolo` enum('admin','utente') DEFAULT 'utente',
  `data_registrazione` datetime DEFAULT current_timestamp(),
  `foto_profilo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mail` (`mail`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Rossi','Marco','marco.rossi@studenthome.com','3331112222','hashed_password1','utente','2023-12-01 10:00:00',NULL),(2,'Bianchi','Luca','luca.bianchi@studenthome.com','3332223333','hashed_password2','utente','2023-12-10 14:30:00',NULL),(3,'Verdi','Anna','anna.verdi@studenthome.com','3333334444','hashed_password3','admin','2023-11-20 08:15:00',NULL),(17,'test','test','test@test.com','3336669696','$2b$10$uyTdqh08rodh4KvQGw6.Zuhwpb0haJpftCFK/UBXEkW7cX9WEyxcu','admin','2025-01-15 18:29:10',NULL),(19,'utente','utente','utente@utente.com','1234567890','$2b$10$aSwF/FlUnzxIdgzSBCtiDuYKMOpiqTEDL.e/rS.7aFKzNGvvFJJQK','utente','2025-01-17 09:21:07',NULL);
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-19 16:19:06
