package com.akonst.banks;

import com.akonst.banks.accounts.BankAccount;
import com.akonst.banks.clients.BankClient;
import com.akonst.banks.service.BanksException;
import com.akonst.banks.transactions.BankTransaction;
import com.akonst.banks.transactions.BankTransactionBuilder;

import java.util.List;
import java.util.ArrayList;

public class CentralBank {
    private int currentDate;
    private List<Bank> banks = new ArrayList<>();
    private List<BankTransaction> transactions = new ArrayList<>();
    private List<BankClient> subscribers = new ArrayList<>();

    private int _currentTransactionId;

    public Bank addBank(Bank bank) throws BanksException {
        if (banks.contains(bank)) {
            throw new BanksException("This bank is exists!");
        }

        banks.add(bank);

        return banks.get(banks.indexOf(bank));
    }

    public Bank getBank(String bankName) throws BanksException {
        Bank bank = null;

        for (Bank b : banks) {
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

        for (Bank b : banks) {
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

        transactions.add(transaction);
    }

    // percent and commission charging causes in time increasing
    public void increaseTime(int days) {
        currentDate += days;
        notifyBanksAboutCurrentDate();
    }

    public void cancelTransaction(int transactionId) throws BanksException {
        BankTransaction transaction = null;

        Bank sendersBank = null;
        Bank receiversBank = null;

        for (BankTransaction bt : transactions) {
            if (bt.getId() == transactionId) {
                transaction = bt;
                break;
            }
        }

        for (Bank b : banks) {
            if (b.bankName.equals(transaction.getSendersBank())) {
                sendersBank = b;
                break;
            }
        }

        for (Bank b : banks) {
            if (b.bankName.equals(transaction.getReceiversBank())) {
                receiversBank = b;
                break;
            }
        }

        if (transaction == null || sendersBank == null || receiversBank == null) {
            throw new BanksException("This transaction doesn't exists!");
        }

        BankAccount sendersAccount = sendersBank.getAccount(transaction.getSenderId());
        BankAccount receiversAccount = receiversBank.getAccount(transaction.getReceiverId());

        sendersAccount.refill(transaction.getSum());
        receiversAccount.immediatelyWithdraw(transaction.getSum());

        transactions.remove(transaction);
    }

    private void notifyBanksAboutCurrentDate() {
        for (Bank b : banks) {
            b.setCurrentDate(currentDate);
        }
    }

    private void bankUpdatesSubscribe(BankClient client) {
        subscribers.add(client);
    }

    private void notifyClientsAboutAction(String info) {
        for (BankClient client : subscribers) {
            notify(client, info);
        }
    }

    private void notify(BankClient client, String info) {
        client.updateInfo(info);
    }
}
