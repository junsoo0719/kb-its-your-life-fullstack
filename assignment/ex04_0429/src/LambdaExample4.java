@FunctionalInterface
interface Calculable4 {
    double calc(double x, double y);
}

class Person4 {
    public void action(Calculable4 calculable) {
        double result = calculable.calc(10, 4);
        System.out.println("결과: " + result);
    }
}

public class LambdaExample4 {
    public static void main(String[] args) {
        Person4 person = new Person4();

        //실행문이 두 개 이상일 경우
        person.action((x, y) -> {
            double result = x + y;
            return result;
        });

        // sum() 메서드를 호출
        person.action((x, y) -> sum(x, y));
    }

    public static double sum(double x, double y) {
        return (x + y);
    }
}
