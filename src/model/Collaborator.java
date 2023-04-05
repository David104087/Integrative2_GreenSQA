package model;

/**
 * The Collaborator class represents a person who collaborated in the creation of a capsule.
*/
public class Collaborator {
    private String collaboratorName;
    private String collaboratorPosition;

    /**
     * Constructs a new Collaborator object with the specified name and position.
     * @param collaboratorName the name of the collaborator
     * @param collaboratorPosition the position of the collaborator
     */
    public Collaborator(String collaboratorName, String collaboratorPosition){
        this.collaboratorName = collaboratorName;
        this.collaboratorPosition = collaboratorPosition;
    }

    /**
     * Returns the name of the collaborator.
     * @return the name of the collaborator
     */
    public String getCollaboratorName() {
        return collaboratorName;
    }

    /**
     * Sets the name of the collaborator.
     * @param collaboratorName the name of the collaborator
     */
    public void setCollaboratorName(String collaboratorName) {
        this.collaboratorName = collaboratorName;
    }

    /**
     * Returns the position of the collaborator.
     * @return the position of the collaborator
     */
    public String getCollaboratorPosition() {
        return collaboratorPosition;
    }

    /**
     * Sets the position of the collaborator.
     * @param collaboratorPosition the position of the collaborator
     */
    public void setCollaboratorPosition(String collaboratorPosition) {
        this.collaboratorPosition = collaboratorPosition;
    }

}
