package com.project.classes;
import java.util.*;
import java.util.Scanner;

//import static com.project.classes.Bank.transactions;

public class Start {
    private static Scanner scanner = new Scanner(System.in);
    static FileReadWrite transactionBlog = new FileReadWrite();

    public static void main(String[] args) {
        Bank bank = new Bank();
        boolean system = true;
        try {
            while (system) {
                showHomePageOptions();
                int input;
                System.out.print("Enter Choice: ");
                input = scanner.nextInt();
                scanner.nextLine();
                switch (input) {
                    case 1:
                        System.out.println("\n  -----Welcome to Employee Section-----  \n");
                        boolean exitEmployeeOperation = false;
                        while (!exitEmployeeOperation) {
                            showEmployeeManagement();
                            System.out.print("Employee Operation Selection: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice) {
                                case 1:
                                    bank.insertEmployee(insertNewEmployee());
                                    System.out.println("Employee ID assigning.......");
                                    System.out.println("Employee Added into the System. Done!");
                                    System.out.println("\n   Returning to Employee Management..... \n");
                                    break;
                                case 2:
                                    bank.getEmployee(removeEmployee());
                                    break;
                                case 3:
                                    bank.showAllEmployees();
                                    break;
                                case 4:
                                    exitEmployeeOperation = true;
                                    System.out.println("\n  Exiting Employee Operation and Returning To System Menu......  \n");
                                    break;
                                }
                            }
                        break;
                    case 2:
                        System.out.println("\n  -----Welcome to Customer Section-----  \n");
                        boolean exitCustomerOperation = false;
                        while (!exitCustomerOperation) {
                            showCustomerOperation();
                            System.out.print("Customer Operation Selection: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice) {
                                case 1:
                                    bank.insertCustomer(createCustomer());
                                    break;
                                case 2:
                                    System.out.println("Enter NID: ");
                                    bank.getCustomer(scanner.nextInt());
                                    break;
                                case 3:
                                    bank.showAllCustomers();
                                    break;
                                case 4:
                                    exitCustomerOperation = true;
                                    System.out.println("  Exiting Customer Operation and Returning to Homepage.....  \n");
                                    break;
                            }
                        }
                        break;

                    case 3:
                        System.out.println("\n  -----WELCOME TO TRANSACTION SECTION-----  \n");
                        boolean exitTransactionOperations = false;
                        while (!exitTransactionOperations) {
                            showTransactionOptions();
                            int nid;
                            int ac;
                            double amount;
                            Customer c;
                            System.out.print("Transaction Operation Selection: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();
                            switch (choice) {
                                case 1:
                                    System.out.print("Enter NID: ");
                                    nid = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Account Number: ");
                                    ac = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Amount: ");
                                    amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    c = bank.getCustomerObject(nid);
                                    if (c != null) {
                                        Account a = c.getAccount(ac);
                                        if (a != null) {
                                            a.deposit(amount);
                                            transactionBlog.writeInFile("Date & Time: " + java.util.Calendar.getInstance().getTime() + "\n Account Number: " + a.getAccountNumber() + "\n Debit: " + amount);
                                            System.out.println("   ---Deposit Successful---   ");
                                            System.out.println("New Balance of the account is: " + a.getBalance());
                                        } else {
                                            System.out.println("Account Doesn't Exist");
                                        }
                                    } else {
                                        System.out.println("Customer Doesn't Exist.");
                                    }
                                    break;
                                case 2:
                                    System.out.print("Enter NID: ");
                                    nid = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Account Number: ");
                                    ac = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter Amount: ");
                                    amount = scanner.nextDouble();
                                    scanner.nextLine();
                                    c = bank.getCustomerObject(nid);
                                    if (c != null) {
                                        Account a = c.getAccount(ac);
                                        if (a != null) {
                                            a.withDraw(amount);
                                            transactionBlog.writeInFile("Date & Time: " + java.util.Calendar.getInstance().getTime() + "\n Account Number: " + a.getAccountNumber() + "\n Credit: " + amount);
                                            System.out.println("   ---Withdraw Successful---   ");
                                            System.out.println("New Balance of the account is: " + a.getBalance());
                                        } else {
                                            System.out.println("Account Doesn't Exist");
                                        }
                                    } else {
                                        System.out.println("Customer Doesn't Exist.");
                                    }
                                    break;
                                case 3:
                                    exitTransactionOperations = true;
                                    try {
                                        transactionBlog.readFromFile();
                                    }
                                    catch (NullPointerException npe) {
                                        System.out.println("Null pointer Exception Caught..");
                                    }
                                    System.out.println("\n    Exiting Transaction Operation and Returning to Homepage....  \n");
                                    break;
                            }
                        }
                        break;
                    case 4:
                        system = false;
                        System.out.println("\n  Signing Out From the System......  \n");
                        break;
                }
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("Invalid Input type.");
        }
    }

    private static void showHomePageOptions() {
        System.out.println("Home Page Options: ");
        System.out.println("1. Employee Management \r");
        System.out.println("2. Customer Management \r");
        System.out.println("3. Account Transaction \r");
        System.out.println("4. Exit \r");
    }

    private static void showEmployeeManagement() {
        System.out.println("Options for Employee Management: ");
        System.out.println("1. Insert New Employee");
        System.out.println("2. Remove Existing Employee");
        System.out.println("3. Show All Employee");
        System.out.println("4. Going Back");
    }

    private static Employee insertNewEmployee() {
        Employee employee = new Employee();
        System.out.print("Enter New Employee Name: ");
        employee.setName(scanner.nextLine());
        System.out.print("Set Salary: ");
        employee.setSalary(scanner.nextDouble());
        return employee;
    }

    private static String removeEmployee() {
            System.out.print("Enter ID: ");
            return scanner.nextLine();
    }

    private static void showCustomerOperation() {
        System.out.println(" Options for Customer Management: ");
        System.out.println("1. Insert New Customer");
        System.out.println("2. Remove Existing Customer");
        System.out.println("3. Show All Customers");
        System.out.println("4. Going Back");
    }

    private static Customer createCustomer() {
        Customer customer = new Customer();
        System.out.print("Enter Customer Name: ");
        customer.setName(scanner.nextLine());
        System.out.print("Enter Customer NID number: ");
        customer.setNid(scanner.nextInt());
        return customer;
    }

    private static void showTransactionOptions() {
        System.out.println("Transaction Options: ");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Going Back");
    }
}
