package com.ofss.main.domain;

public class Account {
    private int accountNumber;
    private String name;
    private double balance;

    public boolean deposit(double amount){
        if(amount > 0 ){
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount){
        if(amount > 0 && amount <= balance){
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

}
