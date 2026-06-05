package lambda;

interface Clickable {
    void click();
}

public class LambdaCant2 {
    public static void main(String[] args) {
        Clickable counter = new Clickable() {
            private int count = 0;

            @Override
            public void click() {
                count++;
                System.out.println("클릭 횟수 : " + count);
            }
        };

        counter.click();
        counter.click();
        counter.click();

        int count = 0;
        Clickable lambdaCounter = () -> {
//            count++;
            System.out.println("클릭 횟수 : " + count);
        };
    }
}
