package com.ndgndg91.chapter5.immutable;

import java.time.LocalDate;

public final class Deposit {
    private final int amount;
    private final LocalDate date;
    private final Account payee;

    private Deposit(int amount, LocalDate date, Account account) {
        this.amount = amount;
        this.date = date;
        this.payee = account;
    }

    public static Deposit of(int amount, LocalDate date, Account payee) {
        return new Deposit(amount, date, payee);
    }

    public static Deposit of(int amount, Account payee) {
        return new Deposit(amount, LocalDate.now(), payee);
    }

    public int amount() { return amount; }
    public LocalDate date() { return date; }
    public Account payee() { return payee; }

    public Deposit roll() {
        // 일자 변경에 대한 감사 이벤트 로그
        return new Deposit(amount, date.plusDays(1), payee);
    }

    public Deposit amend(int newAmount) {
        // 금액 변경에 대한 감사 이벤트 로그
        return new Deposit(newAmount, date, payee);
    }

    public static class DepositBuilder implements Builder<Deposit> {
        private int amount;
        private LocalDate date;
        private Account payee;

        public DepositBuilder withAmount(int amount) {
            this.amount = amount;
            return this;
        }

        public DepositBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public DepositBuilder withPayee(Account payee) {
            this.payee = payee;
            return this;
        }

        @Override
        public Deposit build() {
            return new Deposit(amount, date, payee);
        }
    }

}
