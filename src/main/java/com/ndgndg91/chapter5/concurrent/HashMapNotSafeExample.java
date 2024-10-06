package com.ndgndg91.chapter5.concurrent;

import java.util.HashMap;

public class HashMapNotSafeExample {

    public static void main(String[] args) throws InterruptedException {
        var map = new HashMap<String, String>();
        var SIZE = 10_000;

        Runnable r1 = () -> {
            for (var i = 0; i < SIZE; i++) {
                map.put("t1" + i, "0");
            }
            System.out.println("Thread 1 done.");
        };

        Runnable r2 = () -> {
            for (var i = 0; i < SIZE; i++) {
                map.put("t2" + i, "0");
            }
            System.out.println("Thread 2 done.");
        };

        var t1 = new Thread(r1);
        var t2 = new Thread(r2);
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        // 동시성 이슈로 인해서 항상 20,000 보다 작은 사이즈가 나온다.
        // thread safe 하지 않다.
        System.out.println("Count : " + map.size());
    }
}
