package model;
import java.util.Calendar;

public class Project {

    private String projectName; 
    private String clientName; 
    private String clientPhone; 
    private double budget;  
    private String[] managerName;
    private String[] managerPhone;
    private Calendar startDate;
    private Calendar endDate;


	public Project(String projectName, String clientName, String clientPhone, double budget, String[] managerName, String[] managerPhone){
		this.projectName = projectName; 
		this.clientName = clientName; 
        this.clientPhone = clientPhone; 
        this.budget = budget; 
        this.managerName = managerName;
        this.managerPhone= managerPhone;
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

    public double getBudget() {
        return budget;
    }

    public String[] getManagerName() {
        return managerName;
    }

    public String[] getManagerPhone() {
        return managerPhone;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }
}

