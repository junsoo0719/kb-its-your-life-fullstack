package interfacee;

public class Rectangle implements Shape {
    private String color;
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        this.color = color;
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return (width + height) * 2;
    }

    public void printRectangle() {
        System.out.println("색상 : " + this.color);
        printInfo();
    }
}
