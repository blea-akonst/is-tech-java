package com.akonst.banks.transactions;

public class BankTransactionBuilder {
    private BankTransaction _transaction = new BankTransaction();

    public BankTransactionBuilder setSum(double sum) {
        _transaction.sum = sum;
        return this;
    }

    public BankTransactionBuilder setTransactionId(int id) {
        _transaction.id = id;
        return this;
    }

    public BankTransactionBuilder setSenderId(int id) {
        _transaction.senderId = id;
        return this;
    }

    public BankTransactionBuilder setReceiverId(int id) {
        _transaction.receiverId = id;
        return this;
    }

    public BankTransactionBuilder setSendersBank(String bank) {
        _transaction.sendersBank = bank;
        return this;
    }

    public BankTransactionBuilder setReceiversBank(String bank) {
        _transaction.receiversBank = bank;
        return this;
    }

    public BankTransaction getTransaction() {
        BankTransaction res = _transaction;
        reset();

        return res;
    }

    private void reset() {
        _transaction = new BankTransaction();
    }
}
