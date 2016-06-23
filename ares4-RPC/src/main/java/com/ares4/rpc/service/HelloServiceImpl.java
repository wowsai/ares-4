package com.ares4.rpc.service;

import com.ares4.rpc.annotations.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "Hello " + name + "!";
    }
}
