package com.company.model;

public abstract class Employee {
    protected String id, name, phone;
    protected int daysWorked;
    protected double salaryPerDay;
    private Manager manager;

    public Employee(String id, String name, String phone, int daysWorked, double salaryPerDay) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.daysWorked = daysWorked;
        this.salaryPerDay = salaryPerDay;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getDaysWorked() {
        return daysWorked;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager m) {
        manager = m;
    }

    public double getSalaryPerDay() {
        return salaryPerDay;
    }

    /**
     * Công thức lương theo từng loại
     */
    public abstract double calculateSalary();

    /**
     * In 1 dòng với STT chạy từ Main
     */
    public void printInfo(int stt) {
        System.out.printf("%-3d | %-5s | %-15s | %-12s | %4d | %10.0f | %12.2f\n",
                stt, id, name, phone, daysWorked, salaryPerDay, calculateSalary());
    }
}
