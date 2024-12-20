package fr.insa.iotapp.decisionMS.controller;

import fr.insa.iotapp.decisionMS.service.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/decision")
public class DecisionController {

    private final DecisionService decisionService;

    @Autowired
    public DecisionController(DecisionService decisionService) {
        this.decisionService = decisionService;
    }

    @PostMapping("/start")
    public String startMonitoring() {
        decisionService.startMonitoring();
        return "Surveillance démarrée.";
    }

    @PostMapping("/stop")
    public String stopMonitoring() {
        decisionService.stopMonitoring();
        return "Surveillance arrêtée.";
    }
}

