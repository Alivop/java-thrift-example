package com.example.thrift.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;

@Slf4j
public class TestServiceImpl implements TestService.Iface {
    @Override
    public String sayHello(String message) throws TException {
        log.info("<<<收到消息：{}", message);
        return "Hi, I'm Server!";
    }
}
