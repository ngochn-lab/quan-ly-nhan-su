package com.company.model;

public class RegularEmployee extends Employee {
    public RegularEmployee(String id, String name, String phone, int daysWorked) {
        super(id, name, phone, daysWorked, 100);
    }

    @Override
    public double calculateSalary() {
        return salaryPerDay * daysWorked;
    }
}
