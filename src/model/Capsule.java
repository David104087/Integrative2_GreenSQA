package model;
import java.util.*;

public class Capsule {
    
    private String capsuleID;
    private String description;
    private String capsuleType;
    private Collaborator collaborator;
    private String learning;
    private String hashtag;
    private String URL;
    private String status;
    private Calendar approvalDate;

    public Capsule(String capsuleID, String description, String capsuleType, String collaboratorName, String collaboratorPosition, String learning, String hashtag, String status){
        this.capsuleID = capsuleID;
        this.description = description;
        this.capsuleType = capsuleType;
        this.collaborator = new Collaborator(collaboratorName, collaboratorPosition);
        this.learning = learning;
        this.hashtag = hashtag;
        this. status = status;// cuando se crea debe quedar en "underReview"
    }    


    // public String publishCapsule{//se debe cambiar el aytributo approvalDate, poner la url y la fecha de aaprobacion 

    // }


    public String getCapsuleID() {
        return capsuleID;
    }

    public void setCapsuleID(String capsuleID) {
        this.capsuleID = capsuleID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCapsuleType() {
        return capsuleType;
    }

    public void setCapsuleType(String capsuleType) {
        this.capsuleType = capsuleType;
    }

    public Collaborator getCollaborator() {
        return collaborator;
    }

    public void setCollaborator(Collaborator collaborator) {
        this.collaborator = collaborator;
    }

    public String getLearning() {
        return learning;
    }

    public void setLearning(String learning) {
        this.learning = learning;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }
}
