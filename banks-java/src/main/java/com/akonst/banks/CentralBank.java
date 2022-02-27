package com.akonst.banks;

import com.akonst.banks.accounts.BankAccount;
import com.akonst.banks.clients.BankClient;
import com.akonst.banks.service.BanksException;
import com.akonst.banks.transactions.BankTransaction;
import com.akonst.banks.transactions.BankTransactionBuilder;

import java.util.ArrayList;

public class CentralBank {
    private int _currentDate;
    private ArrayList<Bank> _banks;
    private ArrayList<BankTransaction> _transactions;
    private ArrayList<BankClient> _subscribers;

    {
        _banks = new ArrayList<>();
        _transactions = new ArrayList<>();
        _subscribers = new ArrayList<>();
    }

    private int _currentTransactionId = 0;

    public Bank addBank(Bank bank) throws BanksException {
        if (_banks.contains(bank)) {
            throw new BanksException("This bank is exists!");
        }

        _banks.add(bank);

        return _banks.get(_banks.indexOf(bank));
    }

    public Bank getBank(String bankName) throws BanksException {
        Bank bank = null;

        for (Bank b : _banks) {
            if (b.bankName.equals(bankName)) {
                bank = b;
                break;
            }
        }

        if (bank == null) {
            throw new BanksException("This bank doesn't exists!");
        }

        return bank;
    }

    public void moneyTransfer(BankAccount sendersAccount, BankAccount receiversAccount, double sum) throws BanksException {
        Bank sendersBank = null;

        for (Bank b : _banks) {
            if (b.bankName.equals(sendersAccount.bankName)) {
                sendersBank = b;
                break;
            }
        }

        if (sendersBank == null) {
            throw new BanksException("This bank doesn't exists!");
        }

        if (sendersAccount.owner.isIncorrectClient() && sum > sendersBank.untrustedClientTransactionLimit) {
            throw new BanksException("Please, enter your email and passport data for transaction for this sum!");
        }

        sendersAccount.withdraw(sum);
        receiversAccount.refill(sum);

        var transactionBuilder = new BankTransactionBuilder();

        BankTransaction transaction = transactionBuilder.setSum(sum)
                .setSendersBank(sendersAccount.bankName)
                .setSenderId(sendersAccount.id)
                .setReceiversBank(receiversAccount.bankName)
                .setReceiverId(receiversAccount.id)
                .setTransactionId(_currentTransactionId++)
                .getTransaction();

        _transactions.add(transaction);
    }

    // percent and commission charging causes in time increasing
    public void increaseTime(int days) {
        _currentDate += days;
        notifyBanksAboutCurrentDate();
    }

    public void cancelTransaction(int transactionId) throws BanksException {
        BankTransaction transaction = null;

        Bank sendersBank = null;
        Bank receiversBank = null;

        for (BankTransaction bt : _transactions) {
            if (bt.id == transactionId) {
                transaction = bt;
                break;
            }
        }

        for (Bank b : _banks) {
            if (b.bankName.equals(transaction.sendersBank)) {
                sendersBank = b;
                break;
            }
        }

        for (Bank b : _banks) {
            if (b.bankName.equals(transaction.receiversBank)) {
                receiversBank = b;
                break;
            }
        }

        if (transaction == null || sendersBank == null || receiversBank == null) {
            throw new BanksException("This transaction doesn't exists!");
        }

        BankAccount sendersAccount = sendersBank.getAccount(transaction.senderId);
        BankAccount receiversAccount = receiversBank.getAccount(transaction.receiverId);

        sendersAccount.refill(transaction.sum);
        receiversAccount.immediatelyWithdraw(transaction.sum);

        _transactions.remove(transaction);
    }

    private void notifyBanksAboutCurrentDate() {
        for (Bank b : _banks) {
            b.setCurrentDate(_currentDate);
        }
    }

    private void bankUpdatesSubscribe(BankClient client) {
        _subscribers.add(client);
    }

    private void notifyClientsAboutAction(String info) {
        for (BankClient client : _subscribers) {
            notify(client, info);
        }
    }

    private void notify(BankClient client, String info) {
        client.updateInfo(info);
    }
}
