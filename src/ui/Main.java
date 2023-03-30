package ui; 

import java.util.*; 
import model.KnowledgeCapsuleSystem;

public class Main{

    private KnowledgeCapsuleSystem controller;
    private Scanner reader;  

    // class constructor 
    public Main(){
        this.reader = new Scanner(System.in); 
        controller = new KnowledgeCapsuleSystem();
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
        int numProjects = 0;// counter with the number fo projects, permite mostrar la informacion una vez se creo el proyecto
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
            System.out.println("Enter the duration in months of the start stage: "); 
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
            //call to a control contructor
            //FALTA MANDAR LAS FECHAS
            String msg = controller.registerProject(projectName, clientName, clientPhone, budget, managerName, managerPhone, stageMonths);
            System.out.println("****    Project " + projectName + msg + "    ****");
            System.out.println("------Project Details------");
            System.out.println("Project Name: " + controller.getProject()[numProjects].getProjectName());
            System.out.println("Client Name: " + controller.getProject()[numProjects].getClientName());
            System.out.println("Client phone number: " + controller.getProject()[numProjects].getClientPhone());
            System.out.println("Budget: $" + (int) controller.getProject()[numProjects].getBudget());
            System.out.println("Name of manager: " + controller.getProject()[numProjects].getManager().getManagerName());  
            System.out.println("manager phone number: " + controller.getProject()[numProjects].getManager().getManagerPhone()); 
            System.out.println("Stages: "); 
            System.out.println("INITIATION: " + controller.getProject()[numProjects].getStages()[0].getIsActive()); 
            System.out.println("ANALISYS: " + controller.getProject()[numProjects].getStages()[1].getIsActive()); 
            System.out.println("DESING: " + controller.getProject()[numProjects].getStages()[2].getIsActive()); 
            System.out.println("EXECUTION: " + controller.getProject()[numProjects].getStages()[3].getIsActive()); 
            System.out.println("CLOSE AND FOLLOW-UP: " + controller.getProject()[numProjects].getStages()[4].getIsActive()); 
            System.out.println("PROJECT CONTROL: " + controller.getProject()[numProjects].getStages()[5].getIsActive()); 
        } else {
            System.out.println("Límite de proyectos alcanzado :(");
        }
       
    }

    public void endStage(){
        System.out.println("Por favor ingrese el nombre del proyecto de la etapa: "); 
    }



}
