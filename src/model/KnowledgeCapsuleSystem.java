package model;
import java.util.Calendar;
public class KnowledgeCapsuleSystem {

    public final int PROJECTS = 10;
	private Project[] projects;
    
    public KnowledgeCapsuleSystem(){
        projects = new Project[PROJECTS];

    }
    
    public String registerProject(String projectName, String clientName, String clientPhone, double budget, String managerName, String managerPhone, int[] stageMonths){
        String msg = " fue creado exitosamente!!!";
        Project project = new Project(projectName, clientName, clientPhone, budget, managerName, managerPhone);
        int pos = getFirstValidPosition();
		if(pos != -1){
			projects[pos] = project; 
		} 
        return msg;
        //FALTA CALCULAR FECHAS CON stageMonth
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
