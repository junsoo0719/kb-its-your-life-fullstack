package collection.list;

public class ArrayExample {
    public static void main(String[] args) {
        String[] students = {"이지민", "송태권", "김현태"};

        String[] newStudents = new String[students.length + 1];

        for (int i = 0; i < students.length; i++) {
            newStudents[i] = students[i];
        }

        newStudents[newStudents.length - 1] = "강태규";

        students = newStudents;

        for (String s : students) {
            System.out.println(s);
        }
    }
}
