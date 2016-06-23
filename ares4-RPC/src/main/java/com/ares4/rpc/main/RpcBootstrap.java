package com.ares4.rpc.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RpcBootstrap {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring-zk-rpc-server.xml");
    }
}
