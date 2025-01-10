/**
 * Ce fichier définit le contrôleur REST pour le microservice de gestion 
 * des actionneurs de fenêtres dans l'application IoT. Ce contrôleur gère 
 * les requêtes HTTP permettant de consulter et de modifier l'état des fenêtres.
 * 
 * Fonctionnalités principales :
 * - Récupérer l'état actuel de l'actionneur de fenêtre via le point d'entrée `/state`.
 * - Mettre à jour l'état de l'actionneur de fenêtre via le point d'entrée `/state/{state}`.
 * 
 * Structure :
 * - Annotation `@RestController` : indique que cette classe est un contrôleur Spring.
 * - Annotation `@RequestMapping("/actuator")` : définit la base des URL pour ce contrôleur.
 * - Utilisation du modèle `WindowActuator` pour gérer l'état des fenêtres.
 * 
 * Méthodes :
 * - `getActuatorState()` : retourne l'état actuel de l'actionneur (ex. : ouvert, fermé).
 * - `setActuatorState(int state)` : met à jour l'état de l'actionneur en fonction de la valeur fournie.
 *   - Valide l'état avant de l'appliquer et renvoie un message de succès ou d'erreur.
 * 
 * Remarques :
 * - Le contrôleur instancie un objet `WindowActuator` pour gérer les opérations liées à l'état des fenêtres.
 * - Les états valides sont définis par la logique métier dans le modèle `WindowActuator`.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.windowActuatorMS.controller;

import fr.insa.iotapp.windowActuatorMS.model.WindowActuator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator")
public class WindowActuatorController {

    private final WindowActuator actuator;

    public WindowActuatorController() {
        this.actuator = new WindowActuator(); 
        }

    @GetMapping("/state")
    public int getActuatorState() {
        return actuator.getState();
    }

    @PostMapping("/state/{state}")
    public String setActuatorState(@PathVariable int state) {
        try {
            actuator.setState(state);
            return "État de la fenêtre mis à jour à " + state;
        } catch (IllegalArgumentException e) {
            return "Erreur : " + e.getMessage();
        }
    }
}

