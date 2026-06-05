package generic.method;

public class GenericAptApt<T extends Number> {
    private T number;

    public T getNumber() {
        return number;
    }

    public void setNumber(T number) {
        this.number = number;
    }

    public double transDouble() {
        return number.doubleValue();
    }

    public T genericMethod1(T t) {
        return t;
    }

    public static <U> U genericMethod2(U u) {
        return u;
    }

    public <U> T genericMethod3(U u) {
        return number;
    }

    public static <U extends Number> U numberMethod(U u) {
        return u;
    }

    @Override
    public String toString() {
        return "GenericAptApt{" +
                "number=" + number +
                '}';
    }
}
