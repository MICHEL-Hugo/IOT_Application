/**
 * Cette classe charge et fournit des données de température pour le microservice
 * de gestion de la température INTERIEURE dans l'application IoT.
 * 
 * Fonctionnalités principales :
 * - Charger une liste de températures à partir d'un fichier JSON (`temperatures.json`).
 * - Fournir une méthode pour accéder séquentiellement aux températures (avec une répétition circulaire).
 * 
 * Structure :
 * - Champ `temperatureList` : liste des températures chargées depuis le fichier JSON.
 * - Champ `currentIndex` : index actuel pour la lecture des températures.
 * - Constructeur :
 *   - Charge les données depuis le fichier JSON à l'aide de la bibliothèque Jackson.
 * - Méthodes :
 *   - `getNextTemperature()` : retourne la température suivante dans la liste, 
 *     avec un mécanisme de boucle circulaire.
 * 
 * Annotations :
 * - `@Component` : permet à Spring de gérer cette classe comme un composant.
 * 
 * Remarques :
 * - Assure une gestion fluide des données de température pour les autres composants
 *   du système, comme le contrôleur REST.
 * - En cas de fichier JSON vide, la méthode `getNextTemperature()` renvoie `null`.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.insideTemperatureMS.model;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class TemperatureDataLoader {

    private List<Integer> temperatureList;
    private int currentIndex = 0;

    public TemperatureDataLoader() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/temperatures.json");
        this.temperatureList = objectMapper.readValue(inputStream, new TypeReference<List<Integer>>() {});
    }

    public Integer getNextTemperature() {
        if (temperatureList.isEmpty()) {
            return null;
        }
        Integer temperature = temperatureList.get(currentIndex);
        currentIndex = (currentIndex + 1) % temperatureList.size(); 
        return temperature;
    }
}
