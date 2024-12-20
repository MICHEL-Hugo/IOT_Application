package fr.insa.iotapp.outsideTemperatureMS.controller;

import fr.insa.iotapp.outsideTemperatureMS.model.TemperatureData;
import fr.insa.iotapp.outsideTemperatureMS.model.TemperatureDataLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temperature")
public class OutsideTemperatureController {

    private final TemperatureDataLoader dataLoader;

    public OutsideTemperatureController(TemperatureDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @GetMapping("/current")
    public TemperatureData getCurrentTemperature() {
        Integer temperature = dataLoader.getNextTemperature();
        if (temperature != null) {
            return new TemperatureData(temperature);
        }
        return new TemperatureData(-273); 
    }
}