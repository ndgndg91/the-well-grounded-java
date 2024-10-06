package com.ndgndg91.chapter5.atomic;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
    public static void main(String[] args) {
        var accountId = new AtomicInteger(0);
        try (var executor = Executors.newFixedThreadPool(10)) {
            for (var i = 0; i < 10; i++) {
                executor.submit(() -> {
                    System.out.println(accountId.getAndIncrement());
                    return null;
                });
            }
        }
    }
}
