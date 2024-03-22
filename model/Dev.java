package model;

import java.time.LocalDate;

public class Dev extends Employee {

    private int doneTaskNumber;

    public Dev(String role, String empId, String account, LocalDate workStartingDate, double productivityScore,
            int doneTaskNumber) {
        super(role, empId, account, workStartingDate, productivityScore);
        this.doneTaskNumber = doneTaskNumber;
    }

    public Dev() {
        super();
    }

    public int getDoneTaskNumber() {
        return doneTaskNumber;
    }

    public void setDoneTaskNumber(int doneTaskNumber) {
        this.doneTaskNumber = doneTaskNumber;
    }

    @Override
    public String toString() {
        return "Dev { " + super.toString() + ", " + " số task đã làm xong = " + doneTaskNumber + " }";
    }

    @Override
    public double calMonthlyIncome() {
        return (double) this.getDoneTaskNumber() * 1500000 + this.getRewardSalary();

    }

    @Override
    public double calReward() {
        return (double) this.getProductivityScore() * 3000000;
    }
}
