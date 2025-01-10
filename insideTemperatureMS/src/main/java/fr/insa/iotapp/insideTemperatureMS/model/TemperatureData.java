/**
 * Cette classe représente un modèle de données pour stocker la température
 * INTERIEURE dans l'application IoT.
 * 
 * Fonctionnalités principales :
 * - Stocker une valeur de température via un attribut `temperature`.
 * - Fournir des méthodes getter et setter pour accéder et modifier la température.
 * 
 * Structure :
 * - Champ `temperature` : entier représentant la température actuelle.
 * - Constructeur : initialise l'objet avec une valeur de température.
 * - Méthodes :
 *   - `getTemperature()` : retourne la température actuelle.
 *   - `setTemperature(int temperature)` : met à jour la valeur de la température.
 * 
 * Cette classe est utilisée pour encapsuler les données liées à la température 
 * et faciliter leur manipulation.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.insideTemperatureMS.model;

public class TemperatureData {
    private int temperature;

    public TemperatureData(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
