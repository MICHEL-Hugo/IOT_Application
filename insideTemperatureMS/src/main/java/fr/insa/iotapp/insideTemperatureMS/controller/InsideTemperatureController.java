
/**
 * Ce fichier définit le contrôleur REST pour le microservice de gestion
 * de la température intérieure dans l'application IoT. Ce contrôleur gère
 * les requêtes HTTP liées à la récupération de la température actuelle.
 * 
 * Fonctionnalités principales :
 * - Fournir la température intérieure actuelle via le point d'entrée `/current`.
 * 
 * Le contrôleur utilise le modèle `TemperatureDataLoader` pour charger les
 * données de température depuis une source externe ou simulée.
 * 
 * Structure :
 * - Annotation `@RestController` : indique que cette classe est un contrôleur Spring.
 * - Annotation `@RequestMapping("/temperature")` : définit la base des URL pour ce contrôleur.
 * 
 * Remarques :
 * - Si aucune donnée de température n'est disponible, une valeur par défaut de -273 
 *   (approximativement le zéro absolu) est renvoyée.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.insideTemperatureMS.controller;

import fr.insa.iotapp.insideTemperatureMS.model.TemperatureDataLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class InsideTemperatureController {

    private final TemperatureDataLoader dataLoader;

    public InsideTemperatureController(TemperatureDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @GetMapping("/current")
    public int getCurrentTemperature() {
        Integer temperature = dataLoader.getNextTemperature();
        if (temperature != null) {
            return temperature; 
        }
        return -273; 
    }
}



