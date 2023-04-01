package model;
import java.util.*;
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
    private String status;//en revision o aprobada
    private Calendar approvalDate;

    public Capsule(String description, String capsuleType, String collaboratorName, String collaboratorPosition, String learning, String status){
        UUID uuid = UUID.randomUUID();//luego se debe imprimir ese idetificador
        // Almacenar el identificador único en un String
        this.capsuleID = uuid.toString();;
        this.description = description;
        this.capsuleType = capsuleType;
        this.collaborator = new Collaborator(collaboratorName, collaboratorPosition);
        this.learning = learning;
        this.hashtag = extractKeywords(description, learning);
        this.status = status;// cuando se crea debe quedar en "underReview"
    }   
    
    public static ArrayList<String> extractKeywords(String description, String learning) {
        ArrayList<String> keyWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("#(.*?)#"); // expresión regular para buscar palabras clave
    
        Matcher matcher = pattern.matcher(description);
        while (matcher.find()) {
            keyWords.add(matcher.group(1)); // agregar palabra clave encontrada
        }
    
        matcher = pattern.matcher(learning);
        while (matcher.find()) {
            keyWords.add(matcher.group(1)); // agregar palabra clave encontrada
        }
    
        return keyWords;
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

    public ArrayList<String> getHashtag() {
        return hashtag;
    }

    public String getStatus() {
        return status;
    }

}
