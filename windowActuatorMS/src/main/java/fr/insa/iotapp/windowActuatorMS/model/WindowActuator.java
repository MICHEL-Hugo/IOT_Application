package fr.insa.iotapp.windowActuatorMS.model;

public class WindowActuator {
    private int state; 

    public WindowActuator() {
        this.state = 0; 
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        if (state == 0 || state == 1) {
            this.state = state;
        } else {
            throw new IllegalArgumentException("L'état doit être 0 (fermé) ou 1 (ouvert).");
        }
    }
}

