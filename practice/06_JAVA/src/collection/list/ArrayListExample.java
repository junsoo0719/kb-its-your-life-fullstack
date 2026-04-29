package collection.list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        list.add("이지민");
        list.add("송태권");
        list.add("김현태");

        System.out.println(list);

        list.add("강태규");
        System.out.println(list);
    }
}
