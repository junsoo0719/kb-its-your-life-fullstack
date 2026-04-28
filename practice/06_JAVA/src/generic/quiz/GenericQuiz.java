package generic.quiz;

public class GenericQuiz<T> {
    private T value;

    public static <T> T quizMethod(T t) {
        return t;
    }
}
