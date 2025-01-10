/**
 * Cette classe représente un modèle pour l'actionneur de fenêtre dans l'application IoT.
 * Elle encapsule l'état de l'actionneur et fournit des méthodes pour accéder et modifier cet état.
 * 
 * Fonctionnalités principales :
 * - Stocker l'état actuel de l'actionneur de fenêtre.
 * - Permettre la lecture et la mise à jour de l'état via des méthodes getter et setter.
 * - Valider les valeurs d'état pour garantir qu'elles sont conformes aux règles métier.
 * 
 * Structure :
 * - Champ `state` : entier représentant l'état de l'actionneur (0 = fermé, 1 = ouvert).
 * - Constructeur : initialise l'état par défaut à 0 (fermé).
 * - Méthodes :
 *   - `getState()` : retourne l'état actuel de l'actionneur.
 *   - `setState(int state)` : met à jour l'état si la valeur est valide (0 ou 1). 
 *     - Lance une exception `IllegalArgumentException` pour des valeurs non valides.
 * 
 * Remarques :
 * - Cette classe est conçue pour assurer la gestion sécurisée et cohérente de l'état
 *   des fenêtres dans le système IoT.
 * 
 * Auteur : MICHEL Hugo et MASSON Alexandre
 */

package fr.insa.iotapp.windowActuatorMS.model;

public class WindowActuator {
    private int state; 

    public WindowActuator() {
        this.state = 0; 
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (state == 0 || state == 1) {
            this.state = state;
        } else {
            throw new IllegalArgumentException("L'état doit être 0 (fermé) ou 1 (ouvert).");
        }
    }
}

