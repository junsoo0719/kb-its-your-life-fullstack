package exceptioin.real;

import exceptioin.real.exception.MyCheckException;
import exceptioin.real.exception.MyUnCheckException;

public class Service {
    public static void main(String[] args) {
        Repository repo = new Repository();

        try {
            repo.callUnCheckException();
            repo.callCheckException();
        } catch (Exception e) {
            exceptionHandler(e);
        }

        System.out.println("막았는가!?");
    }

    public static void exceptionHandler(Exception e) {
        System.out.println("시스템 : 죄송합니다. 알 수 없는 문제 발생");

        if (e instanceof MyCheckException) {
            System.out.println("MyCheckException 발생!");
            MyCheckException exception = (MyCheckException) e;
            // MyCheckException 에 맞는 예외 처리
            exception.printStackTrace();
        } else if (e instanceof MyUnCheckException) {
            System.out.println("MyUnCheckException 발생!");
            MyUnCheckException exception = (MyUnCheckException) e;
            // MyUnCheckException 에 맞는 예외 처리
            exception.printStackTrace();
        }
    }
}
