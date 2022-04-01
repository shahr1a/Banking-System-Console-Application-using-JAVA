package com.project.classes;

import com.project.interfaces.CustomerOperations;
import com.project.interfaces.EmployeeOperations;

import java.util.InputMismatchException;
import java.util.Scanner;
import static com.project.classes.Start.transactionBlog;


public class Bank implements CustomerOperations, EmployeeOperations {
    private Customer[] customers = new Customer[500000];
    private Employee[] employees = new Employee[500000];
    //static FileReadWrite transactions = new FileReadWrite();

    private static Scanner scanner = new Scanner(System.in);

    private static int currentEmployeeCreated = 0;
    private static int noOfActiveCustomer = 0;

    private static int lastEmployeeCreated = 10000000;
    private static int lastCustomerAccountNumber = 50000000;

    private static int lastPosition = 0;


    public void setEmployees(Employee e) {

    }

    public void insertEmployee(Employee e) {
        employees[lastEmployeeCreated % 1000000] = e;
        int mod = lastEmployeeCreated % 1000000;
        employees[mod].setEmpId(Integer.toString(lastEmployeeCreated));
        currentEmployeeCreated++;
        lastEmployeeCreated++;
    }

    public void getEmployee(String empId) {
        boolean flag = false;
        for (int i = 0; i < currentEmployeeCreated; i++) {
            if (employees[i] != null) {
                if (empId.equals(employees[i].getEmpId())) {
                    flag = true;
                    System.out.println("\n  Employee found....  \r");
                    System.out.println("\r  Removing Employee...  \n");
                    removeEmployee(getEmployeeObject(empId));
                }
            }
        }
        if(!flag) {
            System.out.println("Employee not found. Try Again....");
        }
    }

    public Employee getEmployeeObject(String empId) {
        int EmpID = Integer.parseInt(empId);

        EmpID = EmpID % 1000000;
        return employees[EmpID];
    }

    public void removeEmployee(Employee e) {
        String empId = e.getEmpId();

        int EmpID = Integer.parseInt(empId);
        EmpID = EmpID % 1000000;

        employees[EmpID] = null;
        currentEmployeeCreated--;
    }

    public void showAllEmployees() {
        int counter1 = 0;
        if(currentEmployeeCreated == 0) {
            System.out.println("\nThis Bank has 0 employees right now!");
            System.out.println("\n  Returning to Employee Operations......  \n");
            System.out.println("   -----------------------  \n");
            return;
        }
        for (int i = 0; counter1 < currentEmployeeCreated; i++) {
            if (employees[i] != null) {
                System.out.println("\n  ...Employee " + (counter1 + 1) + "...  \n");
                System.out.println("Name: " + employees[i].getName());
                System.out.println("ID: " + employees[i].getEmpId());
                System.out.println("Salary: " + employees[i].getSalary());
                counter1++;
            }
        }
        System.out.println("\n  Returning to Employee Operations......  \n");
        System.out.println("   -----------------------  \n");
    }

    @Override
    public void setCustomer(Customer c) {

    }

    @Override
    public void getCustomer(int nid) {
        /*int counter = 0;
        for (int i = 0; counter < noOfActiveCustomer; i++) {
            if (customers[i] != null) {
                if (customers[i].getNid() == nid) {
                    System.out.println("Customer Name -> " + customers[i].getName());
                    System.out.println("Customer NID ->: " + customers[i].getNid());
                    System.out.println("Account Info: ");
                    customers[i].showAllAccounts();
                    break;
                }
                counter++;
            }
        }*/

        boolean flag = false;
        int i;
        for (i = 0; i < noOfActiveCustomer; i++) {
            if (customers[i] != null) {
                if (customers[i].getNid() == nid) {
                    flag = true;
                    break;
                    /*System.out.println("Removing Employee...");
                    removeEmployee(getEmployeeObject(empId)); */
                }
            }
        }
        if(flag) {
            removeCustomer(customers[i]);
        }
        else {
            System.out.println("Customer not found. Try Again....");
        }
    }

