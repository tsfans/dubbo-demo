package com.github.dubbo.demo.service;

import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import com.github.dubbo.demo.facade.api.HelloService;

@Service(version = "1.0.0")
public class HelloServiceImpl implements HelloService {
    
    @Value("${dubbo.application.name}")
    private String serviceName;

    @Override
    public String hello(String name) {
        return String.format("[%s] : Hello, %s", serviceName, name);
    }

}
