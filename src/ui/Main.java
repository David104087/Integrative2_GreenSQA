/**
 * @author [David Artunduaga Penagos]
 * @version [1.0]
 * @since [04/04/2023]
 */
package ui; 
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;
import java.util.Locale;
import model.KnowledgeCapsuleSystem;

/**
 * This Main class represents the main UI class for the Knowledge Capsule System.
 * It contains the main method and all the user interface functionalities.
 */
public class Main{

    private KnowledgeCapsuleSystem controller;
    private Scanner reader;  
    private int numProjects;// counter with the number of projects, allows to display the information once the project is created
    private SimpleDateFormat dateFormat;


    /**
     * This is the class constructor for the Main class.
     * It initializes the scanner, controller, and date format objects, and sets the number of projects to zero.
     */
    public Main(){
        this.reader = new Scanner(System.in); 
        controller = new KnowledgeCapsuleSystem();
        numProjects = 0;
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * This is the main method that starts the execution of the program.
     * It creates an object of the Main class, displays the menu, and waits for user input.
     * It executes the corresponding functionality based on the user's input until the user selects the "Exit" option.
     * Finally, it closes the scanner object.
     * 
     * @param args An array of String objects representing the command-line arguments.
     */
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

    /**
     * This method displays the main menu options for the user to select.
     */
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


    /**
     * This method executes the corresponding functionality based on the user's input.
     * 
     * @param option An integer representing the user's selected option.
     */
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
            case 6:
            collaboratorCapsules();
                break;
            case 0:
                System.out.println("See you soon!"); 
                break;
            case -1: 
                System.out.println("Invalid Option!"); 
                break; 

        }
    }

    /**
     * This method validates the user's input and returns the selected option as an integer.
     * 
     * @return An integer representing the user's selected option.
     */
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

    /**
    * Reads user input to create a new project, and adds it to the project list in the KnowledgeCapsuleSystem class.
     * Prints out the project details.
     */
    public void addProject(){
        String projectName = ""; 
        String clientName = ""; 
        String clientPhone = ""; 
        String managerName = "";
        String managerPhone = "";
        double budget = -1.0;    
        final int STAGES = 6;
        int[] stageMonths = new int[STAGES];   
        String[] stageNames = {"START", "ANALYSIS", "DESING", "EXECUTION", "CLOSE AND FOLLOW-UP", "PROJECT CONTROL"};      

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
            
            for (int i = 0; i < STAGES; i++) {
                System.out.println("Enter the duration in months of the " +  stageNames[i] + " stage: "); 
                stageMonths[i] = reader.nextInt(); 
            }

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

    /**
     * Asks the user to enter the name of a project and then culminates the current stage of that project by
     * invoking the 'culminateStage' method of the controller object.
     * Displays the name of the current stage of the project before culminating it. If the project object does not exist,
     * it catches the NullPointerException thrown by the 'findProjectByName' method and displays a message to the user.
     * @throws NullPointerException if the project object does not exist
     */
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

    /**
     * Adds a new capsule to the current stage of a project in the capsule management system.
     * Prompts the user to enter the required information, such as the collaborator name, position, capsule type,
     * description and learning. After validating the inputs, the method creates a new capsule and prints out the
     * capsule's details, including its ID, type, status, description, lesson learned, collaborator name and position.
     * If the project object does not exist,
     * it catches the NullPointerException thrown by the 'findProjectByName' method and displays a message to the user.
     * @throws NullPointerException if the project object does not exist
     */
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
                reader.nextLine();
                if (capsuleType < 1 || capsuleType > 4) {
                    System.out.println("Please enter a valid capsule type (1-4): ");
                    capsuleType = reader.nextInt();
                    reader.nextLine();
                }
                System.out.println("Enter the description of the situation you want to record, mark the keywords between hashtags(ex: #coding#): ");
                description = reader.nextLine();

                System.out.println("Enter the learning or lesson learned with the situation, mark the keywords between hashtags: ");
                learning = reader.nextLine();
                do {
                    if (hasHashtags(description) == false) {
                        System.out.println("The description must have at least a keyword!!!");
                        System.out.println("Enter the description of the situation you want to record, mark the keywords between hashtags(ex: #coding#): ");
                        description = reader.nextLine();
                    }
                    
                    if (hasHashtags(learning) == false) {
                        System.out.println("The learning must have at least a keyword!!!");
                        System.out.println("Enter the learning or lesson learned with the situation, mark the keywords between hashtags: ");
                        learning = reader.nextLine();
                    }
                } while (hasHashtags(description) == false || hasHashtags(learning) == false);

                if (collaboratorName.isEmpty() || collaboratorPosition.isEmpty() || description.isEmpty() || learning.isEmpty()) {
                    System.out.println("Error: Please enter all required information");
                    return;
                }
   
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

    /**
     * Checks if a given string contains at least two hashtags.
     * @param str the string to check for hashtags
     * @return true if the string contains at least two hashtags, false otherwise
     */
    public boolean hasHashtags(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '#') {
                count++;
            }
        }
        return count >= 2;
    }
    
    /**
     * Prompts the user to enter the name of a project and the ID of a capsule to approve.
     * If the project exists, and the capsule belongs to the project and is in a state that allows approval,
     * the capsule is approved and a success message is printed.
     * If the project does not exist or the capsule cannot be approved, an error message is printed.
     */
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

    /**
     * Prompts the user to enter the name of a project and the ID of a capsule to publish.
     * If the project exists, and the capsule belongs to the project and is in a state that allows publishing,
     * the capsule is published and a success message is printed.
     * If the project does not exist or the capsule cannot be published, an error message is printed.
     */
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
    
    public void collaboratorCapsules() {
        System.out.println("Please enter the collaborator name: ");
        String nameCollaborator = reader.nextLine();
        String capsules = controller.searchColaboratorCapsules(nameCollaborator);
        if (!capsules.isEmpty()) {
            System.out.println("Capsules found for collaborator " + nameCollaborator + ":");
            System.out.println("------------------------");
            System.out.println(capsules);
            System.out.println("------------------------");
        } else {
            System.out.println("No capsules found for collaborator " + nameCollaborator);
        }
    }  
}
