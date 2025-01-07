package fr.insa.iotapp.decisionMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

                // Log the state change to a file
                logStateChange(temp1, temp2, newState, stateMessage);
            }

        } catch (Exception e) {
            System.err.println("Error during monitoring:  " + e.getMessage());
        }
    }

    private void logStateChange(int temp1, int temp2, int newState, String stateMessage) {
        LocalDateTime now = LocalDateTime.now();
        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String logMessage = String.format("[%s] Outside temperature: %d°C, Inside temperature: %d°C, New state: %s",
                timestamp, temp1, temp2, stateMessage);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/logs.txt", true))) {
            writer.write(logMessage);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }

    public void startMonitoring() {
        monitoring.set(true);
    }

    public void stopMonitoring() {
        monitoring.set(false);
    }
}

