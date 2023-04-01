package ui; 
import java.text.*;
import java.util.*;

import javax.xml.stream.events.StartDocument;

import model.KnowledgeCapsuleSystem;

public class Main{

    private KnowledgeCapsuleSystem controller;
    private Scanner reader;  
    private int numProjects;// counter with the number fo projects, permite mostrar la informacion una vez se creo el proyecto


    // class constructor 
    public Main(){
        this.reader = new Scanner(System.in); 
        controller = new KnowledgeCapsuleSystem();
        numProjects = 0;
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
        double budget = 0;    
        final int STAGES = 6;
        int[] stageMonths = new int[STAGES]; 
        System.out.println("                       -------------Welcome to the project management system------------");
        if (controller.getFirstValidPosition() != -1){
            System.out.println("Please provide the following information to create a new project:");        
            System.out.println("Enter the project name: "); 
            projectName = reader.nextLine(); 
            System.out.println("Enter the client name: "); 
            clientName = reader.next(); 
            reader.nextLine();
            System.out.println("Enter the client phone number: "); 
            clientPhone = reader.next(); 
            reader.nextLine();
            System.out.println("Enter the manager name: "); 
            managerName = reader.next(); 
            reader.nextLine();
            System.out.println("Enter the manager phone number: "); 
            managerPhone = reader.next(); 
            System.out.println("Enter the project budget: "); 
            budget = reader.nextDouble(); 
            reader.nextLine();
            System.out.println("Enter the duration in months of the initiation stage: "); 
            stageMonths[0] = reader.nextInt(); 
            reader.nextLine();
            System.out.println("Enter the duration in months of the analysis stage: "); 
            stageMonths[1] = reader.nextInt(); 
            reader.nextLine();
            System.out.println("Enter the duration in months of the design stage: "); 
            stageMonths[2] = reader.nextInt(); 
            reader.nextLine();
            System.out.println("Enter the duration in months of the execution stage: "); 
            stageMonths[3] = reader.nextInt();         
            reader.nextLine();
            System.out.println("Enter the duration in months of the closing and follow-up stage: "); 
            stageMonths[4] = reader.nextInt(); 
            reader.nextLine();
            System.out.println("Enter the duration in months of the project control stage: "); 
            stageMonths[5] = reader.nextInt(); 
            reader.nextLine();
            int projectDuration = 0;//suma de todas las estapas, sirve para establecer la fecha final del proyecto
            for (int i = 0; i < STAGES; i++) {//
                projectDuration += stageMonths[i]; 
            }
            String msg = controller.registerProject(projectName, clientName, clientPhone, budget, managerName, managerPhone, projectDuration, stageMonths);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            
            System.out.println("****    Project " + projectName + msg + "    ****");
            System.out.println("------Project Details------");
            System.out.println("Project Name: " + controller.getProject()[numProjects].getProjectName());
            System.out.println("Project start date: " + dateFormat.format(controller.getProject()[numProjects].getStartDate().getTime())); 
            System.out.println("Project final planned date: " + dateFormat.format(controller.getProject()[numProjects].getEndDate().getTime()));
            System.out.println("Client Name: " + controller.getProject()[numProjects].getClientName());
            System.out.println("Client phone number: " + controller.getProject()[numProjects].getClientPhone());
            System.out.println("Budget: $" + (int) controller.getProject()[numProjects].getBudget());
            System.out.println("Name of manager: " + controller.getProject()[numProjects].getManager().getManagerName());  
            System.out.println("manager phone number: " + controller.getProject()[numProjects].getManager().getManagerPhone()); 
            System.out.println("------Stages------ "); 
            System.out.println("INITIATION: " + controller.getProject()[numProjects].getStages()[0].getIsActive()); 
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
            System.out.println("Límite de proyectos alcanzado :(");
        }
       
    }

    public void addCapsule() {
        
    }



    public void endStage() {
        try {
            System.out.println("Por favor ingrese el nombre del proyecto: "); 
            String searchedProject = reader.nextLine();
            System.out.println("La etapa actual del proyecto es: " + controller.findProjectByName(searchedProject).getCurrentStage().getStageName()); 
            System.out.println(controller.culminateStage(searchedProject)); 
        } catch (NullPointerException e) {
            System.out.println("El proyecto no existe");
        }       
    }
    
        




}
