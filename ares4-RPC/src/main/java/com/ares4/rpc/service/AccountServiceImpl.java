package com.ares4.rpc.service;

import com.ares4.rpc.annotations.RpcService;
import com.ares4.rpc.model.Account;

@RpcService(AccountService.class)
public class AccountServiceImpl implements AccountService {
    @Override
    public Account get() {
        return new Account("12321321", "周永丁");
    }
}
