package fr.insa.iotapp.windowActuatorMS.controller;

import fr.insa.iotapp.windowActuatorMS.model.WindowActuator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuator")
public class WindowActuatorController {

    private final WindowActuator actuator;

    public WindowActuatorController() {
        this.actuator = new WindowActuator(); 
        }

    @GetMapping("/state")
    public int getActuatorState() {
        return actuator.getState();
    }

    @PostMapping("/state/{state}")
    public String setActuatorState(@PathVariable int state) {
        try {
            actuator.setState(state);
            return "État de la fenêtre mis à jour à " + state;
        } catch (IllegalArgumentException e) {
            return "Erreur : " + e.getMessage();
        }
    }
}

