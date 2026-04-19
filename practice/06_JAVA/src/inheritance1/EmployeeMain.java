package inheritance1;

public class EmployeeMain {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.goToWork();
        employee.getSalary();

        Intern intern = new Intern();
        intern.goToWork();
        intern.getSalary();
        intern.threeMonthLater();

        Manager manager = new Manager();
        manager.goToWork();
        manager.getSalary();
        manager.oneYearLater();

        Assistant assistant = new Assistant();
        assistant.goToWork();
        assistant.getSalary();
        assistant.sixMonthLater();
    }
}
