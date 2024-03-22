package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Leader extends Employee {

    private int reviewTaskNumber;
    private int supportTaskNumber;
    private double allowance;

    public Leader() {
        super();
    }

    public Leader(String role, String empId, String account, LocalDate workStartingDate, double productivityScore, int reviewTaskNumber, int supportTaskNumber) {
        super(role, empId, account, workStartingDate, productivityScore);
        this.reviewTaskNumber = reviewTaskNumber;
        this.supportTaskNumber = supportTaskNumber;
    }

    public int getReviewTaskNumber() {
        return reviewTaskNumber;
    }

    public void setReviewTaskNumber(int reviewTaskNumber) {
        this.reviewTaskNumber = reviewTaskNumber;
    }

    public int getSupportTaskNumber() {
        return supportTaskNumber;
    }

    public void setSupportTaskNumber(int supportTaskNumber) {
        this.supportTaskNumber = supportTaskNumber;
    }

    public double getAllowance() {
        return allowance;
    }

    public void setAllowance(double allowance) {
        this.allowance = allowance;
    }

    @Override
    public String toString() {
        return "Leader { " + super.toString() + ", " + "số task đã review = " + reviewTaskNumber + ", số task đã support = " + supportTaskNumber + ", phụ cấp = " + String.format("%.0f", getAllowance()) + '}';
    }

//    }
    @Override
    public double calReward() {
        return (double) this.getProductivityScore() * this.getBasicRewardSalary();
    }

    @Override
    public double calMonthlyIncome() {
        return (double) (this.getReviewTaskNumber() * 4000000) + (this.getSupportTaskNumber() * 400000) + this.getRewardSalary() + this.getAllowance();
    }

    public double calAllowance() {
        int months = (int) ChronoUnit.MONTHS.between(this.getWorkStartingDate(), LocalDate.now());
        if (months >= 36) {
           return (double) 2000000 * this.getProductivityScore();
        } else {
            return (double) 1200000 * this.getProductivityScore();
        }
    }
}
