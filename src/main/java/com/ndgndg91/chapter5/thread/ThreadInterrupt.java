package com.ndgndg91.chapter5.thread;

public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {while (!Thread.interrupted()) {
            System.out.println("infinite loop.");
        }});
        t.start(); // 현재 스레드의 인터럽트를 확인하도록 한다.

        Thread.sleep(10);
        t.interrupt(); // 스레드 중단을 요청
        t.join(); // 메인 스레드에서 다른 스레드가 완료될 때까지 기다린다.
    }
}
