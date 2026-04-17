package access;

public class Account {
    int total;

    Account(int total) {
        this.total = total;
    }

    void deposit(int amount) {
        this.total += amount;
    }

    void withdraw(int amount) {
        this.total -= amount;
    }
}
