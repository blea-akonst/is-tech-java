package com.akonst.banks.clients;

public class BankClient {
    private String _currentBankInfo;

    public String name;
    public String surname;
    public String address;
    public String passportNumber;

    public void updateInfo(String info) {
        _currentBankInfo = info;
    }

    public boolean isIncorrectClient() {
        return address.isEmpty() || passportNumber.isEmpty();
    }
}
