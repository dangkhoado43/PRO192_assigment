package model;

import java.time.LocalDate;

public abstract class Employee {

    private String empId;
    private String account;
    private String role;
    private LocalDate workStartingDate;
    private double productivityScore;
    private double monthlyIncome;
    private double rewardSalary;

    final private int basicRewardSalary = 3000000;

    public Employee() {}

    public Employee(String role, String empId, String account, LocalDate workStartingDate, double productivityScore) {
        this.empId = empId;
        this.account = account;
        this.role = role;
        this.workStartingDate = workStartingDate;
        this.productivityScore = productivityScore;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public double getRewardSalary() {
        return rewardSalary;
    }

    public void setRewardSalary(double rewardSalary) {
        this.rewardSalary = rewardSalary;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getWorkStartingDate() {
        return workStartingDate;
    }

    public void setWorkStartingDate(LocalDate workStartingDate) {
        this.workStartingDate = workStartingDate;
    }

    public double getProductivityScore() {
        return productivityScore;
    }

    public void setProductivityScore(double productivityScore) {
        this.productivityScore = productivityScore;
    }

    public double getMonthlyIncome() {
        return monthlyIncome;
    }

    @Override
    public String toString() {
        return "ID = " + empId + ", account = " + account + ", ngày làm việc = " + workStartingDate + ", năng suất = " + productivityScore + ", thu nhập hằng tháng = " + String.format("%.0f", getMonthlyIncome()) + ", thưởng hằng tháng = " + String.format("%.0f", getRewardSalary());
    }

    public void setMonthlyIncome(double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getBasicRewardSalary() {
        return basicRewardSalary;
    }

    public abstract double calReward();

    public abstract double calMonthlyIncome();
}
