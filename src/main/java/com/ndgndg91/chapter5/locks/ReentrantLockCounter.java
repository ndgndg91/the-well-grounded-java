package com.ndgndg91.chapter5.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Runnable{
    private final Lock lock = new ReentrantLock();
    private int counter = 0;

    @Override
    public void run() {
        lock.lock();
        try {
            // 공유 자원 접근
            System.out.println(Thread.currentThread().getName() + " acquired the lock.");
            counter++;
            System.out.println(Thread.currentThread().getName() + " counter: " + counter);
        } finally {
            lock.unlock(); // 잠금 해제
            System.out.println(Thread.currentThread().getName() + " released the lock.");
        }
    }
}
