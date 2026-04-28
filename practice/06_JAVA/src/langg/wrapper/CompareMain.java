package langg.wrapper;

public class CompareMain {
    public static void main(String[] args) {
        Integer int1 = Integer.valueOf(10);
        Long long1 = 100L;
        Double double1 = 11.18;

        int intValue = int1;
        long longValue = long1;
        double doubleValue = double1;

        System.out.println("intValue = " + intValue);
        System.out.println("int1 = " + int1);
        System.out.println("longValue = " + longValue);
        System.out.println("long1 = " + long1);
        System.out.println("doubleValue = " + doubleValue);
        System.out.println("double1 = " + double1);
    }
}
