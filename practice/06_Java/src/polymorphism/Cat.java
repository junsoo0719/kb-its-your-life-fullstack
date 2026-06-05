package polymorphism;

public class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("메~에아오");
    }

    public void grooming() {
        System.out.println("고양이는 스스로 그루밍 합니다. 나 이뻐!?");
    }
}
