package nested.inner;

public class Car {
    private String model;
    private boolean isStarted = false;

    public Car(String model) {
        this.model = model;
    }

    private class Engine {
        void start() {
            isStarted = true;
            System.out.println("엔진을 켭니다");
        }
    }

    public void drive() {
        Engine engine = new Engine();
        engine.start();

        if (isStarted == true) {
            System.out.println("차를 운전 합니다");
        } else {
            System.out.println("엔진을 켜주세요");
        }
    }
}
