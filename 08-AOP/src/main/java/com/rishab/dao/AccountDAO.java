package com.rishab.dao;

import com.rishab.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount(Account account, boolean vipFlag);

    boolean didAccountAdd();

    String getName();

    void setName(String name);

    String getServiceCode();

    void setServiceCode(String serviceCode);

    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);
}
