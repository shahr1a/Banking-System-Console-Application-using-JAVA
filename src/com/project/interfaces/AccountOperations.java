package com.project.interfaces;

import com.project.classes.Account;

public interface AccountOperations {
    public abstract void insertAccount(Account a);
    public abstract void removeAccount(Account a);
    public abstract Account getAccount(int accountNumber);
    public abstract void showAllAccounts();
}
