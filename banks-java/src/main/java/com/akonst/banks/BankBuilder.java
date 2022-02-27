package com.akonst.banks;

public class BankBuilder {
    private Bank _bank;

    {
        _bank = new Bank();
    }

    public BankBuilder setBankName(String name) {
        _bank.bankName = name;
        return this;
    }

    public BankBuilder setDebitPercent(double percent) {
        _bank.debitPercent = percent;
        return this;
    }

    public BankBuilder setFirstDepositPercent(double percent) {
        _bank.firstDepositPercent = percent;
        return this;
    }

    public BankBuilder setSecondDepositPercent(double percent) {
        _bank.secondDepositPercent = percent;
        return this;
    }

    public BankBuilder setDepositPercentIncreasingBorderSum(double sum) {
        _bank.depositPercentIncreasingBorderSum = sum;
        return this;
    }

    public BankBuilder setCreditCommission(double sum) {
        _bank.creditComission = sum;
        return this;
    }

    public BankBuilder setCreditLimit(double sum) {
        _bank.creditLimit = sum;
        return this;
    }

    public BankBuilder setUntrustedClientTransactionLimit(double sum) {
        _bank.untrustedClientTransactionLimit = sum;
        return this;
    }

    public BankBuilder setStandardDepositTerm(int term) {
        _bank.standardDepositTerm = term;
        return this;
    }

    public Bank getBank() {
        Bank res = _bank;
        reset();

        return res;
    }

    private void reset() {
        _bank = new Bank();
    }
}
