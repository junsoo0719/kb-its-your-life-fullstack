package lambda;

interface MathAddSub {
    int operation(int a, int b);
}

public class AnonymousLambda {
    public static void main(String[] args) {
        MathAddSub add = new MathAddSub() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
        System.out.println("익명 클래스를 이용한 더하기 : " + add.operation(10, 20));

        MathAddSub sub = (a, b) -> a - b;
        System.out.println("람다를 이용한 빼기 : " + sub.operation(10, 5));
    }
}
