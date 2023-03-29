package ui; 

import java.util.Scanner; 
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
        double budget = 0;     
        final int STAGES = 6;
        int[] stageMonths = new int[STAGES]; //name array
        System.out.println("                       -------------Welcome to the project management system------------");
        System.out.println("Please provide the following information to create a new project:");        System.out.println("Enter the project name: "); 
        projectName = reader.next(); //PREGUNTAR ESPACIO
        System.out.println("Enter the client name: "); 
        clientName = reader.next(); 
        System.out.println("Enter the client phone: "); 
        clientPhone = reader.next(); 
        System.out.println("Enter the project budget: "); 
        budget = reader.nextDouble(); 
        System.out.println("How many managers will the project have?"); 
        int managersNum = reader.nextInt();
        String[] managerName = new String[managersNum]; //array of names
        String[] managerPhone = new String[managersNum]; //array of phones 
        for (int i = 0; i < managersNum; i++) {
            System.out.println("Enter the name of the manager " + (i+1)); 
            managerName[i] = reader.next(); 
            System.out.println("Enter the manager's phone number" + (i+1)); 
            managerPhone[i] = reader.next(); 
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
        //call to a control operation
        controller.registerProject(projectName, clientName, clientPhone, budget, managerName, managerPhone, stageMonths);
        //FALTA RECIBIR EL STRING CON EL MENSAJE DE CONFIRMACION, VALIDANDO LO DE LOS 10 PROYECTOS
        System.out.println("Project " + projectName + " has been created successfully!");
        System.out.println("------Project Details------");
        System.out.println("Project Name: " + controller.getProject().getProjectName());
        System.out.println("Client Name: " + controller.getProject().getClientName());
        System.out.println("Client phone number: " + controller.getProject().getClientPhone());
        System.out.println("Budget: " + controller.getProject().getBudget());
        String[] managerNames = controller.getProject().getManagerName();
        System.out.println("Name(s) of manager(s):");        
        for (int i = 0; i < managerNames.length; i++) {
            System.out.println((i+1) + ". " + managerNames[i]);
        }
    
        String[] managerPhones = controller.getProject().getManagerPhone();
        System.out.println("Manager(s) phone number(s):");
        for (int i = 0; i < managerPhones.length; i++) {
            System.out.println((i+1) + ". " + managerPhones[i]);
        }
    }





}
