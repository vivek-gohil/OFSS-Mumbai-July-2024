package com.ofss.main.domain;

public class Savings extends Account {
    private boolean isSalaryAccount;
    private static final int minimumBalance = 1500;

    public Savings() {
        System.out.println("Default constructor of Savings");
    }

    public Savings(int accountNumber, String name, double balance, boolean isSalaryAccount) {
        super(accountNumber, name, balance);
        this.isSalaryAccount = isSalaryAccount;
    }

    

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && isSalaryAccount && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            return true;
        }
        if (amount > 0 &&
                isSalaryAccount == false && getBalance() - amount >= minimumBalance) {
            setBalance(getBalance() - amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount > 0) {
            setBalance(getBalance() + amount);
            return true;
        }
        return false;
    }

    public boolean isSalaryAccount() {
        return isSalaryAccount;
    }

    public void setSalaryAccount(boolean isSalaryAccount) {
        this.isSalaryAccount = isSalaryAccount;
    }

    public static int getMinimumbalance() {
        return minimumBalance;
    }

}
