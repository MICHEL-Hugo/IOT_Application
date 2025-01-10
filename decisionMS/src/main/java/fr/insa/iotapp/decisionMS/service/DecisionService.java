/** 
 * Cette classe est responsable de la gestion de la surveillance des températures
 * internes et externes et de la prise de décision concernant l'état d'ouverture
 * ou de fermeture d'une fenêtre connectée. Elle utilise une planification périodique
 * pour interroger les données des capteurs de température et effectuer les actions
 * nécessaires, telles que l'envoi de requêtes HTTP pour contrôler l'état de la fenêtre.
 * 
 * Fonctionnalités principales :
 * - Récupération périodique des températures interne et externe via des services REST externes.
 * - Prise de décision automatisée : Si la température extérieure est inférieure à la température intérieure,
 *   et qu'elle se situe entre 18 et 27°C, la fenêtre doit être ouverte.
 * - Enregistrement des changements d'état de la fenêtre dans un fichier de logs avec horodatage.
 * - Démarrage et arrêt de la surveillance via des méthodes publiques.
 * 
 * Points techniques :
 * - Planification automatique de la surveillance avec l'annotation `@Scheduled` (chaque seconde).
 * - Utilisation de `RestTemplate` pour envoyer des requêtes HTTP aux services REST pour obtenir les températures
 *   et envoyer des commandes de changement d'état de la fenêtre.
 * - Contrôle de l'état de la surveillance à l'aide d'un objet `AtomicBoolean` pour garantir la gestion concurrente.
 * - Enregistrement des logs dans un fichier texte situé à `src/main/resources/logs.txt` via le service `LogService`.
 * - Chaque changement d'état de la fenêtre est également enregistré dans la base de données.
 * 
 * Auteur : Hugo Michel et MASSON Alexandre
 */


package fr.insa.iotapp.decisionMS.service;

import fr.insa.iotapp.decisionMS.model.logModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DecisionService {

    private final RestTemplate restTemplate;
    private final AtomicBoolean monitoring = new AtomicBoolean(false);
    private int previousState = -1; 

    private final LogService logService; // Injection du service LogService

    @Autowired
    public DecisionService(RestTemplate restTemplate, LogService logService) {
        this.restTemplate = restTemplate;
        this.logService = logService;
    }

    @Scheduled(fixedRate = 1000)
    public void monitor() {
        if (!monitoring.get()) {
            return;
        }

        try {
            ResponseEntity<Integer> response1 = restTemplate.getForEntity("http://outsideTemperatureMS/temperature/current", Integer.class);
            ResponseEntity<Integer> response2 = restTemplate.getForEntity("http://insideTemperatureMS/temperature/current", Integer.class);

            int temp1 = response1.getBody();
            int temp2 = response2.getBody();

            System.out.println("Outside temperature : " + temp1 + "°C , Inside temperature : " + temp2 + "°C ");

            int newState = (temp1 >= 18 && temp1 <= 27 && temp1 < temp2) ? 1 : 0;

            if (newState != previousState) {
                restTemplate.postForEntity("http://windowActuatorMS/actuator/state/" + newState, null, String.class);
                previousState = newState;
                String stateMessage = "Window " + (newState == 1 ? "open." : "close.");
                System.out.println(stateMessage);

                // Sauvegarder le changement d'état dans la base de données
                saveLog(temp1, temp2, newState, stateMessage);
            }

        } catch (Exception e) {
            System.err.println("Error during monitoring:  " + e.getMessage());
        }
    }

    private void saveLog(int temp1, int temp2, int newState, String stateMessage) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        logModel log = new logModel(timestamp, temp1, temp2, stateMessage);

        // Utiliser LogService pour enregistrer le log dans la base de données
        logService.addLog(log);
    }

    public void startMonitoring() {
        monitoring.set(true);
    }

    public void stopMonitoring() {
        monitoring.set(false);
    }
}
