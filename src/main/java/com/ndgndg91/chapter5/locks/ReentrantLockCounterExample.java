package com.ndgndg91.chapter5.locks;

import java.util.concurrent.Executors;

public class ReentrantLockCounterExample {

    public static void main(String[] args) {
        try (var executor = Executors.newFixedThreadPool(5)) {
            var task = new ReentrantLockCounter();
            for (var i = 0; i < 10; i++) {
                executor.submit(task);
            }
        }
    }
}
