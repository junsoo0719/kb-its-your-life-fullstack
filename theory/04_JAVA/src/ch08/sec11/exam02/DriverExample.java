package ch08.sec11.exam02;

import java.util.Scanner;

public class DriverExample {
    public static void main(String[] args) {
        Driver driver = new Driver();

        Bus bus = new Bus();
        Taxi taxi = new Taxi();

        driver.drive(bus);
        driver.drive(taxi);

        // 사용자에게 메뉴 제시
        // 운전할 차를 선택하세요. 1) Taxi, 2) Bus, 3) Truck
        // 운전자의 선택에 따라 실제 운전
        Scanner scanner = new Scanner(System.in);
        System.out.println("운전할 차를 선택하세요. 1) Taxi, 2) Bus, 3) Truck");
        int vehicleNum = scanner.nextInt();
        Vehicle vehicleCars[] = {
                new Taxi(),
                new Bus(),
                new Truck()
        };
        vehicleCars[vehicleNum - 1].run();

//        if (vehicleNum == 1) {
//            driver.drive(new Taxi());
//        } else if (vehicleNum == 2) {
//            driver.drive(new Bus());
//        } else {
//            System.out.println("잘못된 선택입니다.");
//        }
    }
}
