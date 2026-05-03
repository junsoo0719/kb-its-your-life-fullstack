package design_pattern.ch03;

public abstract class AbstractDisplay {
    public abstract void open();

    public abstract void print();

    public abstract void close();

    // AbstractDisplay에서 구현하는 메서드(템플릿)
    public final void display() {
        open();
        for(int
            i = 0;
            i < 5;
            i++) {
            print();
        }
        close();
    }
}
