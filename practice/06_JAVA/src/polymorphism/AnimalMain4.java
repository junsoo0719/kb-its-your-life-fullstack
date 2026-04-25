package polymorphism;

public class AnimalMain4 {
    public static void main(String[] args) {
        Animal animal = new Animal();

        Cat youAreNowCat = (Cat) animal;
        youAreNowCat.grooming();
    }

    private static void soundAnimal(Animal animal) {
        animal.sound();
    }
}
