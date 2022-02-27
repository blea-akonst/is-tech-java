package com.akonst.banks.clients;

public class BankClientBuilder {
    private BankClient _client = new BankClient();

    public BankClientBuilder setName(String name) {
        _client.name = name;
        return this;
    }

    public BankClientBuilder setSurname(String surname) {
        _client.surname = surname;
        return this;
    }

    public BankClientBuilder setAddress(String address) {
        _client.address = address;
        return this;
    }

    public BankClientBuilder setPassportNumber(String number) {
        _client.passportNumber = number;
        return this;
    }

    public BankClient getClient() {
        BankClient res = _client;
        reset();

        return res;
    }

    private void reset() {
        _client = new BankClient();
    }
}
