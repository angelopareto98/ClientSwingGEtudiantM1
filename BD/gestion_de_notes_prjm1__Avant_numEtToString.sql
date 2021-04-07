-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mer. 07 avr. 2021 à 23:01
-- Version du serveur :  10.3.15-MariaDB-1
-- Version de PHP : 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_de_notes_prjm1`
--

-- --------------------------------------------------------

--
-- Structure de la table `Etudiant`
--

CREATE TABLE `Etudiant` (
  `numEt` int(10) NOT NULL,
  `nomEt` varchar(50) NOT NULL,
  `niveauEt` varchar(3) NOT NULL,
  `moyenne` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Etudiant`
--

INSERT INTO `Etudiant` (`numEt`, `nomEt`, `niveauEt`, `moyenne`) VALUES
(1, 'Numero un', 'M1', 16),
(2, 'Numero deux', 'M1', 2),
(5, 'Puyol', 'L1', 10),
(12, 'Abel Anicet', 'L1', NULL),
(33, 'Edmondo', 'M1', 13),
(35, 'Kererion', 'M1', NULL),
(44, 'Hackeretovic', 'M1', NULL),
(66, 'UPdate', 'M1', NULL),
(95, 'UPdate', 'M1', 16),
(122, 'UPdate', 'M1', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `Matiere`
--

CREATE TABLE `Matiere` (
  `codeMat` varchar(7) NOT NULL,
  `libelleMat` varchar(50) NOT NULL,
  `coefMat` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Matiere`
--

INSERT INTO `Matiere` (`codeMat`, `libelleMat`, `coefMat`) VALUES
('E001', 'Analyse Numerique', 2),
('E002', 'Gestion du Projet', 3),
('E003', 'Analyse de donnees', 2),
('E004', 'Technique de communication', 1),
('E005', 'Jython', 1),
('E006', 'Java Avancé', 2),
('E007', 'Tena tsara', 3);

-- --------------------------------------------------------

--
-- Structure de la table `Moyenne`
--

CREATE TABLE `Moyenne` (
  `note` int(11) DEFAULT NULL,
  `moyenne` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Moyenne`
--

INSERT INTO `Moyenne` (`note`, `moyenne`) VALUES
(13, 14),
(12, NULL),
(13, 13);

-- --------------------------------------------------------

--
-- Structure de la table `Notes`
--

CREATE TABLE `Notes` (
  `numInscription` varchar(10) NOT NULL,
  `numEt` int(10) NOT NULL,
  `codeMat` varchar(7) NOT NULL,
  `note` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `Notes`
--

INSERT INTO `Notes` (`numInscription`, `numEt`, `codeMat`, `note`) VALUES
('444 H-F', 35, 'E003', 17),
('686 H-F', 95, 'E002', 18),
('686 H-F', 95, 'E003', 16),
('33 H-F', 33, 'E001', 7),
('33 H-F', 33, 'E002', 7),
('686 H-F', 95, 'E002', 15),
('686 H-F', 95, 'E007', 15),
('5 H-F', 5, 'E002', 15),
('5 H-F', 5, 'E007', 14),
('5 H-F', 5, 'E001', 4),
('5 H-F', 5, 'E003', 7),
('5 H-F', 5, 'E004', 2),
('5 H-F', 5, 'E006', 17),
('33 H-F', 33, 'E003', 17),
('33 H-F', 33, 'E004', 17),
('1 H-F', 1, 'E001', 17),
('1 H-F', 1, 'E002', 14),
('2 H-F', 2, 'E004', 12);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `Etudiant`
--
ALTER TABLE `Etudiant`
  ADD PRIMARY KEY (`numEt`);

--
-- Index pour la table `Matiere`
--
ALTER TABLE `Matiere`
  ADD PRIMARY KEY (`codeMat`);

--
-- Index pour la table `Notes`
--
ALTER TABLE `Notes`
  ADD KEY `fk_etudiant_numEt` (`numEt`),
  ADD KEY `fk_matiere_codeMat` (`codeMat`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `Notes`
--
ALTER TABLE `Notes`
  ADD CONSTRAINT `Notes_ibfk_1` FOREIGN KEY (`numEt`) REFERENCES `Etudiant` (`numEt`),
  ADD CONSTRAINT `Notes_ibfk_2` FOREIGN KEY (`codeMat`) REFERENCES `Matiere` (`codeMat`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
