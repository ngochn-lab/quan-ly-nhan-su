package com.company.model;

public class Director extends Employee {
    private double sharePercent;

    public Director(String id, String name, String phone, int workDays, double dailyWage) {
        super(id, name, phone, workDays, dailyWage);
    }

    @Override
    protected double baseSalary() {
        return dailyWage;
    }

    @Override
    public double calculateSalary() {
        return baseSalary();
    }
}
