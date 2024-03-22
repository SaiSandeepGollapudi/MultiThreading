package org.example;

class ATM {

    // Synchronized method to check balance
    synchronized public void checkBalance(String customerName) {
        System.out.print(customerName + " Checking ");

        try {
            // Simulate some delay for checking balance
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Balance");
    }

    // Synchronized method to withdraw amount
    synchronized public void withdraw(String customerName, int withdrawalAmount) {
        System.out.print(customerName + " withdrawing ");

        try {
            // Simulate some delay for withdrawal
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(withdrawalAmount);
    }
}

class Customer extends Thread {
    private String customerName;
    private int withdrawalAmount;
    private ATM atm;

    Customer(String name, ATM atm, int amount) {
        customerName = name;
        this.atm = atm;
        withdrawalAmount = amount;
    }

    // Method to perform ATM transactions
    public void useATM() {
        atm.checkBalance(customerName);
        atm.withdraw(customerName, withdrawalAmount);
    }

    @Override
    public void run() {
        useATM();
    }
}

public class SCThread1 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        // Create customer objects
        Customer customer1 = new Customer("Smith", atm, 100);
        Customer customer2 = new Customer("John", atm, 200);
        // Start customer threads
        customer1.start();
        customer2.start();
    }
}
