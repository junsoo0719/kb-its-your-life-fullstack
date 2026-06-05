package generic.method;

public class World2 {
    public static void main(String[] args) {
        GenericAptApt<Integer> integerGenericAptApt = new GenericAptApt<>();
        String type = integerGenericAptApt.genericMethod1(10).getClass().getName();
        System.out.println(type);

        String str = GenericAptApt.<String>genericMethod2("문자열");
        String str2 = GenericAptApt.genericMethod2("문자열");
        System.out.println(str);

        Double doubleValue = GenericAptApt.numberMethod(10.3);
        System.out.println(doubleValue);
    }
}
