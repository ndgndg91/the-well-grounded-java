package com.ndgndg91.chapter5.lostupdate;

public class LostUpdateMain {

    public static void main(String[] args) throws InterruptedException {
        var acc = new Account(0);
        var tA = new Thread(() -> acc.deposit(70, false));
        var tB = new Thread(() -> acc.deposit(50, false));
        tA.start();
        tB.start();
        tA.join();
        tB.join();

        // 70 으로 잔고 갱신으로 문제가 된다.
        // A: getfield
        // B: getfield
        // B: putfield
        // A: putfield

        // 50 으로 잔고 갱신으로 문제가 된다.
        // A: getfield
        // B: getfield
        // A: putfield
        // B: putfield

        // 120 으로 기대한 값이 된다.
        // A: getfield
        // A: putfield
        // B: getfield
        // B: putfield
        System.out.println(acc.getRawBalance());
    }
}

