package com.akonst.banks.accounts;

import com.akonst.banks.clients.BankClient;
import com.akonst.banks.service.BanksException;

public abstract class BankAccount {
    protected double _balance;

    public int id;
    public int currentDate;
    public String bankName;
    public BankClient owner;

    public double refill(double sum) {
        _balance += sum;

        return _balance;
    }

    public double immediatelyWithdraw(double sum) {
        _balance -= sum;

        return _balance;
    }

    public double getBalance() {
        return _balance;
    }

    public abstract void chargePercentsAndCommissions(int monthsToCharge);

    public abstract double withdraw(double sum) throws BanksException;
}
