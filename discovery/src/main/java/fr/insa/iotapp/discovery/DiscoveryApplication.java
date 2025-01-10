/**
 * Cette classe représente le point d'entrée principal pour le microservice de 
 * découverte de services dans l'application IoT. Elle configure et démarre un 
 * serveur Eureka pour permettre l'enregistrement et la découverte des microservices.
 * 
 * Fonctionnalités principales :
 * - Activer un serveur Eureka pour gérer le registre des microservices dans l'écosystème.
 * - Fournir un mécanisme centralisé pour l'enregistrement et la localisation dynamique 
 *   des services.
 * 
 * Structure :
 * - Annotation `@SpringBootApplication` : désigne cette classe comme une application 
 *   Spring Boot, facilitant l'initialisation automatique des composants.
 * - Annotation `@EnableEurekaServer` : active le serveur Eureka pour cette application.
 * - Méthode `main(String[] args)` : point d'entrée de l'application, démarre le serveur 
 *   Eureka via `SpringApplication.run()`.
 * 
 * Remarques :
 * - Ce serveur Eureka joue un rôle clé dans l'architecture des microservices, permettant 
 *   une communication flexible entre les différents services.
 * - Doit être configuré avec les propriétés nécessaires dans le fichier `application.properties` 
 *   ou `application.yml` pour un fonctionnement optimal.
 * 
 * Auteur : MASSON Alexandre et MICHEL Hugo
 */

package fr.insa.iotapp.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryApplication.class, args);
	}

}
