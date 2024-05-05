-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 05 mai 2024 à 10:07
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
(9, 'Qu\'est-ce que JSF ?', 'JavaServer Faces, un framework de développement d\'interfaces utilisateur pour les applications web Java', 'Java Server Framework', 'Java Server Flow', 'Java Swing Framework', 1),
(10, 'Quelle est la différence entre un conteneur web et un conteneur EJB ?', 'Le conteneur web gère les Servlets et JSP, tandis que le conteneur EJB gère les composants EJB', 'Le conteneur web gère les sessions HTTP, tandis que le conteneur EJB gère les transactions', 'Le conteneur web gère les requêtes HTTP, tandis que le conteneur EJB gère les accès à la base de données', 'Le conteneur web gère les pages HTML, tandis que le conteneur EJB gère les objets métier', 1),
(11, 'Qu\'est-ce que JMS ?', 'Java Message Service, une API Java pour la communication asynchrone basée sur des messages', 'Java Mail Service, une API Java pour l\'envoi de courriels', 'Java Micro Service, une architecture Java pour les microservices', 'Java Memory Service, une API Java pour la gestion de la mémoire', 1),
(12, 'Qu\'est-ce que JDBC ?', 'Java Database Connectivity, une API Java pour interagir avec les bases de données relationnelles', 'Java Distributed Component, une technologie Java pour les applications distribuées', 'Java Document Converter, une bibliothèque Java pour convertir des documents', 'Java Directory Service, une API Java pour l\'annuaire LDAP', 1),
(13, 'Qu\'est-ce que JNDI ?', 'Java Naming and Directory Interface, une API Java pour l\'accès aux services de nommage et de répertoire', 'Java Network Directory Interface, une API Java pour la gestion des réseaux', 'Java Naming and Database Interface, une API Java pour l\'accès aux bases de données', 'Java Network Development Interface, une API Java pour le développement réseau', 1),
(14, 'Qu\'est-ce que JTA ?', 'Java Transaction API, une API Java pour la gestion des transactions distribuées', 'Java Thread API, une API Java pour la gestion des threads', 'Java Timezone API, une API Java pour la gestion des fuseaux horaires', 'Java Token API, une API Java pour la gestion des jetons d\'accès', 1),
(15, 'Qu\'est-ce que JAXB ?', 'Java Architecture for XML Binding, une API Java pour la liaison XML-objet', 'Java API for XML Parsing, une API Java pour l\'analyse XML', 'Java API for XML Messaging, une API Java pour la messagerie XML', 'Java API for XML Services, une API Java pour les services XML', 1),
(16, 'Quels sont les principaux composants de l\'architecture MVC ?', 'Modèle, Vue, Contrôleur', 'Modèle, Visuel, Connecteur', 'Modèle, Valeur, Couche', 'Modèle, Vue, Composant', 1),
(17, 'Qu\'est-ce que la servlet de contrôle ?', 'Un composant de la couche de contrôleur dans l\'architecture MVC', 'Un composant de la couche de modèle dans l\'architecture MVC', 'Un composant de la couche de vue dans l\'architecture MVC', 'Un composant de la couche de service dans l\'architecture MVC', 1),
(18, 'Qu\'est-ce que le conteneur de servlets ?', 'Un composant du serveur web qui gère le cycle de vie des servlets', 'Un composant du serveur d\'application qui gère les EJB', 'Un conteneur Docker pour exécuter des applications web', 'Un composant de développement pour la création de servlets', 1),
(19, 'Qu\'est-ce que le patron de conception Front Controller ?', 'Un patron de conception utilisé pour centraliser la gestion des requêtes dans une application web', 'Un patron de conception utilisé pour gérer les interfaces utilisateur dans une application web', 'Un patron de conception utilisé pour valider les entrées utilisateur dans une application web', 'Un patron de conception utilisé pour stocker les données de session dans une application web', 1),
(23, 'Quel est le plus grand pays d\'Afrique ?', 'Algérie', 'Nigéria', 'Égypte', 'Afrique du Sud', 3),
(24, 'Quelle est la capitale du Canada ?', 'Ottawa', 'Toronto', 'Montréal', 'Vancouver', 3),
(25, 'Quel est le plus grand désert chaud du monde ?', 'Sahara', 'Kalahari', 'Atacama', 'Gobi', 3),
(26, 'Quelle est la plus haute montagne du monde ?', 'Mont Everest', 'K2', 'Kangchenjunga', 'Lhotse', 3),
(27, 'Quel est le plus grand lac d\'eau douce du monde ?', 'lac Supérieur', 'lac Baïkal', 'lac Victoria', 'lac Tanganyika', 3),
(28, 'Quel est le plus grand fleuve du monde en termes de débit ?', 'Amazone', 'Nil', 'Yangtsé', 'Mississippi', 3),
(29, 'Quelle est la plus grande île du monde ?', 'Groenland', 'Australie', 'Madagascar', 'Japon', 3),
(30, 'Quel est le plus grand pays d\'Amérique du Sud ?', 'Brésil', 'Argentine', 'Pérou', 'Colombie', 3),
(31, 'Quelle est la capitale de l\'Inde ?', 'New Delhi', 'Mumbai', 'Kolkata', 'Bangalore', 3),
(32, 'Quel est le plus petit continent du monde ?', 'Océanie', 'Europe', 'Amérique du Nord', 'Asie', 3),
(33, 'Dans quel océan se trouve l\'île de Madagascar ?', 'Océan Indien', 'Océan Atlantique', 'Océan Pacifique', 'Océan Arctique', 3),
(34, 'Quelle est la capitale de Madagascar ?', 'Antananarivo', 'Morondava', 'Toamasina', 'Fianarantsoa', 3),
(35, 'Quel est le plus grand lac de Madagascar ?', 'lac Alaotra', 'lac Tritriva', 'lac Itasy', 'lac Ravelobe', 3),
(36, 'Quel est le plus haut sommet de Madagascar ?', 'Mont Maromokotro', 'Mont Tsiafajavona', 'Mont Ivohibe', 'Mont Tsimanampetsotsa', 3),
(37, 'Quel est le plus grand parc national de Madagascar ?', 'Parc National de Masoala', 'Parc National de Ranomafana', 'Parc National d\'Andohahela', 'Parc National de Kirindy Mitea', 3),
(38, 'Quel est le plus long fleuve de Madagascar ?', 'Mangoky', 'Tsiribihina', 'Mananara Nord', 'Onilahy', 3),
(39, 'Quelle est la plus grande île de Madagascar ?', 'Nosy Be', 'Nosy Mitsio', 'Nosy Komba', 'Nosy Boraha', 3),
(40, 'Dans quelle province se trouve le parc national de l\'Isalo ?', 'Fianarantsoa', 'Toliara', 'Antananarivo', 'Antsiranana', 3),
(41, 'Quelle est la plus grande ville de Madagascar en termes de population ?', 'Antananarivo', 'Toamasina', 'Fianarantsoa', 'Mahajanga', 3),
(42, 'Quel est le principal port de Madagascar ?', 'Toamasina', 'Mahajanga', 'Toliara', 'Antsiranana', 3),
(43, 'Quel est le plus grand producteur de riz à Madagascar ?', 'Sahamalaza', 'Miangaly', 'Sambava', 'Lac Alaotra', 3),
(44, 'Quelle est la principale exportation de Madagascar ?', 'Vanille', 'Café', 'Poivre', 'Thé', 3),
(45, 'Quel est le plus grand parc national marin de Madagascar ?', 'Baly Bay', 'Antongil Bay', 'Tsimanampetsotsa', 'Nosy Tanikely', 3),
(46, 'Dans quelle région de Madagascar se trouve le Parc National de la Montagne d\'Ambre ?', 'Diana', 'Sava', 'Analanjirofo', 'Atsinanana', 3),
(47, 'Quelle est la plus grande réserve naturelle de Madagascar ?', 'Parc National de Zahamena', 'Parc National de la Marojejy', 'Réserve Naturelle Intégrale de Tsingy de Bemaraha', 'Réserve Spéciale d\'Ankarana', 3),
(48, 'Quel est le plus grand lac salé de Madagascar ?', 'Lac Tsimanampetsotsa', 'Lac Itasy', 'Lac Alaotra', 'Lac Tritriva', 3),
(49, 'Quelle est la plus grande baie de Madagascar ?', 'Baie d\'Antongil', 'Baie de Baly', 'Baie de Narinda', 'Baie de Manafiafy', 3),
(50, 'Dans quelle province se trouve la ville de Toliara ?', 'Toliara', 'Antananarivo', 'Fianarantsoa', 'Antsiranana', 3),
(51, 'Quel est le plus grand fleuve navigable de Madagascar ?', 'Tsiribihina', 'Mangoky', 'Mananara Nord', 'Onilahy', 3),
(52, 'Quel est le plus grand parc national de Madagascar ?', 'Parc National de l\'Isalo', 'Parc National de Masoala', 'Parc National de Ranomafana', 'Parc National de Kirindy Mitea', 3),
(53, 'Quelle est la plus grande péninsule de Madagascar ?', 'Péninsule de Masoala', 'Péninsule d\'Antongil', 'Péninsule de Mandena', 'Péninsule de Mahafaly', 3),
(54, 'Quelle est la capitale de la France ?', 'Paris', 'Londres', 'Berlin', 'Rome', 2),
(55, 'Quel est le plus long fleuve du monde ?', 'Nil', 'Amazone', 'Mississippi', 'Yangtsé', 2),
(56, 'Qui a écrit Hamlet ?', 'William Shakespeare', 'Charles Dickens', 'Jane Austen', 'Mark Twain', 2),
(57, 'Combien de continents y a-t-il ?', '7', '6', '5', '8', 2),
(58, 'Quel est le plus grand océan du monde ?', 'Océan Pacifique', 'Océan Atlantique', 'Océan Indien', 'Océan Arctique', 2),
(59, 'Combien de planètes composent notre système solaire ?', '8', '9', '7', '10', 2),
(60, 'Qui a peint la Joconde ?', 'Leonardo da Vinci', 'Pablo Picasso', 'Vincent van Gogh', 'Michel-Ange', 2),
(61, 'Quel est le symbole chimique de l\'or ?', 'Au', 'Ag', 'Fe', 'Cu', 2),
(62, 'Quelle est la capitale de l\'Espagne ?', 'Madrid', 'Barcelone', 'Rome', 'Lisbonne', 2),
(64, 'Qui a écrit le livre \"1984\" ?', 'George Orwell', 'Ernest Hemingway', 'F. Scott Fitzgerald', 'J.R.R. Tolkien', 2),
(65, 'Quelle est la plus grande ville des États-Unis ?', 'New York', 'Los Angeles', 'Chicago', 'Houston', 2),
(66, 'Qui a découvert l\'Amérique ?', 'Christophe Colomb', 'Vasco de Gama', 'Marco Polo', 'Amerigo Vespucci', 2),
(67, 'Quel est le plus grand désert du monde ?', 'Sahara', 'Antarctique', 'Gobi', 'Kalahari', 2),
(68, 'Quelle est la monnaie du Japon ?', 'Yen', 'Euro', 'Dollar', 'Peso', 2),
(69, 'Qui a écrit \"Le Petit Prince\" ?', 'Antoine de Saint-Exupéry', 'Victor Hugo', 'Charles Baudelaire', 'Voltaire', 2),
(70, 'Quel est le plus haut sommet du monde ?', 'Mont Everest', 'K2', 'Kangchenjunga', 'Lhotse', 2),
(71, 'Qui a peint la Nuit étoilée ?', 'Vincent van Gogh', 'Pablo Picasso', 'Leonardo da Vinci', 'Claude Monet', 2),
(72, 'Combien de langues officielles sont parlées aux Nations unies ?', '6', '5', '4', '7', 2),
(73, 'Quelle est la capitale de l\'Italie ?', 'Rome', 'Milan', 'Florence', 'Venise', 2);

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
(23, 80, '2024-05-03 17:12:04', 1, 5),
(24, 70, '2024-05-03 18:48:30', 3, 3),
(25, 60, '2024-05-03 18:53:50', 2, 3),
(26, 100, '2024-05-03 19:02:22', 1, 3),
(27, 90, '2024-05-03 19:39:31', 1, 5),
(28, 70, '2024-05-04 10:56:05', 3, 3),
(29, 50, '2024-05-04 11:02:04', 2, 17),
(30, 100, '2024-05-04 12:27:07', 15, 5),
(31, 100, '2024-05-05 05:40:27', 15, 5),
(32, 100, '2024-05-05 06:24:05', 1, 3);

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
(1, 'Java EE'),
(2, 'Général'),
(3, 'Pays et Géographie');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sexe` varchar(5) NOT NULL,
  `datenaiss` datetime NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(255) DEFAULT 'USER'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `email`, `sexe`, `datenaiss`, `password`, `role`) VALUES
(3, 'Torien', 'X', 'torien.x@jee.qcm', 'M', '1589-01-01 00:00:00', '123', 'USER'),
(5, 'Admin', 'User', 'admin@jee.qcm', 'M', '1001-01-01 00:00:00', '123', 'ADMIN'),
(17, 'Amara', 'Zeineb ', 'amara.zeineb@jee.qcm', 'F', '2000-06-13 00:00:00', '123', 'USER');

--
-- Index pour les tables déchargées
--

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
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;

--
-- AUTO_INCREMENT pour la table `scores`
--
ALTER TABLE `scores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT pour la table `tests`
--
ALTER TABLE `tests`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
