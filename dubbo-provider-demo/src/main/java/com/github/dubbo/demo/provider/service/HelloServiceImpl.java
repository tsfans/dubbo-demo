package com.github.dubbo.demo.provider.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.dubbo.demo.facade.api.HelloService;

@Profile("!consumer")
@Service(version = "${dubbo.service.version.v1}")
public class HelloServiceImpl implements HelloService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Value("${dubbo.service.version.v1}")
    private String serviceVersion;

    @Override
    public String hello(String name) {
        return String.format("[%s][%s] : Hello, %s", serviceName, serviceVersion, name);
    }

}
