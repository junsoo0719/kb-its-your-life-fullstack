package inheritance2;

public class Intern extends Employee {
    public String name = "인턴 클래스";
    public int salary = 1800000;

    public Intern(String name, int salary) {
        super(name, salary);
        System.out.println("Intern 생성자");
    }

    @Override
    public void goToWork() {
        System.out.println("인턴이 출근 합니다!");
    }

    @Override
    public void getSalary() {
        int salaryGap = super.salary - this.salary;
        System.out.println("인턴이 기본급 보다 " + salaryGap + " 적은 " + this.salary + " 를 받습니다!");
    }

    public void threeMonthLater() {
        System.out.println("이대로 하면 정직원이 되는거죠!?");
    }
}
