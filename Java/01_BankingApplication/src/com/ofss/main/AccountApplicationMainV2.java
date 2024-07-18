package com.ofss.main;

import java.util.Scanner;

import com.ofss.main.domain.Account;

public class AccountApplicationMainV2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String continueChoice = "No";
        double amount;
        boolean result;

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

        do {
            System.out.println("Transaction Menu");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("Enter your choice");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter amount to withdraw");
                    amount = scanner.nextDouble();
                    result = firstAccount.withdraw(amount);
                    if (result)
                        System.out.println("Withdraw successfull");
                    else
                        System.out.println("Withdraw failed");
                    break;
                case 2:
                    System.out.println("Enter amount to deposit");
                    amount = scanner.nextDouble();
                    result = firstAccount.deposit(amount);
                    if (result)
                        System.out.println("deposit successfull");
                    else
                        System.out.println("deposit failed");
                    break;
                case 3:
                    System.out.println("Balance :: " + firstAccount.getBalance());
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }

            System.out.println("Do you want to continue");
            continueChoice = scanner.next();
        } while (continueChoice.equals("Yes"));

    }
}
