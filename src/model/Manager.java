package model;

/**
 * The Manager class represents a person associated with a project, who has a name and phone number.
 */
public class Manager {

    private String managerName;
    private String managerPhone;

    /**
     * Constructs a Manager with the specified name and phone number.
     *
     * @param managerName the name of the manager.
     * @param managerPhone the phone number of the manager.
     */
    public Manager(String managerName, String managerPhone) {
        this.managerName = managerName;
        this.managerPhone = managerPhone;
    }

    /**
     * Returns the name of the manager.
     *
     * @return the name of the manager.
     */
    public String getManagerName() {
        return managerName;
    }

    /**
     * Returns the phone number of the manager.
     *
     * @return the phone number of the manager.
     */
    public String getManagerPhone() {
        return managerPhone;
    }

}







