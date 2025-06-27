package com.company.model;

import java.util.*;

public class Manager extends Employee {
    private List<Employee> subordinates = new ArrayList<>();

    public Manager(String id, String name, String phone, int daysWorked) {
        super(id, name, phone, daysWorked, 200);
    }

    public void addSubordinate(Employee e) {
        if (!subordinates.contains(e)) subordinates.add(e);
    }
    public void removeSubordinate(Employee e) {
        subordinates.remove(e);
    }
    public void clearSubordinates() {
        subordinates.clear();
    }
    public List<Employee> getSubordinates() {
        return new ArrayList<>(subordinates);
    }
    public int getSubCount() {
        return subordinates.size();
    }

    @Override
    public double calculateSalary() {
        return salaryPerDay * daysWorked + 100 * getSubCount();
    }
}
