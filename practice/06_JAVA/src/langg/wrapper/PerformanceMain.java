package langg.wrapper;

public class PerformanceMain {
    public static void main(String[] args) {
        int max = 1_000_000_000;
        long startTime, endTime;

        // 원시형
        long sum1 = 0;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            sum1 += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("원시형 자료 연산에 걸린 시간 : " + (endTime - startTime) + " ms");

        // 래퍼 타입
        Long sum2 = 0L;
        startTime = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            sum2 += i;
        }
        endTime = System.currentTimeMillis();
        System.out.println("래퍼 클래스 자료 연산에 걸린 시간 : " + (endTime - startTime) + " ms");
    }
}
