package abstractt;

public abstract class Shape {
    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public abstract double area();

    public abstract double perimeter();

    public void printInfo() {
        System.out.println("색상 : " + this.color);
        System.out.println("넓이 : " + area());
        System.out.println("둘레 : " + perimeter());
    }
}