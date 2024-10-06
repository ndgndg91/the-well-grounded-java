package com.ndgndg91.chapter5.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {
        var queue = new ArrayBlockingQueue<WorkUnit<AwesomeTask>>(10);

        var t1 = new Thread(() -> {
            for (var i = 0; i < 20; i++) {
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                queue.offer(new WorkUnit<>(new AwesomeTask(System.currentTimeMillis(), ""+ i)));
            }
        });

        var t2 = new Thread(() -> {
            for (var i = 0; i < 20; i++) {
                try {
                    Thread.sleep(100);
                    WorkUnit<AwesomeTask> workUnit = queue.take();
                    System.out.println(workUnit);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    static class WorkUnit<T> {
        private final T unit;

        public WorkUnit(T unit) {
            this.unit = unit;
        }

        @Override
        public String toString() {
            return unit.toString();
        }
    }

    static class AwesomeTask {
        private final long time;
        private final String name;

        public AwesomeTask(long time, String name) {
            this.time = time;
            this.name = name;
        }

        public String toString() {
            return "time=" + time +
                    ", name=" + name;
        }
    }
}
