package nested.staticNested2;

public class CarMain {
    public static void main(String[] args) {
        Car car = new Car("페라리");
        car.drive();

        Car.CarStatus status = new Car.CarStatus(car);
        status.showStatus();
    }
}
