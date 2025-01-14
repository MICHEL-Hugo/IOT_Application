# IoT Application Prototype

## Table des matières
1. [Introduction](#introduction)
2. [Architecture](#architecture)
    1. [Microservices](#microservices)
    2. [Capteurs](#capteurs)
    3. [Base de données](#base-de-données)
3. [Flux et Cas d'usage](#flux-et-cas-dusage)
4. [Technologies utilisées](#technologies-utilisées)
5. [Installation](#installation)
    1. [Prérequis](#prérequis)
    2. [Étapes d'installation](#étapes-dinstallation)
6. [Exemple de cas d'utilisation](#exemple-de-cas-dutilisation)

---

## Introduction

Ce projet illustre un prototype de système IoT qui inclut plusieurs services, capteurs, actionneurs, et une communication entre les composants via des API RESTful. L'application est construite selon une architecture microservices, où chaque composant (service, capteur, actionneur) est isolé et communique avec les autres via des requêtes HTTP.

Le cas d'usage de cette application est de surveiller les capteurs de température dans une pièce et d'ouvrir ou fermer automatiquement les fenêtres en fonction de certaines conditions. Ce système démontre comment les appareils IoT (capteurs et actionneurs) peuvent travailler ensemble pour prendre des décisions automatisées en fonction des données en temps réel.

---

## Architecture

L'application est composée des composants suivants :

### Microservices

1. **Service de décision(decisionMS)** : Le service principal qui évalue les données de température et prend des décisions concernant l'état des fenêtres (ouvertes/fermées).
2. **Services de capteurs(inside/outsideTemperatureMS)** : Deux services simulant des capteurs de température externes et internes fournissant des données de température en temps réel.
3. **Service d'actionneur(windowActuatorMS)** : Contrôle l'état des fenêtres (ouvertes ou fermées).
4. **Service de découverte(discovery)** : Permet à l'application de découvrir et d'enregistrer dynamiquement les services.
5. **Service de configuration(configuration)** : Gère les paramètres de configuration du système, tels que les ports et la connexion à la base de données.

### Capteurs

1. **Capteur de température extérieur** : Fournit une valeure de température extérieure actuelle, disponible via une API REST. Ces données sont simulées et issues du fichier [temperatures.json](https://github.com/MICHEL-Hugo/IOT_Application/blob/main/insideTemperatureMS/src/main/resources/temperatures.json).
2. **Capteur de température intérieur** : Fournit une valeure de température intérieure actuelle,disponible via une API REST. Ces données sont simulées et issues du fichier [temperatures.json](https://github.com/MICHEL-Hugo/IOT_Application/blob/main/outsideTemperatureMS/src/main/resources/temperatures.json).

### Base de données

La base de données stocke les journaux relatant des changements d'état des fenêtres. Chaque entrée comprend :
- **Horodatage** : Date et heure de l'entrée.
- **Température extérieure** : Température à l'extérieur de la pièce.
- **Température intérieure** : Température à l'intérieur de la pièce.
- **État des fenêtres** : État des fenêtres (ouvertes ou fermées).

---

## Flux et Cas d'usage

Le cas d'usage qui pilote ce système est le suivant :

1. **Récupération des températures** : Les capteurs de température dans la pièce fournissent des mises à jour périodiques concernant les températures extérieures et intérieures.
2. **Prise de décision** : Si la température extérieure est inférieure à la température intérieure et se situe entre 18°C et 27°C, le système ouvrira automatiquement les fenêtres.
3. **Action** : L'actionneur contrôle l'état des fenêtres en fonction de la décision prise par le service de décision. La fenêtre s'ouvre si les conditions sont remplies et reste fermée sinon.
4. **Journalisation** : Toutes les actions (relevés de température, changements d'état des fenêtres) sont enregistrées pour un suivi et une analyse.

---

## Technologies utilisées

- **Spring Boot** : Framework utilisé pour la création des microservices.
- **Spring Cloud** : Pour la découverte et la communication entre les services.
- **Spring JDBC** : Pour interagir avec la base de données.
- **API RESTful** : Communication entre les microservices.
- **Base de données** : Base de données relationnelle pour stocker les logs.

---

## Installation

### Prérequis

Pour exécuter cette application, vous aurez besoin des éléments suivants :
- Java 11 ou version supérieure.
- [Eclipse IDE for Enterprise Java and Web Developers](https://www.eclipse.org/downloads/packages/release/2021-03/r/eclipse-ide-enterprise-java-and-web-developers)
- Une instance en cours d'exécution de MySQL ou d'une autre base de données relationnelle (si vous utilisez une autre base de données, ajustez la configuration en conséquence).
- 
### Étapes d'installation

1. **Clonez le repository** :
    ```bash
    git clone https://github.com/yourusername/your-repo.git
    cd your-repo
    ```
2. **Configurez la base de données** :
   - Assurez-vous que votre base de données fonctionne.  
   - Assurez-vous que votre base de données fonctionne, et configurez les paramètres de connexion dans le fichier [`decisionMS.properties`](https://github.com/MICHEL-Hugo/IOT_Application/blob/main/decisionMS.properties).
     
3. **Initialisez le workspace dans Eclipse**
 Ouvrez Eclipse et créez un nouveau workspace dans le répertoire cloné :  
   - **File > Switch Workspace > Other**  
   - Sélectionnez le répertoire cloné comme nouveau workspace et cliquez sur **Launch**.  
   Importez les microservices dans Eclipse :  
   - **File > Import > Existing Maven Projects**.  
   - Parcourez chaque dossier de microservice dans le repository, sélectionnez-les et cliquez sur **Finish**.  
   

4. **Exécutez les services dans l'ordre**

   **Service de découverte** :  
   - Accédez au dossier `src/main/java` du projet de service de découverte.  
   - Faites un clic droit sur le fichier principal contenant la méthode `main` (souvent nommé `Application` ou similaire).  
   - Cliquez sur **Run As > Java Application**.  

   **Service de configuration** :  
   - Répétez les mêmes étapes que ci-dessus pour lancer le service de configuration.  

   **Autres microservices** :  
   - Lancez les autres microservices un par un en suivant la même procédure.  


 5. **Accédez à l'application**
    - Pour effectuer des requêtes **GET**, vous pouvez utiliser un navigateur web en saisissant l'URL appropriée (par exemple : `http://localhost:8080/{endpoint}`).  
    - Pour d'autres types de requêtes (POST, PUT, DELETE), utilisez un outil comme [**Postman**](https://www.postman.com/downloads/) :  
      - Configurez vos requêtes avec l'URL, le corps de la requête, les en-têtes, etc.  
      - Envoyez les requêtes et vérifiez les réponses des services.
    
    **Observation du fonctionnement de l'application**
    - Le fonctionnement global de l'application peut être observé via :  
        - Les **logs** stockées sur la base de données.  
        - La **console du service de décision**, qui reflète l'état et les interactions des différents services.  

---

## Exemple de cas d'utilisation

1. **Démarrer la surveillance** :
   - Envoyez une requête POST à `/decision/start` pour commencer à surveiller les données de température.

2. **Afficher les journaux** :
   - Envoyez une requête GET à `/decision/logs` pour voir les journaux de toutes les actions effectuées par le système, y compris les relevés de température et les changements d'état des fenêtres.

3. **Arrêter la surveillance** :
   - Envoyez une requête POST à `/decision/stop` pour arrêter la surveillance.



## Répertoire des API

### 1. **Service de Décision (`/decision`)**

- **Démarrer la surveillance**
  - **Méthode** : `POST`
  - **URL** : `/decision/start`
  - **Description** : Démarre la surveillance des données de température.
  
- **Arrêter la surveillance**
  - **Méthode** : `POST`
  - **URL** : `/decision/stop`
  - **Description** : Arrête la surveillance des données de température.

- **Récupérer tous les logs**
  - **Méthode** : `GET`
  - **URL** : `/decision/logs`
  - **Description** : Récupère tous les logs d'actions effectuées par le système.
  
- **Récupérer un log par ID**
  - **Méthode** : `GET`
  - **URL** : `/decision/logs/{id}`
  - **Description** : Récupère un log spécifique par son identifiant.

- **Ajouter un log**
  - **Méthode** : `POST`
  - **URL** : `/decision/logs`
  - **Description** : Ajoute un nouveau log dans la base de données.
  
- **Mettre à jour un log**
  - **Méthode** : `PUT`
  - **URL** : `/decision/logs/{id}`
  - **Description** : Met à jour un log existant dans la base de données.
  
- **Supprimer un log**
  - **Méthode** : `DELETE`
  - **URL** : `/decision/logs/{id}`
  - **Description** : Supprime un log spécifique de la base de données.

### 2. **Service Actionneur de Fenêtre (`/actuator`)**

- **Récupérer l'état de la fenêtre**
  - **Méthode** : `GET`
  - **URL** : `/actuator/state`
  - **Description** : Récupère l'état actuel de la fenêtre (ouverte ou fermée).
  
- **Mettre à jour l'état de la fenêtre**
  - **Méthode** : `POST`
  - **URL** : `/actuator/state/{state}`
  - **Description** : Met à jour l'état de la fenêtre à la valeur spécifiée (0 pour fermée, 1 pour ouverte).

### 3. **Service Température Extérieure (`/temperature`)**

- **Récupérer la température extérieure actuelle**
  - **Méthode** : `GET`
  - **URL** : `/temperature/current`
  - **Description** : Récupère la température extérieure actuelle.


### 4. **Service Température Intérieure (`/temperature`)**

- **Récupérer la température intérieure actuelle**
  - **Méthode** : `GET`
  - **URL** : `/temperature/current`
  - **Description** : Récupère la température intérieure actuelle.


