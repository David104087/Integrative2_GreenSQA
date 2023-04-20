package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



/**
 * The KnowledgeCapsuleSystem class represents a system that manages a set of projects,
 * each with their own set of stages and knowledge capsules. It provides methods for creating
 * projects, adding stages to projects, adding knowledge capsules to stages, and more.
 */
public class KnowledgeCapsuleSystem {


    /**
     * This variable is the number of projects
     */
    public final int PROJECTS = 10;


	private Project[] projects;
	private SimpleDateFormat dateFormat;

    /**
	 * Constructs a new KnowledgeCapsuleSystem object with an array of Projects and a SimpleDateFormat object.
	 */
    public KnowledgeCapsuleSystem(){
        projects = new Project[PROJECTS];
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    }
    
	/**
	 * Creates a new Project object and adds it to the projects array.
	 * @param projectName the name of the project.
	 * @param clientName the name of the client.
	 * @param clientPhone the phone number of the client.
	 * @param budget the budget of the project.
	 * @param managerName the name of the project manager.
	 * @param managerPhone the phone number of the project manager.
	 * @param projectDuration the duration of the project in months.
	 * @param stageMonths an array that stores the duration of each stage of the project in months.
	 * @return a message indicating whether the project was successfully created.
	 */
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

	/**
	 * Sets the current stage of a project to the next stage.
	 * @param projectName the name of the project
	 * @return a message indicating if the stage was culminated successfully
	 */
	public String culminateStage(String projectName) {
		String msg = "";
		if (findProjectByName(projectName) != null) {
			msg = findProjectByName(projectName).setCurrentStage();
		} 
		return msg;
	}

	/**
	 * Updates the status of the capsule to "Approved" and sets the date of approval.
	 * @param capsuleID the ID of the capsule to pass
	 * @param searchedProject the name of the project to which the capsule belongs
	 * @return a message indicating the status of the capsule after being passed and the date of approval if it was approved, or a message indicating that the capsule does not exist
	 */
	public String passCapsule(String capsuleID, String searchedProject) {
		String msg = "The capsule does not exist!!!";
		if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID) != null) {
			msg = findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).setStatus() + ", the date of approval is: " + dateFormat.format(findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).getApprovalDate().getTime());
		} 
		return msg;
	}


	/**
	 * Releases a capsule by setting its URL, assuming it has been approved and not published before.
	 * @param capsuleID the ID of the capsule to be released
	 * @param searchedProject the name of the project where the capsule belongs
	 * @return a message indicating if the capsule was successfully released, or if it does not exist, was not approved, or has been published before
	 */
	public String releaseCapsule(String capsuleID, String searchedProject) {
		String msg = "The capsule does not exist!!!";
		if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID) != null) {

			if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).getStatus() == "Approved") {
				if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).getURL().isEmpty()) {
					msg = findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).setURL();
				} else {
					msg = "The capsule has been published before!!!";				
				}
			} else {
				msg = "The capsule must be approved first!!!";
			}
		} 
		return msg;
	}

	/**
	 * Searches for a project in the array of projects by its name.
	 * @param name the name of the project to be searched for
	 * @return the project with the given name, or null if it is not found
	 */
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
	 */
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

	/**
	 * 	Searches for capsules that have a collaborator with a given name.
	 * @param nombreColaborador The name of the collaborator to search for.
	 * @return A String containing the IDs of the capsules that have the collaborator
	*/
	public String searchColaboratorCapsules(String nombreColaborador) {
		String msg = "";
		for (Project project : projects) {
			if (project != null) {
				for (Stage stage : project.getStages()) {
					if(project.getStages() != null) {
						for (Capsule capsule : stage.getCapsules()) {
							if (capsule != null) {
								Collaborator collaborator = capsule.getCollaborator();
								if (collaborator.getCollaboratorName().equals(nombreColaborador)) {
									msg += "- ID: " + capsule.getCapsuleID() + "\n";
								}
							}
						}
					}
				}
			}
		}
		return msg;
	}

	public String searchLearns(String Keyword) {
		String msg = "";
		for (Project project : projects) {
			if (project != null) {
				for (Stage stage : project.getStages()) {
					if(project.getStages() != null) {
						for (Capsule capsule : stage.getCapsules()) {
							if (capsule != null) {
								ArrayList<String> hashtags = capsule.getHashtag();

								boolean isFound = hashtags.contains(Keyword);
								String status = capsule.getStatus();

								if (isFound == true && status.equals("Approved") ) {
									msg += "- ID: " + capsule.getCapsuleID() + "\n" + "Learning: " + capsule.getLearning() + "\n";
								}
							}
						}
					}
				}
			}
		}
		return msg; 
    }
	
	/**
	 * Returns the array of projects.
	 * @return an array of projects
	 */
    public Project[] getProject(){
		return projects;
	}

	
}
