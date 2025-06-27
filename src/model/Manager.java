package model;

public class Manager extends Employee {
    private int subordinatesNum;

    public Manager(String id, String name, String phone, int workDays, double dailyWage, int subordinatesNum) {
        super(id, name, phone, workDays, dailyWage);
        this.subordinatesNum = subordinatesNum;
    }

    @Override
    public double calculateSalary() {
        return baseSalary() + 100 * subordinatesNum;
    }

}
