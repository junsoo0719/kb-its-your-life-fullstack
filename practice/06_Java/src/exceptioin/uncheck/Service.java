package exceptioin.uncheck;

import exceptioin.check.MyCheckException2;

public class Service {
    public static void main(String[] args) {
        Repository repo = new Repository();

        try {
            repo.callUnCheckException();
        } catch (MyUnCheckException e) {
            e.printStackTrace();
        }
        System.out.println("막았는가!?");
    }
}
