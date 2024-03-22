package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Employee;
import model.Leader;

public class LeaderController extends EmployeeController {
    ValidateController validate = new ValidateController();
    ArrayList<Employee> employee_list = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    private Leader model;

    public LeaderController(Leader model) {
        this.model = model;
    }

    public LeaderController() {
        super();
    }

    public void setRewardSalary(double rewardSalary) {
        model.setRewardSalary(calReward());
    }

    public void setMonthlyIncome(double monthlyIncome) {
        model.setMonthlyIncome(monthlyIncome);
    }

    public void setAllowance(double allowance) {
        model.setAllowance(allowance);
    }

    public double calAllowance() {
        return model.calAllowance();
    }

    @Override
    public double calReward() {
        return model.calReward();
    }

    @Override
    public double calMonthlyIncome() {
        return model.calMonthlyIncome();
    }

    @Override
    public void updateSalaryFunction() {
        model.setAllowance(calAllowance());
        model.setRewardSalary(calReward());
        model.setMonthlyIncome(calMonthlyIncome());
    }

    @Override
    public void addEmployee(ArrayList<Employee> employee_list) {
        MainController controller = new MainController();
        Employee employee = new Leader();

        boolean flag = false;
        while (flag == false) {
            try {
                boolean isInvalid = false;
                System.out.println("Leader: ");

                String empId = null;
                do {
                    isInvalid = false;

                    try {
                        System.out.print("Enter ID (starts with MNV and must have 6 characters): ");
                        empId = String.valueOf(sc.nextLine()).toUpperCase();
                        if (!validate.isValidEmpId(empId)) {
                            System.out.println(
                                    "Employee ID is not in the correct format (must start with MNV and have 6 characters). Please enter again!!!");
                            isInvalid = true;
                            continue;
                        }
                        if (validate.isExistedID(employee_list, empId)) {
                            System.out.println("The employee ID existed. Please enter again!!!");
                            isInvalid = true;
                            continue;
                        }
                    } catch (Exception e) {
                        isInvalid = true;
                    }
                } while (isInvalid == true);

                String account = null;
                do {
                    isInvalid = false;

                    try {
                        System.out.print("Enter account: ");
                        account = String.valueOf(sc.nextLine());
                        if (validate.isInvalidAccount(account)) {
                            System.out.println("Account cannot be empty!!!");
                            isInvalid = true;
                        }
                    } catch (Exception e) {
                        isInvalid = true;
                    }
                } while (isInvalid == true);

                LocalDate workStartingDate = null;
                do {
                    isInvalid = false;
                    try {
                        System.out.print("Enter the date of joining the company (dd/mm/yyyy): ");
                        String dateString = String.valueOf(sc.nextLine());
                        workStartingDate = LocalDate.parse(dateString,
                                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        if (workStartingDate.isAfter(LocalDate.now())) {
                            System.out.println(
                                    "The date of joining the company must be less than the current date. Please enter again!!!");
                            isInvalid = true;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("The date you entered is incorrect. Please enter again!!!");
                        isInvalid = true;
                    } catch (Exception e) {
                        System.out.println("The date you entered is incorrect. Please enter again!!!");
                        isInvalid = true;
                    }

                } while (isInvalid == true);
                double productivityScore = 0;
                do {
                    isInvalid = false;

                    try {
                        System.out.print("Enter productivity score ( 0.8 <= x <= 1.2 ): ");
                        productivityScore = Double.parseDouble(sc.nextLine());
                        if (!validate.isValidProductivity(productivityScore)) {
                            System.out.println("Productivity score must be from 0.8 to 1.2");
                            isInvalid = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("You entered incorrect. Please enter again!!!");
                        isInvalid = true;
                    }

                } while (isInvalid == true);
                int taskDone = 0;
                do {
                    isInvalid = false;

                    try {
                        System.out.print("Enter the number of reviewed tasks: ");
                        taskDone = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You must enter an integer. Please enter again!!!");
                        isInvalid = true;
                    }
                } while (isInvalid == true);
                int taskSpecified = 0;
                do {
                    isInvalid = false;
                    try {
                        System.out.print("Enter the number of supported tasks: ");
                        taskSpecified = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("You must enter an integer. Please enter again!!!");
                        isInvalid = true;
                    } catch (Exception e) {
                        System.out.println("There was an error from the system! Please re-enter!!!");
                        isInvalid = true;
                    }
                } while (isInvalid == true);
                flag = true;
                employee = new Leader("2", empId, account, workStartingDate, productivityScore, taskDone,
                        taskSpecified);
                // controller.calculate(empId);
                employee_list.add(employee);
                try {
                    // Ghi ra file
                    controller.writeEmployeedata(employee_list);
                } catch (IOException e) {
                    System.out.println("There is a system error, file recording failed!!!");
                    System.out.println("Create a failed object!!!");
                }
            } catch (Exception e) {
                System.out.println("There was an error from the system! Please re-enter!!!");
            }
            controller.created();
        }
    }
}
