package com.project.classes;

import com.project.interfaces.AccountOperations;

public class Customer implements AccountOperations {
    private String name;
    private int nid;
    private Account[] account = new Account[5];

    private int lastAccountIndex = 0;

    public void setName(String name) {
        this.name = name;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getName() {
        return name;
    }

    public int getNid() {
        return nid;
    }

    @Override
    public void insertAccount(Account a) {
        account[lastAccountIndex] = a;
        lastAccountIndex++;
    }

    @Override
    public void removeAccount(Account a) {
        boolean flag = false;
        for(int i=0; i<5 ; i++) {
            if(account[i] == a) {
                account[i] = null;
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Account not found...");
        }
    }

    @Override
    public Account getAccount(int accountNumber) {
        for(int i=0; i<lastAccountIndex; i++) {
            if(account[i].getAccountNumber() == accountNumber) {
                return account[i];
            }
        }
        return null;
    }

   public Account getAccount() {
        return account[lastAccountIndex -1];
    }

    @Override
    public void showAllAccounts() {
        System.out.println("No of Account: " + lastAccountIndex);
        for(int i=0; i<lastAccountIndex; i++) {
            System.out.println("Account " + (i + 1) + "-> ");
            account[i].showInfo();
        }
    }

    public int getNoOfAccounts() {
        return lastAccountIndex;
        //return lastAccountIndex + 1;
    }
}
