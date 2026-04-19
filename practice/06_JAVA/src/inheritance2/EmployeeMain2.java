package inheritance2;

public class EmployeeMain2 {
    public static void main(String[] args) {
        Employee employee = new Employee("이대주", 3000000);

        employee.goToWork();
        employee.getSalary();

        Intern intern = new Intern("송준수", 2000000);
    }
}
