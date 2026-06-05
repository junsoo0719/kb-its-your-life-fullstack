package generic.limit;

public class World1 {
    public static void main(String[] args) {
        GenericAptApt<Integer> integerGenericAptApt = new GenericAptApt<>();
        integerGenericAptApt.setNumber(1118);
        System.out.println(integerGenericAptApt.getNumber());

        GenericAptApt<Long> longGenericAptApt = new GenericAptApt<>();
        longGenericAptApt.setNumber(711L);
        System.out.println(longGenericAptApt.getNumber());

        Double seungHwanBirthday = longGenericAptApt.transDouble();
        System.out.println(seungHwanBirthday);
    }
}
