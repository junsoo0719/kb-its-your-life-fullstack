package generic.limit;

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

    @Override
    public String toString() {
        return "GenericAptApt{" +
                "number=" + number +
                '}';
    }
}
