package com.github.dubbo.demo.service;

import org.apache.dubbo.config.annotation.Service;

import com.github.dubbo.demo.api.HelloService;

@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

}
