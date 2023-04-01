package model;
import java.util.*;


public class Stage {

    public static final int CAPSULES = 50; 

    private String stageName;//los tipos: Incios, analisis...
    private boolean isActive;
    private Calendar planStartDate;
    private Calendar planEndDate;
    private Calendar realStartDate;
    private Calendar realEndDate;
    private Capsule[] capsules;

    public Stage(String stageName, boolean isActive) {
        this.stageName = stageName;
        this.isActive = isActive;
        capsules = new Capsule[CAPSULES];
    }


    public String createCapsule(String capsuleID, String description, String capsuleType, String collaboratorName, String collaboratorPosition, String learning, String hashtag, String status) {
        String msg = " fue creado exitosamente!!!";
        Capsule capsule = new Capsule(capsuleID, description, capsuleType, collaboratorName, collaboratorPosition, learning, hashtag, status);
        int pos = getFirstValidPositionCaps();
		if(pos != -1){
			capsules[pos] = capsule; 
		} 
        return msg;

    }

    public Capsule[] getCapsules(){
		return capsules;
	}

	public int getFirstValidPositionCaps(){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < CAPSULES && !isFound; i++){
			if(capsules[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
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

    public void setIsActive(boolean status) {
        this.isActive = status;
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

    public void setRealStartDate(Calendar realStartDate){
        this.realStartDate = realStartDate;
    }

    public void setRealEndDate(Calendar date){
        this.realEndDate = date;
    }
    

    



}
