package com.ndgndg91.chapter5.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class CountDownLatchCounterExample {

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 5; // 스레드 개수
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger counter = new AtomicInteger(0); // AtomicInteger를 사용하여 스레드 안전하게 값 변경

        try (var executorService = Executors.newFixedThreadPool(numberOfThreads)) {

            // 스레드 시작
            for (int i = 0; i < numberOfThreads; i++) {
                executorService.submit(new CountDownLatchCounter(latch, i + 1, counter));
            }

            // 모든 스레드가 끝날 때까지 기다림
            latch.await();

            // 종료 후 결과 출력
            System.out.println("Final counter value: " + counter.get());

            executorService.shutdown();
        }
    }
}
