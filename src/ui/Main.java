package ui; 
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;

import model.KnowledgeCapsuleSystem;

public class Main{

    private KnowledgeCapsuleSystem controller;
    private Scanner reader;  
    private int numProjects;// counter with the number of projects, allows to display the information once the project is created
    private SimpleDateFormat dateFormat;


    // class constructor 
    public Main(){
        this.reader = new Scanner(System.in); 
        controller = new KnowledgeCapsuleSystem();
        numProjects = 0;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    public static void main(String[] args){
        Main view = new Main(); // the call to the class constructor 

        int option = 0; 

        do{
            view.menu(); 
            option = view.validateIntegerInput(); //set view. pa all methods as they need to be executed for that object
            view.executeOption(option);
        }while(option != 0);


        view.reader.close();
    }

    public void menu(){
        System.out.println("                       -------------MENU------------");
        System.out.println("0. Exit"); 
        System.out.println("1. Create a project");
        System.out.println("2. Complete stage of a project");
        System.out.println("3. Register capsule");
        System.out.println("4. Approve capsule");
        System.out.println("5. Publish capsule");
        System.out.print("Select an option: ");
    }


    public void executeOption(int option){
        switch (option) {
            case 1:
            addProject();
                break; 
            case 2:
            endStage();
                break; 
            case 3:
            addCapsule();
                break; 
            case 4:
            approveCapsule();
                break; 
            case 5:
            publishCapsule();
                break; 
            case 0:
                System.out.println("See you soon!"); 
                break;
            case -1: 
                System.out.println("Invalid Option!"); 
                break; 

        }
    }

    public int validateIntegerInput(){
        int option = 0; 
        if(reader.hasNextInt()){
            option = reader.nextInt(); 
            reader.nextLine();
        }
        else{
            reader.nextLine();// clear the scanner 
            option = -1; 
            System.out.println("Enter an integer value"); 
        }
        return option; 
    }

    public void addProject(){
        String projectName = ""; 
        String clientName = ""; 
        String clientPhone = ""; 
        String managerName = "";
        String managerPhone = "";
        double budget = -1.0;    
        final int STAGES = 6;
        int[] stageMonths = new int[STAGES]; 
        System.out.println("                       -------------Welcome to the project management system------------");
        if (controller.getFirstAvailableProjectIndex() != -1){
            System.out.println("Please provide the following information to create a new project:");        
            System.out.println("Enter the project name: "); 
            projectName = reader.nextLine(); 
            System.out.println("Enter the client name: "); 
            clientName = reader.nextLine(); 
            System.out.println("Enter the client phone number: "); 
            clientPhone = reader.nextLine(); 
            System.out.println("Enter the manager name: "); 
            managerName = reader.nextLine(); 
            System.out.println("Enter the manager phone number: "); 
            managerPhone = reader.nextLine(); 
            if (projectName.isEmpty() || clientName.isEmpty() || clientPhone.isEmpty() || managerName.isEmpty() || managerPhone.isEmpty()) {
                System.out.println("Error: Please enter all required information");
                return;
            }            
            while (budget < 0) {
                System.out.println("Enter the project budget: ");
                budget = reader.nextDouble();
                
                if (budget < 0) {
                    System.out.println("The budget must be a positive number");
                }
            } 
            System.out.println("Enter the duration in months of the start stage: "); 
            stageMonths[0] = reader.nextInt(); 
            System.out.println("Enter the duration in months of the analysis stage: "); 
            stageMonths[1] = reader.nextInt(); 
            System.out.println("Enter the duration in months of the design stage: "); 
            stageMonths[2] = reader.nextInt(); 
            System.out.println("Enter the duration in months of the execution stage: "); 
            stageMonths[3] = reader.nextInt();         
            System.out.println("Enter the duration in months of the closing and follow-up stage: "); 
            stageMonths[4] = reader.nextInt(); 
            System.out.println("Enter the duration in months of the project control stage: "); 
            stageMonths[5] = reader.nextInt(); 

            int projectDuration = 0;//sum of all stages, is used to set the end date of the project.
            for (int i = 0; i < STAGES; i++) {//
                projectDuration += stageMonths[i]; 
            }

            String msg = controller.createProject(projectName, clientName, clientPhone, budget, managerName, managerPhone, projectDuration, stageMonths);

            System.out.println("****    Project " + projectName + msg + "    ****");
            System.out.println("------Project Details------");
            System.out.println("Project Name: " + controller.getProject()[numProjects].getProjectName());
            System.out.println("Project start date: " + dateFormat.format(controller.getProject()[numProjects].getStartDate().getTime())); 
            System.out.println("Project final planned date: " + dateFormat.format(controller.getProject()[numProjects].getEndDate().getTime()));
            System.out.println("Client Name: " + controller.getProject()[numProjects].getClientName());
            System.out.println("Client phone number: " + controller.getProject()[numProjects].getClientPhone());
            NumberFormat formatoDolares = NumberFormat.getCurrencyInstance(Locale.US);
            String numDollars = formatoDolares.format(controller.getProject()[numProjects].getBudget());
            System.out.println("Budget: " + numDollars);

            System.out.println("Name of manager: " + controller.getProject()[numProjects].getManager().getManagerName());  
            System.out.println("manager phone number: " + controller.getProject()[numProjects].getManager().getManagerPhone()); 
            System.out.println("------Stages------ "); 
            System.out.println("START: " + controller.getProject()[numProjects].getStages()[0].getIsActive()); 
            System.out.println("Planned start date: " + dateFormat.format(controller.getProject()[numProjects].getCurrentStage().getPlanStartDate().getTime()));  
            System.out.println("Planned final date: " + dateFormat.format(controller.getProject()[numProjects].getCurrentStage().getPlanEndDate().getTime()));  
            System.out.println("ANALISYS: " + controller.getProject()[numProjects].getStages()[1].getIsActive()); 
            System.out.println("Planned start date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[1].getPlanStartDate().getTime()));  
            System.out.println("Planned final date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[1].getPlanEndDate().getTime()));  
            System.out.println("DESING: " + controller.getProject()[numProjects].getStages()[2].getIsActive()); 
            System.out.println("Planned start date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[2].getPlanStartDate().getTime()));  
            System.out.println("Planned final date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[2].getPlanEndDate().getTime()));  
            System.out.println("EXECUTION: " + controller.getProject()[numProjects].getStages()[3].getIsActive()); 
            System.out.println("Planned start date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[3].getPlanStartDate().getTime()));  
            System.out.println("Planned final date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[3].getPlanEndDate().getTime()));  
            System.out.println("CLOSE AND FOLLOW-UP: " + controller.getProject()[numProjects].getStages()[4].getIsActive()); 
            System.out.println("Planned start date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[4].getPlanStartDate().getTime()));  
            System.out.println("Planned final date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[4].getPlanEndDate().getTime()));  
            System.out.println("PROJECT CONTROL: " + controller.getProject()[numProjects].getStages()[5].getIsActive()); 
            System.out.println("Planned start date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[5].getPlanStartDate().getTime()));  
            System.out.println("Planned final date: " + dateFormat.format(controller.getProject()[numProjects].getStages()[5].getPlanEndDate().getTime()));  
            numProjects++;
        } else {
            System.out.println("Project limit reached :(");
        }
       
    }

    public void endStage() {
        try {
            System.out.println("Please enter the project name: ");            
            String searchedProject = reader.nextLine();
            System.out.println("The current stage of the project is: " + controller.findProjectByName(searchedProject).getCurrentStage().getStageName()); 
            System.out.println(controller.culminateStage(searchedProject)); 
        } catch (NullPointerException e) {//validates if the project object exists
            System.out.println("The project does not exist");
        }  
    }

    public void addCapsule() {
        String collaboratorName;
        String collaboratorPosition;
        int capsuleType;
        String description;
        String learning;
        System.out.println(" -------------Welcome to the capsule management system------------");
        try {
            System.out.println("Please enter the project name: ");
            String searchedProject = reader.nextLine();
            System.out.println("The current stage of the project is: " + controller.findProjectByName(searchedProject).getCurrentStage().getStageName());
            if (controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() != -1){
                System.out.println("Please provide the following information to create a new capsule in the stage:");
                System.out.println("Enter the collaborator name: ");
                collaboratorName = reader.nextLine();
                System.out.println("Enter the collaborator position: ");
                collaboratorPosition = reader.nextLine();
                System.out.println("Enter the capsule type: ");
                System.out.println("1. Technician");
                System.out.println("2. Management ");
                System.out.println("3. Domain");
                System.out.println("4. Experience");
                capsuleType = reader.nextInt();
                if (capsuleType < 1 || capsuleType > 4) {
                    System.out.println("Please enter a valid capsule type (1-4): ");
                    capsuleType = reader.nextInt();
                }
                System.out.println("Enter the description of the situation you want to record, mark the keywords between hashtags(ex: #coding#): ");
                reader. nextLine();
                description = reader.nextLine();
   
                System.out.println("Enter the learning or lesson learned with the situation, mark the keywords between hashtags: ");
                learning = reader.nextLine();
   
   
                String msg = controller.findProjectByName(searchedProject).getCurrentStage().createCapsule(description, capsuleType, collaboratorName, collaboratorPosition, learning);
                System.out.println("**** Capsule " + (controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 50 : (controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps( ))) + msg + " ****");
                System.out.println("------Capsule Details------");
                System.out.println("ID capsule(You need it to approve and publish the capsule): " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps( ) == -1 ? 49 : (controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getCapsuleID());
                System.out.println("Capsule type: " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : ( controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getCapsuleType());
                System.out.println("Status: " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : (controller. findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getStatus());
                System.out.println("Description: " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : (controller. findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getDescription());
                System.out.println("Lesson learned: " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : (controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getLearning());
                System.out.println("Collaborator Name: " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : ( controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getCollaborator().getCollaboratorName());
                System.out.println("Collaborator Position: " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : ( controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getCollaborator().getCollaboratorPosition());
    
    
                if (!controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : (controller.findProjectByName(searchedProject).getCurrentStage() .getFirstValidPositionCaps()-1)].getHashtag().isEmpty()) {
                    System.out.println("Keywords: " );
                    for (int i = 0; i < controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : (controller.findProjectByName( searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getHashtag().size(); i++) {
                        System.out.println("- " + controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : (controller.findProjectByName (searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].getHashtag().get(i));
                    }
                } else {
                    System.out.println("No keywords " );
                }
   
               
            } else {
                System.out.println("Capsule limit reached for this stage :(");
            }
   
   
        } catch (NullPointerException e) { // check if the project object exists
            System.out.println("The project does not exist");
        }
       
    }

    public void approveCapsule() {
        try {
            System.out.println("Please enter the project name: ");
            String searchedProject = reader.nextLine();
            System.out.println("The current stage of the project is: " + controller.findProjectByName(searchedProject).getCurrentStage().getStageName());
            System.out.println("Please enter the ID of the capsule to approve: ");
            String capsuleID = reader.nextLine();
            System.out.println(controller.passCapsule(capsuleID, searchedProject));
        } catch (NullPointerException e) { // check if the project object exists
            System.out.println("The project does not exist");
        }
    }

    public void publishCapsule() {
        try {
            System.out.println("Please enter the project name: ");
            String searchedProject = reader.nextLine();
            System.out.println("The current stage of the project is: " + controller.findProjectByName(searchedProject).getCurrentStage().getStageName());
            System.out.println("Please enter the ID of the capsule to publish: ");
            String capsuleID = reader.nextLine();
            System.out.println(controller.releaseCapsule(capsuleID, searchedProject));
           
        } catch (NullPointerException e) { // check if the project object exists
            System.out.println("The project does not exist");
        }
    }
    
        
}
