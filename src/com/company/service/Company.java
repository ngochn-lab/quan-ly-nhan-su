package com.company.service;

import com.company.model.Employee;

import java.util.*;

public class Company {
    private String name;    // Tên công ty
    private String taxCode; // Mã số thuế
    private double revenue; //
    private List<Employee> employees = new ArrayList<>(); // List nhân viên

    private static Company instance;
    public static Company getInstance() {
        if (instance == null) instance = new Company();
        return instance;
    }

    private Company() {}

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void removeEmployee(String id) {
        this.employees.removeIf(employee -> employee.getId().equals(id));
    }

    /*
     *   Tính chi phí trả lương của công ty
     */
    public double totalSalaryExpense() {
        return this.employees.stream()
                            .mapToDouble(Employee::calculateSalary)
                            .sum();
    }

    /*  Tính doanh thu
     *   Doanh thu = Lợi Nhuận - Doanh Thu
     */
    public double calculateProfit() {
        return revenue - totalSalaryExpense(); // Lợi Nhuận
    }
}
