package com.ndgndg91.chapter5.deadlock;

public class FSOAccount {
    private int balance;

    public FSOAccount(int openingBalance) {
        if (openingBalance <= 0) {
            throw new RuntimeException("opening balance is lte zero.");
        }
        this.balance = openingBalance;
    }

    public synchronized boolean withdraw(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("amount is lte zero.");
        }
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        }

        return false;
    }

    public synchronized void deposit(int amount) {
        if (amount <= 0) {
            throw new RuntimeException("amount is lte zero.");
        }
        balance = balance + amount;
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized boolean transferTo(FSOAccount other, int amount) {
        if (amount <= 0) {
            throw new RuntimeException("amount is lte zero.");
        }
        //해당 sleep 으로 인해 교착 상태로 빠진다.
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (balance >= amount) {
            balance = balance - amount;
            other.deposit(amount);
            return true;
        }
        return false;
    }
}
