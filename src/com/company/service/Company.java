package com.company.service;

import com.company.model.*;
import java.util.*;
import java.util.stream.*;

public class Company {
    private String name, taxCode;
    private double monthlyRevenue;
    private List<Employee> employees = new ArrayList<>();

    // 1. Nhập thông tin công ty
    public void setName(String name)                   { this.name = name; }
    public void setTaxCode(String taxCode)             { this.taxCode = taxCode; }
    public void setMonthlyRevenue(double monthlyRevenue){ this.monthlyRevenue = monthlyRevenue; }

    // 3a. Thêm nhân sự
    public void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("Đã thêm: " + e.getId() + " - " + e.getName());
    }

    // 2. Phân bổ NV vào TP
    public void assignToManager(String empId, String mgrId) {
        Employee emp = findById(empId);
        Employee mgr = findById(mgrId);
        if (emp == null || !(mgr instanceof Manager)) {
            System.out.println("Sai mã NV hoặc không phải Trưởng phòng.");
            return;
        }
        // Gỡ khỏi TP cũ (nếu có)
        employees.stream()
                .filter(e->e instanceof Manager)
                .map(e->(Manager)e)
                .forEach(m -> m.removeSubordinate(emp));

        ((Manager)mgr).addSubordinate(emp);
        System.out.println("Gán " + empId + " cho Trưởng phòng " + mgrId);
    }

    // 3b. Xóa nhân sự
    public void removeEmployeeById(String id) {
        Employee e = findById(id);
        if (e == null) {
            System.out.println("Không tìm thấy NV " + id);
            return;
        }
        // Nếu xóa TP, ngắt liên kết subordinates
        if (e instanceof Manager) {
            ((Manager)e).clearSubordinates();
        }
        employees.remove(e);
        System.out.println("Đã xóa NV " + id);
    }

    // hỗ trợ tìm NV
    private Employee findById(String id) {
        return employees.stream()
                .filter(e->e.getId().equalsIgnoreCase(id))
                .findFirst().orElse(null);
    }

    // 4. Xuất thông tin toàn bộ
    public void printAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Chưa có nhân sự nào.");
            return;
        }
        System.out.println("\n--- DANH SÁCH NHÂN SỰ ---");
        System.out.printf("%-3s | %-5s | %-15s | %-12s | %4s | %10s | %12s\n",
                "STT","Mã NV","Họ Tên","SĐT","Ngày","L/ngày","Tổng lương");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < employees.size(); i++) {
            employees.get(i).printInfo(i+1);
        }
    }

    // 5. Tổng chi phí lương
    public double totalSalaryExpense() {
        return employees.stream()
                .mapToDouble(Employee::calculateSalary)
                .sum();
    }

    // 6. NV thường có lương cao nhất
    public void findTopRegular() {
        employees.stream()
                .filter(e->e instanceof RegularEmployee)
                .max(Comparator.comparingDouble(Employee::calculateSalary))
                .ifPresentOrElse(
                        e-> {
                            System.out.println("NV Thường lương cao nhất:");
                            printSingleEmployee(e);
                        },
                        ()-> System.out.println("Không có NV Thường.")
                );
    }

    // 7. TP có nhiều NV dưới quyền nhất
    public void findTopManager() {
        employees.stream()
                .filter(e->e instanceof Manager)
                .map(e->(Manager)e)
                .max(Comparator.comparingInt(Manager::getSubCount))
                .ifPresentOrElse(
                        m-> {
                            System.out.println("TP nhiều NV dưới quyền nhất:");
                            printSingleEmployee(m);
                        },
                        ()-> System.out.println("Không có Trưởng Phòng.")
                );
    }

    // 8. Sắp xếp theo ABC tên
    public void sortByName() {
        employees.sort(Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER));
        System.out.println("Đã sắp xếp theo tên (ABC).");
    }

    // 9. Sắp xếp theo lương giảm dần
    public void sortBySalaryDesc() {
        employees.sort(Comparator.comparingDouble(Employee::calculateSalary).reversed());
        System.out.println("Sắp xếp theo lương giảm dần.");
    }

    // 10. GD nhiều cổ phần nhất
    public void findTopDirector() {
        employees.stream()
                .filter(e->e instanceof Director)
                .map(e->(Director)e)
                .max(Comparator.comparingDouble(Director::getSharePercent))
                .ifPresentOrElse(
                        d-> {
                            System.out.println("Giám đốc nhiều cổ phần nhất:");
                            printSingleEmployee(d);
                        },
                        ()-> System.out.println("Không có Giám đốc.")
                );
    }

    // 11. Thu nhập từng Giám Đốc
    public void printDirectorsIncome() {
        double profit = monthlyRevenue - totalSalaryExpense();
        System.out.println("\n--- THU NHẬP GIÁM ĐỐC ---");
        System.out.printf("%-5s | %-15s | %-6s | %12s\n",
                "Mã NV","Họ Tên","%CP","Thu nhập");
        System.out.println("-------------------------------------------");
        employees.stream()
                .filter(e->e instanceof Director)
                .map(e->(Director)e)
                .forEach(d -> {
                    double income = d.calculateSalary() + d.getSharePercent()/100 * profit;
                    System.out.printf("%-5s | %-15s | %5.1f%% | %12.2f\n",
                            d.getId(), d.getName(), d.getSharePercent(), income);
                });
    }

    // in 1 nhân sự (khi tìm top)
    private void printSingleEmployee(Employee e) {
        System.out.printf("%-5s | %-15s | SĐT: %-12s | Ngày: %2d | L/ngày: %.0f | Tổng: %.2f\n",
                e.getId(), e.getName(), e.getPhone(),
                e.getDaysWorked(), e.getSalaryPerDay(), e.calculateSalary());
    }
}
