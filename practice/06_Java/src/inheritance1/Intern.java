package inheritance1;

public class Intern extends Employee {
    @Override
    public void goToWork() {
        System.out.println("인턴이 출근 합니다!");
    }

    @Override
    public void getSalary() {
        System.out.println("인턴이 월급을 받습니다!");
    }

    public void threeMonthLater() {
        System.out.println("이대로 하면 정직원이 되는거죠!?");
    }
}
