package model;
import java.util.Calendar;

public class Stage {
    private String stageName;
    private boolean isActive;
    private Calendar realStartDate;
    private Calendar realEndDate;
    private Calendar planStartDate;
    private Calendar planEndDate;

    public Stage(String stageName, boolean isActive) {
        this.stageName = stageName;
        this.isActive = isActive;
        this.planStartDate = planStartDate;
        this.planEndDate = planEndDate;

    }

    public String getStageName() {
        return stageName;
    }

    public String getIsActive() {
        String msg = "Inactiva";
        if (isActive == true) {
            msg = "Activa";
        }
        return msg;
    }

    public Calendar getPlanStartDate() {
        return planStartDate;
    }

    public Calendar getPlanEndDate() {
        return planEndDate;
    }
}
