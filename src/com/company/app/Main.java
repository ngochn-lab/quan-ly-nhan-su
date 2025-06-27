package com.company.app;

import com.company.model.*;
import com.company.service.Company;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Company c = new Company();

        boolean running = true;
        while (running) {
            showMenu();
            int ch = Integer.parseInt(sc.nextLine());
            switch (ch) {
                case 1 -> {
                    System.out.print("Tên công ty: ");
                    c.setName(sc.nextLine());
                    System.out.print("Mã số thuế: ");
                    c.setTaxCode(sc.nextLine());
                    System.out.print("Doanh thu tháng: ");
                    c.setMonthlyRevenue(Double.parseDouble(sc.nextLine()));
                    System.out.println("Đã lưu thông tin công ty.");
                }
                case 2 -> {
                    System.out.print("Mã NV cần phân bổ: ");
                    String eid = sc.nextLine();
                    System.out.print("Mã TP quản lý: ");
                    String mid = sc.nextLine();
                    c.assignToManager(eid, mid);
                }
                case 3 -> {
                    System.out.print("1-Thêm  2-Xóa nhân sự: ");
                    int op = Integer.parseInt(sc.nextLine());
                    if (op == 1) {
                        // thêm
                        System.out.print("Loại (1-NV Thường,2-TP,3-GĐ): ");
                        int type = Integer.parseInt(sc.nextLine());
                        System.out.print("Mã NV: ");   String id   = sc.nextLine();
                        System.out.print("Họ Tên: ");  String name = sc.nextLine();
                        System.out.print("SĐT: ");     String phone= sc.nextLine();
                        System.out.print("Số ngày: "); int days  = Integer.parseInt(sc.nextLine());
                        Employee e = switch (type) {
                            case 1 -> new RegularEmployee(id,name,phone,days);
                            case 2 -> new Manager(id,name,phone,days);
                            case 3 -> {
                                System.out.print("Phần trăm CP: ");
                                double pct = Double.parseDouble(sc.nextLine());
                                yield new Director(id,name,phone,days,pct);
                            }
                            default -> { System.out.println("Loại không hợp lệ."); yield null; }
                        };
                        if (e != null) c.addEmployee(e);
                    } else {
                        // xóa
                        System.out.print("Mã NV cần xóa: ");
                        c.removeEmployeeById(sc.nextLine());
                    }
                }
                case 4 -> c.printAllEmployees();
                case 5 -> System.out.println("➤ Tổng lương: " + c.totalSalaryExpense());
                case 6 -> c.findTopRegular();
                case 7 -> c.findTopManager();
                case 8 -> { c.sortByName();  c.printAllEmployees(); }
                case 9 -> { c.sortBySalaryDesc();  c.printAllEmployees(); }
                case 10-> c.findTopDirector();
                case 11-> c.printDirectorsIncome();
                case 0 -> running = false;
                default-> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
        sc.close();
    }

    private static void showMenu() {
        System.out.println(
                "\n=== MENU CHỨC NĂNG ===\n" +
                        "1. Nhập thông tin công ty\n" +
                        "2. Phân bổ Nhân viên vào Trưởng phòng\n" +
                        "3. Thêm/Xóa nhân sự\n" +
                        "4. Xuất toàn bộ nhân sự\n" +
                        "5. Tính tổng lương công ty\n" +
                        "6. NV thường có lương cao nhất\n" +
                        "7. TP có nhiều NV dưới quyền nhất\n" +
                        "8. Sắp xếp theo tên (ABC)\n" +
                        "9. Sắp xếp theo lương giảm dần\n" +
                        "10. Tìm Giám Đốc nhiều cổ phần nhất\n" +
                        "11. Xuất thu nhập của từng Giám Đốc\n" +
                        "0. Thoát\n" +
                        "Chọn: "
        );
    }
}
