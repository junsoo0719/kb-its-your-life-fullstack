package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentStream {
    public static void main(String[] args) {
        List<String> students = Arrays.asList(
                "강태규", "권유현", "김건우", "김기선", "김민철",
                "김수현", "김현태", "송준수", "송태권", "양승환",
                "오진호", "이대주", "이민호", "이아영", "이지민",
                "이지은", "이채연", "장지연", "최규진", "최보윤",
                "홍상우", "황지원"
        );

        for (String s : students) {
            if (s.startsWith("이")) {
                System.out.println(s);
            }
        }

        students.stream()
                .filter((s) -> s.startsWith("이"))
                .forEach((s) -> System.out.println(s));

        students.stream()
                .filter((s) -> s.startsWith("이"))
                .forEach(System.out::println);

        List<String> kims = students.stream()
                .filter(s -> s.startsWith("김"))
                .collect(Collectors.toList());
        System.out.println(kims);

    }
}
