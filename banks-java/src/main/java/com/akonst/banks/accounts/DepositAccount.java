package com.akonst.banks.accounts;

import com.akonst.banks.service.BanksException;

public class DepositAccount extends BankAccount {
    public int depositExpiryDate;
    public double firstDepositPercent;
    public double secondDepositPercent;
    public double depositPercentIncreasingBorderSum;

    @Override
    public void chargePercentsAndCommissions(int monthsToCharge) {
        double percent;

        if (depositExpiryDate < currentDate) {
            return;
        }

        percent = _balance < depositPercentIncreasingBorderSum ? firstDepositPercent : secondDepositPercent;

        double monthlyPart = (percent / 12) / 100;
        double chargingPercent = monthsToCharge * monthlyPart;

        double chargingSum = _balance * chargingPercent;
        refill(chargingSum);
    }

    @Override
    public double withdraw(double sum) throws BanksException {
        if (currentDate < depositExpiryDate) {
            throw new BanksException("The term of the deposit has not ended!");
        }

        if (_balance - sum < 0) {
            throw new BanksException("Not enough money in the account!");
        }

        if (!owner.isIncorrectClient()) {
            throw new BanksException("Please, fill your address and passport data!");
        }

        _balance -= sum;

        return _balance;
    }
}
