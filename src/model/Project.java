package model;
import java.util.Calendar;
public class Project {

    public static final int STAGES = 6;//number of stages
    public int posCurrentStage = 0;//index indicating the current stage

    private String projectName; 
    private String clientName; 
    private String clientPhone; 
    private Manager manager;
    private Stage[] stages;
    private double budget;  
    private Calendar startDate;
    private Calendar endDate;
    private Stage currentStage;


    public Project(String projectName, String clientName, String clientPhone, double budget, String managerName, String managerPhone){
		this.projectName = projectName; 
		this.clientName = clientName; 
        this.clientPhone = clientPhone; 
        this.budget = budget; 


        manager =new Manager(managerName, managerPhone);
        stages = new Stage[STAGES];
        stages[0] = new Stage("START", true);
        stages[1] = new Stage("ANALYSIS", false);
        stages[2] = new Stage("DESIGN", false);
        stages[3] = new Stage("EXECUTION", false);
        stages[4] = new Stage("CLOSE AND FOLLOW-UP", false);
        stages[5] = new Stage("PROJECT CONTROL", false);
        currentStage = stages[posCurrentStage];
	}
    
    public String getProjectName() {
        return projectName;
    }
    
    public String getClientName() {
        return clientName;
    }
    
    public String getClientPhone() {
        return clientPhone;
    }
    
    public Manager getManager() {
        return manager;
    }
    
    public Stage[] getStages() {
        return stages;
    }
    
    public double getBudget() {
        return budget;
    }

    public Calendar getStartDate() {
        return startDate;
    }
    
    public Calendar getEndDate() {
        return endDate;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public String setCurrentStage() {
        String msg = "";
        posCurrentStage++;
        Calendar date = Calendar.getInstance();
        currentStage.setRealEndDate(date);//adds actual end date to the current stage
        currentStage.setIsActive(false);//deactivates the current stage

        try {
            currentStage = stages[posCurrentStage];//change to new stage
            currentStage.setRealStartDate(date);//adds the actual start date to the new stage
            currentStage.setIsActive(true);//activates the new stage 
            msg = "Stage successfully completed, the new project stage is: " + getCurrentStage().getStageName();
        } catch (ArrayIndexOutOfBoundsException e) {///validates in case there is an array overflow and indicates that it is the last stage
            msg = "The project is in its last stage";
            return msg;
        }
        return msg;
    }
    
    public void setStartDate() {
        this.startDate = Calendar.getInstance();
    }
    
    public void setEndDate(int projectDuration) {
        this.endDate = (Calendar) this.startDate.clone();
        this.endDate.add(Calendar.MONTH, projectDuration);
    }

    //for the second installment of the task:
    // public long getRemainingTime() {
    //     Calendar currentDate = Calendar.getInstance();
    //     long remainingTimeInMillis = endDate.getTimeInMillis() - currentDate.getTimeInMillis();
    //     long remainingDays = TimeUnit.MILLISECONDS.toDays(remainingTimeInMillis);
    //     return remainingDays;
    // }
    
    
}

