package nested.anonymous;

public class CarMain {
    public static void main(String[] args) {
        Car car = new Car("페라리");
        car.drive();

        Car.CarStatus.showStatus();
    }
}
