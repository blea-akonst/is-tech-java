package com.akonst.banks.accounts;

import com.akonst.banks.service.BanksException;

public class DebitAccount extends BankAccount {
    public double debitPercent;

    @Override
    public void chargePercentsAndCommissions(int monthsToCharge) {
        double monthlyPart = (debitPercent / 12) / 100;
        double chargingPercent = monthsToCharge * monthlyPart;

        double chargingSum = _balance * chargingPercent;
        refill(chargingSum);
    }

    @Override
    public double withdraw(double sum) throws BanksException {
        if (_balance - sum < 0) {
            throw new BanksException("You can't take more money than there is in the account!");
        }

        if (owner.isIncorrectClient()) {
            throw new BanksException("Please, fill your address and passport data!");
        }

        _balance -= sum;

        return _balance;
    }
}
