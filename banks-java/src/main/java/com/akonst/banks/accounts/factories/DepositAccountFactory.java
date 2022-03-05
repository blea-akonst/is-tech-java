package com.akonst.banks.accounts.factories;

import com.akonst.banks.Bank;
import com.akonst.banks.accounts.DepositAccount;
import com.akonst.banks.clients.BankClient;

public class DepositAccountFactory {
    public static DepositAccount makeAccount(Bank bank, BankClient owner, int depositExpiryDate) {
        var account = new DepositAccount();

        account.bankName = bank.bankName;
        account.firstDepositPercent = bank.firstDepositPercent;
        account.secondDepositPercent = bank.secondDepositPercent;
        account.depositPercentIncreasingBorderSum = bank.depositPercentIncreasingBorderSum;
        account.depositExpiryDate = depositExpiryDate;
        account.owner = owner;

        return account;
    }
}
