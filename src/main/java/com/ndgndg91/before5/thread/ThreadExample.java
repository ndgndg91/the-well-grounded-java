package com.ndgndg91.before5.thread;

public class ThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            var start = System.currentTimeMillis();
            var wasInterrupted = false;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                wasInterrupted = true;
                e.printStackTrace(); // stack 만 출력하고 나머지 내용은 계속 실행된다.
            }

            var thisThread = Thread.currentThread();
            System.out.println(thisThread.getName() + " slept for " + (System.currentTimeMillis() - start));
            if (wasInterrupted) {
                System.out.println("Thread " + thisThread.getName() + " was interrupted");
            }
        };

        var t = new Thread(runnable); // 스레드의 메타데이터 객체가 생성된다.
        t.setName("Worker");
        t.start(); // 운영체제가 실제 스레드를 생성한다.
        t.interrupt();
        t.join(); // 메인 스레드가 일시 중지되고 워커가 종료될 때까지 기다렸다가 계속 진행한다.
        System.out.println("Exiting");
    }
}
