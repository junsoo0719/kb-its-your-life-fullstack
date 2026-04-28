package interfacee;

public interface Shape {
    public static final double PI = 3.14;

    double area();

    double perimeter();

    default void printInfo() {
        System.out.println("넓이 : " + area());
        System.out.println("둘레 : " + perimeter());
    }
}