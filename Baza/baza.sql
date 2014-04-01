-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 06, 2014 at 08:34 PM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `baza`
--
CREATE DATABASE IF NOT EXISTS `baza` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `baza`;

-- --------------------------------------------------------

--
-- Table structure for table `artikal`
--

CREATE TABLE IF NOT EXISTS `artikal` (
  `evBroj` int(11) NOT NULL AUTO_INCREMENT,
  `vrsta` smallint(6) NOT NULL,
  `naziv` varchar(35) COLLATE utf8_bin NOT NULL,
  `model` varchar(35) COLLATE utf8_bin NOT NULL,
  `datumNabavke` date NOT NULL,
  `garantniRok` date NOT NULL,
  `idKomp` int(11) NOT NULL,
  `vredDin` double NOT NULL,
  `vredEur` double NOT NULL,
  `idLab` int(11) NOT NULL,
  `idStatusa` int(11) NOT NULL,
  `kolicina` int(11) NOT NULL,
  PRIMARY KEY (`evBroj`),
  KEY `idKomp` (`idKomp`,`idStatusa`),
  KEY `idStatusa` (`idStatusa`),
  KEY `idKomp_2` (`idKomp`),
  KEY `idLab` (`idLab`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=14 ;

--
-- Dumping data for table `artikal`
--

INSERT INTO `artikal` (`evBroj`, `vrsta`, `naziv`, `model`, `datumNabavke`, `garantniRok`, `idKomp`, `vredDin`, `vredEur`, `idLab`, `idStatusa`, `kolicina`) VALUES
(2, 2, 'sto', 'radni', '2014-01-01', '2016-01-01', 1, 5000, 50, 103, 3, 20),
(4, 2, 'stolica', 'mega2000', '2013-04-02', '2016-02-06', 1, 2000, 20, 103, 1, 20),
(5, 2, 'orman', 'ultra lajt', '2014-04-09', '2016-02-06', 1, 8000, 80, 103, 1, 2),
(6, 1, 'projektor', 'Dell 2134', '2014-03-01', '2016-02-06', 2, 30000, 300, 103, 1, 1),
(7, 1, 'monitor', 'LS22C150NS/EN', '2013-11-01', '2015-11-01', 3, 13000, 130, 405, 7, 23),
(8, 1, 'racunar', 'MT A6-3600', '2014-01-16', '2016-01-16', 4, 47880, 470, 405, 1, 20),
(10, 1, 'projektor', 'kjkszpj', '2014-02-03', '2016-02-03', 3, 45000, 450, 103, 1, 1),
(11, 2, 'orman', 'hrastov', '2014-02-03', '2024-02-03', 1, 15000, 150, 109, 4, 3),
(12, 1, 'monitor', 'samsung MN-3212', '2014-02-02', '2016-02-02', 3, 16000, 160, 109, 7, 20),
(13, 2, 'sto ', 'kancelarijski', '2014-01-08', '2019-01-08', 5, 7000, 70, 405, 7, 24);

-- --------------------------------------------------------

--
-- Table structure for table `kompanija`
--

CREATE TABLE IF NOT EXISTS `kompanija` (
  `idKomp` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(30) COLLATE utf8_bin NOT NULL,
  `adresa` varchar(50) COLLATE utf8_bin NOT NULL,
  `telefon` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin NOT NULL,
  `pib` int(11) NOT NULL,
  `imeKontakta` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idKomp`),
  KEY `idKomp` (`idKomp`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Dumping data for table `kompanija`
--

INSERT INTO `kompanija` (`idKomp`, `naziv`, `adresa`, `telefon`, `email`, `pib`, `imeKontakta`) VALUES
(1, 'Forma ideale', 'Dusanovac 1', '011/8563324', 'formaideale@ideale.com', 103251239, 'Neko Nekic'),
(2, 'Dell', 'Proleterskih brigada', '011/658463', 'prodaja@dell.com', 21543, 'Osoba u dellu'),
(3, 'samsung', 'Beogradska 1', '011/5846-236', 'prodaja@samsung.com', 66958, 'Kontakt u samsungu'),
(4, 'HP', 'Beogradska 2', '011/8556-321', 'prodaja@hp.com', 53212, 'Osoba u hpu'),
(5, 'Eurosalon', 'Bulevar Vojvode Misica 12', '+38 11 12067', 'info@eurosalon.rs', 106109857, 'Rastko Lazic');

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

CREATE TABLE IF NOT EXISTS `korisnici` (
  `ime` varchar(20) COLLATE utf8_bin NOT NULL,
  `prezime` varchar(20) COLLATE utf8_bin NOT NULL,
  `kor_ime` varchar(16) COLLATE utf8_bin NOT NULL,
  `lozinka` varchar(16) COLLATE utf8_bin NOT NULL,
  `adresa` varchar(50) COLLATE utf8_bin NOT NULL,
  `telefon` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `tip` int(11) DEFAULT NULL,
  `odobren` tinyint(4) NOT NULL,
  `prihvacen` tinyint(4) NOT NULL,
  PRIMARY KEY (`kor_ime`),
  KEY `kor_ime` (`kor_ime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`ime`, `prezime`, `kor_ime`, `lozinka`, `adresa`, `telefon`, `email`, `tip`, `odobren`, `prihvacen`) VALUES
('Dragan', 'Nikolic', 'flojd123', 'flojd123', 'Neznanog junaka 1, Beograd', '011/1111111', 'dragan@nikolic.com', 3, 1, 1),
('Jovan', 'Kojadinovic', 'kojadin123', '123jovan', 'Jovanova ulica 23, Beograd', '066/123654', 'jovan@kojadinovic.com', 2, 1, 1),
('marko', 'markovic', 'mmarko', 'marko123', 'Markova ulica 1,Beograd', '021/54862146', 'marko@markovic.com', 2, 1, 1),
('Nenad', 'Jakovljevic', 'nenad123', 'nenad123', 'Nenadova ulica 23,Beograd', '064/4444444', 'nenad@gmail.com', NULL, 0, 0),
('nenad', 'tasic', 'nenadn', 'nenad123', 'neka adresa 2, Beograd', '123232141', 'nenad.tasic@asdf.asdf', NULL, 0, 0),
('nikola', 'nikolic', 'nikolaniko', 'nikola1234', 'ds ds ds', '2345', 'nikola.nikolic@asdf.asdf', 2, 0, 1),
('petar', 'stankovic', 'petar123', 'petar123', 'zeleznicka 49', '38923819', 'kameni92@yahoo.com', 2, 1, 1),
('Predrag', 'Tasic', 'predrag', 'pedja1234', 'Put za Klenje 2b, Beograd', '012/3456789', 'pedja-t@hotmail.com', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `laboratorija`
--

CREATE TABLE IF NOT EXISTS `laboratorija` (
  `idLab` int(11) NOT NULL,
  `naziv` varchar(50) COLLATE utf8_bin NOT NULL,
  `kvadratura` double NOT NULL,
  `brojMesta` int(11) NOT NULL,
  PRIMARY KEY (`idLab`),
  KEY `idLab` (`idLab`),
  KEY `idLab_2` (`idLab`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `laboratorija`
--

INSERT INTO `laboratorija` (`idLab`, `naziv`, `kvadratura`, `brojMesta`) VALUES
(103, 'Laboratorija za elektroniku', 35, 20),
(109, 'Laboratorija za Protokole', 35.4, 24),
(112, 'Laboratorija za TVP', 22, 22),
(202, 'Laboratorija za sve prilike', 100, 100),
(405, 'Laboratorija za Baze podataka', 15.4, 20);

-- --------------------------------------------------------

--
-- Table structure for table `pomocna`
--

CREATE TABLE IF NOT EXISTS `pomocna` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kor_ime` varchar(16) COLLATE utf8_bin NOT NULL,
  `idLab` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `kor_ime` (`kor_ime`,`idLab`),
  KEY `idLab` (`idLab`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=11 ;

--
-- Dumping data for table `pomocna`
--

INSERT INTO `pomocna` (`id`, `kor_ime`, `idLab`) VALUES
(5, 'kojadin123', 103),
(8, 'kojadin123', 202),
(6, 'kojadin123', 405),
(4, 'mmarko', 103),
(1, 'mmarko', 109),
(9, 'mmarko', 202),
(3, 'nikolaniko', 109),
(2, 'nikolaniko', 405),
(10, 'petar123', 103),
(7, 'petar123', 202);

-- --------------------------------------------------------

--
-- Table structure for table `sifrarnik`
--

CREATE TABLE IF NOT EXISTS `sifrarnik` (
  `idStatusa` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idStatusa`),
  KEY `idStatusa` (`idStatusa`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=8 ;

--
-- Dumping data for table `sifrarnik`
--

INSERT INTO `sifrarnik` (`idStatusa`, `naziv`) VALUES
(1, 'ispravno'),
(2, 'neispravno'),
(3, 'traba servisirati'),
(4, 'odneto na servis'),
(5, 'vraceno sa servisa'),
(6, 'treba odpisati'),
(7, 'odpisano');

-- --------------------------------------------------------

--
-- Table structure for table `zahtevnabavka`
--

CREATE TABLE IF NOT EXISTS `zahtevnabavka` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tip` smallint(6) NOT NULL,
  `naziv` varchar(30) COLLATE utf8_bin NOT NULL,
  `kolicina` int(11) NOT NULL,
  `idLab` int(11) NOT NULL,
  `odobren` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idLab` (`idLab`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Dumping data for table `zahtevnabavka`
--

INSERT INTO `zahtevnabavka` (`id`, `tip`, `naziv`, `kolicina`, `idLab`, `odobren`) VALUES
(1, 1, 'tastatura', 22, 109, 0),
(2, 1, 'laptop', 1, 109, 0),
(3, 2, 'orman', 2, 103, 0);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artikal`
--
ALTER TABLE `artikal`
  ADD CONSTRAINT `artikal_ibfk_1` FOREIGN KEY (`idKomp`) REFERENCES `kompanija` (`idKomp`) ON UPDATE CASCADE,
  ADD CONSTRAINT `artikal_ibfk_2` FOREIGN KEY (`idStatusa`) REFERENCES `sifrarnik` (`idStatusa`) ON UPDATE CASCADE,
  ADD CONSTRAINT `artikal_ibfk_3` FOREIGN KEY (`idLab`) REFERENCES `laboratorija` (`idLab`) ON UPDATE CASCADE;

--
-- Constraints for table `pomocna`
--
ALTER TABLE `pomocna`
  ADD CONSTRAINT `pomocna_ibfk_1` FOREIGN KEY (`kor_ime`) REFERENCES `korisnici` (`kor_ime`) ON UPDATE CASCADE,
  ADD CONSTRAINT `pomocna_ibfk_2` FOREIGN KEY (`idLab`) REFERENCES `laboratorija` (`idLab`) ON UPDATE CASCADE;

--
-- Constraints for table `zahtevnabavka`
--
ALTER TABLE `zahtevnabavka`
  ADD CONSTRAINT `zahtevnabavka_ibfk_1` FOREIGN KEY (`idLab`) REFERENCES `laboratorija` (`idLab`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
