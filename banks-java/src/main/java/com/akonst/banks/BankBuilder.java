package com.akonst.banks;

public class BankBuilder {
    private Bank bank;

    {
        bank = new Bank();
    }

    public BankBuilder setBankName(String name) {
        bank.bankName = name;
        return this;
    }

    public BankBuilder setDebitPercent(double percent) {
        bank.debitPercent = percent;
        return this;
    }

    public BankBuilder setFirstDepositPercent(double percent) {
        bank.firstDepositPercent = percent;
        return this;
    }

    public BankBuilder setSecondDepositPercent(double percent) {
        bank.secondDepositPercent = percent;
        return this;
    }

    public BankBuilder setDepositPercentIncreasingBorderSum(double sum) {
        bank.depositPercentIncreasingBorderSum = sum;
        return this;
    }

    public BankBuilder setCreditCommission(double sum) {
        bank.creditComission = sum;
        return this;
    }

    public BankBuilder setCreditLimit(double sum) {
        bank.creditLimit = sum;
        return this;
    }

    public BankBuilder setUntrustedClientTransactionLimit(double sum) {
        bank.untrustedClientTransactionLimit = sum;
        return this;
    }

    public BankBuilder setStandardDepositTerm(int term) {
        bank.standardDepositTerm = term;
        return this;
    }

    public Bank getBank() {
        Bank res = bank;
        reset();

        return res;
    }

    private void reset() {
        bank = new Bank();
    }
}
