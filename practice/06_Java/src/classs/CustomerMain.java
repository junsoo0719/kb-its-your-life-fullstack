package classs;

public class CustomerMain {
    public static void main(String[] args) {
        Customer customer1 = new Customer();

        customer1.name = "송준수";
        customer1.age = 26;
        customer1.total = 10000000;
        customer1.blacklist = true;

        System.out.println("고객의 이름은? : " + customer1.name);
        System.out.println("고객의 나이는? : " + customer1.age);
        System.out.println("고객이 총 사용한 금액은? : " + customer1.total);
        System.out.println("고객의 blacklist 여부는? : " + customer1.blacklist);
    }
}
