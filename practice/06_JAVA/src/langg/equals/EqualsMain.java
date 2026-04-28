package langg.equals;

public class EqualsMain {
    public static void main(String[] args) {
        User user1 = new User("이효석", "xenosign");
        User user2 = new User("이효석", "xenosign");

        System.out.println(user1 == user2);
        System.out.println(user1.equals(user2));
    }
}
