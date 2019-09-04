package com.github.dubbo.demo.provider.service;

import org.springframework.beans.factory.annotation.Value;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.dubbo.demo.facade.api.HelloService;

@Service(version = "${dubbo.service.version.v2}")
public class HelloServiceV2Impl implements HelloService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    @Value("${dubbo.service.version.v2}")
    private String serviceVersion;

    @Override
    public String hello(String name) {
        return String.format("[%s][%s] : Hello, %s", serviceName, serviceVersion, name);
    }

}