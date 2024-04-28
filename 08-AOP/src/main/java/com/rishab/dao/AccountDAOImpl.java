package com.rishab.dao;

import com.rishab.Account;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private String name;
    private String serviceCode;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        System.out.println(getClass() + ": in setName()");
    }

    @Override
    public String getServiceCode() {
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
        System.out.println(getClass() + ": in setServiceCode()");
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("An exception occurred.");
        }

        List<Account> accounts = new ArrayList<>();

        accounts.add(new Account("Ed Sheeran", "Silver"));
        accounts.add(new Account("Zayn", "Gold"));
        accounts.add(new Account("The Weeknd", "Platinum"));

        return accounts;
    }

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + ": just added an account.");
    }

    @Override
    public boolean didAccountAdd() {
        System.out.println(getClass() + ": did account add?");
        return true;
    }
}
