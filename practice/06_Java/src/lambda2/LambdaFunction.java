package lambda2;

import java.util.function.Function;

public class LambdaFunction {
    public static void main(String[] args) {
        Function<Integer, String> func = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return integer.toString();
            }
        };

        System.out.println(func.apply(1118));

        Function<Integer, String> func2 = (i) -> i.toString();
        System.out.println(func2.apply(1118).getClass().getName());

        Function<Integer, String> func3 = Object::toString;
        System.out.println(func2.apply(1118).getClass().getName());
    }
}
