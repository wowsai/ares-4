package com.ares4.rpc.model;

import com.google.common.base.MoreObjects;

public class Account {
    private String accountNo;
    private String accountName;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account(String accountNo, String accountName) {
        this.accountNo = accountNo;
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(Account.class)
                .omitNullValues()
                .add("accountNo", accountNo)
                .add("accountName", accountName)
                .toString();
    }
}
