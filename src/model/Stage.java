package model; 

public class Stage {
    public enum Status {
        INITATION, ANALYSIS, DESING, EXCUTION, CLOSURE, CONTROL
    }

    private Status StatusActual;
    private boolean isActive;

    public Stage() {
        StatusActual = Status.INITATION;
        isActive = true;
    }

    public Status getStatusActual() {
        return StatusActual;
    }

    public void setStatusActual(Status nuevoStatus) {
        // Changes the actual Status of the Stage
        StatusActual = nuevoStatus;

        // Change the value of the "isActive" attribute
        if (StatusActual == Status.INITATION) {
            isActive = true;
        } else {
            isActive = false;
        }
    }

    public boolean isActive() {
        return isActive;
    }
}
