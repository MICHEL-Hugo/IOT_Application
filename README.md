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
7. [Licence](#licence)

---

## Introduction

Ce projet illustre un prototype de système IoT qui inclut plusieurs services, capteurs, actionneurs, et une communication entre les composants via des API RESTful. L'application est construite selon une architecture microservices, où chaque composant (service, capteur, actionneur) est isolé et communique avec les autres via des requêtes HTTP.

Le cas d'usage de cette application est de surveiller les capteurs de température dans une pièce et d'ouvrir ou fermer automatiquement les fenêtres en fonction de certaines conditions. Ce système démontre comment les appareils IoT (capteurs et actionneurs) peuvent travailler ensemble pour prendre des décisions automatisées en fonction des données en temps réel.

---

## Architecture

L'application est composée des composants suivants :

### Microservices

1. **Service de décision** : Le service principal qui évalue les données de température et prend des décisions concernant l'état des fenêtres (ouvertes/fermées).
2. **Services de capteurs** : Deux services simulant des capteurs de température externes et internes, fournissant des données de température en temps réel.
3. **Service d'actionneur** : Contrôle l'état des fenêtres (ouvertes ou fermées).
4. **Service de découverte** : Permet à l'application de découvrir et d'enregistrer dynamiquement les services.
5. **Service de configuration** : Gère les paramètres de configuration du système, tels que les seuils de température et les paramètres du système.

### Capteurs

1. **Capteur de température extérieur** : Fournit la température extérieure actuelle via une API REST.
2. **Capteur de température intérieur** : Fournit la température intérieure actuelle via une API REST.

### Base de données

La base de données stocke les journaux des relevés de température et des changements d'état des fenêtres. Chaque entrée comprend :
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
- **Spring JDBC** : Pour interagir avec la base de données et gérer les entrées de journaux.
- **API RESTful** : Communication entre les microservices.
- **Base de données** : Base de données relationnelle (par exemple, MySQL) pour stocker les journaux.

---

## Installation

### Prérequis

Pour exécuter cette application, vous aurez besoin des éléments suivants :
- Java 11 ou version supérieure.
- Maven ou Gradle pour la gestion des dépendances.
- Une instance en cours d'exécution de MySQL ou d'une autre base de données relationnelle (si vous utilisez une autre base de données, ajustez la configuration en conséquence).
- Docker (optionnel) pour la conteneurisation.

### Étapes d'installation

1. **Clonez le repository** :
    ```bash
    git clone https://github.com/yourusername/your-repo.git
    cd your-repo
    ```

2. **Construisez l'application** :
    Si vous utilisez Maven :
    ```bash
    mvn clean install
    ```
    Si vous utilisez Gradle :
    ```bash
    gradle build
    ```

3. **Exécutez les services** :
   - Vous pouvez démarrer chaque service individuellement en utilisant les commandes de Spring Boot, ou utiliser Docker Compose pour démarrer tous les services en même temps si vous avez un fichier `docker-compose.yml`.

4. **Configurez la base de données** :
   - Assurez-vous que votre base de données fonctionne, et configurez les paramètres de connexion dans le fichier `application.properties` (ou `application.yml`).

5. **Accédez à l'application** :
   - Une fois que tout est en cours d'exécution, vous pouvez interagir avec le système en utilisant les points d'entrée REST fournis.

---

## Exemple de cas d'utilisation

1. **Démarrer la surveillance** :
   - Envoyez une requête POST à `/decision/start` pour commencer à surveiller les données de température.

2. **Afficher les journaux** :
   - Envoyez une requête GET à `/decision/logs` pour voir les journaux de toutes les actions effectuées par le système, y compris les relevés de température et les changements d'état des fenêtres.

3. **Arrêter la surveillance** :
   - Envoyez une requête POST à `/decision/stop` pour arrêter la surveillance.

---

## Licence

Ce projet est sous licence MIT - voir le fichier [LICENSE](LICENSE) pour plus de détails.
