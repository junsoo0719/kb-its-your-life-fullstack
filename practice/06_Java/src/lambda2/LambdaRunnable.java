package lambda2;

public class LambdaRunnable {
    public static void main(String[] args) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("내일 놉니다!!");
            }
        };
        run.run();

        Runnable run2 = () -> System.out.println("오늘 밤에 취합니다!");
        run2.run();
    }
}
