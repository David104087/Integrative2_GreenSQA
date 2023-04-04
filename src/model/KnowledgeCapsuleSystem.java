package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class KnowledgeCapsuleSystem {

    public final int PROJECTS = 10;//number of projects
	private Project[] projects;
	private SimpleDateFormat dateFormat;

    
    public KnowledgeCapsuleSystem(){
        projects = new Project[PROJECTS];
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    }
    
    public String createProject(String projectName, String clientName, String clientPhone, double budget, String managerName, String managerPhone, int projectDuration, int[] stageMonths){
		
		String msg = " was successfully created!!!";
        Project project = new Project(projectName, clientName, clientPhone, budget, managerName, managerPhone);
        int pos = getFirstAvailableProjectIndex();
		if(pos != -1){
			projects[pos] = project; 
			projects[pos].setStartDate();
            projects[pos].setEndDate(projectDuration);
			Calendar startDate = projects[pos].getStartDate();//extracts the start date of the project to assign the dates of the stages
			Stage[] stages = projects[pos].getStages();//extract the stages of the project in an array
			stages[0].setRealStartDate(startDate);//assigns to the start stage the actual start date (the project's)
			for (int i = 0; i < stages.length; i++) {
				Calendar endDate = (Calendar) startDate.clone();
				endDate.add(Calendar.MONTH, stageMonths[i]);
				stages[i].setPlanStartDate(startDate);
				stages[i].setPlanEndDate(endDate);
				startDate = (Calendar) endDate.clone();
			}
		} 
        return msg;

	}

	public String culminateStage(String projectName) {
		String msg = "";
		if (findProjectByName(projectName) != null) {
			msg = findProjectByName(projectName).setCurrentStage();
		} 
		return msg;
	}

	public String passCapsule(String capsuleID, String searchedProject) {
		String msg = "The capsule does not exist";
		if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID) != null) {
			msg = findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).setStatus() + ", the date of approval is: " + dateFormat.format(findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).getApprovalDate().getTime());
		} 
		return msg;
	}

	public String releaseCapsule(String capsuleID, String searchedProject) {
		String msg = "The capsule does not exist";
		if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID) != null) {

			if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).getStatus() == "Approved") {
				if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).getURL().isEmpty()) {
					msg = findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).setURL();
				} else {
					msg = "The capsule has been published before";				
				}
			} else {
				msg = "The capsule must be approved first";
			}
		} 
		return msg;
	}

	public Project findProjectByName(String name) {
        for (Project project : projects) {//tour the project array
            if (project != null && project.getProjectName().equals(name)) {
                return project;
            }
        }
        return null;
    }


    /**
	 * getFirstAvailableProjectIndex: search in array if exist one valid position
	 * @return pos -1 if the position does not exist, a number in [0, 9] if exist a valid position
	 * */
	public int getFirstAvailableProjectIndex(){
		int pos = -1; 
		boolean isFound = false; 
		for(int i = 0; i < PROJECTS && !isFound; i++){
			if(projects[i] == null){
				pos = i; 
				isFound = true;
			}
		}
		return pos; 
	}
    
    public Project[] getProject(){
		return projects;
	}
}
