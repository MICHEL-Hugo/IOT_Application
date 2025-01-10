/**
 * Ce fichier définit le contrôleur REST pour le microservice de décision
 * dans l'application IoT. Le contrôleur gère les requêtes HTTP liées
 * à la surveillance, à l'accès aux logs et à la gestion des logs dans
 * la base de données.
 * 
 * Fonctionnalités principales :
 * - Démarrage et arrêt de la surveillance via les points d'entrée `/start` et `/stop`.
 * - Récupération de tous les logs via le point d'entrée `/logs`.
 * - Récupération d'un log spécifique par son ID via le point d'entrée `/logs/{id}`.
 * - Ajout d'un nouveau log via le point d'entrée `/logs` (méthode POST).
 * - Mise à jour d'un log existant via le point d'entrée `/logs/{id}` (méthode PUT).
 * - Suppression d'un log via le point d'entrée `/logs/{id}` (méthode DELETE).
 * 
 * Le contrôleur repose sur le service `DecisionService` pour implémenter la logique
 * métier associée aux actions de surveillance et sur le service `LogService` pour la gestion
 * des logs dans la base de données.
 * 
 * Structure :
 * - Annotation `@RestController` : indique que cette classe est un contrôleur Spring.
 * - Annotation `@RequestMapping("/decision")` : définit la base des URL pour ce contrôleur.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.decisionMS.controller;

import fr.insa.iotapp.decisionMS.model.logModel;
import fr.insa.iotapp.decisionMS.service.DecisionService;
import fr.insa.iotapp.decisionMS.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/decision")
public class DecisionController {

    private final DecisionService decisionService;
    private final LogService logService;

    // Injection des services nécessaires
    @Autowired
    public DecisionController(DecisionService decisionService, LogService logService) {
        this.decisionService = decisionService;
        this.logService = logService;
    }

    // Démarrer la surveillance
    @PostMapping("/start")
    public String startMonitoring() {
        decisionService.startMonitoring();
        return "Surveillance démarrée.";
    }

    // Arrêter la surveillance
    @PostMapping("/stop")
    public String stopMonitoring() {
        decisionService.stopMonitoring();
        return "Surveillance arrêtée.";
    }

    // Récupérer tous les logs depuis la base de données
    @GetMapping("/logs")
    public List<logModel> getLogs() {
        return logService.getAllLogs(); // Retourne la liste des logs depuis la base de données
    }

    // Récupérer un log par ID
    @GetMapping("/logs/{id}")
    public logModel getLogById(@PathVariable int id) {
        return logService.getLogById(id); // Retourne un log spécifique par ID
    }

    // Ajouter un nouveau log
    @PostMapping("/logs")
    public String addLog(@RequestBody logModel log) {
        logService.addLog(log); // Ajoute le log à la base de données
        return "Log ajouté avec succès.";
    }

    // Mettre à jour un log existant
    @PutMapping("/logs/{id}")
    public String updateLog(@PathVariable int id, @RequestBody logModel log) {
        log.setId(id); // Assurez-vous que l'ID du log est bien défini avant la mise à jour
        logService.updateLog(log); // Met à jour le log dans la base de données
        return "Log mis à jour avec succès.";
    }

    // Supprimer un log par ID
    @DeleteMapping("/logs/{id}")
    public String deleteLog(@PathVariable int id) {
        logService.deleteLog(id); // Supprime le log de la base de données
        return "Log supprimé avec succès.";
    }
}
