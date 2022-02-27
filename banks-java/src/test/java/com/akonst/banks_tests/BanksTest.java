package com.akonst.banks_tests;

import com.akonst.banks.*;
import com.akonst.banks.accounts.*;
import com.akonst.banks.accounts.factories.*;
import com.akonst.banks.clients.*;
import com.akonst.banks.service.BanksException;

import org.junit.jupiter.api.*;

public class BanksTest {
    private static CentralBank _centralBank = new CentralBank();

    private DebitAccount _accountBankOneDebit;
    private CreditAccount _accountBankTwo;

    @BeforeEach
    public void setUp() throws BanksException {
        reset();

        var bankBuilder = new BankBuilder();

        double depositPercentFirst = 5;
        double depositPercentSecond = 7;
        Bank _bankOne = bankBuilder.setBankName("Tinkoff Bank")
                .setCreditCommission(2000)
                .setDebitPercent(3)
                .setCreditLimit(200000)
                .setFirstDepositPercent(depositPercentFirst)
                .setSecondDepositPercent(depositPercentSecond)
                .setDepositPercentIncreasingBorderSum(50000)
                .setUntrustedClientTransactionLimit(15000)
                .getBank();
        _centralBank.addBank(_bankOne);

        Bank _bankTwo = bankBuilder.setBankName("Alfa-Bank")
                .setCreditCommission(3000)
                .setDebitPercent(4)
                .setCreditLimit(100000)
                .setFirstDepositPercent(depositPercentFirst)
                .setSecondDepositPercent(depositPercentSecond)
                .setDepositPercentIncreasingBorderSum(100000)
                .setUntrustedClientTransactionLimit(10000)
                .getBank();
        _centralBank.addBank(_bankTwo);

        var clientBuilder = new BankClientBuilder();

        BankClient client;

        client = clientBuilder.setName("Mykola")
                .setSurname("Scherbak")
                .setAddress("Ukraine, Kyiv, Khreschatik st., bld. 5")
                .setPassportNumber("UA-ZEKRUTO")
                .getClient();

        client = _bankOne.addClient(client);

        _accountBankOneDebit = DebitAccountFactory.makeAccount(_bankOne, client);
        _bankOne.addAccount(_accountBankOneDebit);

        client = clientBuilder.setName("Azamat")
                .setSurname("Kayratov")
                .setAddress("Republic of Kazakhstan, Almaty, Al-Farabi avenue, bld. 5")
                .setPassportNumber("KZ-NZRBVSSHL")
                .getClient();

        client = _bankTwo.addClient(client);

        _accountBankTwo = CreditAccountFactory.MakeAccount(_bankTwo, client);
    }

    @Test
    public void createClientAndRefillHimBalance_BalanceIsCorrect() {
        Assertions.assertEquals(0, _accountBankOneDebit.getBalance());
        double refillSum = _accountBankOneDebit.refill(456);
        Assertions.assertEquals(refillSum, _accountBankOneDebit.getBalance());
    }

    @Test
    public void increaseTimeForOneMonth_ExpectedBalanceWithPercents() {
        double refillSum = _accountBankOneDebit.refill(456);
        _centralBank.increaseTime(31);
        Assertions.assertTrue(_accountBankOneDebit.getBalance() > refillSum);
    }

    @Test
    public void moneyTransferFromFirstBankToSecond_SecondBankClientReceivedMoney() throws BanksException {
        _accountBankOneDebit.refill(1000);
        _centralBank.moneyTransfer(_accountBankOneDebit, _accountBankTwo, 1000);

        Assertions.assertEquals(0, _accountBankOneDebit.getBalance());
        Assertions.assertEquals(1000, _accountBankTwo.getBalance());
    }

    @Test
    public void clientInfoIsIncompleteAndHeTriesWithdrawMoney_ThrowsException() {
        _accountBankOneDebit.owner.passportNumber = "";
        _accountBankOneDebit.refill(1000);

        Assertions.assertThrows(BanksException.class, () -> _accountBankOneDebit.withdraw(1000));
    }

    @Test
    public void clientHasNotEnoughMoneyForWithdraw_ThrowsException() {
        _accountBankOneDebit.refill(999);
        
        Assertions.assertThrows(BanksException.class, () -> _accountBankOneDebit.withdraw(1000));
    }

    private void reset() {
        _centralBank = new CentralBank();
    }
}