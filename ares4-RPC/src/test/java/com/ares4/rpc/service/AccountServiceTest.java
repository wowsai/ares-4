package com.ares4.rpc.service;

import com.ares4.rpc.client.RpcProxy;
import com.ares4.rpc.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-zk-rpc-client.xml"})
public class AccountServiceTest {
    @Autowired
    private RpcProxy rpcProxy;

    @Test
    public void testGet() {
        AccountService accountService = rpcProxy.create(AccountService.class);
        Account result = accountService.get();
        System.out.println(result);
    }
}
