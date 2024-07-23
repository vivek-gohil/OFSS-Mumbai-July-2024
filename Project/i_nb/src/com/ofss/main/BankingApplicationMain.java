package com.ofss.main;

import com.ofss.main.domain.Account;
import com.ofss.main.domain.Current;
import com.ofss.main.domain.Customer;
import com.ofss.main.domain.Login;
import com.ofss.main.domain.Savings;
import com.ofss.main.service.CurrentService;
import com.ofss.main.service.CustomerService;
import com.ofss.main.service.LoginService;
import com.ofss.main.service.SavingsService;
import com.ofss.main.service.impl.CurrentServiceImpl;
import com.ofss.main.service.impl.CustomerServiceImpl;
import com.ofss.main.service.impl.LoginServiceImpl;
import com.ofss.main.service.impl.SavingsServiceImpl;
import java.util.Scanner;

public class BankingApplicationMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        Login login = null;
        Account account = null;
        boolean result = false;

        LoginService loginService = new LoginServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        CurrentService currentService = new CurrentServiceImpl();
        SavingsService savingsService = new SavingsServiceImpl();

        int mainMenuChoice = printMainMenu(scanner);
        customer = menuOperations(mainMenuChoice, login, customer, scanner);

        //System.out.println(customer);
        if (customer != null) {
            if (mainMenuChoice == 1) {
                login = customer.getLogin();
                login = loginService.createNewLogin(login);
                System.out.println("Login created successfully!!");
                System.out.println(login);
                customer = customerService.addNewCustomer(customer);
                System.out.println("Customer created successfully");

                System.out.println();
                System.out.println("Your customerId :: " + customer.getCustomerId());
                System.out.println();

                account = createNewAccount(customer, scanner);
                if(account instanceof Current current){
                    result = currentService.addNewCurrentAccount(current);
                }
                if(account instanceof  Savings savings){
                    result = savingsService.addNewSavingsAccount(savings);
                }
                if (account != null && result) {
                    System.out.println("Account created successfully with account id : " + account.getAccountId()); 
                }else {
                    System.out.println("Failed to create new account");
                }

            }
            if (mainMenuChoice == 2) {
              System.out.println("Customer Login");

            }
        } else {
            System.out.println("Thank you!");
        }

    }

    private static int printMainMenu(Scanner scanner) {
        System.out.println("Main Menu");
        System.out.println("1. New Customer Registration");
        System.out.println("2. Login - Existing Customer");
        System.out.println("Enter your choice");
        int choice = scanner.nextInt();
        return choice;
    }

    private static Customer menuOperations(int mainMenuChoice, Login login, Customer customer, Scanner scanner) {
        switch (mainMenuChoice) {
            case 1:
                System.out.println("Enter Details To Open New Account");

                System.out.println("Enter first name");
                String firstName = scanner.next();
                System.out.println("Enter last name");
                String lastName = scanner.next();
                System.out.println("Enter gender");
                String gender = scanner.next();
                System.out.println("Enter email address");
                String email = scanner.next();
                System.out.println("Enter mobile number");
                String mobile = scanner.next();
                System.out.println("Enter your password");
                String password = scanner.next();

                login = new Login(password);
                customer = new Customer(firstName, lastName, gender, email, mobile, login);
                return customer;
            case 2:
                System.out.println("Enter your customerId");
                int customerId = scanner.nextInt();
                System.out.println("Enter your password");
                password = scanner.next();
                login = new Login(password);
                customer = new Customer();
                customer.setCustomerId(customerId);
                customer.setLogin(login);
                return customer;
            default:
                System.out.println("Invalid Choice");
                break;
        }
        return null;
    }

    private static Account createNewAccount(Customer customer, Scanner scanner ) {
        System.out.println("Account Menu");
        System.out.println("1. New Savings Account");
        System.out.println("2. New Current Account");
        System.out.println("Enter your choice");
        int accountChoice = scanner.nextInt();

        if (accountChoice == 1) {
           Savings savings = new Savings(customer, "SAVINGS");
            return savings;
        }
        if (accountChoice == 2) {
            Current current = new Current(customer, "CURRENT");
            return current;
        }
        return null;
    }
}
