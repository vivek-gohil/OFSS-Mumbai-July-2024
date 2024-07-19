package com.ofss.main.domain;

public class Account {
    private int accountNumber;
    private String name;
    private double balance;

    public Account() {
        System.out.println("Default constructor of Account");
    }

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
        System.out.println("Overloaded constructor of Account");
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    //function overriding
    public String toString() {
        return "Account [accountNumber= " + accountNumber + " , name= " 
                            + name + " , balance " + balance + "]";
    }
}
