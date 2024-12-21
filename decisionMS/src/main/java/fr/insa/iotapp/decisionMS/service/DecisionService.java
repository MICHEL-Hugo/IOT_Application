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
    private int previousState = -1; 
    
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
            ResponseEntity<Integer> response1 = restTemplate.getForEntity("http://localhost:8080/temperature/current", Integer.class);
            ResponseEntity<Integer> response2 = restTemplate.getForEntity("http://localhost:8081/temperature/current", Integer.class);

            int temp1 = response1.getBody(); 
            int temp2 = response2.getBody(); 

            System.out.println("Outside temperature : " + temp1 + "°C , Inside temperature : " + temp2 + "°C ");

            int newState = (temp1 >= 18 && temp1 <= 27 && temp1 < temp2) ? 1 : 0;

            if (newState != previousState) {
                restTemplate.postForEntity("http://localhost:8082/actuator/state/" + newState, null, String.class);
                previousState = newState; 
                System.out.println("Window " + (newState == 1 ? "open." : "close."));
            } //else {
               // System.out.println("No change of state.");
            //}

        } catch (Exception e) {
            System.err.println("Error during monitoring:  " + e.getMessage());
        }
    }

    public void startMonitoring() {
        monitoring.set(true);
    }

    public void stopMonitoring() {
        monitoring.set(false);
    }
}
