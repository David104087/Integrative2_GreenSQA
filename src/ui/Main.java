/**
 * @author [David Artunduaga Penagos]
 * @version [2.0]
 * @since [04/04/2023]
 */
package ui; 
import java.util.Scanner;
import model.KnowledgeCapsuleSystem;

/**
 * This Main class represents the main UI class for the Knowledge Capsule System.
 * It contains the main method and all the user interface functionalities.
 */
public class Main{

    private KnowledgeCapsuleSystem controller;
    private Scanner reader;   
    private int capsulesTecnician;
    private int capsulesManagement;
    private int capsulesDomain;
    private int capsulesExpeirence ;

    /**
     * This is the class constructor for the Main class.
     * It initializes the scanner, controller, and date format objects, and sets the number of projects to zero.
     */
    public Main(){
        this.reader = new Scanner(System.in); 
        controller = new KnowledgeCapsuleSystem();
        capsulesTecnician = 0;
        capsulesManagement = 0;
        capsulesDomain = 0;
        capsulesExpeirence = 0;
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
        System.out.println("6. Capsules by type");
        System.out.println("7. List of capsule learning");
        System.out.println("8. Project with more capsules registered");
        System.out.println("9. Consult the capsules of a collaborator");
        System.out.println("10. Consult capsule learning");
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
            capsuleBytype();
                break;
            case 7:
            listLearnings();
                break;
            case 8:
            projectMaxCapsules();
                break;
            case 9:
            collaboratorCapsules();
                break;
            case 10:
            consultLearns();
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
            System.out.println(controller.findProjectByName(projectName).toString());
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

                int capsuleType;
                do {
                    System.out.println("Enter the capsule type: ");
                    System.out.println("1. Technician");
                    System.out.println("2. Management ");
                    System.out.println("3. Domain");
                    System.out.println("4. Experience");
                    
                    while (!reader.hasNextInt()) {
                        System.out.println("Invalid input. Please enter an integer between 1 and 4.");
                        reader.next();
                    }
                    capsuleType = reader.nextInt();
                } while (capsuleType < 1 || capsuleType > 4);

                reader.nextLine();

                if(capsuleType == 1) {
                    capsulesTecnician++;
                }
                if(capsuleType == 2) {
                    capsulesManagement++;
                }
                if(capsuleType == 3) {
                    capsulesDomain++;
                }                
                if(capsuleType == 4) {
                    capsulesExpeirence++;
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
                System.out.println(controller.findProjectByName(searchedProject).getCurrentStage().getCapsules()[controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps() == -1 ? 49 : ( controller.findProjectByName(searchedProject).getCurrentStage().getFirstValidPositionCaps()-1)].toString());
    
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
    
    /**
     * This function prints out the number of capsules by type.
     */
    public void capsuleBytype() {
        System.out.println("Capsules by type: ");
        System.out.println("------------------------");
        System.out.println("TECHNICAL:" + capsulesTecnician);
        System.out.println("MANAGEMENT:" + capsulesManagement);
        System.out.println("DOMAIN:" + capsulesDomain);
        System.out.println("EXPERIENCE:" +  capsulesExpeirence);
        System.out.println("------------------------");
    }


    /**
     * This function prints the project with the most registered capsules.
     */
    public void projectMaxCapsules() { 
        System.out.println("The project with the most registered capsules is:");
        String projectName = controller.projectMaxCapsules();

        if (!projectName.isEmpty()) {
            System.out.println("------------------------");
            System.out.println("Project(s): " + projectName);
            System.out.println("------------------------");
        } else {
            System.out.println("No projects found");
        }
    }

    /**
     * This function prompts the user to enter a project name and stage, validates the input, and then
     * calls a controller to retrieve and display any learnings associated with the project and
     * stage.
     */
    public void listLearnings() {
        System.out.println("Please enter the project name: ");
        String projectName = reader.nextLine();
        int stage;

        do {
            System.out.println("Please enter the project stage, select (1-6): ");
            System.out.println("1. START ");
            System.out.println("2. ANALYSIS");
            System.out.println("3. DESING ");
            System.out.println("4. EXECUTION ");
            System.out.println("5. CLOSE AND FOLLOW-UP ");
            System.out.println("6. PROJECT CONTROL ");
            
            while (!reader.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer between 1 and 6.");
                reader.next();
            }
            stage = reader.nextInt();
        } while (stage < 1 || stage > 6);
      
        String learnings = controller.listLearnings(projectName, stage);
        
        if (!learnings.isEmpty()) {
            System.out.println("Learnigs found: ");
            System.out.println("------------------------");
            System.out.println("Capsules: " + "\n");
            System.out.println(learnings);
            System.out.println("------------------------");
        } else {
            System.out.println("No learnings found");
        }

    }
    
    
    /**
     * This function prompts the user to enter a collaborator name, searches for capsules associated with
     * that collaborator, and displays the capsules if found.
     */
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

    /**
     * This function prompts the user to enter a keyword and searches for learnings related to that
     * keyword, displaying the results if found.
     */
    public void consultLearns() {
        System.out.println("Enter the capsule keyword to learn about the learnings:");
        String keyWord = reader.nextLine();

        String learns = controller.searchLearns(keyWord);
        if (!learns.isEmpty()) {
            System.out.println("Learnings capsules:   ");
            System.out.println("------------------------");
            System.out.println(learns);
            System.out.println("------------------------");
        } else {
            System.out.println("No learns founds");
        }
    }
}
