package fr.insa.iotapp.decisionMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class DecisionService {

    private final RestTemplate restTemplate;
    private final AtomicBoolean monitoring = new AtomicBoolean(false);

    @Autowired
    public DecisionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 1000) 
    public void monitor() {
        if (!monitoring.get()) {
            return;
        }

        try {

            ResponseEntity<Double> response1 = restTemplate.getForEntity("http://localhost:8080/temperature/current", Double.class);
            ResponseEntity<Double> response2 = restTemplate.getForEntity("http://localhost:8081/temperature/current", Double.class);
            
            double temp1 = response1.getBody();
            double temp2 = response2.getBody();

            if (temp1 >= 18 && temp1 <= 27 && temp1 < temp2) {
                restTemplate.postForEntity("http://localhost:8082/actuator/state/1", null, String.class);
                System.out.println("Actionneur activé.");
            } else {
                restTemplate.postForEntity("http://localhost:8082/actuator/state/0", null, String.class);
                System.out.println("Actionneur désactivé.");
            }

        } catch (Exception e) {
            System.err.println("Erreur lors de la surveillance : " + e.getMessage());
        }
    }

    public void startMonitoring() {
        monitoring.set(true);
    }

    public void stopMonitoring() {
        monitoring.set(false);
    }
}

