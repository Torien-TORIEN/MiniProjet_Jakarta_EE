-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 24 avr. 2024 à 01:50
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `jeeqcmdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `nations`
--

CREATE TABLE `nations` (
  `id` int(11) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `population` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `nations`
--

INSERT INTO `nations` (`id`, `nom`, `population`) VALUES
(1, 'France', 67000000),
(2, 'Allemagne', 83000000),
(3, 'Espagne', 47000000),
(4, 'Italie', 60000000),
(5, 'Royaume-Uni', 66000000);

-- --------------------------------------------------------

--
-- Structure de la table `questions`
--

CREATE TABLE `questions` (
  `id` int(11) NOT NULL,
  `question` text NOT NULL,
  `reponse` text NOT NULL,
  `rep1` varchar(255) NOT NULL,
  `rep2` varchar(255) NOT NULL,
  `rep3` varchar(255) NOT NULL,
  `id_test` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `questions`
--

INSERT INTO `questions` (`id`, `question`, `reponse`, `rep1`, `rep2`, `rep3`, `id_test`) VALUES
(2, 'Qu\'est-ce que Java EE ?', 'Java Enterprise Edition', 'Java Standard Edition', 'Java Micro Edition', 'Java Android Edition', 1),
(3, 'Quels sont les composants principaux de Java EE ?', 'Servlets, JSP, EJB, JPA, JSF, JMS, etc.', 'Swing, AWT, JavaFX', 'Hibernate, Spring, Struts', 'JDBC, JNDI, JTA', 1),
(4, 'Qu\'est-ce qu\'un Servlet ?', 'Un composant Java utilisé pour créer des applications web', 'Un framework de développement d\'applications web', 'Une classe Java utilisée pour manipuler les bases de données', 'Un format de fichier XML utilisé pour configurer les applications Java', 1),
(5, 'Qu\'est-ce qu\'un JSP ?', 'JavaServer Pages, une technologie utilisée pour créer des pages web dynamiques', 'Java Standard Protocol', 'Java Server Project', 'Java Server Programming', 1),
(6, 'Qu\'est-ce qu\'un EJB ?', 'Enterprise JavaBeans, un composant d\'entreprise utilisé pour développer des applications distribuées', 'Event JavaBeans, un gestionnaire d\'événements en Java', 'Easy JavaBeans, une bibliothèque Java pour les débutants', 'Extended JavaBeans, une version améliorée de JavaBeans', 1),
(7, 'Qu\'est-ce que JPA ?', 'Java Persistence API, une spécification Java pour la persistance des données', 'Java Programming Architecture', 'Java Persistence Architecture', 'Java Persistence Application', 1),
(8, 'Quels sont les avantages de JPA ?', 'Abstraction de la persistance des données, facilité d\'utilisation, indépendance du fournisseur', 'Haute performance, faible consommation de mémoire', 'Simplicité, flexibilité, rapidité', 'Interopérabilité, évolutivité, sécurité', 1),
(9, 'Qu\'est-ce que JSF ?', 'JavaServer Faces, un framework de développement d\'interfaces utilisateur pour les applications web J', 'Java Server Framework', 'Java Server Flow', 'Java Swing Framework', 1),
(10, 'Quelle est la différence entre un conteneur web et un conteneur EJB ?', 'Le conteneur web gère les Servlets et JSP, tandis que le conteneur EJB gère les composants EJB', 'Le conteneur web gère les sessions HTTP, tandis que le conteneur EJB gère les transactions', 'Le conteneur web gère les requêtes HTTP, tandis que le conteneur EJB gère les accès à la base de don', 'Le conteneur web gère les pages HTML, tandis que le conteneur EJB gère les objets métier', 1),
(11, 'Qu\'est-ce que JMS ?', 'Java Message Service, une API Java pour la communication asynchrone basée sur des messages', 'Java Mail Service, une API Java pour l\'envoi de courriels', 'Java Micro Service, une architecture Java pour les microservices', 'Java Memory Service, une API Java pour la gestion de la mémoire', 1),
(12, 'Qu\'est-ce que JDBC ?', 'Java Database Connectivity, une API Java pour interagir avec les bases de données relationnelles', 'Java Distributed Component, une technologie Java pour les applications distribuées', 'Java Document Converter, une bibliothèque Java pour convertir des documents', 'Java Directory Service, une API Java pour l\'annuaire LDAP', 1),
(13, 'Qu\'est-ce que JNDI ?', 'Java Naming and Directory Interface, une API Java pour l\'accès aux services de nommage et de réperto', 'Java Network Directory Interface, une API Java pour la gestion des réseaux', 'Java Naming and Database Interface, une API Java pour l\'accès aux bases de données', 'Java Network Development Interface, une API Java pour le développement réseau', 1),
(14, 'Qu\'est-ce que JTA ?', 'Java Transaction API, une API Java pour la gestion des transactions distribuées', 'Java Thread API, une API Java pour la gestion des threads', 'Java Timezone API, une API Java pour la gestion des fuseaux horaires', 'Java Token API, une API Java pour la gestion des jetons d\'accès', 1),
(15, 'Qu\'est-ce que JAXB ?', 'Java Architecture for XML Binding, une API Java pour la liaison XML-objet', 'Java API for XML Parsing, une API Java pour l\'analyse XML', 'Java API for XML Messaging, une API Java pour la messagerie XML', 'Java API for XML Services, une API Java pour les services XML', 1),
(16, 'Quels sont les principaux composants de l\'architecture MVC ?', 'Modèle, Vue, Contrôleur', 'Modèle, Visuel, Connecteur', 'Modèle, Valeur, Couche', 'Modèle, Vue, Composant', 1),
(17, 'Qu\'est-ce que la servlet de contrôle ?', 'Un composant de la couche de contrôleur dans l\'architecture MVC', 'Un composant de la couche de modèle dans l\'architecture MVC', 'Un composant de la couche de vue dans l\'architecture MVC', 'Un composant de la couche de service dans l\'architecture MVC', 1),
(18, 'Qu\'est-ce que le conteneur de servlets ?', 'Un composant du serveur web qui gère le cycle de vie des servlets', 'Un composant du serveur d\'application qui gère les EJB', 'Un conteneur Docker pour exécuter des applications web', 'Un composant de développement pour la création de servlets', 1),
(19, 'Qu\'est-ce que le patron de conception Front Controller ?', 'Un patron de conception utilisé pour centraliser la gestion des requêtes dans une application web', 'Un patron de conception utilisé pour gérer les interfaces utilisateur dans une application web', 'Un patron de conception utilisé pour valider les entrées utilisateur dans une application web', 'Un patron de conception utilisé pour stocker les données de session dans une application web', 1);

-- --------------------------------------------------------

--
-- Structure de la table `scores`
--

CREATE TABLE `scores` (
  `id` int(11) NOT NULL,
  `score` double NOT NULL,
  `done_at` datetime NOT NULL DEFAULT current_timestamp(),
  `id_test` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `scores`
--

INSERT INTO `scores` (`id`, `score`, `done_at`, `id_test`, `id_user`) VALUES
(1, 75.5, '2024-04-24 00:00:00', 1, 1),
(2, 80.5, '2024-04-24 00:00:00', 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `tests`
--

CREATE TABLE `tests` (
  `id` int(11) NOT NULL,
  `categorie` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `tests`
--

INSERT INTO `tests` (`id`, `categorie`) VALUES
(1, 'Java'),
(2, 'Général'),
(3, 'Pays et Géographie');

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `sexe` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `nom`, `prenom`, `email`, `sexe`) VALUES
(1, 'Torien', 'Toto', 'toto@email.com', 'M');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `datenaiss` datetime NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(255) DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `sexe`, `datenaiss`, `password`, `role`) VALUES
(1, 'Doe', 'John', 'john.doe@example.com', 'M', '1990-01-01 00:00:00', '', 'USER'),
(2, 'Smith', 'Jane', 'jane.smith@example.c', 'F', '1995-03-15 00:00:00', '', 'USER'),
(3, 'TORIEN', 'X', 'torien@qcm.com', 'M', '1999-05-24 00:00:00', '123', 'USER'),
(4, 'Kim', 'Soo', 'soo.kim@example.com', 'F', '1988-11-10 00:00:00', '', 'USER'),
(5, 'Singh', 'Raj', 'raj.singh@example.co', 'M', '1993-05-25 00:00:00', '', 'USER');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `nations`
--
ALTER TABLE `nations`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `scores`
--
ALTER TABLE `scores`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `tests`
--
ALTER TABLE `tests`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT pour la table `scores`
--
ALTER TABLE `scores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `tests`
--
ALTER TABLE `tests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
