package com.ndgndg91.chapter5.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicTaskManager implements Runnable {
    private final AtomicBoolean shutdown = new AtomicBoolean(false);
    public void shutdown() {
        shutdown.set(true);
    }

    @Override
    public void run() {
        while (!shutdown.get()) {
            // 일부 작업 수행
        }
    }
}
