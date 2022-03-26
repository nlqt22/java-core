package entity;

public class Employee extends User{

    private String proSkill;

    public Employee(int id, String fullName, String email, String proSkill) {
        super(id, fullName, email, Role.EMPLOYEE);
        this.proSkill = proSkill;
    }

    public Employee(int id, String fullName, String email) {
        super(id, fullName, email, Role.EMPLOYEE);
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }
}
