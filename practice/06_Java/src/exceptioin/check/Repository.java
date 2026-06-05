package exceptioin.check;

import java.util.Random;

public class Repository {
    public void callCheckException() throws MyCheckException {
        boolean con = false;

        // DB 관련 통신 작업
        if (!con) throw new MyCheckException("Repository DB 작업에서 예외 발생");
        // 원하던 작업을 수행
    }

    public void callRandException() throws MyCheckException2 {
        Random random = new Random();
        boolean randomResult = random.nextBoolean();

        if (randomResult) {
            throw new MyCheckException2("오늘은 운이 없으시군요");
        } else {
            System.out.println("오늘은 운이 좋으시군요");
        }
    }
}
