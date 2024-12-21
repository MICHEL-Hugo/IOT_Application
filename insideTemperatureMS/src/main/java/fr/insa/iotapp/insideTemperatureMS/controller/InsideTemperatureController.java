package fr.insa.iotapp.insideTemperatureMS.controller;

import fr.insa.iotapp.insideTemperatureMS.model.TemperatureDataLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class InsideTemperatureController {

    private final TemperatureDataLoader dataLoader;

    public InsideTemperatureController(TemperatureDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @GetMapping("/current")
    public int getCurrentTemperature() {
        Integer temperature = dataLoader.getNextTemperature();
        if (temperature != null) {
            return temperature; 
        }
        return -273; 
    }
}



