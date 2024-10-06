package com.ndgndg91.chapter5.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

public class ForkJoinPoolExample {

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        int result;
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            int[] array = IntStream.rangeClosed(1, 10_000).toArray();
            SumTask sumTask = new SumTask(array, 0, array.length);
            result = forkJoinPool.invoke(sumTask);
        }
        var end = System.currentTimeMillis();
        System.out.println("Total Sum: " + result);
        System.out.println("time : " + (end - start));
    }

    static class SumTask extends RecursiveTask<Integer> {
        private final int[] array;
        private final int start;
        private final int end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= 10) { // 작은 작업으로 분할할 임계값
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += array[i];
                }
                return sum;
            } else {
                int middle = (start + end) / 2;
                SumTask leftTask = new SumTask(array, start, middle);
                SumTask rightTask = new SumTask(array, middle, end);
                leftTask.fork(); // 왼쪽 작업 비동기적으로 실행
                return rightTask.compute() + leftTask.join(); // 오른쪽 작업 실행 및 결과 병합
            }
        }
    }

}
