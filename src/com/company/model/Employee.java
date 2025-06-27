package com.company.model;

public abstract class Employee {
    protected String id, name, phone;
    protected int workDays;
    protected double dailyWage;

    public Employee(String id, String name, String phone, int workDays, double dailyWage) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.workDays = workDays;
        this.dailyWage = dailyWage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /*
     * Lương cơ bản: lương 1 ngày * số ngày làm việc
     */
    protected double baseSalary() {
        return workDays * dailyWage;
    }

    /*
     * Tính lương tháng: mỗi subclass override cách tính riêng của loại nhân viên
     */
    public abstract double calculateSalary();

    public void printInfo() {
        System.out.printf("%-5s | %-15s | %-10s | %2d days | %.0f/day | Salary: %.2f\n",
                id, name, phone, workDays, dailyWage, calculateSalary());
    }
}
