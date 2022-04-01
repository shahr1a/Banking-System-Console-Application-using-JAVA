package com.project.classes;

import com.project.interfaces.ITransaction;

public class
Account implements ITransaction {
    private int accountNumber;
    private double balance = 0;

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        setBalance(getBalance() + amount);
    }    @Override
    public void withDraw(double amount) {
        if(balance - amount >= 0.0) {
            balance -= amount;
        }
        else {
            System.out.println("Shortage of Amount: " + (amount - getBalance()));
            balance = 0.0;
        }
    }

    public void showInfo() {
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Balance: " + getBalance());
    }
}
