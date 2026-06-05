package collection.set;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        List<String> students = Arrays.asList(
                "강태규", "권유현", "김건우", "김기선", "김민철",
                "김수현", "김현태", "송준수", "송태권", "양승환",
                "오진호", "이대주", "이민호", "이아영", "이지민",
                "이지은", "이채연", "장지연", "최규진", "최보윤",
                "홍상우", "황지원"
        );

        Set<String> lastNames = new HashSet<>();
        for (String name : students) {
            lastNames.add(name.substring(0, 1));
        }

        System.out.println(lastNames.toString());
        System.out.println("총 인원 : " + students.size());
        System.out.println("성 종류 : "+ lastNames.size());
    }
}
