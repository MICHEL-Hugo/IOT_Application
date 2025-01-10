/**
 * Ce fichier définit le contrôleur REST pour le microservice de décision
 * dans l'application IoT. Le contrôleur gère les requêtes HTTP liées
 * à la surveillance et à l'accès aux logs.
 * 
 * Fonctionnalités principales :
 * - Démarrage et arrêt de la surveillance via les points d'entrée `/start` et `/stop`.
 * - Récupération des logs de surveillance via le point d'entrée `/logs`.
 * 
 * Le contrôleur repose sur le service `DecisionService` pour implémenter la logique
 * métier associée aux actions de surveillance. 
 * 
 * Structure :
 * - Annotation `@RestController` : indique que cette classe est un contrôleur Spring.
 * - Annotation `@RequestMapping("/decision")` : définit la base des URL pour ce contrôleur.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.decisionMS.controller;

import fr.insa.iotapp.decisionMS.service.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/decision")
public class DecisionController {

    private final DecisionService decisionService;

    @Autowired
    public DecisionController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @PostMapping("/start")
    public String startMonitoring() {
        decisionService.startMonitoring();
        return "Surveillance démarrée.";
    }

    @PostMapping("/stop")
    public String stopMonitoring() {
        decisionService.stopMonitoring();
        return "Surveillance arrêtée.";
    }

    @GetMapping("/logs")
    public String getLogs() {
        File logFile = new File("src/main/resources/logs.txt");

        if (!logFile.exists()) {
            return "Le fichier de logs est introuvable.";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            return "Erreur lors de la lecture des logs : " + e.getMessage();
        }
    }
}
