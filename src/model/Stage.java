package model;
import java.util.*;


public class Stage {
    private String stageName;
    private boolean isActive;
    private Calendar planStartDate;
    private Calendar planEndDate;
    private Calendar realStartDate;
    private Calendar realEndDate;


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

    public void setPlanStartDate(Calendar planStartDate){
        this.planStartDate = planStartDate;
    }

    public void setPlanEndDate(Calendar planEndDate){
        this.planEndDate = planEndDate;
    }


}
