package com.project.interfaces;

import com.project.classes.Customer;

public interface CustomerOperations {
    public abstract void setCustomer(Customer c);
    public abstract void getCustomer(int nid);
    public abstract void insertCustomer(Customer c);

    public abstract void removeCustomer(Customer c);
    public abstract void showAllCustomers();
}
