package langg.object;

public class ObjectMain {
    public static void main(String[] args) {
        Child child = new Child();

        System.out.println(child.toString());

        Object[] classes = {new Parent(), new Child(), new OtherClass()};
    }
}
