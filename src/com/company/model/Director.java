package com.company.model;

public class Director extends Employee {
    private double sharePercent;  // 0–100%

    public Director(String id, String name, String phone, int daysWorked, double sharePercent) {
        super(id, name, phone, daysWorked, 300);
        this.sharePercent = Math.min(sharePercent, 100);
    }

    public double getSharePercent() {
        return sharePercent;
    }

    @Override
    public double calculateSalary() {
        return salaryPerDay * daysWorked;
    }
}
