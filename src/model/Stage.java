package model;
import java.util.Calendar;

/**
 * The Stage class represents the stages of a project. It contains information such as the 
 * stage name, whether the stage is active or not, the planned start and end dates, the actual 
 * start and end dates, and the capsules related to the stage.
 */
public class Stage {
    /**
     * This variable is the number of capsules
     */
    public static final int CAPSULES = 50;

    private String stageName;//types of stages(Start, Analysis...)
    private boolean isActive;//indicates whether a stage is active or not
    private Calendar planStartDate;
    private Calendar planEndDate;
    private Calendar realStartDate;
    private Calendar realEndDate;
    private Capsule[] capsules;

    /**
     * Creates a new Stage object with the given stage name and active status.
     * @param stageName the name of the stage
     * @param isActive whether the stage is active or not
     */
    public Stage(String stageName, boolean isActive) {
        this.stageName = stageName;
        this.isActive = isActive;
        capsules = new Capsule[CAPSULES];
    }

    /**
     * Returns the name of the stage.
     * @return the name of the stage
     */
    public String getStageName() {
        return stageName;
    }

    /**
     * Returns the status of the stage as a string.
     * @return the status of the stage
     */
    public String getIsActive() {
        String msg = isActive ? "Active" : "Inactive";
        return msg;
    }
    
    /**
     * Returns the planned start date of the stage.
     * @return the planned start date of the stage
     */
    public Calendar getPlanStartDate() {
        return planStartDate;
    }

    /**
     * Returns the actual start date of the stage.
     * @return the actual start date of the stage
     */
    public Calendar getRealStartDate() {
        return realStartDate;
    }

    /**
     * Returns the planned end date of the stage.
     * @return the planned end date of the stage
     */
    public Calendar getPlanEndDate() {
        return planEndDate;
    }

    /**
     * Returns the actual end date of the stage.
     * @return the actual end date of the stage
     */
    public Calendar getRealEndDate() {
        return realEndDate;
    }

    /**
     * Sets the status of the stage.
     * @param status the status of the stage
     */
    public void setIsActive(boolean status) {
        this.isActive = status;
    }

    /**
     * Sets the planned start date of the stage.
     * @param planStartDate the planned start date of the stage
     */
    public void setPlanStartDate(Calendar planStartDate){
        this.planStartDate = planStartDate;
    }

    /**
     * Sets the planned end date of the stage.
     * @param planEndDate the planned end date of the stage
     */
    public void setPlanEndDate(Calendar planEndDate){
        this.planEndDate = planEndDate;
    }

    /**
     * Sets the actual start date of the stage.
     * @param realStartDate the actual start date of the stage
     */
    public void setRealStartDate(Calendar realStartDate){
        this.realStartDate = realStartDate;
    }

    /**
     * Sets the actual end date of the stage.
     * @param realEndDate the actual end date of the stage
     */
    public void setRealEndDate(Calendar realEndDate){
        this.realEndDate = realEndDate;
    }

    /**
     * Returns the array of capsules for this stage
     * @return the array of capsules for this stage
     */
    public Capsule[] getCapsules(){
		return capsules;
	}

    /**
     * Creates a new capsule and adds it to the array of capsules for this stage
     * @param description the description of the capsule
     * @param capsuleType the type of the capsule (1: Technical, 2: Management, 3: Domain, 4: Experience)
     * @param collaboratorName the name of the collaborator who created the capsule
     * @param collaboratorPosition the position of the collaborator who created the capsule
     * @param learning the learning outcomes of the capsule
     * @return a message indicating that the capsule was successfully created
    */
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

    /**
     * Returns the index of the first valid position in the array of capsules
     * @return the index of the first valid position in the array of capsules, or -1 if the array is full
     */
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

    /**
     * Finds a capsule in the array of capsules with the given capsule ID
     * @param capsuleID the ID of the capsule to find
     * @return the capsule with the given ID, or null if no such capsule is found
     */
    public Capsule findCapsuleByID(String capsuleID) {
        for (Capsule capsule : capsules) {
            if (capsule != null && capsule.getCapsuleID().equals(capsuleID)) {
                return capsule;
            }
        }
        return null;
    }
    
}
