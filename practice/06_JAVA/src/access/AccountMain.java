package access;

public class AccountMain {
    public static void main(String[] args) {
        Account account = new Account(1000);

        account.deposit(10000); // 용돈
        account.withdraw(7000); // 계밥

        System.out.println("남은 금액은 : " + account.total);
    }
}
