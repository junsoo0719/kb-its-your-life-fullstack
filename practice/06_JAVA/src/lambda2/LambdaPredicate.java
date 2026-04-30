package lambda2;

import java.util.function.Predicate;

public class LambdaPredicate {
    public static void main(String[] args) {
        Predicate<String> predi = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };

        System.out.println(predi.test(""));
        System.out.println(predi.test("앞 만 보고 가실게요~!"));

        Predicate<String> predi2 = (s) -> s.isEmpty();
        System.out.println(predi2.test(""));
        System.out.println(predi2.test("앞 만 보고 가실게요~!"));
    }
}
