package nested.anonymous;

import java.util.Date;

interface SafetyCheck {
    void check();
}

public class Car {
    private String model;
    private boolean isStarted = false;
    private static int soldCount = 0;
    private boolean isSafe = false;

    public Car(String model) {
        soldCount++;
        CarStatus.lastModel = model;
        this.model = model;
    }

    public static class CarStatus {
        static private String lastModel;

        public static void showStatus() {
            System.out.println("지금까지 팔린 차는 : " + soldCount);
            System.out.println("지금 차는 : " + lastModel);
        }
    }

    private class Engine {
        void start() {
            isStarted = true;
            System.out.println("엔진을 켭니다");
        }
    }

    public void drive() {
        class DriveLog {
            private Date date;
            private int count = 0;

            DriveLog(Date date) {
                this.date = date;
            }

            void log() {
                count++;
                System.out.println("[기록] " + model + " " + date.toString() + " 에 운전 시작, " + count + "번째 로그");
            }
        }

        SafetyCheck check1 = new SafetyCheck() {
            @Override
            public void check() {
                System.out.println("[점검 1]" + model + " 타이어 공기압 확인");
            }
        };
        check1.check();

        SafetyCheck check2 = new SafetyCheck() {
            @Override
            public void check() {
                System.out.println("[점검 2]" + model + " 타이어 공기압 확인");
                isSafe = true;
            }
        };
        check2.check();

        if(isSafe == false) return;

        Engine engine = new Engine();
        engine.start();

        DriveLog driveLog = new DriveLog(new Date());
        driveLog.log();
        driveLog.log();

        if (isStarted == true) {
            System.out.println("차를 운전 합니다");
        } else {
            System.out.println("엔진을 켜주세요");
        }
    }
}
