package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * The Project class represents a project that has several stages and a manager.
 * Each project has a name, a client name, a client phone number, a budget,
 * and start and end dates.
 */
public class Project {

    /**
     * This variable is the number of stages
     */
    public static final int STAGES = 6;

    /**
     * This variable is the index indicating the current stage
     */
    public int posCurrentStage = 0;

    private SimpleDateFormat dateFormat;


    private String projectName; 
    private String clientName; 
    private String clientPhone; 
    private Manager manager;
    private Stage[] stages;
    private double budget;  
    private Calendar startDate;
    private Calendar endDate;
    private Stage currentStage;


    /**
     * Creates a new Project with the specified parameters.
     * 
     * @param projectName the name of the project
     * @param clientName the name of the client
     * @param clientPhone the phone number of the client
     * @param budget the budget for the project
     * @param managerName the name of the manager in charge of the project
     * @param managerPhone the phone number of the manager in charge of the project
     */
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

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

	}
    
    /**
     * Returns the name of the project.
     * 
     * @return the name of the project
     */
    public String getProjectName() {
        return projectName;
    }
    
    /**
     * Returns the name of the client.
     * 
     * @return the name of the client
     */
    public String getClientName() {
        return clientName;
    }
    
    /**
     * Returns the phone number of the client.
     * 
     * @return the phone number of the client
     */
    public String getClientPhone() {
        return clientPhone;
    }
    
    /**
     * Returns the manager in charge of the project.
     * 
     * @return the manager in charge of the project
     */
    public Manager getManager() {
        return manager;
    }
    
    /**
     * Returns the stages of the project.
     * 
     * @return the stages of the project
     */
    public Stage[] getStages() {
        return stages;
    }
    
    /**
     * Returns the budget allocated for the project.
     *
     * @return the budget allocated for the project
     */
    public double getBudget() {
        return budget;
    }
    /**
     * Returns the start date of the project.
     *
     * @return the start date of the project
     */
    public Calendar getStartDate() {
        return startDate;
    }

    /**
     * Returns the end date of the project.
     *
     * @return the end date of the project
     */
    public Calendar getEndDate() {
        return endDate;
    }

    /**
     * Returns the current stage of the project.
     *
     * @return the current stage of the project
     */
    public Stage getCurrentStage() {
        return currentStage;
    }

    /**
     * Sets the current stage of the project to the next stage in the sequence and
     * updates the start and end dates of the stage accordingly.
     *
     * @return a message indicating the new project stage
     * @throws ArrayIndexOutOfBoundsException if there is an array overflow and
     * the current stage is the last stage
     */
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
        } catch (ArrayIndexOutOfBoundsException e) {//validates in case there is an array overflow and indicates that it is the last stage
            msg = "The project is in its last stage!!!";
            return msg;
        }
        return msg;
    }
    
    /**
     * Sets the start date of the project to the current date and time.
     */
    public void setStartDate() {
        this.startDate = Calendar.getInstance();
    }

    /**
     * Sets the end date of the project based on the given project duration
     * and the start date of the project.
     *
     * @param projectDuration the duration of the project in months
     */
    public void setEndDate(int projectDuration) {
        this.endDate = (Calendar) this.startDate.clone();
        this.endDate.add(Calendar.MONTH, projectDuration);
    }

    /**
     * This function returns a formatted string representation of a project object, including details such
     * as project name, start and end dates, client and manager information, budget, and stages with their
     * planned start and end dates.
     * 
     * @return A string representation of a project object, including its name, start and end dates, client
     * and manager information, budget, and information about each stage of the project.
     */
    @Override
    public String toString() {
        NumberFormat formatoDolares = NumberFormat.getCurrencyInstance(Locale.US);
        String numDollars = formatoDolares.format(budget);
        return "ProjectName: " + projectName + "\n"
                + "Start date: " + dateFormat.format(startDate.getTime()) + "\n"
                + "End Date: " + dateFormat.format(endDate.getTime()) + "\n"
                + "Client name: " + clientName + "\n"
                + "Client phone: " + clientPhone + "\n"
                + "Manager name: " + manager.getManagerName() + "\n"
                + "Manager phone: " + manager.getManagerPhone() + "\n"
                + "Budget: " + numDollars + "\n"
                + "----------Stages----------" + "\n"
                + "START: " + stages[0].getIsActive() + "\n"
                + "Planned start date: " + dateFormat.format(currentStage.getPlanStartDate().getTime()) + "\n"
                + "Planned end date: " + dateFormat.format(currentStage.getPlanEndDate().getTime()) + "\n"
                + "ANALISYS: " + stages[1].getIsActive() + "\n"
                + "Planned start date: " + dateFormat.format(stages[1].getPlanStartDate().getTime()) + "\n"
                + "Planned end date: " + dateFormat.format(stages[1].getPlanEndDate().getTime()) + "\n"
                + "DESING: " + stages[2].getIsActive() + "\n"
                + "Planned start date: " + dateFormat.format(stages[2].getPlanStartDate().getTime()) + "\n"
                + "Planned end date: " + dateFormat.format(stages[2].getPlanEndDate().getTime()) + "\n"
                + "EXECUTION: " + stages[3].getIsActive() + "\n"
                + "Planned start date: " + dateFormat.format(stages[3].getPlanStartDate().getTime()) + "\n"
                + "Planned end date: " + dateFormat.format(stages[3].getPlanEndDate().getTime()) + "\n"
                + "CLOSE AND FOLLOW-UP: "  + stages[4].getIsActive() + "\n"
                + "Planned start date: " + dateFormat.format(stages[4].getPlanStartDate().getTime()) + "\n"
                + "Planned end date: " + dateFormat.format(stages[4].getPlanEndDate().getTime()) + "\n"
                + "PROJECT CONTROL: "  + stages[5].getIsActive() + "\n"
                + "Planned start date: " + dateFormat.format(stages[5].getPlanStartDate().getTime()) + "\n"
                + "Planned end date: " + dateFormat.format(stages[5].getPlanEndDate().getTime()) + "\n";
    }
    
}

