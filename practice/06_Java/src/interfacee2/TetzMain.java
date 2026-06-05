package interfacee2;

public class TetzMain {
    public static void main(String[] args) {
        Dog dog = new Dog();
        Tetz tetz = new Tetz();

        animalDo(dog);
        animalDo(tetz);
        humanDo(tetz);
    }

    public static void animalDo(Animal animal) {
        animal.eat();
        animal.sleep();
    }

    public static void humanDo(Human human) {
        human.think();
    }
}
