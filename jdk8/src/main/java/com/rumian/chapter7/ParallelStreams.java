package com.rumian.chapter7;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreams {
    
    public static void main(String[] args) {
        System.out.println("parallelism size : " + System.getProperty("java.util.current.ForkJoinPool.common.parallelism") );
        System.out.println("parallelism size : " + Runtime.getRuntime().availableProcessors());
        System.out.println(
                "sequentialSum : " + 
                sequentialSum(10)
                );
        System.out.println(
                "parallelSum : " + 
                parallelSum(10)
                );
        
        // 98
        System.out.println(
                "sequential sum done in : " + measureSumPerf(ParallelStreams::sequentialSum, 10_000_000) + " msecs"
                );
        // 2
        System.out.println(
                "iterativeSum sum done in : " + measureSumPerf(ParallelStreams::iterativeSum, 10_000_000) + " msecs"
                );
        // 89
        System.out.println(
                "parallelSum sum done in : " + measureSumPerf(ParallelStreams::parallelSum, 10_000_000) + " msecs"
                );
        // 위의 원인은 결국 boxing unboxing의 이슈이다.
        
        // 4
        System.out.println(
                "rangedSum sum done in : " + measureSumPerf(ParallelStreams::rangedSum, 10_000_000) + " msecs"
                );
        System.out.println(
                "parallelRangedSum sum done in : " + measureSumPerf(ParallelStreams::parallelRangedSum, 10_000_000) + " msecs"
                );
    }
    
    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                         .reduce(0L, Long::sum);
    }
    
    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                         .parallel()
                         .reduce(0L, Long::sum);
    }
    
    
    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1) // 무한 자연수 스트림 생
                     .limit(n) // n개 이하로 제
                     .reduce(0L, Long::sum); // 모든 숫자를 더하는 스트림 리듀싱 연
    }
    
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                     .limit(n)
                     .parallel()
                     .reduce(0L, Long::sum);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            //System.out.println("result : " + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }

        return fastest;
    }
}
