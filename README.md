# MINI PROJET JAVA JEE
Ce projet est réalisé dans le cadre du cours JAVA  Entreprise Edition, à POLYTECH ANGERS pour combiner et enrichir les techiniques appreises lors des travaux pratiques.
Ceci est docn dévéloppé par TORIEN et AMARA Zeineb au cours de l'année scolaire 2023/2024.

# Description 
## Les Entités
Ce projet vise à créer un site pour faire un QUIZ. Pour cela nous avons utilisés 4 entitées 
- "Utilisateur" qui peut etre un client simple (USER) ou un administarateur 
- Un "Test" qui est caractérisé par une catégorie, et qui comprend plusieurs questions
- Une "Question" qui composé par une question et 4 reponses dont une seule reponse est vraie
- Un "Score" qui conclut la note des QCM effectué avec un score spécifique selon un "Test" à une date donnée

## Diagramme de classe
![Diagramme de classe ](./Diagramme sans nom.drawio.png)

## Deroulement de QCM
Lors de test , 10 questions au hasard sont choisies dans la base de données (MySQL) , et les quatres reponses sont affichées dans l'odre aléatoire sur l'ecran de l'utilisateu

# Developpement
Pour réaliser ceci nous avons créé deux projets, utilisé un serveur Tomcat v9.0.88 
## API REST (TestAPI)
Pour l'API , nous avaons structuré le projet en entities , dao, services 
Pour resoudre les problèmes de dependances nous avons créé unprojet (Web Dynamic Project) puis nous l'avons transformé en projet MAVEN (right clic -> configure -> Convert To Maven Project)
Et nous avons créé  un dossier classes dans laquelle nous avons mis le fichier persistence.xml
Nous avons donc utilisées ces mots-clés suivants : JDBC, JPA, JPQL, DAO, requêtes précompilées et JAX-RS, JAXB, API REST.
- Utilisation de JPA avec Hibernate pour la persistance de données.
- .. . continuer l'esplication des mots clés


## FRONT END (QCM_FrontEnd)
Nous avons créé un projet Web Dynamic , et nous avons ajouter les fichiers pour gérér les JSON , mapper JSON aux models que nous avons en utilisant Jackson (dans le dossier Ressources de ce ripositorie)

### Structures des projets
Nous avons  créé quatres packages :
#### Models
Pour reprendre les entités dans l'API (Test, Utilisateur,Qeustion et Score)
#### Services 
qui comprennent les différentes methodes (CRUD  et plus) pour faire à appel à l'API REST
#### Servlets
Qui recoient les requettes via le navigateur et traite cette requettes et renvoie ensuite des pages JSP traité 
#### Filters 
Qui restrint l'accès à certaines pages de l'application en fonction de la session de l'utilisateur


# INSTALLATION
## Téléchargement
Telecharger ce projet via la commande `git clone https://github.com/Torien-TORIEN/MiniProjet_Jakarta_EE`