package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import model.Employee;

public class ValidateController {

    public ValidateController() {
    }

    public boolean isExistedID(ArrayList<Employee> employee_list, String empId) {
        for (Employee employee : employee_list) {
            if (empId.equalsIgnoreCase(employee.getEmpId())) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidRole(String role) {
        try {
            if (role == null || role.isEmpty()) {
                return false;
            }
            int intValue = Integer.parseInt(role);             
            // if (intValue >= 1 && intValue <= 3) {
            return intValue >= 1 && intValue <= 3;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isValidProductivity(double productivityScore) {
        try {
            if (String.valueOf(productivityScore) == null || String.valueOf(productivityScore).isEmpty()) {
                return false;
            }
            return productivityScore >= 0.8 && productivityScore <= 1.2;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isInvalidAccount(String account) {
        return (account == null || account.isEmpty());
    }

    public boolean isValidEmpId(String empId) {
        return (empId != null && !empId.isEmpty() && empId.length() == 6 && empId.startsWith("MNV"));

    }

    public boolean isValidDate(String dateString, String line) throws Exception {
        try {
            LocalDate workStartingDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (workStartingDate.isAfter(LocalDate.now())) {
                System.out.println("Ngày vào công ty phải nhỏ hơn ngày hiện tại, vui lòng nhập lại: " + line);
                throw new Exception();
            }
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Ngày bắt đầu làm việc không hợp lệ, vui lòng nhập lại: " + line);
            throw new Exception();
        }

    }
}
