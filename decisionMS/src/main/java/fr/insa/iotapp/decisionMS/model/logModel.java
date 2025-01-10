
/**
 * La classe `logModel` représente un modèle de log utilisé pour stocker les informations relatives 
 * à l'état de la fenêtre et aux températures interne et externe au moment d'un changement d'état.
 * Elle sert de structure de données pour enregistrer les logs dans la base de données ou pour être 
 * utilisée dans d'autres systèmes liés à la gestion des fenêtres connectées.
 *
 * Attributs :
 * - `id` : Identifiant unique du log dans la base de données (généralement auto-généré).
 * - `timestamp` : Horodatage du log, indiquant la date et l'heure du changement d'état de la fenêtre.
 * - `outsideTemperature` : Température extérieure mesurée au moment du changement d'état de la fenêtre.
 * - `insideTemperature` : Température intérieure mesurée au moment du changement d'état de la fenêtre.
 * - `windowState` : État de la fenêtre au moment du log (par exemple "open" pour ouverte ou "close" pour fermée).
 *
 * Méthodes :
 * - Constructeur : Initialise un objet `logModel` avec le timestamp, la température extérieure, la température intérieure,
 *   et l'état de la fenêtre.
 * - Getters et Setters : Accesseurs pour chaque attribut (permet de lire et de modifier les valeurs des attributs).
 *
 * Auteur : Hugo Michel et MASSON Alexandre
 */
package fr.insa.iotapp.decisionMS.model;

public class logModel {
    private int id;
    private String timestamp;
    private int outsideTemperature;
    private int insideTemperature;
    private String windowState;

    public logModel(String timestamp, int outsideTemperature, int insideTemperature, String windowState) {
        this.timestamp = timestamp;
        this.outsideTemperature = outsideTemperature;
        this.insideTemperature = insideTemperature;
        this.windowState = windowState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(int outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public int getInsideTemperature() {
        return insideTemperature;
    }

    public void setInsideTemperature(int insideTemperature) {
        this.insideTemperature = insideTemperature;
    }

    public String getWindowState() {
        return windowState;
    }

    public void setWindowState(String windowState) {
        this.windowState = windowState;
    }
}
