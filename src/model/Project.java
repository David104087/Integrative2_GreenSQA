package model;
import java.util.*;
public class Project {

    public static final int STAGES = 6; 


    private String projectName; 
    private String clientName; 
    private String clientPhone; 
    private Manager manager;
    private Stage[] stages;
    private double budget;  
    private Calendar startDate;
    private Calendar endDate;


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
        //FALTA CALCULAR FECHAS CON stageMonth
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

    public void setStartDate() {
        this.startDate = Calendar.getInstance();
    }
    
    public void setEndDate(int projectDuration) {
        this.endDate = (Calendar) this.startDate.clone();
        this.endDate.add(Calendar.MONTH, projectDuration);
    }
    
}

