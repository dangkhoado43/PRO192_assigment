package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Management extends Employee {

    private int resolveIssueNumber;
    private int otherTaskNumber;
    private double allowance;

    public Management(String role, String empId, String account, LocalDate workStartingDate, double productivityScore,
            int resolveIssueNumber, int otherTaskNumber) {
        super(role, empId, account, workStartingDate, productivityScore);
        this.resolveIssueNumber = resolveIssueNumber;
        this.otherTaskNumber = otherTaskNumber;
    }

    public Management() {
        super();
    }

    public int getResolveIssueNumber() {
        return resolveIssueNumber;
    }

    public void setResolveIssueNumber(int resolveIssueNumber) {
        this.resolveIssueNumber = resolveIssueNumber;
    }

    public int getOtherTaskNumber() {
        return otherTaskNumber;
    }

    public void setOtherTaskNumber(int otherTaskNumber) {
        this.otherTaskNumber = otherTaskNumber;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    @Override
    public double calReward() {
        return getProductivityScore() * getBasicRewardSalary();

    }

    @Override
    public double calMonthlyIncome() {
        return (getResolveIssueNumber() * 5000000) + (getOtherTaskNumber() * 500000)
                + getRewardSalary() + getAllowance();

    }

    public double calAllowance() {
        int months = (int) ChronoUnit.MONTHS.between(this.getWorkStartingDate(), LocalDate.now());
        if (months >= 36) {
            return ((double) (2000000 * this.getProductivityScore()));
        } else {
            return ((double) (1200000 * this.getProductivityScore()));
        }
    }

    @Override
    public String toString() {
        return "Management {  " + super.toString() + ", " + " số lượng vấn đề giải quyết =" + resolveIssueNumber
                + ", số task thực hiện thêm = " + otherTaskNumber + ", tiền phụ cấp = "
                + String.format("%.0f", getAllowance()) + '}';
    }

}
