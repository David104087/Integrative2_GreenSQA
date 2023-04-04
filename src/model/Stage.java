package model;
import java.util.Calendar;

public class Stage {

    public static final int CAPSULES = 50;//number of capsules

    private String stageName;//types of stages(Start, Analysis...)
    private boolean isActive;//indicates whether a stage is active or not
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

    public String getStageName() {
        return stageName;
    }

    public String getIsActive() {
        String msg = isActive ? "Active" : "Inactive";
        return msg;
    }
    

    public Calendar getPlanStartDate() {
        return planStartDate;
    }

    public Calendar getRealStartDate() {
        return realStartDate;
    }

    public Calendar getPlanEndDate() {
        return planEndDate;
    }

    public Calendar getRealEndDate() {
        return realEndDate;
    }

    public void setIsActive(boolean status) {
        this.isActive = status;
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

    public Capsule[] getCapsules(){
		return capsules;
	}

    public String createCapsule(String description, int capsuleType, String collaboratorName, String collaboratorPosition, String learning) {
        String msg = " was successfully created!!!";
        String typeCapsule = "";
        String status = "Under review";
        if (capsuleType == 1) {
            typeCapsule = "Technical";
        }
        if (capsuleType == 2) {
        typeCapsule = "Management";
        }
        if (capsuleType == 3) {
        typeCapsule = "Domain";
        }
        if (capsuleType == 4) {
        typeCapsule = "Experience";
        }

        Capsule capsule = new Capsule(description, typeCapsule, collaboratorName, collaboratorPosition, learning, status);
        int pos = getFirstValidPositionCaps();
		if(pos != -1){
			capsules[pos] = capsule; 
		} 
        return msg;
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

    public Capsule findCapsuleByID(String capsuleID) {
        for (Capsule capsule : capsules) {
            if (capsule != null && capsule.getCapsuleID().equals(capsuleID)) {
                return capsule;
            }
        }
        return null;
    }
    
}
