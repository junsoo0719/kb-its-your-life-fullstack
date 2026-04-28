package exceptioin.check;

public class Service {
    public static void main(String[] args) {
        Repository repo = new Repository();

        try {
            repo.callRandException();
            repo.callCheckException();
        } catch (MyCheckException | MyCheckException2 e) {
            // MyCheckException 에 맞는 예외 처리
            e.printStackTrace();
        }
        System.out.println("막았는가!?");
    }
}
