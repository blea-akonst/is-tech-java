package com.akonst.banks.accounts.factories;

import com.akonst.banks.Bank;
import com.akonst.banks.accounts.CreditAccount;
import com.akonst.banks.clients.BankClient;

public class CreditAccountFactory {
    public static CreditAccount MakeAccount(Bank bank, BankClient owner) {
        var account = new CreditAccount();

        account.bankName = bank.bankName;
        account.creditComission = bank.creditComission;
        account.creditLimit = bank.creditLimit;
        account.owner = owner;

        return account;
    }
}
