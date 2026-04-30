package lambda2;

import java.util.function.Supplier;

public class LambdaSupplier {
    public static void main(String[] args) {
        Supplier<String> sup = new Supplier<String>() {
            @Override
            public String get() {
                return "익명 클래스 보급";
            }
        };

        String str = sup.get();
        System.out.println(str);

        Supplier<String> sup2 = () -> "람다 보급!";
        String str2 = sup2.get();
        System.out.println(str2);

        String str3 = "이 글자의 길이는!?";
        Supplier<Integer> sup3 = str3::length;
        System.out.println(sup3.get());
    }
}
