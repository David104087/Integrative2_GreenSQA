package model;

public class Manager {
    private String managerName;
    private String managerPhone;

    public Manager(String managerName, String managerPhone) {
        this.managerName = managerName;
        this.managerPhone = managerPhone;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }
}
