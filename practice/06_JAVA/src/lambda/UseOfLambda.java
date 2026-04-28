package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UseOfLambda {
    public static void main(String[] args) {
        List<String> names = List.of("Kim", "Lee", "Song", "Choi");

        List<String> filtered = new ArrayList<>();
        for (String name : names) {
            if (name.length() > 3) {
                filtered.add(name);
            }
        }
        System.out.println(filtered);

        List<String> result = new ArrayList<>();
        for (String name : filtered) {
            result.add(name.toUpperCase());
        }
        System.out.println(result);

        List<String> lambdaResult = names.stream()
                .filter(name -> name.length() > 3)
                .map(name -> name.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(lambdaResult);
    }
}
