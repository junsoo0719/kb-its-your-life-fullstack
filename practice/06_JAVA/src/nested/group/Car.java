package nested.group;

public class Car {
    private String model;
    public boolean isStarted = false;

    public Car(String model) {
        this.model = model;
    }

    public void drive() {
        if (isStarted == true) {
            System.out.println("차를 운전 합니다");
        } else {
            System.out.println("엔진을 켜주세요");
        }
    }
}
