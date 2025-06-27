package com.company.model;

public class RegularEmployee extends Employee {
    private Manager manager = null;

    public RegularEmployee(String id, String name, String phone, int workDays, double dailyWage, Manager manager) {
        super(id, name, phone, workDays, dailyWage);
        this.manager = manager;
    }

    @Override
    public double calculateSalary() {
        return baseSalary();
    }
}
