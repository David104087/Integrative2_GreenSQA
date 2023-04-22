package model;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Capsule class represents a Capsule object, which is a piece of content created by a collaborator.
 * The Capsule contains a unique identifier, a description, a capsule type (technical, management, domain, or experience),
 * a collaborator (name and position), a learning statement, a list of keywords (hashtags), a URL, a status (under review or approved),
 * and an approval date (if the capsule is approved).
 */
public class Capsule {
    
    private String capsuleID;
    private String description;
    private String capsuleType;
    private Collaborator collaborator;
    private String learning;
    private ArrayList<String> hashtags;
    private String URL;
    private String status;//under review or approved
    private Calendar approvalDate;

    /**
     * Constructs a Capsule object with the given parameters and generates a unique identifier for it.
     * @param description a string containing the capsule's description.
     * @param capsuleType a string containing the capsule's type (technical, management, domain, or experience).
     * @param collaboratorName a string containing the collaborator's name.
     * @param collaboratorPosition a string containing the collaborator's position.
     * @param learning a string containing the capsule's learning statement.
     * @param status a string containing the capsule's status (under review or approved).
     */
    public Capsule(String description, String capsuleType, String collaboratorName, String collaboratorPosition, String learning, String status){
        UUID uuid = UUID.randomUUID();//generates a unique identifier
        this.capsuleID = uuid.toString();
        this.description = description;
        this.capsuleType = capsuleType;
        this.collaborator = new Collaborator(collaboratorName, collaboratorPosition);
        this.learning = learning;
        this.hashtags = extractKeywords(description, learning);
        this.URL = "";
        this.status = status;
    }   
    
    /**
     * Returns the URL of the capsule.
     * @return a string containing the URL of the capsule.
     */
    public String getURL() {
        return URL;
    }

    /**
     * Returns the capsule's learning statement.
     * @return a string containing the capsule's learning statement.
     */
    public String getLearning() {
        return learning;
    }

    /**
     * Returns the capsule's description.
     * @return a string containing the capsule's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the capsule's unique identifier.
     * @return a string containing the capsule's unique identifier.
     */
    public String getCapsuleID() {
        return capsuleID;
    }

    /**
     * Returns the approval date of the capsule.
     * @return a Calendar object representing the approval date of the capsule.
     */
    public Calendar getApprovalDate() {
        return approvalDate;
    }

    /**
     * Returns the capsule's type (technical, management, domain, or experience).
     * @return a string containing the capsule's type.
     */
    public String getCapsuleType() {
        return capsuleType;
    }

    /**
     * Returns the capsule's collaborator (name and position).
     * @return a Collaborator object representing the capsule's collaborator.
     */
    public Collaborator getCollaborator() {
        return collaborator;
    }

    /**
     * Returns the list of hashtags associated with the capsule.
     * @return an ArrayList of Strings representing the hashtags.
     */
    public ArrayList<String> getHashtag() {
        return hashtags;
    }

    /**
     * Returns the status of the capsule.
     * @return a String representing the current status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the ID of the capsule.
     * @param capsuleID a String representing the ID to be set.
     */
    public void setCapsuleID(String capsuleID) {
        this.capsuleID = capsuleID;
    }

    /**
     * Sets the URL of the capsule.
     * @return a String containing a message indicating the success of the operation and the URL of the capsule.
     */
    public String setURL() {
        String msg = "";
        this.URL = "https://greensqa.com/en/capsule/" + getCapsuleID();
        msg = "Capsule successfully published!!!, the URL of the capsule is: " +  URL;
        return msg;
    }
    
    /**
     * Sets the description of the capsule.
     * @param description a String representing the description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the type of the capsule.
     * @param capsuleType a String representing the type to be set.
     */
    public void setCapsuleType(String capsuleType) {
        this.capsuleType = capsuleType;
    }

    /**
     * Sets the collaborator of the capsule.
     * @param collaborator a Collaborator object representing the collaborator to be set.
     */
    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    /**
     * Sets the learning of the capsule.
     * @param learning a String representing the learning to be set.
     */
    public void setLearning(String learning) {
        this.learning = learning;
    }

    /**
     * Sets the status of the capsule to "Approved".
     * @return a String containing a message indicating the result of the operation.
     */
    public String setStatus() {
        String msg = "The capsule was previously approved";
        if (this.status.equals("Under review")) {
            setApprovalDate();
            this.status = "Approved";
            msg = "The capsule was successfully approved";
        } 
        return msg;
    }

    /**
     * Sets the approval date of the capsule to the current date and time.
     */
    public void setApprovalDate() {
        this.approvalDate = Calendar.getInstance();

    }

    /**
     * Extracts the keywords from the description and learning of the capsule.
     * @param description a String representing the description of the capsule.
     * @param learning a String representing the learning of the capsule.
     * @return an ArrayList of Strings representing the keywords extracted from the description and learning.
     */
    public ArrayList<String> extractKeywords(String description, String learning) {
        hashtags = new ArrayList<>();
        Pattern pattern = Pattern.compile("#(.*?)#"); //the regular expression "#(.*?)#" is used, which means that it will 
        //will look for any string that starts with "#" and ends with "#", and that contains any number of characters between 
        //the two "#" symbols.
    
        Matcher matcher = pattern.matcher(description);
        while (matcher.find()) {
            hashtags.add(matcher.group(1)); //add keyword found
        }
    
        matcher = pattern.matcher(learning);
        while (matcher.find()) {
            hashtags.add(matcher.group(1)); // add keyword found
        }
    
        return hashtags;
    }

    /**
     * This function returns a string representation of an object with various properties including capsule
     * ID, type, status, learnings, description, and collaborator information.
     * 
     * @return A string representation of an object, which includes the capsule ID, capsule type, status,
     * learnings, description, collaborator name, and collaborator position.
     */
    @Override
    public String toString() {
        return  "ID: " + capsuleID + "\n"
                + "Capsule Type: " + capsuleType + "\n"
                + "Status: " + status + "\n"
                + "Learnings: " + learning + "\n"
                + "Description: " + description + "\n"
                + "Collaborator name: " + collaborator.getCollaboratorName() + "\n"
                + "Collaborator Position: " + collaborator.getCollaboratorPosition() + "\n";
    }
    

    
}
