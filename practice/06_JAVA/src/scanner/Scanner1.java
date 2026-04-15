package scanner;

import java.util.Scanner;

public class Scanner1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("문자열을 입력하세요 : ");
        String str = scanner.nextLine();
        System.out.println("입력한 문자열은!? : " + str);

        System.out.println("정수를 입력하세요 : ");
        int intNum = scanner.nextInt();
        System.out.println("입력한 정수는!? : " + intNum);

        System.out.println("실수를 입력하세요 : ");
        double doubleNum = scanner.nextDouble();
        System.out.println("입력한 실수는!? : " + doubleNum);
    }
}
