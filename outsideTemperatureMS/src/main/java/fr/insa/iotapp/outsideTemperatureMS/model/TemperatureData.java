package fr.insa.iotapp.outsideTemperatureMS.model;

public class TemperatureData {
    private int temperature;

    public TemperatureData(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}

