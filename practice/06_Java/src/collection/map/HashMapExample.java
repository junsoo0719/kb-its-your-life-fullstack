package collection.map;

import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        List<String> students = Arrays.asList(
                "강태규", "권유현", "김건우", "김기선", "김민철",
                "김수현", "김현태", "송준수", "송태권", "양승환",
                "오진호", "이대주", "이민호", "이아영", "이지민",
                "이지은", "이채연", "장지연", "최규진", "최보윤",
                "홍상우", "황지원"
        );

        Map<String, List<String>> lastNameMap = new HashMap<>();

        for (String name : students) {
            String lastName = name.substring(0, 1);

            if (lastNameMap.containsKey(lastName) == false) {
                lastNameMap.put(lastName, new ArrayList<>());
            }

            lastNameMap.get(lastName).add(name);
        }

        Map<String, List<String>> sortedMap = new TreeMap<>(lastNameMap);

        for (Map.Entry<String, List<String>> entry : sortedMap.entrySet()) {
            String lastName = entry.getKey();
            List<String> names = entry.getValue();
            System.out.println(lastName + " 씨, (" + names.size() + " 명" + names);
        }
    }
}
