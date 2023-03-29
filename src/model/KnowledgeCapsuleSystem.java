package model;

public class KnowledgeCapsuleSystem {

	private Project project;
    
    public KnowledgeCapsuleSystem(){}

    public void registerProject(String projectName, String clientName, String clientPhone, double budget, String[] managerName, String[] managerPhone, int[] stageMonths){
        this.project = new Project(projectName, clientName, clientPhone, budget, managerName, managerPhone);
	}
    public Project getProject(){
		return project;
	}
}
