package exceptioin.basic;

public class ExceptionWithoutTryCatch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int idx = 5;

        if (idx < arr.length) {
            System.out.println(arr[idx]);
        } else {
            System.out.println("나오나요?");
        }
    }
}
