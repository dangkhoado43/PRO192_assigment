package view;

import java.util.ArrayList;

import controller.DevController;
import controller.LeaderController;
import controller.MainController;
import controller.ManagementController;
import model.Dev;
import model.Employee;
import model.Leader;
import model.Management;


public class View extends Menu {

    static MainController main_controller = new MainController();

    static Management management = new Management();
    static Dev dev = new Dev();
    static Leader leader = new Leader();

    static LeaderController leader_controller = new LeaderController(leader);
    static ManagementController management_controller = new ManagementController(management);
    static DevController dev_controller = new DevController(dev);

    static String menu[] = {"Calculate money for employee", "Display a list of employees", "Add employee", "Exit"};

    static ArrayList<Employee> employee_list = new ArrayList<>();

    public void displaySortEmployee() {
        String menu2[] = {"Print a list of employees ascending by monthly income and descending by name",
            "Print a list of employees ascending by title and ascending by ID", "Exit"};
        Menu m = new Menu("DISPLAYING LIST OF EMPLOYEES SYSTEM", menu2) {
            @Override
            public void execute(int n) {
                System.out.println();
                switch (n) {
                    case 1:
                        main_controller.sortByIncomeAndAccount(employee_list);
                        break;
                    case 2:
                        main_controller.sortByRoleAndEmpId(employee_list);
                        break;
                    case 3:
                        break;
                }

            }
        };
        do {
            int choice1 = m.getSelected();
            if (choice1 > menu2.length || choice1 < 1) {
                System.out.println("You must enter 1, 2, 3!!!");
            }
            m.execute(m.getSelected());
        } while (m.getSelected() != 3);
    }

    public void displayAddEmployee() {
        String menu3[] = {"Add a Management", "Add a Leader", "Add a Development", "Exit"};
        Menu m = new Menu("ADD EMPLOYEE SYSTEM", menu3) {
            @Override
            public void execute(int n) {
                System.out.println();
                switch (n) {
                    case 1:
                        management_controller.addEmployee(employee_list);
                        break;
                    case 2:
                        leader_controller.addEmployee(employee_list);
                        break;
                    case 3:
                        dev_controller.addEmployee(employee_list);
                        break;
                    case 4:
                        break;
                }
                System.out.println();

            }
        };
        do {
            int choice2 = m.getSelected();
            if (choice2 > menu3.length || choice2 < 1) {
                System.out.println("You must enter 1, 2, 3, 4!!!");
            }
            m.execute(m.getSelected());
        } while (m.getSelected() != 4);
    }

    public View() {
        super("EMPLOYEE MANAGEMENT SYSTEM", menu);
    }

    public static void main(String[] args) throws Exception {
        View v = new View();
        employee_list = main_controller.inputFile();
        v.run();
    }

    @Override
    public void execute(int n) {
        System.out.println();
        switch (n) {
            case 1:
                // input new data (income, birthday) to update the employee information
                main_controller.calculateSalaryByID(employee_list);
                break;
            case 2:
                displaySortEmployee();
                break;
            case 3:
                displayAddEmployee();
                break;
            case 4:
                System.out.println("Bye!");
                System.exit(0);
            default:
                break;
        }
        System.out.println();
    }

}
