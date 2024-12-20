package fr.insa.iotapp.outsideTemperatureMS.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class TemperatureDataLoader {

    private List<Integer> temperatureList;
    private int currentIndex = 0;

    public TemperatureDataLoader() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getResourceAsStream("/temperatures.json");
        this.temperatureList = objectMapper.readValue(inputStream, new TypeReference<List<Integer>>() {});
    }

    public Integer getNextTemperature() {
        if (temperatureList.isEmpty()) {
            return null;
        }
        Integer temperature = temperatureList.get(currentIndex);
        currentIndex = (currentIndex + 1) % temperatureList.size(); 
        return temperature;
    }
}

