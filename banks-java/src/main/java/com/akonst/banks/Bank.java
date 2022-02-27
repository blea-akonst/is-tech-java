package com.akonst.banks;

import com.akonst.banks.accounts.BankAccount;
import com.akonst.banks.clients.BankClient;
import com.akonst.banks.service.BanksException;

import java.util.ArrayList;
import java.util.Objects;

public class Bank {
    private int _currentDate;

    private ArrayList<BankClient> _clients;
    private ArrayList<BankAccount> _accounts;

    {
        _clients = new ArrayList<>();
        _accounts = new ArrayList<>();
    }

    public int standardDepositTerm;
    public double debitPercent;
    public double firstDepositPercent;
    public double secondDepositPercent;
    public double depositPercentIncreasingBorderSum;
    public double creditComission;
    public double creditLimit;
    public double untrustedClientTransactionLimit;

    public String bankName;

    public int getCurrentDate() {
        return _currentDate;
    }

    public void setCurrentDate(int date) {
        _currentDate = date;

        for (BankAccount acc : _accounts) {
            int prevDate = acc.currentDate;
            int monthsToCharge = ((prevDate % 30) + _currentDate - prevDate) / 30;
            acc.chargePercentsAndCommissions(monthsToCharge);
            acc.currentDate = _currentDate;
        }
    }

    public BankClient addClient(BankClient client) {
        _clients.add(client);

        return _clients.get(_clients.indexOf(client));
    }

    public void addAccount(BankAccount account) {
        int id = _accounts.size();
        account.id = id;
        _accounts.add(account);
    }

    public double refill(BankAccount account, double sum) {
        return account.refill(sum);
    }

    public double withdraw(BankAccount account, double sum) throws BanksException {
        return account.withdraw(sum);
    }

    public BankAccount getAccount(int id) throws BanksException {
        BankAccount account = null;

        for (BankAccount acc : _accounts) {
            if (acc.id == id) {
                account = acc;
                break;
            }
        }

        if (account == null) {
            throw new BanksException("This account doesn't exists!");
        }

        return account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return bankName.equals(bank.bankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankName);
    }
}
