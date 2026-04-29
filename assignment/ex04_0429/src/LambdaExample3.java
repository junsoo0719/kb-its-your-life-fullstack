@FunctionalInterface
interface Workable3 {
    void work(String name, String job);
}

@FunctionalInterface
interface Speakable3 {
    void speak(String content);
}

class Person3 {
    public void action1(Workable3 workable) {
        workable.work("홍길동", "프로그래밍");
    }
    public void action2(Speakable3 speakable) {
        speakable.speak("안녕하세요");
    }
}

public class LambdaExample3 {
    public static void main(String[] args) {
        Person3 person = new Person3();

        //매개변수가 두 개일 경우
        person.action1((name, job) -> {
            System.out.print(name + " 이 ");
            System.out.println(job + " 을 합니다.");
        });
        person.action1((name, job) -> System.out.println(name + " 이 " + job + " 을 하지 않습니다."));

        //매개변수가 한 개일 경우
        person.action2((word) -> {
            System.out.print("\"" + word + "\"");
            System.out.println(" 라고 말합니다.");
        });
        person.action2((word) -> System.out.println("\"" + word + "\" 라고 외칩니다."));
    }
}
