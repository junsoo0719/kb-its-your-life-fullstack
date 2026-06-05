package stream;

import java.util.stream.LongStream;

public class ForVsStream {
    static private final long SIZE = 100_000_000L;

    public static void main(String[] args) {
        long start, end, sum = 0;

        start = System.currentTimeMillis();
        for (int i = 1; i <= SIZE; i++) {
            sum += i;
        }
        end = System.currentTimeMillis();
        System.out.println("for 문 결과 : " + sum);
        System.out.println("for 문 시간 : " + (end - start));

        start = System.currentTimeMillis();
        sum = LongStream.rangeClosed(1, SIZE).sum();
        end = System.currentTimeMillis();
        System.out.println("stream 결과 : " + sum);
        System.out.println("stream 시간 : " + (end - start));

        start = System.currentTimeMillis();
        sum = LongStream.rangeClosed(1, SIZE).parallel().sum();
        end = System.currentTimeMillis();
        System.out.println("stream 결과 : " + sum);
        System.out.println("stream 시간 : " + (end - start));
    }
}
