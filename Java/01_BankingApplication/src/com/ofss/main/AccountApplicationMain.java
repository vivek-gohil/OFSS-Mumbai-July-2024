package com.ofss.main;

import java.util.Scanner;

import com.ofss.main.domain.Account;

public class AccountApplicationMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter account number");
        int accountNumber = scanner.nextInt();

        scanner.nextLine();
        
        System.out.println("Enter name");
        String name = scanner.nextLine();

        System.out.println("Enter balance");
        double balance = scanner.nextDouble();

        Account firstAccount = new Account();
        firstAccount.setAccountNumber(accountNumber);
        firstAccount.setName(name);
        firstAccount.setBalance(balance);

        System.out.println("Account Number :: " + firstAccount.getAccountNumber());
        System.out.println("Name :: " + firstAccount.getName());
        System.out.println("Balance :: " + firstAccount.getBalance());

    }
}