    public void insertCustomer(Customer c) {
        boolean alreadyExist = false;
        int i;
        for(i=0; i<lastPosition; i++) {
            if (customers[i] != null) {
                if (c.getNid() == customers[i].getNid()) {
                    alreadyExist = true;
                    break;
                }
            }
        }
        if(!alreadyExist) {
            customers[lastPosition] = c;
            Account account = new Account();
            account.setAccountNumber(lastCustomerAccountNumber);
            //customers[lastPosition].getNewAccount().setAccountNumber(lastCustomerAccountNumber);
            customers[lastPosition].insertAccount(account);
            System.out.print("Enter initial balance input:");
            try {
                customers[lastPosition].getAccount().setBalance(scanner.nextDouble());
                transactionBlog.writeInFile("Date & Time: " + java.util.Calendar.getInstance().getTime() +"\n Account Number: " + customers[lastPosition].getAccount().getAccountNumber() + "\n Debit: " + customers[lastPosition].getAccount().getBalance());
                noOfActiveCustomer++;
                lastPosition++;
                System.out.println("\n  New Account added into Existing Customer  \n");
                lastCustomerAccountNumber++;
            }
            catch(NullPointerException npe) {
                System.out.println("Null point exception caught.. ");
            }
        }

        else {
            Account account = new Account();
            account.setAccountNumber(lastCustomerAccountNumber);
            customers[i].insertAccount(account);
            System.out.print("Enter initial balance input:");
            try {
                customers[i].getAccount().setBalance(scanner.nextDouble());
                transactionBlog.writeInFile("Date & Time: " + java.util.Calendar.getInstance().getTime() +"\n Account Number: " + customers[i].getAccount().getAccountNumber() + "\n Debit: " + customers[i].getAccount().getBalance());
                lastCustomerAccountNumber++;
            }
            catch(NullPointerException npe) {
                System.out.println("Null point exception caught.");
            }
            catch(InputMismatchException ime) {
                System.out.println("\n  Input type error!...  \n");
            }
        }
    }

    public void removeCustomer(Customer c) {
        if(c == null) {
            System.out.println("Customer Doesn't Exit....");
            return;
        }

        boolean customerExistence = false;
        int i;
        for(i = 0; i<lastPosition; i++) {
            if(customers[i] != null) {
                if (c.getNid() == customers[i].getNid()) {
                    customerExistence = true;
                    break;
                }
            }
        }

        if(customerExistence) {
            System.out.println("You have total " + customers[i].getNoOfAccounts() + " Accounts...");
            System.out.println("Press 1-> To delete all accounts");
            System.out.println("Press 2-> To delete specific account");
            System.out.println("Press 3-> Cancel Operations");
            boolean cancellation = false;

            try {
                int choice = scanner.nextInt();

                while (!cancellation) {
                    switch (choice) {
                        case 1:
                            System.out.println("Removing All Accounts....");
                            customers[i] = null;
                            noOfActiveCustomer--;
                            System.out.println("   All the accounts along with customer information has been removed   ");
                            System.out.println("     -----------------     ");
                            break;
                        case 2:
                            System.out.print("Enter the account number you want to delete: ");
                            System.out.println("Removing Account....");
                            customers[i].removeAccount(customers[i].getAccount(scanner.nextInt()));
                            System.out.println("    Account Deleted Successfully from the Database    ");
                            System.out.println("     -----------------     ");
                            break;
                        case 3:
                            cancellation = true;
                            break;
                    }
                }
            }
            catch (InputMismatchException ime) {
                System.out.println("Invalid Input..");
            }
        }
    }

    public Customer getCustomerObject(int nid) {
        int counter = 0;
        for (int i = 0; counter < noOfActiveCustomer; i++) {
            if (customers[i] != null) {
                if (customers[i].getNid() == nid) {
                    return customers[i];
                }
                counter++;
            }
        }
        return null;
    }

    public void showAllCustomers() {

        if(noOfActiveCustomer == 0) {
            System.out.println("\nThis Bank has 0 Customers right now!");
            System.out.println("\n  Returning to Customer Operations......  \n");
            System.out.println("   -----------------------  \n");
            return;
        }

        System.out.println("The System has total " + noOfActiveCustomer + " customers!!!!!\n");
        int count1 = 0;
        for(int i=0; count1<noOfActiveCustomer; i++) {
            if(customers[i] != null) {
                System.out.println("Customer Count " + (count1+1));
                count1++;

                System.out.println("Customer Name -> " + customers[i].getName());
                System.out.println("Customer NID ->: " + customers[i].getNid());
                System.out.println("Account Info: ");
                customers[i].showAllAccounts();

                System.out.println("    -----------------     \n\n");
            }
        }
        System.out.println("  Returning to Customer Operations......  \n");
        System.out.println("         -----------------------     \n");
    }
}

