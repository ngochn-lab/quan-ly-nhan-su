package model;

abstract class Employee {
    protected String id;
    protected String name;
    protected String phone;
    protected int workDays;
    protected double dailyWage;

    public Employee(String id, String name, String phone, int workDays, double dailyWage) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.workDays = workDays;
        this.dailyWage = dailyWage;
    }

    /*
     * Lương cơ bản: lương 1 ngày * số ngày làm việc
     */
    protected double baseSalary(){
        return workDays * dailyWage;
    }

    /*
     * Tính lương tháng: mỗi subclass override cách tính riêng của loại nhân viên
     */
    public abstract double calculateSalary();
}
