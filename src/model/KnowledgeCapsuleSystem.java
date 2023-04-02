package model;
import java.util.*;

public class KnowledgeCapsuleSystem {

    public final int PROJECTS = 10;
	private Project[] projects;
    
    public KnowledgeCapsuleSystem(){
        projects = new Project[PROJECTS];

    }
    
    public String registerProject(String projectName, String clientName, String clientPhone, double budget, String managerName, String managerPhone, int projectDuration, int[] stageMonths){
		
		String msg = " fue creado exitosamente!!!";
        Project project = new Project(projectName, clientName, clientPhone, budget, managerName, managerPhone);
        int pos = getFirstValidPosition();
		if(pos != -1){
			projects[pos] = project; 
			projects[pos].setStartDate();
            projects[pos].setEndDate(projectDuration);
			Calendar startDate = projects[pos].getStartDate();//extrae la fecha de inicio del proyecto para asiganr la fechas de las etapas
			Stage[] stages = projects[pos].getStages();//extrae las etapas del proyecto
			stages[0].setRealStartDate(startDate);
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
			boolean flag = findProjectByName(projectName).setCurrentStage();
			if (flag = true) {
				msg = "Etapa culminada con éxito, la nueva etapa del proyecto es: " + findProjectByName(projectName).getCurrentStage().getStageName();
			} else {
				msg = "El proyecto ya ha sido finalizado";
			}
		} 
		return msg;

	}

	public String passCapsule(String capsuleID, String searchedProject) {
		String msg = "La capsula no existe";
		if (findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID) != null) {
			msg = findProjectByName(searchedProject).getCurrentStage().findCapsuleByID(capsuleID).setApprovaDate();
		} 
		return msg;
	}

	public Project findProjectByName(String name) {
        for (Project project : projects) {
            if (project != null && project.getProjectName().equals(name)) {
                return project;
            }
        }
        return null;
    }


    /**
	 * getFirstValidPosition: search in array if exist one valid position
	 * @return pos -1 if the position does not exist, a number in [0, 9] if exist a valid position
	 * */
	public int getFirstValidPosition(){
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
