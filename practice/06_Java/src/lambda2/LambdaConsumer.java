package lambda2;

import java.util.function.Consumer;

public class LambdaConsumer {
    public static void main(String[] args) {
        Consumer<String> cons = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("익명 클래스로 전달 받은 " + s);
            }
        };
        cons.accept("한 동안 놉니다!!");

        Consumer<String> cons2 = (s) -> System.out.println("람다로 전달 받은" + s);
        cons2.accept("다음주 화요일 까지 잇힝!");

        Consumer<String> cons3 = System.out::println;
        cons3.accept("앞 만 보고 가실게요!");
    }
}
