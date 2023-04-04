package model;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Capsule {
    
    private String capsuleID;
    private String description;
    private String capsuleType;
    private Collaborator collaborator;
    private String learning;
    private ArrayList<String> hashtag;
    private String URL;
    private String status;//under review or approved
    private Calendar approvalDate;

    public Capsule(String description, String capsuleType, String collaboratorName, String collaboratorPosition, String learning, String status){
        UUID uuid = UUID.randomUUID();//generates a unique identifier
        this.capsuleID = uuid.toString();
        this.description = description;
        this.capsuleType = capsuleType;
        this.collaborator = new Collaborator(collaboratorName, collaboratorPosition);
        this.learning = learning;
        this.hashtag = extractKeywords(description, learning);
        this.URL = "";
        this.status = status;
    }   
    
    public String getURL() {
        return URL;
    }

    public String getLearning() {
        return learning;
    }

    public String getDescription() {
        return description;
    }
    
    public String getCapsuleID() {
        return capsuleID;
    }

    public Calendar getApprovalDate() {
        return approvalDate;
    }

    public String getCapsuleType() {
        return capsuleType;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public ArrayList<String> getHashtag() {
        return hashtag;
    }

    public String getStatus() {
        return status;
    }

    public void setCapsuleID(String capsuleID) {
        this.capsuleID = capsuleID;
    }

    public String setURL() {
        StringBuilder html = new StringBuilder();
        String msg = "";
        // Add HTML tags to create the web page
        html.append("<html><head><title>Capsule Details</title></head><body>");
        
        // Add HTML tags to display Capsule attributes
        html.append("<h1>Capsule Details</h1>");
        html.append("<p><b>Capsule ID:</b> ").append(capsuleID).append("</p>");
        html.append("<p><b>Description:</b> ").append(description).append("</p>");
        html.append("<p><b>Capsule Type:</b> ").append(capsuleType).append("</p>");
        html.append("<p><b>Collaborator:</b> ").append(collaborator.getCollaboratorName()).append(" - ").append(collaborator.getCollaboratorPosition()).append("</p>");
        html.append("<p><b>Learning:</b> ").append(learning).append("</p>");
        html.append("<p><b>Status:</b> ").append(status).append("</p>");
        html.append("<p><b>Approval Date:</b> ").append(approvalDate != null ? approvalDate.getTime().toString() : "N/A").append("</p>");
    
        // Adds HTML tags to close the web page
        html.append("</body></html>");
    
        // Converts the StringBuilder to a text string, and initializes the URL
        this.URL = "data:text/html," + html.toString();
        msg = "Capsule successfully published!!!, the URL of the capsule is: " +  URL;
        return msg;
    }
    
    

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCapsuleType(String capsuleType) {
        this.capsuleType = capsuleType;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String setStatus() {
        String msg = "The capsule was previously approved";
        if (this.status.equals("Under review")) {
            setApprovalDate();
            this.status = "Approved";
            msg = "The capsule was successfully approved";
        } 
        return msg;
    }

    public void setApprovalDate() {
        this.approvalDate = Calendar.getInstance();

    }

    public static ArrayList<String> extractKeywords(String description, String learning) {
        ArrayList<String> keyWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("#(.*?)#"); //the regular expression "#(.*?)#" is used, which means that it will 
        //will look for any string that starts with "#" and ends with "#", and that contains any number of characters between 
        //the two "#" symbols.
    
        Matcher matcher = pattern.matcher(description);
        while (matcher.find()) {
            keyWords.add(matcher.group(1)); //add keyword found
        }
    
        matcher = pattern.matcher(learning);
        while (matcher.find()) {
            keyWords.add(matcher.group(1)); // add keyword found
        }
    
        return keyWords;
    }

}
