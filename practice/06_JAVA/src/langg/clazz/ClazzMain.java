package langg.clazz;

public class ClazzMain {
    public static void main(String[] args) throws ClassNotFoundException {
        Class clazz1 = User.class;
        Class clazz2 = new User("효석", "xenosign").getClass();
        Class clazz3 = Class.forName("langg.clazz.User");

        System.out.println("clazz1 = " + clazz1);
        System.out.println("clazz2 = " + clazz2);
        System.out.println("clazz3 = " + clazz3);
    }
}
