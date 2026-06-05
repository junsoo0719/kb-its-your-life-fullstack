package generic.ex2;

public class GenericBoxMain {
    public static void main(String[] args) {
        GenericBox<Integer> intBox = new GenericBox<>();
        intBox.setValue(10);
        System.out.println("intBox value = " + intBox.getValue());

        GenericBox<String> strBox = new GenericBox<>();
        strBox.setValue("마니또 감사합니다!");
        System.out.println("strBox value = " + strBox.getValue());
    }
}
