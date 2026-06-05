package generic.ex1;

public class BoxMain {
    public static void main(String[] args) {
        IntegerBox intBox = new IntegerBox();
        intBox.setValue(27);
        System.out.println("IntegerBox value = " + intBox.getValue());

        StringBox strBox = new StringBox();
        strBox.setValue("마니또 뭐함!?");
        System.out.println("Stringbox value = " + strBox.getValue());
    }
}
