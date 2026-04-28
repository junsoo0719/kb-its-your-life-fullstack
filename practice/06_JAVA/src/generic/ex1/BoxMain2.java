package generic.ex1;

public class BoxMain2 {
    public static void main(String[] args) {
        ObjBox intBox = new ObjBox();
        intBox.setObj("l00");
        Integer integer = (Integer) intBox.getObj();
        System.out.println("intBox value = " + integer);
    }
}
