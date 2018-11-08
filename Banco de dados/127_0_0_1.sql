-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 08-Nov-2018 às 11:45
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `compaerea`
--
CREATE DATABASE IF NOT EXISTS `compaerea` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `compaerea`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `assento`
--

CREATE TABLE IF NOT EXISTS `assento` (
  `cod` varchar(45) NOT NULL,
  `aviao_cod` int(11) NOT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk_assento_aviao1_idx` (`aviao_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `assento`
--

INSERT INTO `assento` (`cod`, `aviao_cod`) VALUES
('a01a01', 1),
('a01a02', 1),
('a01a03', 1),
('a01a04', 1),
('a01a05', 1),
('a01a06', 1),
('a02a01', 2),
('a02a02', 2),
('a02a03', 2),
('a02a04', 2),
('a02a05', 2),
('a04a02', 2),
('a03a01', 3),
('a03a02', 3),
('a03a03', 3),
('a03a04', 3),
('a03a05', 3),
('a04a01', 4),
('a04a03', 4),
('a04a04', 4),
('a04a05', 4),
('a05a01', 5),
('a05a02', 5),
('a05a03', 5),
('a05a04', 5),
('a05a05', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `aviao`
--

CREATE TABLE IF NOT EXISTS `aviao` (
  `cod` int(11) NOT NULL,
  `marca` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `voo_cod` int(11) NOT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk_aviao_voo1_idx` (`voo_cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `aviao`
--

INSERT INTO `aviao` (`cod`, `marca`, `modelo`, `voo_cod`) VALUES
(1, 'wolksvagem', 'L171', 1),
(2, 'Goow', 'G129', 2),
(3, 'Fordy', 'F01', 3),
(4, 'Khaizer', 'K7', 4),
(5, 'Fiatche', 'Fdos', 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `reserva`
--

CREATE TABLE IF NOT EXISTS `reserva` (
  `cod` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(45) DEFAULT NULL,
  `assento_cod` varchar(45) NOT NULL,
  PRIMARY KEY (`cod`),
  KEY `fk_reserva_assento_idx` (`assento_cod`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Extraindo dados da tabela `reserva`
--

INSERT INTO `reserva` (`cod`, `cpf`, `assento_cod`) VALUES
(1, '123', 'a01a01'),
(2, '123', 'a02a01'),
(3, '123', 'a05a01'),
(8, '09817224660', 'a01a02');

-- --------------------------------------------------------

--
-- Estrutura da tabela `voo`
--

CREATE TABLE IF NOT EXISTS `voo` (
  `cod` int(11) NOT NULL,
  `origem` varchar(45) DEFAULT NULL,
  `datasaida` varchar(45) DEFAULT NULL,
  `destino` varchar(45) DEFAULT NULL,
  `datachegada` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `voo`
--

INSERT INTO `voo` (`cod`, `origem`, `datasaida`, `destino`, `datachegada`) VALUES
(1, 'passos', '10/10/2018-11:15', 'alpinopolis', '10/10/2018-11:20'),
(2, 'passos', '15/11/2018-12:15', 'salvador', '19/11/2018-12:45'),
(3, 'passos', '15/11/2018-12:15', 'formiga', '15/11/2018-15:45'),
(4, 'passos', '15/11/2018-12:15', 'gloria', '15/11/2018-15:45'),
(5, 'passos', '15/11/2018-12:15', 'itau', '15/11/2018-12:15');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `assento`
--
ALTER TABLE `assento`
  ADD CONSTRAINT `fk_assento_aviao1` FOREIGN KEY (`aviao_cod`) REFERENCES `aviao` (`cod`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `aviao`
--
ALTER TABLE `aviao`
  ADD CONSTRAINT `fk_aviao_voo1` FOREIGN KEY (`voo_cod`) REFERENCES `voo` (`cod`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `reserva`
--
ALTER TABLE `reserva`
  ADD CONSTRAINT `fk_reserva_assento` FOREIGN KEY (`assento_cod`) REFERENCES `assento` (`cod`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
