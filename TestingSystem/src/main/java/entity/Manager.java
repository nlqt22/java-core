package entity;

public class Manager extends User{

    private int projectId;
    private int expInYear;

    public Manager(int id, String fullName, String email, int expInYear) {
        super(id, fullName, email, Role.MANAGER);
        this.expInYear = expInYear;
    }

    public Manager(int id, String fullName, String email) {
        super(id, fullName, email, Role.MANAGER);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }
}
