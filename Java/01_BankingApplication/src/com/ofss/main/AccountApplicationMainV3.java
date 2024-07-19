package com.ofss.main;

import com.ofss.main.domain.Current;

public class AccountApplicationMainV3 {
    public static void main(String[] args) {
        System.out.println("Testing Current Account");

        Current current = new Current(101, "Tanmay ", 10000, 50000);

        System.out.println("withdraw : 5000");
        current.withdraw(5000);
        System.out.println("Balance : " + current.getBalance()); //5000
        System.out.println("Overdraft Balance : " + current.getOverdraftBalance()); //50000

        System.out.println();

        System.out.println("withdraw : 15000");
        current.withdraw(15000);
        System.out.println("Balance : " + current.getBalance()); //0
        System.out.println("Overdraft Balance : " + current.getOverdraftBalance()); //40000

        System.out.println();

        System.out.println("withdraw : 5000");
        current.withdraw(5000);
        System.out.println("Balance : " + current.getBalance()); //0
        System.out.println("Overdraft Balance : " + current.getOverdraftBalance()); //35000

        System.out.println();

        System.out.println("deposit : 5000");
        current.deposit(5000);
        System.out.println("Balance : " + current.getBalance()); //0
        System.out.println("Overdraft Balance : " + current.getOverdraftBalance()); //40000

        
        System.out.println();

        System.out.println("deposit : 20000");
        current.deposit(20000);
        System.out.println("Balance : " + current.getBalance()); //10000
        System.out.println("Overdraft Balance : " + current.getOverdraftBalance()); //50000

        System.out.println();

        System.out.println("deposit : 5000");
        current.deposit(5000);
        System.out.println("Balance : " + current.getBalance()); //15000
        System.out.println("Overdraft Balance : " + current.getOverdraftBalance()); //50000

    }
}
