public class ExceptionHandlingExample1 {
    public static void printLength(String data) {
        try {
            int result = data.length(); // NPE 발생 가능!
            System.out.println("문자 수: " + result);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        } finally {
            System.out.println("[마무리 실행]\n");
        }
    }
    public static void main(String[] args) {
        System.out.println("[프로그램 시작]\n");
        printLength("ThisIsJava");
        printLength(null); // null 값 대입 -> NPE 발생
        System.out.println("[프로그램 종료]");
    }

}
