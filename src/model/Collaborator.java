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

    public void setCollaboratorName(String collaboratorName) {
        this.collaboratorName = collaboratorName;
    }

    public String getCollaboratorPosition() {
        return collaboratorPosition;
    }

    public void setCollaboratorPosition(String collaboratorPosition) {
        this.collaboratorPosition = collaboratorPosition;
    }
}
