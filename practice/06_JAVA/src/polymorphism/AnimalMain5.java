package polymorphism;

public class AnimalMain5 {
    public static void main(String[] args) {
        Donkey donkey = new Donkey();
        Mosquito mosquito = new Mosquito();

        System.out.println(donkey instanceof Donkey);
        System.out.println(donkey instanceof Animal);
        System.out.println(mosquito instanceof Mosquito);
//        System.out.println(mosquito instanceof Animal);
    }
}
