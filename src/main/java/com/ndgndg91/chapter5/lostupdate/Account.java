package com.ndgndg91.chapter5.lostupdate;

public class Account {
    private int balance;
    private static int nextAccountId = 1;
    private final int accountId;

    public Account(int openingBalance) {
        balance = openingBalance;
        accountId = getAndIncrementNextAccountId();
    }

    public int getAccountId() {
        return accountId;
    }

    private static synchronized int getAndIncrementNextAccountId() {
        int result = nextAccountId;
        nextAccountId =  nextAccountId + 1;
        return result;
    }

    public boolean withdraw(int amount, boolean safe) {
        if (safe) {
            return safeWithdraw(amount);
        } else {
            return rawWithdraw(amount);
        }
    }

    public void deposit(int amount, boolean safe) {
        if (safe) {
            safeDeposit(amount);
        } else {
            rawDeposit(amount);
        }
    }

    public boolean rawWithdraw(int amount) {
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        }

        return false;
    }

    public void rawDeposit(int amount) {
        try {
            var curBalance = balance;
            Thread.sleep(100);
            balance = curBalance + amount;
        } catch (InterruptedException e) {
            System.out.println(Thread.interrupted());
        }
    }

    public int getRawBalance() {
        return balance;
    }

    public boolean safeWithdraw(int amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance = balance - amount;
                return true;
            }
            return false;
        }

    }

    public void safeDeposit(int amount) {
        synchronized (this) {
            try {
                Thread.sleep(100);
                balance = balance + amount;
            } catch (InterruptedException e) {
                System.out.println(Thread.interrupted());
            }
        }
    }

    public int getSafeBalance() {
        synchronized (this) {
            return balance;
        }
    }

    /**
     * deadlock 을 발생시킨다.
     */
    public boolean naiveSafeTransferTo(Account other, int amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance = balance - amount;
                synchronized (other) {
                    other.rawDeposit(amount);
                }
                return true;
            }

            return false;
        }
    }

    public boolean safeTransferTo(final Account other, final int amount) {
        if (accountId == other.getAccountId()) {
            // 본인 계좌로는 이차 불가능.
            return false;
        }

        if (accountId < other.getAccountId()) {
            synchronized (this) {
                if (balance >= amount) {
                    balance = balance - amount;
                    synchronized (other) {
                        other.rawDeposit(amount);
                    }
                    return true;
                }
            }
            return false;
        } else {
            synchronized (other) {
                synchronized (this) {
                    if (balance >= amount) {
                        balance = balance - amount;
                        other.rawDeposit(amount);
                        return true;
                    }

                }
            }
            return false;
        }
    }
}
