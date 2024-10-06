package com.ndgndg91.chapter5.locks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 여러 스레드가 조정 지점에 도달하고, 그 지점에서 대기하다가 배리어가 해제 될 때까지 기다릴 수 있는 간단한 동시성 원시 도구.
 */
public class CountDownLatchCounter implements Runnable {
    private final CountDownLatch latch;
    private final int value;
    private final AtomicInteger counter;

    public CountDownLatchCounter(CountDownLatch latch, int value, AtomicInteger counter) {
        this.latch = latch;
        this.value = value;
        this.counter = counter;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(value);
        counter.addAndGet(value);
        latch.countDown();
    }
}
