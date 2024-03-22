package controller;

import java.util.ArrayList;
import model.Employee;

public abstract class EmployeeController {

    private Employee model;

    ArrayList<Employee> list = new ArrayList<>();

    public EmployeeController(Employee model) {
        this.model = model;
    }

    public EmployeeController() {}

    public abstract void updateSalaryFunction();
    
    public abstract double calReward();

    public abstract double calMonthlyIncome();

    public abstract void addEmployee(ArrayList<Employee> employee_list);
}
