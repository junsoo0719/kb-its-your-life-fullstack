package polymorphism;

public class AnimalMain3 {
    public static void main(String[] args) {
        Animal cat = new Cat();

//        Cat catPoly = (Cat) cat;
//        catPoly.grooming();

        Animal cat2 = new Cat();
        cat2.animalMethod();
    }

    private static void soundAnimal(Animal animal) {
        animal.sound();
    }
}
