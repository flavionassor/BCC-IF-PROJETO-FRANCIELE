  CREATE DATABASE compaerea;
  use compaerea;
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
('a02a01', 2),
('a02a02', 2),
('a05a01', 5),
('a05a02', 5);

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
(1, '1', '1', 1),
(2, '2', '2', 1),
(3, '3', '3', 2),
(4, '4', '4', 2),
(5, '5', '5', 3),
(6, '6', '6', 3),
(7, '7', '7', 4),
(8, '8', '8', 4),
(9, '9', '9', 5),
(10, '10', '10', 5);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `reserva`
--

INSERT INTO `reserva` (`cod`, `cpf`, `assento_cod`) VALUES
(1, '123', 'a01a01'),
(2, '123', 'a02a01'),
(3, '123', 'a05a01');

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
(1, '1', '1', '1', '1'),
(2, '2', '2', '2', '2'),
(3, '3', '3', '3', '3'),
(4, '4', '4', '4', '4'),
(5, '5', '5', '5', '5');

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
