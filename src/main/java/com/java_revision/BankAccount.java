package com.java_revision;

abstract class Account {
    private double balance = 0;

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Total Balance " + balance);
    }

    public abstract void withdraw(double amount);

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    protected boolean hasSufficientBalance(double amount){
        if (balance > amount){
            setBalance(balance - amount);
            System.out.println("Balance After Withdrawal " + balance);
            return true;
        }
        
        System.out.println("Unable to Withdraw Low Balance " + balance);
        return false;
    }
}

class SavingsAccount extends Account {

    public SavingsAccount(double balance) {
        super(balance);
    }

    int withdrawalLimit = 3;

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawal Amount " + amount);
        if (withdrawalLimit > 0){
            if (hasSufficientBalance(amount))
                withdrawalLimit--;
        } else{
            System.out.println("Reached Withdrawal Limit of 3 times per day");
        }
        
    }
}

class CurrentAccount extends Account {

    public CurrentAccount(double balance) {
        super(balance);
    }

    int withdrawalLimit = 100000;
    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawal Amount " + amount);
        if (amount < withdrawalLimit){
            if (hasSufficientBalance(amount))
                withdrawalLimit -= amount;
        } else{
            System.out.println("Unable to Withdraw because withdrawalLimit for day is over" );
        }
        
    }
}

public class BankAccount {
    public static void main(String[] args) {
        System.out.println("================== Savings Acccount =========================");
        Account account  = new SavingsAccount(20000);
        account.deposit(40000);
        account.withdraw(30000);
        account.withdraw(10000);
        account.withdraw(10000);
        account.withdraw(10000);
        System.out.println("================== Current Acccount =========================");

        account = new CurrentAccount(1000000);
        account.deposit(40000);
        account.withdraw(30000);
        account.withdraw(10000);
        account.withdraw(50000);
        account.withdraw(60000);
    }
}
