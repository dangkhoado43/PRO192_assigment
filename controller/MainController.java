package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import model.Employee;
import model.Dev;
import model.Leader;
import model.Management;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainController {

    Scanner sc = new Scanner(System.in);
    ArrayList<Employee> employee_list = new ArrayList<>();
    Dev dev = new Dev();

    Management management = new Management();
    Leader leader = new Leader();

    DevController dev_controller = new DevController(dev);
    LeaderController leader_controller = new LeaderController(leader);
    ManagementController management_controller = new ManagementController(management);
    // EmployeeController employeeController = null;
    ValidateController validate = new ValidateController();

    public ArrayList<Employee> inputFile() throws Exception {
        try {
            File file = new File("D:/java_program/assignment_pro192_se17c02/assignment_03/file/data.csv");
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line = reader.readLine();

                while (line != null) {
                    if (line.trim().isEmpty()) {
                        line = reader.readLine(); // read next line
                        continue;
                    }
                    boolean flag = false;
                    try {
                        String[] data = line.split(",");
                        // Get info
                        String role = data[0].trim();
                        String empId = data[1].trim();
                        String account = data[2].trim();
                        String dateString = data[3].trim();
                        double productivityScore = Double.parseDouble(data[4].trim());
                        Integer taskDone = Integer.parseInt(data[5].trim());
                        Integer taskSpecified;

                        // Validate
                        validateEmployee(line, role, empId, account, dateString, productivityScore);

                        LocalDate workStartingDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        Employee employee = null;
                        // Get employee and add
                        switch (role) {
                            case "1":
                                taskSpecified = Integer.parseInt(data[6].trim());
                                employee = new Management(role, empId, account, workStartingDate, productivityScore,
                                        taskDone, taskSpecified);
                                break;
                            case "2":
                                taskSpecified = Integer.parseInt(data[6].trim());
                                employee = new Leader(role, empId, account, workStartingDate,
                                        productivityScore, taskDone, taskSpecified);
                                break;
                            case "3":
                                employee = new Dev(role, empId, account, workStartingDate,
                                        productivityScore, taskDone);
                                break;
                        }
                        employee_list.add(employee);
                        flag = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Các dữ liệu riêng cho nhân viên có lỗi, vui lòng nhập lại: " + line);
                        throw e;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Dữ liệu có lỗi, vui lòng kiểm tra và nhập lại");
                        throw e;
                    } catch (Exception e) {
                        throw e;
                    }
                    if (flag == true) {
                        line = reader.readLine();
                    } else {
                        break;
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Xin lỗi, có lỗi khi đọc dữ liệu");
            throw e;
        }
        return employee_list;
    }

    private void validateEmployee(String line, String role, String empId, String account, String dateString,
            double productivityScore) throws Exception {
        try {
            if (!validate.isValidRole(role)) {
                System.out.println("Role không hợp lệ tại dòng: " + line + ". Vui lòng nhập lại");
                throw new Exception();
            }
            if (!validate.isValidEmpId(empId)) {
                System.out.println("Mã nhân viên không đúng format (phải bắt đầu bằng MNV và có 6 ký tự) tại dòng: "
                        + line + ". Vui lòng nhập lại");
                throw new Exception();
            }
            if (validate.isExistedID(employee_list, empId)) {
                System.out.println("Xin lỗi, các nhân viên có mã ID " + empId + " có trùng lặp, vui lòng nhập lại");
                throw new Exception();
            }
            if (validate.isInvalidAccount(account)) {
                System.out.println("Account không hợp lệ tại dòng: " + line + ". Vui lòng nhập lại");
                throw new Exception();
            }
            if (!validate.isValidProductivity(productivityScore)) {
                System.out.println("Năng suất phải nằm trong khoảng 0.8 đến 1.2, vui lòng nhập lại: " + line);
                throw new Exception();
            }
            if (!validate.isValidDate(dateString, line)) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void writeEmployeedata(ArrayList<Employee> employee_list) throws IOException {
        PrintWriter writer = new PrintWriter("D:/java_program/assignment_pro192_se17c02/assignment_03/file/data.csv",
                "UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (Employee e : employee_list) {
            String formattedDate = e.getWorkStartingDate().format(formatter);
            if (e.getRole().equals("1")) {
                writer.println(((Management) e).getRole() + "," + ((Management) e).getEmpId() + ","
                        + ((Management) e).getAccount() + "," + formattedDate + ","
                        + ((Management) e).getProductivityScore() + "," + ((Management) e).getResolveIssueNumber() + ","
                        + ((Management) e).getOtherTaskNumber());
            }
            if (e.getRole().equals("2")) {
                writer.println(((Leader) e).getRole() + "," + ((Leader) e).getEmpId() + "," + ((Leader) e).getAccount()
                        + "," + formattedDate + "," + ((Leader) e).getProductivityScore() + ","
                        + ((Leader) e).getReviewTaskNumber() + "," + ((Leader) e).getSupportTaskNumber());
            }
            if (e.getRole().equals("3")) {
                writer.println(((Dev) e).getRole() + "," + ((Dev) e).getEmpId() + "," + ((Dev) e).getAccount() + ","
                        + formattedDate + "," + ((Dev) e).getProductivityScore() + "," + ((Dev) e).getDoneTaskNumber());
            }
        }
        writer.close();
    }

    public void sortByIncomeAndAccount(ArrayList<Employee> employee_list) {
        if (!employee_list.isEmpty()) {
            Collections.sort(employee_list, (Employee e1, Employee e2) -> {
                int incomeCmp = Double.compare(e1.getMonthlyIncome(), e2.getMonthlyIncome());
                if (incomeCmp != 0) {
                    return incomeCmp;
                }
                return e1.getAccount().compareToIgnoreCase(e2.getAccount());
            });
            displayEmployee(employee_list);
        } else {
            System.out.println(
                    "No employees at the moment. Please add employee to make it happen.");
        }
    }

    public void sortByRoleAndEmpId(ArrayList<Employee> employee_list) {
        if (!employee_list.isEmpty()) {
            Collections.sort(employee_list, (Employee e1, Employee e2) -> {
                int roleCmp = e1.getRole().compareToIgnoreCase(e2.getRole());
                if (roleCmp != 0) {
                    return roleCmp;
                }
                return e1.getEmpId().compareToIgnoreCase(e2.getEmpId());
            });
            displayEmployee(employee_list);
        } else {
            System.out.println("No employees at the moment. Please add employee to make it happen.");
        }
    }

    public void calculateSalaryByID(ArrayList<Employee> employee_list) {
        boolean flag = true;
        System.out.print("SALARY CALCULATION SYSTEM FOR EMPLOYEES!");
        System.out.print("Enter employee ID: ");
        String empId = sc.nextLine();

        Employee employee = this.findEmployeeById(empId);

        if (employee != null) {
            flag = false;
            EmployeeController controller = createControllerForEmployee(employee);
            System.out.println("Employee with ID: " + empId.toUpperCase());
            System.out.println("Employee information: ");
            System.out.println(employee);
            controller.updateSalaryFunction();
            System.out.println("Monthly Income : " + String.format("%.0f", employee.getMonthlyIncome()));
            System.out.println("Reward salary per month: " + String.format("%.0f", employee.getRewardSalary()));
            switch (employee.getRole()) {
                case "1":
                    System.out.println("Allowance: " + String.format("%.0f", ((Management) employee).getAllowance()));
                    break;
                case "2":
                    System.out.println("Allowance: " + String.format("%.0f", ((Leader) employee).getAllowance()));
                    break;
            }
            updated();
        }

        if (flag == true) {
            System.out.println("Sorry, the employee ID doesn't seem to match the data");
        }
    }

    private Employee findEmployeeById(String empId) {
        for (Employee employee : employee_list) {
            if (empId.equalsIgnoreCase(employee.getEmpId())) {
                return employee;
            }
        }
        return null;
    }

    private EmployeeController createControllerForEmployee(Employee employee) {
        switch (employee.getRole()) {
            case "1":
                return new ManagementController((Management) employee);
            case "2":
                return new LeaderController((Leader) employee);
            case "3":
                return new DevController((Dev) employee);
            default:
                throw new IllegalArgumentException("No role existed: " + employee.getRole());
        }
    }

    public void displayEmployee(ArrayList<Employee> employee_list) {
        // employee_list.forEach((employee) -> {
        //     System.out.println(employee);
        // });
        for(Employee employee : employee_list) {
            System.out.println(employee);
        }
    }

    public void created() {
        System.out.println();
        System.out.println("Create a object successfully!");
    }

    public void updated() {
        System.out.println();
        System.out.println("Update date successfully!");
    }

}
