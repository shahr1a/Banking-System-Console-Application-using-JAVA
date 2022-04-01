package com.project.interfaces;

import com.project.classes.Employee;

public interface EmployeeOperations {
    public abstract void setEmployees(Employee e);
    public abstract void getEmployee(String empId);
    public abstract void insertEmployee(Employee e);
    public abstract void removeEmployee(Employee e);
    public abstract void showAllEmployees();
}
