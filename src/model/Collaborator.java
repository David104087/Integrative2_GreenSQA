package model;

public class Collaborator {
    private String collaboratorName;
    private String collaboratorPosition;

    public Collaborator(String collaboratorName, String collaboratorPosition){
        this.collaboratorName = collaboratorName;
        this.collaboratorPosition = collaboratorPosition;
    }

    public String getCollaboratorName() {
        return collaboratorName;
    }

    public String getCollaboratorPosition() {
        return collaboratorPosition;
    }

    
}
