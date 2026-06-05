package interfacee;

public class ShapeInterfaceMain {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle("orange", 10, 10);
        Circle circle = new Circle("skyblue", 3);

        rectangle.printInfo();
        rectangle.printRectangle();

        circle.printInfo();
        circle.printCircle();
    }
}
